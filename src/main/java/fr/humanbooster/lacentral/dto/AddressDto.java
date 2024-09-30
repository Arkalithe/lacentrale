package fr.humanbooster.lacentral.dto;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import fr.humanbooster.lacentral.jsonviews.AddressJsonview;



@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonView(AddressJsonview.addressCommonView.class)
public class AddressDto {

    @NotBlank
    private String streetNumber;

    @NotBlank
    private String streetName;

    @NotBlank
    private String zipCode;

    @NotBlank
    private String city;

    @NotNull
    private Float longitude;

    @NotNull
    private Float latitude;

}
