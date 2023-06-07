package ir.ac.kntu.regularUsers.userLibrary;

public enum UserLibraryOptions {

    LIST_OF_YOUR_PRODUCTS("1"),
    SEARCH_IN_YOUR_PRODUCTS("2"),
    SHOW_YOUR_PRODUCTS_BY_FILTERING_COST("3");

    private String value;

    UserLibraryOptions(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
