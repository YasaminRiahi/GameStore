package ir.ac.kntu.admins.sellers;

public enum SellerOptions {

    PROFILE("1"),
    ACCESSORIES("2"),
    VIEW_REPORT_MASSAGE("3");

    private String value;

    SellerOptions(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}