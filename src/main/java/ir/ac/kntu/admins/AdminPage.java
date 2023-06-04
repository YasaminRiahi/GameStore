package ir.ac.kntu.admins;

import ir.ac.kntu.helpers.ConsoleColors;
import ir.ac.kntu.menu.MainMenu;
import ir.ac.kntu.store.DataBase;
import ir.ac.kntu.store.User;

import java.util.ArrayList;

import static ir.ac.kntu.helpers.TextTypings.*;


public class AdminPage implements UsernameAndPasswordChecker {

    private TypeOfAdmins typeOfAdmins;

    private DataBase dataBase;

    public AdminPage(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public void goToAdminPage() {
        drawingLines();
        System.out.println(ConsoleColors.BLUE_BOLD + "******( ADMIN MENU )******" + ConsoleColors.RESET);
        String nextChoose = whereToGo();
        if (nextChoose.equals("1")) {
            showAdminsMenu();
        } else if (nextChoose.equals("2")) {
            MainMenu mainMenu = new MainMenu(dataBase);
            mainMenu.showMenu();
        } else if (nextChoose.equals("3")) {
            drawingLines();
            exit();
        } else {
            incorrect();
            goToAdminPage();
        }
    }

    public void showAdminsMenu() {
        for (TypeOfAdmins typeOfAdmins1 : TypeOfAdmins.values()) {
            System.out.print(typeOfAdmins1.getValue() + ")");
            System.out.println(typeOfAdmins1);
        }
        fromValue(getNumberFromOptions());
    }

    public void fromValue(String value) {
        for (TypeOfAdmins e : TypeOfAdmins.values()) {
            if (e.getValue().equals(value)) {
                this.typeOfAdmins = e;
                goToOptions();
            }
        }
        incorrect();
        showAdminsMenu();
    }

    public void goToOptions() {
        if (typeOfAdmins == TypeOfAdmins.Managers) {
            int index = UsernameAndPasswordChecker.checkUsernameAndPassword(dataBase.getManagers());
            if (index != -1) {
                ManagerPage managerPage = new ManagerPage(dataBase);
                managerPage.goToManagerPage(index);
            } else {
                goToAdminPage();
            }
        } else if (typeOfAdmins == TypeOfAdmins.Developers) {
            int index = UsernameAndPasswordChecker.checkUsernameAndPassword(dataBase.getDevelopers());
            if (index != -1) {
                ;
            } else {
                goToAdminPage();
            }
        } else {
            int index = UsernameAndPasswordChecker.checkUsernameAndPassword(dataBase.getSellers());
            if (index != -1) {
                ;
            } else {
                goToAdminPage();
            }
        }
    }
}
