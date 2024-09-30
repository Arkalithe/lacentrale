package fr.humanbooster.lacentral.controller;


import fr.humanbooster.lacentral.entity.embededid.UserListingId;
import fr.humanbooster.lacentral.service.FavoriteService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/favorites")
@AllArgsConstructor
public class FavoriteController {

    private final FavoriteService favoriteService;

    @PostMapping
    public Boolean create(@Valid  @RequestBody UserListingId favoriteDto) {
        return favoriteService.handleFavorite(favoriteDto);
    }

}
