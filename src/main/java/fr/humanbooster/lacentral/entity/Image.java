package fr.humanbooster.lacentral.entity;

import com.fasterxml.jackson.annotation.JsonView;
import fr.humanbooster.lacentral.jsonviews.ListingJsonview;
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
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String uuid;

    @NotBlank
    @Column(nullable = false, columnDefinition = "TEXT")
    @JsonView(ListingJsonview.listingCommonView.class)
    private String path;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Listing listing;
}