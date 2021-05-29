package yuce.kerem.thesis.dto;

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
    private Date createdAt;
    private UserDto recommendedBy;
    private WebPageDto recommendedWebPage;
    private Set<CommentDto> comments;

}
