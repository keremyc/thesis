package yuce.kerem.thesis.services.impl;

import org.springframework.stereotype.Service;
import yuce.kerem.thesis.dto.WebPageDto;
import yuce.kerem.thesis.model.WebPage;
import yuce.kerem.thesis.repositories.WebPageRepository;
import yuce.kerem.thesis.services.WebPageService;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Kerem(Nurullah)
 * @version 1.0
 * @date 5/31/21 4:29 PM
 */

@Service
public class WebPageServiceImpl implements WebPageService {

    private final WebPageRepository webPageRepository;

    public WebPageServiceImpl(WebPageRepository webPageRepository) {
        this.webPageRepository = webPageRepository;
    }

    @Override
    public WebPage save(WebPage webPage) {
        return webPageRepository.save(webPage);
    }

    @Override
    public WebPage getById(Long id) {
        return webPageRepository.findById(id).orElseThrow(
                () -> { throw new RuntimeException("no WebPAge with given id"); }
        );
    }


    public Set<WebPage> findAll() {
        HashSet<WebPage> webPages = new HashSet<>();

       webPageRepository.findAll().forEach(wp -> webPages.add(wp));

       return webPages;
    }

    @Override
    public WebPage getByUrl(String url) {
        return webPageRepository.findByUrl(url).orElse(null);
    }

    @Override
    public void delete(WebPage webPage) {
        webPageRepository.delete(webPage);
    }

    @Override
    public void deleteById(Long id) {
        webPageRepository.deleteById(id);
    }
}
