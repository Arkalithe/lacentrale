package fr.humanbooster.lacentral.repository;

import fr.humanbooster.lacentral.entity.Favorite;
import fr.humanbooster.lacentral.entity.embededid.UserListingId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, UserListingId> {

}