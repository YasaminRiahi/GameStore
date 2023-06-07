package ir.ac.kntu.userSearcher;

public enum SearchOptions {

    SEARCH_BY_USERNAME("1"),
    SEARCH_BY_EMAIL("2"),
    SEARCH_BY_PHONE_NUMBER("3");

    private String value;

    SearchOptions(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}