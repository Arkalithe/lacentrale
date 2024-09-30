package fr.humanbooster.lacentral.entity.embededid;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserListingId implements Serializable {

    @Column(name = "user_uuid")
    private String user_uuid;
    @Column(name = "listing_uuid")
    private String listing_uuid;
}
