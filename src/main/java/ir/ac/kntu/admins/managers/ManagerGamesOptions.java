package ir.ac.kntu.admins.managers;

public enum ManagerGamesOptions {

    ADD_A_GAME("1"),
    CHANGE_GAMES_INFORMATION("2"),
    REMOVE_A_GAME("3"),
    GAME_CRASH_REPORT("4");

    String value;

    ManagerGamesOptions(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}