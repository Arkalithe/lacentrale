package fr.humanbooster.lacentral.service;

import fr.humanbooster.lacentral.entity.Listing;
import fr.humanbooster.lacentral.entity.User;
import fr.humanbooster.lacentral.entity.embededid.UserListingId;
import fr.humanbooster.lacentral.repository.FavoriteRepository;
import fr.humanbooster.lacentral.entity.Favorite;
import fr.humanbooster.lacentral.dto.FavoriteDto;
import fr.humanbooster.lacentral.service.interfaces.ServiceInterface;
import fr.humanbooster.lacentral.service.interfaces.ServiceInterfaceBase;
import fr.humanbooster.lacentral.service.interfaces.ServiceListInterface;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FavoriteService {

    private final FavoriteRepository favoriteRepository;

    public Boolean handleFavorite(UserListingId id)
    {
        Optional<Favorite> optionalFavorite  = favoriteRepository.findById(id);
        if (optionalFavorite.isEmpty()) {
            Favorite favorite= new Favorite();
            favorite.setId(id);
            favorite.setCreatedAt(LocalDateTime.now());
            favoriteRepository.saveAndFlush(favorite);
            return true;
        }
        favoriteRepository.delete(optionalFavorite .get());
        return false;
        }
}
