package fr.humanbooster.lacentral.controller;


import com.fasterxml.jackson.annotation.JsonView;
import fr.humanbooster.lacentral.custom_response.JwtResponse;
import fr.humanbooster.lacentral.dto.UserDto;
import fr.humanbooster.lacentral.dto.UserLoginDto;
import fr.humanbooster.lacentral.dto.UserRegisterDto;
import fr.humanbooster.lacentral.entity.User;
import fr.humanbooster.lacentral.jsonviews.UserJsonview;
import fr.humanbooster.lacentral.security.JwtAuthenticatorService;
import fr.humanbooster.lacentral.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UserSecurityController {

    private final UserService userService;
    private final JwtAuthenticatorService jwtAuthenticatorService;

    @PostMapping("/api/register")
    public User register(@Valid @RequestBody UserRegisterDto user) {
        return userService.create(user);
    }

    @PostMapping("/api/login")
    public ResponseEntity<JwtResponse> login(@RequestBody UserLoginDto user) {
        return jwtAuthenticatorService.authenticate(user);
    }

}
