package fr.humanbooster.lacentral.dto;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import fr.humanbooster.lacentral.jsonviews.FuelJsonview;


@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonView(FuelJsonview.fuelCommonView.class)
public class FuelDto {

    @NotBlank
    private String type;

    @NotBlank
    private String logo;
}
