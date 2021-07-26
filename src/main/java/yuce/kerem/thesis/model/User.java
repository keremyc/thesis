package yuce.kerem.thesis.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
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

    @Column(name = "Email")
    private String email;

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

    @OneToMany(mappedBy = "recommendedBy")
    private Set<Recommendation> recommendations;

    @ManyToMany()
    @JoinTable(
            name = "USER_FAVOURITES",
            joinColumns = @JoinColumn(name = "UserId"),
            inverseJoinColumns = @JoinColumn(name = "WebPageId")
    )
    private Set<WebPage> favoritesWebPages;

    public void addToFavorites(WebPage page) {
        favoritesWebPages.add(page);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", age=" + age +
                ", occupation='" + occupation + '\'' +
                ", eduLevel=" + eduLevel +
                ", gender=" + gender +
                '}';
    }
}
