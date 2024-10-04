package fr.humanbooster.lacentral.entity;

import com.fasterxml.jackson.annotation.JsonView;
import fr.humanbooster.lacentral.jsonviews.FavoriteJsonview;
import fr.humanbooster.lacentral.jsonviews.ListingJsonview;
import fr.humanbooster.lacentral.jsonviews.UserJsonview;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import org.json.JSONArray;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JsonView(UserJsonview.userCommonView.class)
    private String uuid;

    @NotBlank
    @Column(nullable = false)
    @JsonView({UserJsonview.userCommonView.class, ListingJsonview.listingShowView.class})
    private String email;

    @JsonView(UserJsonview.userShowView.class)
    private String firstName;

    @JsonView(UserJsonview.userShowView.class)
    private String lastName;

    @NotBlank
    @Column(nullable = false)
    private String password;

    @NotBlank
    @Column(nullable = false)
    @JsonView(UserJsonview.userShowView.class)
    private String phone;

    @JsonView(UserJsonview.userShowView.class)
    private String siret;

    @JsonView(UserJsonview.userShowView.class)
    private String photo;

    private String activationCode;

    private LocalDateTime activationCodeSentAt;

    @NotNull
    @Column(nullable = false)
    @JsonView(UserJsonview.userShowView.class)
    private LocalDate birthAt;

    @NotNull
    @Column(nullable = false)
    @JsonView(UserJsonview.userCommonView.class)
    private LocalDateTime createdAt;

    @NotBlank
    @Column(nullable = false)
    private String roles;

    @OneToMany(mappedBy = "owner")
    private List<Listing> listing = new ArrayList<>();


    @OneToOne(mappedBy = "user_uuid")
    @JsonView(UserJsonview.userShowView.class)
    private Address address;

    @OneToMany(mappedBy = "user")
    @JsonView({UserJsonview.userShowView.class, FavoriteJsonview.favoriteShowView.class})
    private List<Favorite> favorite = new ArrayList<>();

    @JsonView(UserJsonview.userShowView.class)
    private boolean isActive() {
        return this.activationCode == null;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        JSONArray roles = new JSONArray(this.roles);
        roles.forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.toString()));
        });
        return authorities;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isEnabled() {
        return isActive();
    }
}