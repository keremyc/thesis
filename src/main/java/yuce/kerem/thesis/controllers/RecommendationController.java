package yuce.kerem.thesis.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import yuce.kerem.thesis.dto.RecommendationDto;
import yuce.kerem.thesis.dto.mappers.RecommendationMapper;
import yuce.kerem.thesis.model.AppUser;
import yuce.kerem.thesis.model.Recommendation;
import yuce.kerem.thesis.model.WebPage;
import yuce.kerem.thesis.services.RecommendationService;
import yuce.kerem.thesis.services.WebPageService;


/**
 * @author Kerem(Nurullah)
 * @version 1.0
 * @date 5/31/21 8:14 PM
 */

@RestController
@CrossOrigin(originPatterns = "*")
@RequestMapping(path = "/api/recs")
public class RecommendationController {

    private final RecommendationService recommendationService;
    private final WebPageService webPageService;

    public RecommendationController(RecommendationService recommendationService, WebPageService webPageService) {
        this.recommendationService = recommendationService;
        this.webPageService = webPageService;
    }

    @PostMapping
    public ResponseEntity saveRecommendation(@RequestBody RecommendationDto recommendationDto,
                                             @AuthenticationPrincipal AppUser appUser) {

        Recommendation recommendation = RecommendationMapper.recDtoToRec(recommendationDto);
        WebPage webPage = webPageService.getByUrl(recommendation.getRecommendedWebPage().getUrl());

        if (webPage == null) {
            webPage = WebPage.builder()
                    .description(recommendation.getRecommendedWebPage().getDescription())
                    .title(recommendation.getRecommendedWebPage().getTitle())
                    .url(recommendation.getRecommendedWebPage().getUrl())
                    .build();
        }

        recommendation.setRecommendedBy(appUser.getUser());
        webPage.addRecommedation(recommendation);

        webPageService.save(webPage);

        return ResponseEntity.ok("Created");
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<RecommendationDto> getRecommendation(@PathVariable("id") Long id) {

        Recommendation recomemdation = recommendationService.getById(id);

        if (recomemdation == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(RecommendationMapper.recToRecDto(recomemdation));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRecommendationById(@PathVariable("id") Long id) {
        recommendationService.deleteById(id);

        return ResponseEntity.ok("Deleted");
    }

    @PutMapping("/{id}")
    public ResponseEntity<RecommendationDto> updateRecommendationById(@PathVariable("id") Long id,
                                                                      @RequestBody RecommendationDto recommendationDto) {
        Recommendation recommendation = recommendationService.getById(id);

        recommendation.setRecommendationText(recommendationDto.getRecommendationText());
        recommendation.setLiked(recommendationDto.isLiked());

        recommendationService.save(recommendation);

        return ResponseEntity.ok(RecommendationMapper.recToRecDto(recommendation));

    }



}
