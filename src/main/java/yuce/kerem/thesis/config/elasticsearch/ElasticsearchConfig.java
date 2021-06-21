package yuce.kerem.thesis.config.elasticsearch;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

/**
 * @author Kerem(Nurullah)
 * @version 1.0
 * @date 6/9/21 9:49 PM
 */
@Configuration
@EnableElasticsearchRepositories(basePackages = "yuce.kerem.thesis.repositories")
public class ElasticsearchConfig extends AbstractElasticsearchConfiguration {

    @Override
    @Bean
    public RestHighLevelClient elasticsearchClient() {
        final ClientConfiguration clientConfiguration = ClientConfiguration
                .builder()
                .connectedTo("167.71.62.179:9200")
                .build();

        return RestClients.create(clientConfiguration).rest();
    }
}
