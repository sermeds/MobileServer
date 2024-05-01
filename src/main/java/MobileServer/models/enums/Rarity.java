package MobileServer.models.enums;

public enum Rarity {
    COMMON("обычная"), RARE("редкая"), EPIC("эпическая"), LEGENDARY("легендарная"), SECRET("секретная");

    private final String value;

    Rarity(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
