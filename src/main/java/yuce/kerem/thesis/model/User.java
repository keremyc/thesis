package yuce.kerem.thesis.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "LastName", nullable = false)
    private String lastName;

    @Column(name = "FirstName", nullable = false)
    private String firstName;

    @Column(name = "Age")
    private int age;

    @Column(name = "Occupation")
    private String occupation;

    @Column(name = "EducationLevel")
    @Enumerated(EnumType.STRING)
    private EducationLevel eduLevel;

    @Column(name = "Gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @OneToMany
    @JoinColumn(name = "CreatedBy")
    private Set<WebPage> createdWebPages;

    @OneToMany(mappedBy = "recommendedBy")
    private Set<Recommendation> recommendations;

    @ManyToMany
    @JoinTable(
            name = "USER_FAVOURITES",
            joinColumns = @JoinColumn(name = "UserId"),
            inverseJoinColumns = @JoinColumn(name = "WebPageId")
    )
    private Set<WebPage> favoritesWebPages;

}
