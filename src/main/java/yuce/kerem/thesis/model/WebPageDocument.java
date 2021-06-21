package yuce.kerem.thesis.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.Id;

/**
 * @author Kerem(Nurullah)
 * @version 1.0
 * @date 6/9/21 8:45 PM
 */

@Document(indexName = "web-page")
@Getter
@Setter
@Builder
public class WebPageDocument {

    @Id
    private Long id;

    @Field(type = FieldType.Text)
    private String url;

    @Field(type = FieldType.Text)
    private String description;

    @Field(type = FieldType.Text)
    private String title;
}
