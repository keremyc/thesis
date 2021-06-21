package yuce.kerem.thesis.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import yuce.kerem.thesis.model.AppUser;

/**
 * @author Kerem(Nurullah)
 * @version 1.0
 * @date 5/25/21 4:52 PM
 */
public interface AppUserService extends UserDetailsService {

    public String signUpUser(AppUser appUser);

}
