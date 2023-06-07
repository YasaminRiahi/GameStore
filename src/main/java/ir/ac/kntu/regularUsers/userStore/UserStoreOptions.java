package ir.ac.kntu.regularUsers.userStore;

import java.util.PrimitiveIterator;

public enum UserStoreOptions {

    LIST_OF_PRODUCTS("1"),
    SEARCH_IN_PRODUCTS("2"),
    SHOW_PRODUCTS_BY_FILTERING_COST("3"),
    SHOW_BEST_SELLING_PRODUCTS("4"),
    CALCULATE_COST_OF_A_LIST("5");

    private String value;

    UserStoreOptions(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
