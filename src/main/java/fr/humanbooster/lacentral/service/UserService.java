package fr.humanbooster.lacentral.service;

import fr.humanbooster.lacentral.dto.UserRegisterDto;
import fr.humanbooster.lacentral.dto.UserUpdateDto;
import fr.humanbooster.lacentral.entity.Address;
import fr.humanbooster.lacentral.exception.AlreadyActiveException;
import fr.humanbooster.lacentral.exception.ExpiredCodeException;
import fr.humanbooster.lacentral.repository.AddressRepository;
import fr.humanbooster.lacentral.repository.UserRepository;
import fr.humanbooster.lacentral.entity.User;
import fr.humanbooster.lacentral.dto.UserDto;
import fr.humanbooster.lacentral.service.interfaces.ServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;

import java.nio.channels.AlreadyBoundException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.TimeoutException;

@Service
@AllArgsConstructor
public class UserService implements ServiceInterface<User, String, UserRegisterDto, UserUpdateDto>, UserDetailsService {

    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User create(UserRegisterDto userDto) {
        User user = new User();
        user.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        user.setEmail(userDto.getEmail());
        return userRepository.saveAndFlush(objectCreateFromDto(new User(), userDto));
    }

    @Override
    public User update(UserUpdateDto userDto, String id) {
        User user = objectUpdateFromDto(findById(id), userDto);
        user.setUuid(id);
        return userRepository.saveAndFlush(user);
    }

    @Override
    public Boolean delete(String id) {
        try {
            User user = findById(id);
            user.setPhone(null);
            user.setBirthAt(null);
            user.setPhoto(null);
            user.setSiret(null);
            user.setLastName(null);
            user.setFirstName(null);
            user.setEmail("Utilisateur supprimé");
            Address address = user.getAddress();
            if (address!= null) {
                address.setUser_uuid(null);
                addressRepository.save(address);
            }
            userRepository.saveAndFlush(user);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    @Override
    public User findById(String id) {
        return userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    private User objectCreateFromDto(User user, UserRegisterDto userDto) {
        user.setEmail(userDto.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setActivationCode(UUID.randomUUID().toString());
        user.setPhone(userDto.getPhone());
        user.setCreatedAt(LocalDateTime.now());
        user.setBirthAt(userDto.getBirthAt());
        user.setRoles("[\"ROLE_USER\"]");
        // Send Mail ? mailerService.setActivationCodeSentAt(user)
         user.setActivationCodeSentAt(LocalDateTime.now());
        return user;
    }

    private User objectUpdateFromDto(User user, UserUpdateDto userDto) {
        user.setPhone(userDto.getPhone());
        user.setCreatedAt(LocalDateTime.now());
        user.setBirthAt(userDto.getBirthAt());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPhoto(userDto.getPhoto());
        user.setSiret(userDto.getSiret());
        return user;
    }

    public User activate(String code){
        User user = userRepository.findUserByActivationCode(code).orElseThrow(() -> new AlreadyActiveException("Ce code d'activation n'existe pas !"));
        LocalDateTime current = LocalDateTime.now();
        if (current.isAfter(user.getActivationCodeSentAt().plusMinutes(10))) {
            throw new ExpiredCodeException("La durée du code a expiré");
        }
        user.setActivationCode(null);
        user.setActivationCodeSentAt(null);
        return  userRepository.saveAndFlush(user);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmailAndActivationCodeIsNull(username)
                .orElseThrow(() -> new UsernameNotFoundException("Fire in the hole"));
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                user.getAuthorities()

        );

    }


//    private Collection<? extends GrantedAuthority> userGrantedAuthority(String jsonRole) {
//        List<GrantedAuthority> authorities = new ArrayList<>();
//        List<String> roles = Collections.singletonList(jsonRole);
//        roles.forEach(r -> authorities.add(new SimpleGrantedAuthority( r == null ? "toto" : r )));
//        return authorities;
//    }


}
