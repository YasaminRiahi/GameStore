package ir.ac.kntu.menu;

import ir.ac.kntu.admins.AdminPage;
import ir.ac.kntu.helpers.ConsoleColors;
import ir.ac.kntu.store.DataBase;
import ir.ac.kntu.store.TypeOfUsers;

import static ir.ac.kntu.helpers.TextTypings.*;

public class MainMenu {

    private DataBase dataBase;

    private TypeOfUsers typeOfUsers;

    public MainMenu(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public void showMenu() {
        drawingLines();
        System.out.println(ConsoleColors.BLUE_BOLD + "******( MENU )******" + ConsoleColors.RESET);
        for (TypeOfUsers typeOfUsers1 : TypeOfUsers.values()) {
            System.out.print(typeOfUsers1.getValue() + ")");
            System.out.println(typeOfUsers1);
        }
        fromValue(getNumberFromOptions());
    }

    public void fromValue(String value) {
        for (TypeOfUsers e : TypeOfUsers.values()) {
            if (e.getValue().equals(value)) {
                this.typeOfUsers = e;
                goToOptions();
            }
        }
        incorrect();
        showMenu();
    }

    public void goToOptions() {
        if (typeOfUsers == TypeOfUsers.ADMIN) {
            AdminPage adminPage = new AdminPage(dataBase);
            adminPage.goToAdminPage();
        } else {
            ;
        }
    }

}