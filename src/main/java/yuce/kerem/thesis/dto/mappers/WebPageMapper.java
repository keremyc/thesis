package yuce.kerem.thesis.dto.mappers;

import yuce.kerem.thesis.dto.WebPageDto;
import yuce.kerem.thesis.model.WebPage;

import java.util.stream.Collectors;

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
                .recommendations(
                        webPageDto.getRecommendations().stream()
                        .map( r-> RecommendationMapper.recDtoToRec(r) )
                        .collect(Collectors.toSet())
                )
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
                .recommendations(
                        webPage.getRecommendations().stream()
                        .map( r -> RecommendationMapper.recToRecDto(r) )
                        .collect(Collectors.toSet())
                )
                .build();

        return webPageDto;
    }

}
