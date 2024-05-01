package MobileServer.models.enums;

public enum Complexity {
    EASY ("легкий"), MEDIUM("средний"), HARD("тяжелый");

    private final String value;

    Complexity(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
