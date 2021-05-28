package yuce.kerem.thesis.repositories;

import org.springframework.data.repository.CrudRepository;
import yuce.kerem.thesis.model.User;

/**
 * @author Kerem(Nurullah)
 * @version 1.0
 * @date 5/25/21 2:29 PM
 */
public interface UserRepository extends CrudRepository<User, Long> {

}
