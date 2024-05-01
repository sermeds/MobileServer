package MobileServer.models.enums;

public enum AvatarElementType {
    HAT("шляпа"), HAIR("волосы"), FACE("лицо"), EYES("глаза"), MOUTH("рот"), CLOTHES("одежда");

    private String value;

    AvatarElementType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
