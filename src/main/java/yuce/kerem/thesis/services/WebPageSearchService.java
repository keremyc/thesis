package yuce.kerem.thesis.services;

import yuce.kerem.thesis.model.WebPageDocument;

import java.util.List;

/**
 * @author Kerem(Nurullah)
 * @version 1.0
 * @date 6/9/21 8:50 PM
 */

public interface WebPageSearchService {

    public List<WebPageDocument> processSearch(final String query);

}
