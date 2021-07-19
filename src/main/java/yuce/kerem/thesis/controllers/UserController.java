package yuce.kerem.thesis.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yuce.kerem.thesis.dto.RecommendationDto;
import yuce.kerem.thesis.dto.UserDto;
import yuce.kerem.thesis.dto.WebPageDto;
import yuce.kerem.thesis.dto.mappers.RecommendationMapper;
import yuce.kerem.thesis.dto.mappers.UserMapper;
import yuce.kerem.thesis.dto.mappers.WebPageMapper;
import yuce.kerem.thesis.model.User;
import yuce.kerem.thesis.services.UserService;

import java.util.HashSet;
import java.util.Set;


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

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{username}")
    public UserDto getUser(@PathVariable("username") String username) {
        return UserMapper.userToUserDto(userService.getUserByUsername(username));
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
                .map( w -> WebPageMapper.webPageToWebPageDto(w) )
                .forEach(webPageDtos::add);

        return ResponseEntity.ok(webPageDtos);
    }

}
