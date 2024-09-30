package fr.humanbooster.lacentral.entity;

import com.fasterxml.jackson.annotation.JsonView;
import fr.humanbooster.lacentral.jsonviews.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({ModelJsonview.modelListViewSimple.class, BrandJsonview.brandShowView.class})
    private Long id;

    @NotBlank
    @Column(nullable = false)
    @JsonView({ListingJsonview.listingShowView.class, ModelJsonview.modelCommonView.class, BrandJsonview.brandShowView.class, UserJsonview.userShowView.class, FavoriteJsonview.favoriteShowView.class})
    private String name;

    @ManyToOne
    @JsonView({ListingJsonview.listingShowView.class, ModelJsonview.modelCommonView.class, UserJsonview.userShowView.class, FavoriteJsonview.favoriteShowView.class})
    private Brand brand;

}