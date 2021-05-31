package yuce.kerem.thesis.repositories;

import org.springframework.data.repository.CrudRepository;
import yuce.kerem.thesis.model.Recommendation;

/**
 * @author Kerem(Nurullah)
 * @version 1.0
 * @date 5/31/21 4:38 PM
 */
public interface RecommendationRepository extends CrudRepository<Recommendation, Long> {

}
