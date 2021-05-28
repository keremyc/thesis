package yuce.kerem.thesis.services;

import yuce.kerem.thesis.model.User;

import java.util.Optional;

/**
 * @author Kerem(Nurullah)
 * @version 1.0
 * @date 5/25/21 4:54 PM
 */
public interface UserService {

    public Optional<User> getUserById(Long id);
    public Optional<User> getUserByUsername(String username);

}
