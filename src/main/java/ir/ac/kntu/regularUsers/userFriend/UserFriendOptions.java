package ir.ac.kntu.regularUsers.userFriend;

public enum UserFriendOptions {

    LIST_OF_FRIENDS("1"),
    SEARCH_FRIENDS("2"),
    FIND_FRIENDS("3"),
    REQUESTS("4");

    private String value;

    UserFriendOptions(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}