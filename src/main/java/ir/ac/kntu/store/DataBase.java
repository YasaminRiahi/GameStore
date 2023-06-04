package ir.ac.kntu.store;

import ir.ac.kntu.admins.Admin;
import ir.ac.kntu.products.Games;
import ir.ac.kntu.regularUsers.RegularUser;

import java.util.ArrayList;

public class DataBase {

    private ArrayList<Games> games;

    private ArrayList<Admin> managers;

    private ArrayList<Admin> developers;

    private ArrayList<Admin> sellers;

    private ArrayList<RegularUser> regularUsers;


    public ArrayList<Games> getGames() {
        return games;
    }

    public void setGames(ArrayList<Games> games) {
        this.games = games;
    }

    public ArrayList<Admin> getManagers() {
        return managers;
    }

    public void setManagers(ArrayList<Admin> managers) {
        this.managers = managers;
    }

    public void addManagers(Admin admin) {
        managers.add(admin);
    }

    public ArrayList<Admin> getDevelopers() {
        return developers;
    }

    public void setDevelopers(ArrayList<Admin> developers) {
        this.developers = developers;
    }

    public void addDevelopers(Admin admin) {
        developers.add(admin);
    }

    public ArrayList<Admin> getSellers() {
        return sellers;
    }

    public void setSellers(ArrayList<Admin> sellers) {
        this.sellers = sellers;
    }

    public void addSellers(Admin admin) {
        sellers.add(admin);
    }

    public ArrayList<RegularUser> getRegularUsers() {
        return regularUsers;
    }

    public void setRegularUsers(ArrayList<RegularUser> regularUsers) {
        this.regularUsers = regularUsers;
    }

    public DataBase() {
        this.managers = new ArrayList<>();
        this.developers = new ArrayList<>();
        this.sellers = new ArrayList<>();
        this.games = new ArrayList<>();
        this.regularUsers = new ArrayList<>();
    }

    public void addEveryThing() {
        addDefaultAdmins();
    }

    public void addDefaultAdmins() {
        Admin admin1 = new Admin("YasaminRiahi", "Y1274183871r", "091404369998", "ysminriahi@gmail.com");
        Admin admin2 = new Admin("BaharOrak", "Bahar821017", "09160340853", "baharorak@gmail.com");
        Admin admin3 = new Admin("RaziehRiahi", "Razii78Riahi", "09167594761", "raziriahi@gmail.com");
        Admin admin4 = new Admin("ShakibaMirzadeh", "Shakiba1352", "09132135049", "ShMirzadeh8962");
        addManagers(admin1);
        addManagers(admin2);
        addDevelopers(admin3);
        addDevelopers(admin3);
        addSellers(admin4);
    }

}
