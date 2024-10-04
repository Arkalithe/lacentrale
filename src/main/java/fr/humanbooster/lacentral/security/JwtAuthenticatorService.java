package fr.humanbooster.lacentral.security;

import fr.humanbooster.lacentral.custom_response.JwtResponse;
import fr.humanbooster.lacentral.dto.UserLoginDto;
import fr.humanbooster.lacentral.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class JwtAuthenticatorService {

    private final AuthenticationManager authenticationManager;
    private final UserService userRedditishService;
    private final JwtService jwtService;

    public ResponseEntity<JwtResponse> authenticate(UserLoginDto dto) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword()));

            String token = jwtService.generateToken(dto.getEmail());
            return ResponseEntity.ok(new JwtResponse(token));

        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        //
        // IF you want to use a login based on multiple fields (like email or username)
        // u should get the UserDetails from the impl. of UserDetailsService and use the
        // loadUserByUsername method to get the UserDetails
        //
//        UserDetails userDetails = userRedditishService.loadUserByUsername(dto.getEmail());
//        if (userDetails != null) {
//            authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(
//                            userDetails.getUsername(), dto.getPassword()));
//
//            String token = jwtService.generateToken(userDetails.getUsername());
//            return ResponseEntity.ok(new JwtResponse(token));
//        }
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

}
