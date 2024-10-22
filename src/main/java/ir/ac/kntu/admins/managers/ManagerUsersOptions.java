package ir.ac.kntu.admins.managers;

public enum ManagerUsersOptions {

    VIEW_USER_INFORMATION("1"),
    ADD_A_USER("2"),
    CHANGE_A_USER_INFORMATION("3"),
    REMOVE_A_USER("4"),

    VIEW_THE_MOST_ACTIVE_USERS("5");

    private final String value;

    ManagerUsersOptions(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}