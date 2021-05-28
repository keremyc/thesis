package yuce.kerem.thesis.services;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import yuce.kerem.thesis.model.AppUser;
import yuce.kerem.thesis.model.User;
import yuce.kerem.thesis.repositories.UserRepository;

import java.util.Optional;

/**
 * @author Kerem(Nurullah)
 * @version 1.0
 * @date 5/25/21 4:55 PM
 */

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AppUserService appUserService;

    public UserServiceImpl(UserRepository userRepository, AppUserService appUserService) {
        this.userRepository = userRepository;
        this.appUserService = appUserService;
    }

    @Override
    public Optional<User> getUserById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("id cannot be null");
        }

        return userRepository.findById(id);
    }

    @Override
    public Optional<User> getUserByUsername(String username) {
        if (username == null) {
            throw new IllegalArgumentException("username cannot be null");
        }

        return Optional.of(((AppUser) appUserService.loadUserByUsername(username)).getUser());
    }
}