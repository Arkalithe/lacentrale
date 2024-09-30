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
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView( BrandJsonview.brandCommonView.class)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    @JsonView({ListingJsonview.listingShowView.class, ModelJsonview.modelCommonView.class, BrandJsonview.brandCommonView.class, UserJsonview.userShowView.class, FavoriteJsonview.favoriteShowView.class})
    private String name;

    @JsonView(BrandJsonview.brandShowView.class)
    @OneToMany(mappedBy = "brand")
    private List<Model> models = new ArrayList<>();

    @JsonView(BrandJsonview.brandListViewSimple.class)
    public Integer modelCount() {
        return models != null ? models.size() : 0;
    }

}