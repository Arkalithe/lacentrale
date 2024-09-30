package fr.humanbooster.lacentral.controller;

import fr.humanbooster.lacentral.dto.UserDto;
import fr.humanbooster.lacentral.dto.UserUpdateDto;
import fr.humanbooster.lacentral.service.UserService;
import fr.humanbooster.lacentral.jsonviews.UserJsonview;
import fr.humanbooster.lacentral.entity.User;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    @JsonView(UserJsonview.userShowView.class)
    public User getUserById(@PathVariable String id) {
        return userService.findById(id);
    }

    @PostMapping
    public User create(@Valid @RequestBody UserDto userDto) {
        return userService.create(userDto);
    }

    @GetMapping("/activate/{code}")
    @JsonView(UserJsonview.userShowView.class)
    public User activate(@PathVariable String code) {

        return userService.activate(code);
    }

    @PutMapping("/{id}")
    public User update(@PathVariable String id,@Valid @RequestBody UserUpdateDto userDto) {
        return userService.update(userDto, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        userService.delete(id);
    }
}
