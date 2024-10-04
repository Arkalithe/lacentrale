package fr.humanbooster.lacentral.repository;

import fr.humanbooster.lacentral.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findUserByActivationCode(String code);

    Optional<User> findByEmail(String code);


    Optional<User> findByEmailAndActivationCodeIsNull(String email);
}