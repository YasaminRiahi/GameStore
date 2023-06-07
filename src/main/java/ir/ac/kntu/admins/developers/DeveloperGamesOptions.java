package ir.ac.kntu.admins.developers;

public enum DeveloperGamesOptions {

    ADD_A_GAME("1"),
    CHANGE_GAMES_INFORMATION("2"),
    REMOVE_A_GAME("3");

    String value;

    DeveloperGamesOptions(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}