package yuce.kerem.thesis.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.Set;

/**
 * @author Kerem(Nurullah)
 * @version 1.0
 * @date 5/28/21 11:00 PM
 */

@Data
@Builder
public class RecommendationDto {

    private Long id;
    private String recommendationText;
    private boolean liked;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date createdAt;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UserDto recommendedBy;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private WebPageDto recommendedWebPage;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Set<CommentDto> comments;

}
