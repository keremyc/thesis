package yuce.kerem.thesis.services.impl;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import yuce.kerem.thesis.model.AppUser;
import yuce.kerem.thesis.model.User;
import yuce.kerem.thesis.repositories.UserRepository;
import yuce.kerem.thesis.services.AppUserService;
import yuce.kerem.thesis.services.UserService;

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
    public User save(User obj) {
        return userRepository.save(obj);
    }

    @Override
    public User getById(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> { throw new RuntimeException("No User with given id"); }
        );
    }

    @Override
    public void delete(User obj) {
        userRepository.delete(obj);
        // todo: what about the app user !!
    }

    @Override
    public void deleteById(Long aLong) {
        userRepository.deleteById(aLong);
        // todo: what about the app user !!
    }

    @Override
    public User getUserByUsername(String username) {
        if (username == null) {
            throw new IllegalArgumentException("user name cannot be null.");
        }

        return ((AppUser) appUserService.loadUserByUsername(username)).getUser();
    }
}