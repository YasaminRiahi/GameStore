package ir.ac.kntu;

import ir.ac.kntu.menu.MainMenu;
import ir.ac.kntu.store.DataBase;

public class Main {

    public static void main(String[] args) {
        DataBase dataBase = new DataBase();
        dataBase.addEveryThing();
        MainMenu mainMenu = new MainMenu(dataBase);
        mainMenu.showMenu();
    }
}