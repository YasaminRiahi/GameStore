package ir.ac.kntu.store;

public enum TypeOfUsers {

    ADMIN("1"),
    REGULAR_USERS("2");

    private String value;

    TypeOfUsers(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}