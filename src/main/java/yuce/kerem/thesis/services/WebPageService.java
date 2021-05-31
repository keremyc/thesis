package yuce.kerem.thesis.services;

import yuce.kerem.thesis.model.WebPage;

/**
 * @author Kerem(Nurullah)
 * @version 1.0
 * @date 5/31/21 4:31 PM
 */
public interface WebPageService extends CrudService<WebPage, Long> {

    public WebPage getByUrl(String url);

}
