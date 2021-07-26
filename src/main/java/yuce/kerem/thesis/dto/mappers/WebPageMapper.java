package yuce.kerem.thesis.dto.mappers;

import yuce.kerem.thesis.dto.WebPageDto;
import yuce.kerem.thesis.model.WebPage;
import yuce.kerem.thesis.model.WebPageDocument;

import java.util.HashSet;

/**
 * @author Kerem(Nurullah)
 * @version 1.0
 * @date 5/28/21 10:45 PM
 */
public class WebPageMapper {

    /**
     * For given WebPageDto object, returns corresponding WebPage object;
     * @param webPageDto
     * @return
     */
    public static WebPage webPageDtoToWebPage(WebPageDto webPageDto) {

        WebPage webPage = WebPage.builder()
                .id(webPageDto.getId())
                .title(webPageDto.getTitle())
                .description(webPageDto.getDescription())
                .url(webPageDto.getUrl())
                .build();

        return webPage;
    }

    /**
     * For given WebPage object, returns corresponding WebPageDto object;
     * @param webPage
     * @return
     */
    public static WebPageDto webPageToWebPageDto(WebPage webPage) {

        WebPageDto webPageDto = WebPageDto.builder()
                .id(webPage.getId())
                .title(webPage.getTitle())
                .description(webPage.getDescription())
                .numberOfLikes(webPage.getNumberOfLikes())
                .numberOfDislikes(webPage.getNumberOfDislikes())
                .url(webPage.getUrl())
                .build();

        return webPageDto;
    }

    public static WebPageDto documentToDto(final WebPageDocument document) {

        if (document == null) {
           throw new RuntimeException("document cannot be null");
        }

        WebPageDto webPageDto = WebPageDto.builder()
                .id(document.getId())
                .title(document.getTitle())
                .description(document.getDescription())
                .url(document.getUrl())
                .build();

        return webPageDto;
    }

    public static WebPage docToEntity(WebPageDocument doc) {

        WebPage webPage = WebPage.builder()
                .id(doc.getId())
                .url(doc.getUrl())
                .title(doc.getTitle())
                .numberOfLikes(0)
                .numberOfDislikes(0)
                .description(doc.getDescription())
                .recommendations(new HashSet<>())
                .build();

        return webPage;
    }
}
