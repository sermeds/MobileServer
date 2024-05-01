package MobileServer.models.enums;

public enum Sex {
    MALE("мужской"), FEMALE("женский");

    private final String value;

    Sex(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
