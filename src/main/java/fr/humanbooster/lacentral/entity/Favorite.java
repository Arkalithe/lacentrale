package fr.humanbooster.lacentral.entity;

import com.fasterxml.jackson.annotation.JsonView;
import fr.humanbooster.lacentral.entity.embededid.UserListingId;
import fr.humanbooster.lacentral.jsonviews.FavoriteJsonview;
import fr.humanbooster.lacentral.jsonviews.UserJsonview;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class Favorite {

    @EmbeddedId
    @JsonView( FavoriteJsonview.favoriteShowView.class)
    private UserListingId id;

    @NotNull
    @Column(nullable = false)
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(nullable = false, name = "user_uuid", updatable = false, insertable = false)
    private User user;

    @ManyToOne
    @JoinColumn(nullable = false, name = "listing_uuid", updatable = false, insertable = false)
    @JsonView({UserJsonview.userShowView.class, FavoriteJsonview.favoriteShowView.class})
    private Listing listing;
}