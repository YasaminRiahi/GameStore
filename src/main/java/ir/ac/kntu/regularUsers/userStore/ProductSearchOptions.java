package ir.ac.kntu.regularUsers.userStore;

public enum ProductSearchOptions {
    SEARCH_IN_ALL_PRODUCTS("1"),
    SEARCH_IN_ALL_ACCESSORIES("2"),
    SEARCH_IN_GAMES("3"),
    SEARCH_IN_GAMING_MONITORS("4"),
    SEARCH_IN_GAME_PADS("5");

    private String value;

    ProductSearchOptions(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
