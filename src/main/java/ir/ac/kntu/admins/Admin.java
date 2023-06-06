package ir.ac.kntu.admins;

import ir.ac.kntu.products.games.Games;
import ir.ac.kntu.store.User;

import java.util.ArrayList;

public class Admin extends User {

    private TypeOfAdmins typeOfAdmins;

    private ArrayList<Games> developerGame;

    public Admin(String userName, String password, String phoneNumber, String email) {
        super(userName, password, phoneNumber, email);
        this.developerGame = new ArrayList<>();
    }

    public ArrayList<Games> getDeveloperGame() {
        return developerGame;
    }

    public void setDeveloperGame(ArrayList<Games> developerGame) {
        this.developerGame = developerGame;
    }
}
