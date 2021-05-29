package yuce.kerem.thesis.dto;

import lombok.Builder;
import lombok.Data;
import yuce.kerem.thesis.model.EducationLevel;
import yuce.kerem.thesis.model.Gender;
import yuce.kerem.thesis.model.Recommendation;
import yuce.kerem.thesis.model.WebPage;

import java.util.Set;

/**
 * @author Kerem(Nurullah)
 * @version 1.0
 * @date 5/28/21 9:28 PM
 */

@Builder
@Data
public class UserDto {

    private Long id;
    private String lastName;
    private String firstName;
    private int age;
    private String occupation;
    private Gender gender;
    private EducationLevel eduLevel;
    private Set<WebPageDto> createdWebPages;
    private Set<RecommendationDto> recommendations;
    private Set<WebPageDto> favoriteWebPages;

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", age='" + age + '\'' +
                ", occupation='" + occupation + '\'' +
                ", gender=" + gender +
                ", eduLevel=" + eduLevel +
                '}';
    }

}
