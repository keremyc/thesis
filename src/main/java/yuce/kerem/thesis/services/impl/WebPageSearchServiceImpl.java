package yuce.kerem.thesis.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Service;
import yuce.kerem.thesis.model.WebPageDocument;
import yuce.kerem.thesis.services.WebPageSearchService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Kerem(Nurullah)
 * @version 1.0
 * @date 6/9/21 8:51 PM
 */

@Service
@Slf4j
public class WebPageSearchServiceImpl implements WebPageSearchService {

    public static final String WEB_PAGE_INDEX = "web-page";

    private final ElasticsearchOperations elasticsearchOperations;

    public WebPageSearchServiceImpl(ElasticsearchOperations elasticsearchOperations) {
        this.elasticsearchOperations = elasticsearchOperations;
    }

    @Override
    public List<WebPageDocument> processSearch(String query) {
        log.info("Search with query {}", query);

        QueryBuilder queryBuilder = QueryBuilders
                .multiMatchQuery(query, "description", "title")
                .fuzziness(Fuzziness.AUTO);

        Query searchQuery = new NativeSearchQueryBuilder()
                .withFilter(queryBuilder)
                .build();

        SearchHits<WebPageDocument> webPageSearchHits = elasticsearchOperations
                .search(searchQuery, WebPageDocument.class, IndexCoordinates.of(WEB_PAGE_INDEX));

        return webPageSearchHits.stream()
                .map(hit -> hit.getContent())
                .collect(Collectors.toList());
    }
}
