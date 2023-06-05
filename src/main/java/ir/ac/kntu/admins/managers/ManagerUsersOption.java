package ir.ac.kntu.admins.managers;

public enum ManagerUsersOption {

    VIEW_USER_INFORMATION("1"),
    ADD_A_USER("2"),
    CHANGE_A_USER_INFORMATION("3"),
    REMOVE_A_USER("4");

    private String value;

    ManagerUsersOption(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
