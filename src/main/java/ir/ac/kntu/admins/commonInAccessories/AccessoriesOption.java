package ir.ac.kntu.admins.commonInAccessories;

public enum AccessoriesOption {

    MONITOR_GAMING("1"),
    GAME_PAD("2");

    private String value;

    AccessoriesOption(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
