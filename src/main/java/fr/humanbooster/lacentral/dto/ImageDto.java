package fr.humanbooster.lacentral.dto;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import fr.humanbooster.lacentral.jsonviews.ImageJsonview;


@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonView(ImageJsonview.imageCommonView.class)
public class ImageDto {

    @NotBlank
    private String Path;

}
