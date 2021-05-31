package yuce.kerem.thesis.services.impl;

import org.springframework.stereotype.Service;
import yuce.kerem.thesis.model.Recommendation;
import yuce.kerem.thesis.repositories.RecommendationRepository;
import yuce.kerem.thesis.services.RecommendationService;

/**
 * @author Kerem(Nurullah)
 * @version 1.0
 * @date 5/31/21 4:38 PM
 */

@Service
public class RecommendationRepositoryImpl implements RecommendationService {

    private final RecommendationRepository recRepocitory;

    public RecommendationRepositoryImpl(RecommendationRepository recRepocitory) {
        this.recRepocitory = recRepocitory;
    }

    @Override
    public Recommendation save(Recommendation obj) {
        return recRepocitory.save(obj);
    }

    @Override
    public Recommendation getById(Long id) {
        return recRepocitory.findById(id).orElseThrow(
                () -> { throw new RuntimeException("No Recommendation with given id"); }
        );
    }

    @Override
    public void delete(Recommendation obj) {
        recRepocitory.delete(obj);
    }

    @Override
    public void deleteById(Long id) {
        recRepocitory.deleteById(id);
    }
}
