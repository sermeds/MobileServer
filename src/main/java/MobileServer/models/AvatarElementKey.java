package MobileServer.models;

import MobileServer.models.enums.AvatarElementType;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Data
@Embeddable
public class AvatarElementKey implements Serializable {

    public AvatarElementKey() {
    }

    public AvatarElementKey(int avatarId, AvatarElementType type) {
        this.avatarId = avatarId;
        this.type = type;
    }

    @Column(name = "avatar_id")
    private int avatarId;

    @Column(name = "type")
    private AvatarElementType type;
}
