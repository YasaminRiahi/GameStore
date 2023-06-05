package ir.ac.kntu.userSearcher;

import ir.ac.kntu.admins.managers.ManagerUsersOption;

import static ir.ac.kntu.helpers.TextTypings.getNumberFromOptions;
import static ir.ac.kntu.helpers.TextTypings.incorrect;

public enum SearchOption {

    SEARCH_BY_USERNAME("1"),
    SEARCH_BY_EMAIL("2"),
    SEARCH_BY_PHONE_NUMBER("3");

    private String value;

    SearchOption(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
