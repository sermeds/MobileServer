package MobileServer.models.enums;

public enum Category {
    COMMON("обычное"), RARE("редкое"), EPIC("эпическое"), LEGENDARY("легендарное"), SECRET("секретное");

    private final String value;

    Category(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
