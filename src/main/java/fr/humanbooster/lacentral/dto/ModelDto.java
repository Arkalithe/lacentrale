package fr.humanbooster.lacentral.dto;

import com.fasterxml.jackson.annotation.JsonView;
import fr.humanbooster.lacentral.entity.Brand;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import fr.humanbooster.lacentral.jsonviews.ModelJsonview;


@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonView(ModelJsonview.modelCommonView.class)
public class ModelDto {

    @NotBlank
    private String name;

    private Long brand_id;
}
