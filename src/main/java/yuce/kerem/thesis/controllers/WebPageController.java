package yuce.kerem.thesis.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yuce.kerem.thesis.dto.WebPageDto;
import yuce.kerem.thesis.dto.mappers.WebPageMapper;
import yuce.kerem.thesis.model.WebPage;
import yuce.kerem.thesis.services.WebPageService;

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
}
