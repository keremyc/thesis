package yuce.kerem.thesis.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "WEB_PAGE")
public class WebPage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "Title")
    private String title;

    @Column(name = "Url", unique = true)
    private String url;

    @Column(name = "NumberOfLikes")
    private Integer numberOfLikes = 0;

    @Column(name = "NumberOfDislikes")
    private Integer numberOfDislikes = 0;

    @Lob
    @Column(name = "Description", columnDefinition = "CLOB")
    private String description;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recommendedWebPage")
    private Set<Recommendation> recommendations = new HashSet<>();

    @Override
    public String toString() {
        return "WebPage{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public void addRecommedation(Recommendation recommendation) {
        if (recommendations == null) {
            this.recommendations = new HashSet<>();
        }
        recommendations.add(recommendation);
        recommendation.setRecommendedWebPage(this);

        if (recommendation.isLiked()) {
            numberOfLikes++;
        } else {
           numberOfDislikes++;
        }

    }

    public Integer getNumberOfDislikes() {
        int i = 0;
        for (Recommendation rec: recommendations) {
            if (!rec.isLiked())
                i++;
        }

        return i;
    }

    public Integer getNumberOfLikes() {
        int i = 0;
        for (Recommendation rec: recommendations) {
            if (rec.isLiked())
                i++;
        }

        return i;
    }
}

