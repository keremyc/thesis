package yuce.kerem.thesis.dto;

import lombok.Builder;
import lombok.Data;
import yuce.kerem.thesis.model.Recommendation;

import java.util.Set;

/**
 * @author Kerem(Nurullah)
 * @version 1.0
 * @date 5/28/21 10:41 PM
 */

@Data
@Builder
public class WebPageDto {

    private Long id;
    private String title;
    private String description;
    private Set<RecommendationDto> recommendations;

    @Override
    public String toString() {
        return "WebPageDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

}
