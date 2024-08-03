package ir.ac.kntu.admins;

import ir.ac.kntu.admins.developers.DeveloperPage;
import ir.ac.kntu.admins.managers.ManagerPage;
import ir.ac.kntu.admins.sellers.SellerPage;
import ir.ac.kntu.helpers.ConsoleColors;
import ir.ac.kntu.menu.MainMenu;
import ir.ac.kntu.store.DataBase;


import static ir.ac.kntu.helpers.TextTypings.*;


public class AdminPage {

    private AdminsType adminsType;

    private DataBase dataBase;

    public AdminPage(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public void goToAdminPage() {
        drawingLines();
        System.out.println(ConsoleColors.BLUE_BOLD + "******( ADMIN MENU )******" + ConsoleColors.RESET);
        String nextChoose = whereToGo();
        switch (nextChoose) {
            case "1" -> showAdminsMenu();
            case "2" -> {
                MainMenu mainMenu = new MainMenu(dataBase);
                mainMenu.showMenu();
            }
            case "3" -> {
                drawingLines();
                exit();
            }
            default -> {
                incorrect();
                goToAdminPage();
            }
        }
    }

    public void showAdminsMenu() {
        for (AdminsType adminsType1 : AdminsType.values()) {
            System.out.print(adminsType1.getValue() + ")");
            System.out.println(adminsType1);
        }
        fromValue(getNumberFromOptions());
    }

    public void fromValue(String value) {
        for (AdminsType e : AdminsType.values()) {
            if (e.getValue().equals(value)) {
                this.adminsType = e;
                goToOptions();
            }
        }
        incorrect();
        showAdminsMenu();
    }

    public void goToOptions() {
        if (adminsType == AdminsType.Managers) {
            UsernameAndPasswordChecker usernameAndPasswordChecker = new UsernameAndPasswordChecker();
            int index = usernameAndPasswordChecker.checkUsernameAndPassword(dataBase.getManagers());
            if (index != -1) {
                ManagerPage managerPage = new ManagerPage(dataBase);
                managerPage.goToManagerPage(index);
            } else {
                goToAdminPage();
            }
        } else if (adminsType == AdminsType.Developers) {
            UsernameAndPasswordChecker usernameAndPasswordChecker = new UsernameAndPasswordChecker();
            int index = usernameAndPasswordChecker.checkUsernameAndPassword(dataBase.getDevelopers());
            if (index != -1) {
                DeveloperPage developerPage = new DeveloperPage(dataBase);
                developerPage.goToDeveloperPage(index);
            } else {
                goToAdminPage();
            }
        } else {
            UsernameAndPasswordChecker usernameAndPasswordChecker = new UsernameAndPasswordChecker();
            int index = usernameAndPasswordChecker.checkUsernameAndPassword(dataBase.getSellers());
            if (index != -1) {
                SellerPage sellerPage = new SellerPage(dataBase);
                sellerPage.goToSellerPage(index);
            } else {
                goToAdminPage();
            }
        }
    }
}