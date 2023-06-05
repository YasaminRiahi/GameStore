package ir.ac.kntu.products.games;

public enum GamesLevel {
    LEVEL_1("1"),
    LEVEL_2("2"),
    LEVEL_3("3"),
    LEVEL_4("4");

    String value;

    GamesLevel(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
