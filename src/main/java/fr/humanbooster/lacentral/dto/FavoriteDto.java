package fr.humanbooster.lacentral.dto;

import com.fasterxml.jackson.annotation.JsonView;
import fr.humanbooster.lacentral.entity.Listing;
import fr.humanbooster.lacentral.entity.User;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import fr.humanbooster.lacentral.jsonviews.FavoriteJsonview;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonView(FavoriteJsonview.favoriteCommonView.class)
public class FavoriteDto {

    private String user_uuid;

    private String listing_uuid;

}
