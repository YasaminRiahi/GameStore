package ir.ac.kntu.products.accessories.gamePad;

public enum DeviceType {

    XBOX("1"),
    PLAY_STATION("2"),
    PC("3");

    private String value;

    DeviceType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}