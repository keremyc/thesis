package yuce.kerem.thesis.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yuce.kerem.thesis.dto.RecommendationDto;
import yuce.kerem.thesis.dto.UserDto;
import yuce.kerem.thesis.dto.WebPageDto;
import yuce.kerem.thesis.dto.mappers.RecommendationMapper;
import yuce.kerem.thesis.dto.mappers.UserMapper;
import yuce.kerem.thesis.dto.mappers.WebPageMapper;
import yuce.kerem.thesis.dto.payload.response.MessageResponse;
import yuce.kerem.thesis.model.Recommendation;
import yuce.kerem.thesis.model.User;
import yuce.kerem.thesis.model.WebPage;
import yuce.kerem.thesis.services.RecommendationService;
import yuce.kerem.thesis.services.UserService;
import yuce.kerem.thesis.services.WebPageService;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * @author Kerem(Nurullah)
 * @version 1.0
 * @date 5/25/21 4:49 PM
 */

@RestController
@CrossOrigin(originPatterns = "*")
@RequestMapping(path = "/api/users")
public class UserController {

    private final UserService userService;
    private final WebPageService webPageService;
    private final RecommendationService recommendationService;

    public UserController(UserService userService, WebPageService webPageService, RecommendationService recommendationService) {
        this.userService = userService;
        this.webPageService = webPageService;
        this.recommendationService = recommendationService;
    }

    @GetMapping("/{username}")
    public UserDto getUser(@PathVariable("username") String username) {
        return UserMapper.userToUserDto(userService.getUserByUsername(username));
    }

    @PutMapping(path = "/{username}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("username") String username, @RequestBody UserDto userDto) {
        User user = userService.getUserByUsername(username);

        user.setOccupation(userDto.getOccupation());
        user.setGender(userDto.getGender());
        user.setAge(userDto.getAge());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEduLevel(userDto.getEduLevel());

        user = userService.save(user);

        UserDto newUserDto = UserMapper.userToUserDto(user);

        return ResponseEntity.ok(newUserDto);
    }

    @GetMapping("/{username}/recs")
    public ResponseEntity<Set<RecommendationDto>> getRecsOfUserByUsername(@PathVariable("username") String username) {

        User user = userService.getUserByUsername(username);

        Set<RecommendationDto> recommendationDtos = new HashSet<>();

        user.getRecommendations().stream()
                .map(r -> RecommendationMapper.recToRecDto(r))
                .forEach(recommendationDtos::add);

        return ResponseEntity.ok(recommendationDtos);

    }

    @GetMapping("/{username}/favs")
    public ResponseEntity<Set<WebPageDto>> getFavoritePagesByUsername(@PathVariable("username") String username) {

        User user = userService.getUserByUsername(username);

        Set<WebPageDto> webPageDtos = new HashSet<>();

        user.getFavoritesWebPages().stream()
                .map(w -> WebPageMapper.webPageToWebPageDto(w))
                .forEach(webPageDtos::add);

        return ResponseEntity.ok(webPageDtos);
    }

    @PostMapping(path = "/{username}/favs/{pageId}")
    public ResponseEntity<MessageResponse> addToFavorites(@PathVariable("username") String username, @PathVariable("pageId") Long pageId) {

        User user = userService.getUserByUsername(username);
        WebPage webpage = webPageService.getById(pageId);

        user.addToFavorites(webpage);
        userService.save(user);

        return ResponseEntity.ok(new MessageResponse("Webpage is added as a favorite"));
    }

    @DeleteMapping(path = "/{username}/recs/{pageId}")
    public ResponseEntity<MessageResponse> removeRecommendation(@PathVariable("username") String username, @PathVariable("pageId") Long pageId) {

        WebPage webPage = webPageService.getById(pageId);
        User user = userService.getUserByUsername(username);

        Set<Recommendation> recsToDelete = user.getRecommendations().stream()
                .filter(rec -> !(rec.getRecommendedWebPage().getId().equals(pageId)))
                .collect(Collectors.toSet());

        for (Recommendation rec: recsToDelete) {
            System.out.println(rec);
            user.getRecommendations().remove(rec);
            webPage.getRecommendations().remove(rec);
        }

        userService.save(user);
        webPageService.save(webPage);

        return ResponseEntity.ok(new MessageResponse("recommendation is deleted"));
    }


    @DeleteMapping(path = "/{username}/favs/{pageId}")
    public ResponseEntity<MessageResponse> removeFavorite(@PathVariable("username") String username, @PathVariable("pageId") Long pageId) {

        User user = userService.getUserByUsername(username);
        user.getFavoritesWebPages().remove(webPageService.getById(pageId));

        userService.save(user);

        return ResponseEntity.ok(new MessageResponse("recommendation is deleted"));
    }
}
