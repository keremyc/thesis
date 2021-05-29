package yuce.kerem.thesis.dto;

import lombok.Getter;
import lombok.Setter;
import yuce.kerem.thesis.model.EducationLevel;
import yuce.kerem.thesis.model.Gender;

/**
 * @author Kerem(Nurullah)
 * @version 1.0
 * @date 5/25/21 3:07 PM
 */

@Setter
@Getter
public class RegistrationRequest {

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private int age;
    private EducationLevel eduLevel;
    private Gender gender;
    private String occupation;

}
