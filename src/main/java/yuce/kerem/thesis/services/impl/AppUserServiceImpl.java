package yuce.kerem.thesis.services.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import yuce.kerem.thesis.model.AppUser;
import yuce.kerem.thesis.repositories.AppUserRepository;
import yuce.kerem.thesis.services.AppUserService;

import java.util.Optional;

/**
 * @author Kerem(Nurullah)
 * @version 1.0
 * @date 5/25/21 2:47 PM
 */

@Service
public class AppUserServiceImpl implements AppUserService {

    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;

    public AppUserServiceImpl(AppUserRepository appUserRepository, PasswordEncoder passwordEncoder) {
        this.appUserRepository = appUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return appUserRepository.findByUsername(username)
                .orElseThrow(
                        () -> new UsernameNotFoundException(String.format("user with username %s not found", username))
                );
    }

    public String signUpUser(AppUser appUser) {
        boolean isExists = appUserRepository
                .findByUsername(appUser.getUsername())
                .isPresent();

        if (isExists) {
            throw new IllegalStateException("user already exists");
        }

        String encodedPassword = passwordEncoder.encode(appUser.getPassword());
        appUser.setPassword(encodedPassword);

        // todo: Create a confirmation token.

        appUserRepository.save(appUser);

        return null;
    }

}
