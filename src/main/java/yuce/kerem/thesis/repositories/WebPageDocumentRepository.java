package yuce.kerem.thesis.repositories;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import yuce.kerem.thesis.model.WebPageDocument;

/**
 * @author Kerem(Nurullah)
 * @version 1.0
 * @date 6/9/21 9:27 PM
 */
public interface WebPageDocumentRepository
        extends ElasticsearchRepository<WebPageDocument, Long> {

}
