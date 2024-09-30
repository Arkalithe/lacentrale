package fr.humanbooster.lacentral.dto;

import com.fasterxml.jackson.annotation.JsonView;
import fr.humanbooster.lacentral.entity.Model;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import fr.humanbooster.lacentral.jsonviews.BrandJsonview;


@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonView(BrandJsonview.brandCommonView.class)
public class BrandDto {

    @NotBlank
    private String name;


}
