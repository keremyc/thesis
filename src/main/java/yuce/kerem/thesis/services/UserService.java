package yuce.kerem.thesis.services;

import yuce.kerem.thesis.model.User;

import java.util.Optional;

/**
 * @author Kerem(Nurullah)
 * @version 1.0
 * @date 5/25/21 4:54 PM
 */
public interface UserService extends CrudService<User, Long> {

    public User getUserByUsername(String username);

}
