package ir.ac.kntu.admins.commonInGames;

public enum EditingGameOptions {

    BY_LIST_OF_GAMES("1"),
    BY_SEARCHING_NAME("2");

    String value;

    EditingGameOptions(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
