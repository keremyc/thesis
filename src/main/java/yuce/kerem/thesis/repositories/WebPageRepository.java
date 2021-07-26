package yuce.kerem.thesis.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import yuce.kerem.thesis.model.WebPage;

import java.util.List;
import java.util.Optional;

/**
 * @author Kerem(Nurullah)
 * @version 1.0
 * @date 5/31/21 4:28 PM
 */
public interface WebPageRepository extends CrudRepository<WebPage, Long> {

    public Optional<WebPage> findByUrl(String url);
    public List<WebPage> findAll();

}
