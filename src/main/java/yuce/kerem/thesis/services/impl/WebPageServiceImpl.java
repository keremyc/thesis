package yuce.kerem.thesis.services.impl;

import org.springframework.stereotype.Service;
import yuce.kerem.thesis.dto.WebPageDto;
import yuce.kerem.thesis.model.WebPage;
import yuce.kerem.thesis.repositories.WebPageRepository;
import yuce.kerem.thesis.services.WebPageService;

import java.util.HashSet;
import java.util.List;
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
    public Set<WebPage> findMostPopular5WebSites() {
        Set<WebPage> popularWebSites = new HashSet<>();

        List<WebPage> webPages = webPageRepository.findAll();

        for (int i = 0; i < 5; i++) {
            int indexOfTop = 0;
            int max = 0;
            for (int j = 0; j < webPages.size(); j++) {
                if (webPages.get(j).getNumberOfLikes() > max) {
                    max = webPages.get(j).getNumberOfLikes();
                    indexOfTop = j;
                }
            }
            if (!webPages.isEmpty()){
                popularWebSites.add(webPages.get(indexOfTop));
                webPages.remove(indexOfTop);
            }

        }

        return popularWebSites;
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
