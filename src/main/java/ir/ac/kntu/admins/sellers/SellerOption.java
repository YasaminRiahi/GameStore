package ir.ac.kntu.admins.sellers;

public enum SellerOption {

    PROFILE("1"),
    ACCESSORIES("2");


    private String value;

    SellerOption(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
