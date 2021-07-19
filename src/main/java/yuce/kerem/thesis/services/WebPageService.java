package yuce.kerem.thesis.services;

import yuce.kerem.thesis.model.WebPage;

import java.util.Set;

/**
 * @author Kerem(Nurullah)
 * @version 1.0
 * @date 5/31/21 4:31 PM
 */
public interface WebPageService extends CrudService<WebPage, Long> {

    public WebPage getByUrl(String url);
    public Set<WebPage> findAll();

}
