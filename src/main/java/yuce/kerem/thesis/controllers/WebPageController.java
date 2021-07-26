package yuce.kerem.thesis.controllers;

import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yuce.kerem.thesis.dto.RecommendationDto;
import yuce.kerem.thesis.dto.WebPageDto;
import yuce.kerem.thesis.dto.mappers.RecommendationMapper;
import yuce.kerem.thesis.dto.mappers.WebPageMapper;
import yuce.kerem.thesis.model.WebPage;
import yuce.kerem.thesis.services.WebPageService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Kerem(Nurullah)
 * @version 1.0
 * @date 6/1/21 12:41 AM
 */

@RestController
@CrossOrigin(originPatterns = "*")
@RequestMapping("/api/webpages")
public class WebPageController {

    private final WebPageService webPageService;


    public WebPageController(WebPageService webPageService) {
        this.webPageService = webPageService;
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        Set<WebPage> webPages = webPageService.findAll();

        List<WebPageDto> webPageDtoList = webPages.stream()
                .map(wp -> WebPageMapper.webPageToWebPageDto(wp))
                .collect(Collectors.toList());

        return ResponseEntity.ok(webPageDtoList);
    }

    @GetMapping(path = "/most-popular-websites")
    public ResponseEntity<List<WebPageDto>> getMostPopular5WebSites() {
        List<WebPage> webPages = new ArrayList<>();

        webPageService.findMostPopular5WebSites().forEach(webPages::add);

        List<WebPageDto> webPageDtoList = webPages.stream()
                .map(webPage -> WebPageMapper.webPageToWebPageDto(webPage))
                .collect(Collectors.toList());

        return ResponseEntity.ok(webPageDtoList);
    }

    @GetMapping(path = "/{pageId}/recs")
    public ResponseEntity<List<RecommendationDto>> getRecommendationsForWebPage(@PathVariable("pageId") Long pageId) {
        WebPage webPage = webPageService.getById(pageId);

        List<RecommendationDto> recommendationDtos = webPage.getRecommendations().stream()
                .map(rec -> RecommendationMapper.recToRecDto(rec))
                .collect(Collectors.toList());

        return ResponseEntity.ok(recommendationDtos);
    }
}
