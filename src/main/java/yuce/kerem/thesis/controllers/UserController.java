package yuce.kerem.thesis.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yuce.kerem.thesis.dto.UserDto;
import yuce.kerem.thesis.dto.mappers.UserMapper;
import yuce.kerem.thesis.services.UserService;

/**
 * @author Kerem(Nurullah)
 * @version 1.0
 * @date 5/25/21 4:49 PM
 */

@RestController
@RequestMapping(path = "/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{username}")
    public UserDto getUser(@PathVariable("username") String username) {
        return UserMapper.userToUserDto(userService.getUserByUsername(username));
    }
}
