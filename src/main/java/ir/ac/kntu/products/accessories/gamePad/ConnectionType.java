package ir.ac.kntu.products.accessories.gamePad;

public enum ConnectionType {
    WIRELESS("1"),
    WIRED("2");

    private String value;

    ConnectionType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
