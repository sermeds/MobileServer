package MobileServer.models;

import MobileServer.models.enums.AvatarElementType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@Table(name = "avatar_elements")
@EqualsAndHashCode(of = {"id"})
public class AvatarElement {
    @EmbeddedId
    @Column(name = "id")
    private AvatarElementKey id;

    @Column(name = "number")
    private int number;

    @Column(name = "color")
    private String color;
}
