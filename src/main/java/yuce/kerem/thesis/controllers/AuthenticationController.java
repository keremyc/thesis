package yuce.kerem.thesis.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import yuce.kerem.thesis.config.security.jwt.JwtUtils;
import yuce.kerem.thesis.dto.payload.request.LoginRequest;
import yuce.kerem.thesis.dto.payload.request.RegistrationRequest;
import yuce.kerem.thesis.dto.payload.response.JwtResponse;
import yuce.kerem.thesis.dto.payload.response.MessageResponse;
import yuce.kerem.thesis.model.AppUser;
import yuce.kerem.thesis.model.User;
import yuce.kerem.thesis.services.UserService;
import yuce.kerem.thesis.services.impl.AppUserServiceImpl;

/**
 * @author Kerem(Nurullah)
 * @version 1.0
 * @date 5/25/21 3:04 PM
 */

@RestController
@RequestMapping(path = "/api/auth")
@CrossOrigin(originPatterns = "*")
public class AuthenticationController {

    private final AppUserServiceImpl appUserService;
    private final AuthenticationManager authManager;
    private final JwtUtils jwtUtils;
    private final UserService userService;

    public AuthenticationController(AppUserServiceImpl appUserServiceImpl,
                                    AuthenticationManager authManager,
                                    JwtUtils jwtUtils,
                                    UserService userService) {
        this.appUserService = appUserServiceImpl;
        this.authManager = authManager;
        this.jwtUtils = jwtUtils;
        this.userService = userService;
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest request) {

        Authentication authentication = null;
        try {
            authentication = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                           request.getUsername(),
                           request.getPassword()
                    )
            );
        } catch (AuthenticationException exp) {
            return ResponseEntity.badRequest().body(
                    new MessageResponse("Error: Authentication Failed"));
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtUtils.generateJwtToken(authentication);
        AppUser appUser = (AppUser) authentication.getPrincipal();

        return ResponseEntity.ok(
                new JwtResponse(
                        jwt,
                        appUser.getUser().getId(),
                        appUser.getUsername()
                )
        );
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody RegistrationRequest request) {
        if (appUserService.existsByUsername(request.getUsername())) {
            return ResponseEntity.badRequest()
                    .body(new MessageResponse("Error: Username is already in use."));
        }

        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .build();

        user = userService.save(user);

        AppUser appUser = new AppUser(request.getPassword(),
                request.getUsername(), true, user);

        appUserService.signUpUser(appUser);

        return ResponseEntity.ok(
                new MessageResponse("User registered successfully"));
    }

}
