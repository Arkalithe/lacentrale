package fr.humanbooster.lacentral.dto;

import com.fasterxml.jackson.annotation.JsonView;
import fr.humanbooster.lacentral.entity.*;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import fr.humanbooster.lacentral.jsonviews.ListingJsonview;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonView(ListingJsonview.listingCommonView.class)
public class ListingDto {

    @NotNull
    private Long price;

    @NotNull
    private Long mileage;

    @NotNull
    private LocalDateTime producedAt;

    @NotNull
    private LocalDateTime createdAt;

    @NotBlank
    private String description;

    @NotBlank
    private String title;

    private Long model_id;

    private Long fuel_id;

   private Long address_id;
//
//    private List<Image> images = new ArrayList<>();
//
    private String owner_id ;

}
