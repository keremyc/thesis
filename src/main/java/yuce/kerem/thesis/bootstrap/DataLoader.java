package yuce.kerem.thesis.bootstrap;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.stereotype.Component;
import yuce.kerem.thesis.dto.mappers.WebPageMapper;
import yuce.kerem.thesis.model.WebPage;
import yuce.kerem.thesis.model.WebPageDocument;
import yuce.kerem.thesis.repositories.WebPageDocumentRepository;
import yuce.kerem.thesis.repositories.WebPageRepository;
import yuce.kerem.thesis.services.WebPageSearchService;

import javax.annotation.PreDestroy;
import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Kerem(Nurullah)
 * @version 1.0
 * @date 6/9/21 9:15 PM
 */

@Component
@Slf4j
public class DataLoader implements CommandLineRunner {

    private final WebPageRepository webPageRepository;

    private final WebPageSearchService webPageSearchService;

    private final ElasticsearchOperations elasticsearchOperations;

    private final WebPageDocumentRepository documentRepository;

    public DataLoader(WebPageRepository webPageRepository,
                      WebPageSearchService webPageSearchService,
                      ElasticsearchOperations esOps,
                      WebPageDocumentRepository documentRepository) {
        this.webPageRepository = webPageRepository;
        this.webPageSearchService = webPageSearchService;
        this.elasticsearchOperations = esOps;
        this.documentRepository = documentRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Collection<WebPageDocument> dataCollection = prepareDataSet();

        buildIndex(dataCollection);

        saveDataToDB(
                dataCollection.stream()
                    .map(doc -> WebPageMapper.docToEntity(doc))
                    .collect(Collectors.toList())
        );
    }

    @PreDestroy
    private void deleteIndex() {
        elasticsearchOperations.indexOps(WebPageDocument.class).delete();
    }

    private void buildIndex(Collection<WebPageDocument> data) {
        elasticsearchOperations.indexOps(WebPageDocument.class).refresh();
        documentRepository.deleteAll();
        documentRepository.saveAll(data);
    }

    private void saveDataToDB(Collection<WebPage> data) {
        webPageRepository.saveAll(data);
    }

    private Collection<WebPageDocument> prepareDataSet() {
        Resource resource = new ClassPathResource("web-pages.csv");
        List<WebPageDocument> webPageDocumentList = new ArrayList<>();

        try {
            InputStream input = resource.getInputStream();
            Scanner scanner = new Scanner(input);
            int lineNo = 0;
            while (scanner.hasNextLine()) {
                ++lineNo;
                String line = scanner.nextLine();
                if (lineNo == 1)
                    continue;

                Optional<WebPageDocument> webPageDocumentOptional = csvRowToDocumentMapper(line);
                if (webPageDocumentOptional.isPresent())
                    webPageDocumentList.add(webPageDocumentOptional.get());
            }
        } catch (Exception exp) {
            log.error("file read error {}", exp);
        }

        return webPageDocumentList;
    }

    private Optional<WebPageDocument> csvRowToDocumentMapper(final String line) {
        try (Scanner scanner = new Scanner(line)) {
            scanner.useDelimiter(",");
            if (scanner.hasNext()) {
                Long id = Long.parseLong(scanner.next());
                String url = scanner.next();
                String title = scanner.next();
                String description = scanner.next();
                return Optional.of(
                        WebPageDocument.builder()
                        .id(id)
                        .url(url)
                        .title(title)
                        .description(description)
                        .build()
                );
            }
        }

        return Optional.of(null);
    }



}
