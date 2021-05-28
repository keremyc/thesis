package yuce.kerem.thesis.repositories;

import org.springframework.data.repository.CrudRepository;
import yuce.kerem.thesis.model.AppUser;

import java.util.Optional;

/**
 * @author Kerem(Nurullah)
 * @version 1.0
 * @date 5/25/21 2:44 PM
 */

public interface AppUserRepository extends CrudRepository<AppUser, Long> {

    Optional<AppUser> findByUsername(String username);

}
