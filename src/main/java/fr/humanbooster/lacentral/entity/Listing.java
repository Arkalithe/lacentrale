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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class Listing {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JsonView(ListingJsonview.listingCommonView.class)
    private String uuid;

    @NotNull
    @Column(nullable = false)
    @JsonView({ListingJsonview.listingCommonView.class, UserJsonview.userShowView.class, FavoriteJsonview.favoriteShowView.class})
    private Long price;

    @NotNull
    @Column(nullable = false)
    @JsonView({ListingJsonview.listingCommonView.class, UserJsonview.userShowView.class, FavoriteJsonview.favoriteShowView.class})
    private Long mileage;

    @NotNull
    @Column(nullable = false)
    @JsonView({ListingJsonview.listingCommonView.class, UserJsonview.userShowView.class, FavoriteJsonview.favoriteShowView.class})
    private LocalDateTime producedAt;

    @NotNull
    @Column(nullable = false)
    @JsonView(ListingJsonview.listingShowView.class)
    private LocalDateTime createdAt;

    @NotBlank
    @Column(nullable = false, columnDefinition = "TEXT")
    @JsonView({ListingJsonview.listingShowView.class, UserJsonview.userShowView.class, FavoriteJsonview.favoriteShowView.class})
    private String description;

    @NotBlank
    @Column(nullable = false)
    @JsonView({ListingJsonview.listingCommonView.class, UserJsonview.userShowView.class, FavoriteJsonview.favoriteShowView.class})
    private String title;

    @ManyToOne
    @JoinColumn(nullable = false)
    @JsonView({ListingJsonview.listingShowView.class, UserJsonview.userShowView.class, FavoriteJsonview.favoriteShowView.class})
    private Model model;

    @ManyToOne
    @JoinColumn(nullable = false)
    @JsonView(ListingJsonview.listingShowView.class)
    private Fuel fuel;

    @ManyToOne
    @JsonView(ListingJsonview.listingShowView.class)
    private Address address;

    @OneToMany(mappedBy = "listing")
    @JsonView(ListingJsonview.listingCommonView.class)
    private List<Image> images = new ArrayList<>();

    @ManyToOne
    @JoinColumn(nullable = false)
    @JsonView(ListingJsonview.listingShowView.class)
    private User owner ;
}