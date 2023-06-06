package ir.ac.kntu.admins.commonInAccessories;

public enum AccessoriesPageOptions {

    ADD_ACCESSORIES("1"),
    CHANGE_ACCESSORIES("2"),
    REMOVE_ACCESSORIES("3");

    private String value;

    AccessoriesPageOptions(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
