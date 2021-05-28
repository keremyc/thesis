package yuce.kerem.thesis.controllers;

import org.springframework.web.bind.annotation.*;
import yuce.kerem.thesis.dto.RegistrationRequest;
import yuce.kerem.thesis.model.AppUser;
import yuce.kerem.thesis.model.User;
import yuce.kerem.thesis.services.AppUserServiceImpl;

/**
 * @author Kerem(Nurullah)
 * @version 1.0
 * @date 5/25/21 3:04 PM
 */

@RestController
@RequestMapping(path = "/registration")
public class RegistrationController {

    private final AppUserServiceImpl appUserService;

    public RegistrationController(AppUserServiceImpl appUserServiceImpl) {
        this.appUserService = appUserServiceImpl;
    }

    @PostMapping
    public ResponseBody register(@RequestBody RegistrationRequest request) {

        User newUser = new User();
        newUser.setAge(request.getAge());
        newUser.setEduLevel(request.getEduLevel());
        newUser.setGender(request.getGender());
        newUser.setFirstName(request.getFirstName());
        newUser.setLastName(request.getLastName());
        newUser.setOccupation(request.getOccupation());

        AppUser newAppUser = new AppUser(
                request.getPassword(),
                request.getUsername(),
                true,
                newUser);

        appUserService.signUpUser(newAppUser);

        // todo: refactor return
        return null;
    }

}
