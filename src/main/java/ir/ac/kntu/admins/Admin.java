package ir.ac.kntu.admins;

import ir.ac.kntu.store.User;

public class Admin extends User {

    private TypeOfAdmins typeOfAdmins;

    public Admin(String userName, String password, String phoneNumber, String email) {
        super(userName, password, phoneNumber, email);
    }

}
