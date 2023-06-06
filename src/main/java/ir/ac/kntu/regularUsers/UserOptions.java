package ir.ac.kntu.regularUsers;

public enum UserOptions {
    PROFILE("1"),
    STORE("2"),
    LIBRARY("3"),
    FRIENDS("4");

    private String value;

    UserOptions(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
