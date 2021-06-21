package yuce.kerem.thesis.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yuce.kerem.thesis.dto.WebPageDto;
import yuce.kerem.thesis.dto.mappers.WebPageMapper;
import yuce.kerem.thesis.services.WebPageSearchService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Kerem(Nurullah)
 * @version 1.0
 * @date 6/9/21 8:58 PM
 */

@RestController
@RequestMapping(path = "/webPage/search")
@CrossOrigin(originPatterns = "*")
public class WebPageSearchController {

    private final WebPageSearchService searchService;

    public WebPageSearchController(WebPageSearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping
    public ResponseEntity<List<WebPageDto>> processSearch(@RequestParam("query") final String query) {
        if (query == null) {
            return ResponseEntity.badRequest().build();
        }

        List<WebPageDto> webPageDtos = searchService.processSearch(query)
                .stream()
                .map(doc -> WebPageMapper.documentToDto(doc))
                .collect(Collectors.toList());

        return ResponseEntity.ok(webPageDtos);
    }
}
