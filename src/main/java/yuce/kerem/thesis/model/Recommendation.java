package yuce.kerem.thesis.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "RECOMMENDATION")
public class Recommendation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "RecommendationText")
    private String recommendationText;

    @Column(name = "Liked")
    private boolean liked;

    @Column(name = "CreatedAt")
    private Date createdAt;

    @ManyToOne
    @JoinColumn(name = "RecommendedBy")
    private User recommendedBy;

    @ManyToOne
    @JoinColumn(name = "RecommendedWebPage")
    private WebPage recommendedWebPage;

    @OneToMany
    @JoinColumn(name = "Recommendation")
    private Set<Comment> comments;

    @Override
    public String toString() {
        return "Recommendation{" +
                "id=" + id +
                ", recommendationText='" + recommendationText + '\'' +
                ", liked=" + liked +
                ", createdAt=" + createdAt +
                '}';
    }
}
