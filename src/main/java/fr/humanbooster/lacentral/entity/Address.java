package fr.humanbooster.lacentral.entity;

import com.fasterxml.jackson.annotation.JsonView;
import fr.humanbooster.lacentral.jsonviews.ListingJsonview;
import fr.humanbooster.lacentral.jsonviews.UserJsonview;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    @JsonView({ListingJsonview.listingShowView.class, UserJsonview.userShowView.class})
    private String streetNumber;

    @NotBlank
    @Column(nullable = false)
    @JsonView({ListingJsonview.listingShowView.class, UserJsonview.userShowView.class})
    private String streetName;

    @NotBlank
    @Column(nullable = false)
    @JsonView({ListingJsonview.listingShowView.class, UserJsonview.userShowView.class})
    private String zipCode;

    @NotBlank
    @Column(nullable = false)
    @JsonView({ListingJsonview.listingShowView.class, UserJsonview.userShowView.class})
    private String city;

    @NotNull
    @Column(nullable = false)
    @JsonView({ListingJsonview.listingShowView.class, UserJsonview.userShowView.class})
    private Float longitude;

    @NotNull
    @Column(nullable = false)
    @JsonView({ListingJsonview.listingShowView.class, UserJsonview.userShowView.class})
    private Float latitude;

    @OneToOne
    @JoinColumn(name = "user_uuid")
    private User user_uuid;
}