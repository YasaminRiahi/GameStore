package ir.ac.kntu.admins.developers;

public enum DeveloperOptions {

    PROFILE("1"),
    GAMES("2"),
    CHECK_INBOX("3"),
    SCHEDULED_EVENTS("4"),
    VIEW_FEEDBACK("5"),
    ADD_DEVELOPERS("6");

    private String value;

    DeveloperOptions(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}