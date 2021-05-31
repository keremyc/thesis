package yuce.kerem.thesis.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;


@Data
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

    @Column(name = "Description")
    private String description;

    @OneToMany(mappedBy = "recommendedWebPage")
    private Set<Recommendation> recommendations;

    @Override
    public String toString() {
        return "WebPage{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}

