package yuce.kerem.thesis.repositories;

import org.springframework.data.repository.CrudRepository;
import yuce.kerem.thesis.model.Comment;

/**
 * @author Kerem(Nurullah)
 * @version 1.0
 * @date 5/31/21 4:55 PM
 */
public interface CommentRepository extends CrudRepository<Comment, Long> {
}
