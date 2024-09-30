package fr.humanbooster.lacentral.repository;

import fr.humanbooster.lacentral.entity.Listing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListingRepository extends JpaRepository<Listing, String> {

}