package MobileServer.models;

import MobileServer.models.enums.AvatarElementType;
import lombok.Data;

@Data
public class AvatarElementRequest {
    private AvatarElementType type;

    private int number;

    private String color;
}
