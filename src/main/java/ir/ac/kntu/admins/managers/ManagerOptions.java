package ir.ac.kntu.admins.managers;

public enum ManagerOptions {

    PROFILE("1"),
    USERS("2"),
    GAMES("3"),
    ACCESSORIES("4");

    private String value;

    ManagerOptions(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}