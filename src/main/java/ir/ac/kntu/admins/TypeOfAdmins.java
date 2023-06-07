package ir.ac.kntu.admins;

public enum TypeOfAdmins {

    Managers("1"),
    Developers("2"),
    Sellers("3");

    private String value;

    TypeOfAdmins(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}