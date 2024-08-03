package ir.ac.kntu;

import ir.ac.kntu.menu.MainMenu;
import ir.ac.kntu.store.DaoWriter;
import ir.ac.kntu.store.DataBase;

public class  Main {

    public static void main(String[] args) {
        DataBase dataBase = new DataBase();
        dataBase.addEveryThing();
        DaoWriter.writeData(dataBase);
//        DataBase dataBase = DaoWriter.read();
        MainMenu mainMenu = new MainMenu(dataBase);
        mainMenu.showMenu();
    }
}