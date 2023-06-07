package ir.ac.kntu.admins.managers;

import ir.ac.kntu.store.DataBase;
import ir.ac.kntu.helpers.ConsoleColors;

import static ir.ac.kntu.helpers.TextTypings.*;

public class ManagerUserPage {

    private DataBase dataBase;

    private ManagerUsersOptions managerUsersOption;

    public ManagerUserPage(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public void usersPage(int whichManager) {
        drawingLines();
        System.out.println(ConsoleColors.BLUE_BOLD + "******( MANAGERS USERS PAGE )******" + ConsoleColors.RESET);
        String nextChoose = whereToGo();
        switch (nextChoose) {
            case "1" -> showManagerUsersOptions(whichManager);
            case "2" -> {
                ManagerPage managerPage = new ManagerPage(dataBase);
                managerPage.goToManagerPage(whichManager);
            }
            case "3" -> {
                drawingLines();
                exit();
            }
            default -> {
                incorrect();
                usersPage(whichManager);
            }
        }
    }

    public void showManagerUsersOptions(int whichManager) {
        for (ManagerUsersOptions managerUsersOption1 : ManagerUsersOptions.values()) {
            System.out.print(managerUsersOption1.getValue() + ")");
            System.out.println(managerUsersOption1);
        }
        fromValue(getNumberFromOptions(), whichManager);
    }

    public void fromValue(String value, int whichManager) {
        for (ManagerUsersOptions e : ManagerUsersOptions.values()) {
            if (e.getValue().equals(value)) {
                this.managerUsersOption = e;
                goToOptions(whichManager);
            }
        }
        incorrect();
        showManagerUsersOptions(whichManager);
    }

    public void goToOptions(int whichManager) {
        if (managerUsersOption == ManagerUsersOptions.VIEW_USER_INFORMATION) {
            ViewUserInformation viewUserInformation = new ViewUserInformation(dataBase);
            viewUserInformation.viewUsersInformation(whichManager);
        } else if (managerUsersOption == ManagerUsersOptions.ADD_A_USER) {
            AddAUser addAUser = new AddAUser(dataBase);
            addAUser.toAddUsers(whichManager);
        } else if (managerUsersOption == ManagerUsersOptions.CHANGE_A_USER_INFORMATION) {
            ChangeUserInformation changeUserInformation = new ChangeUserInformation(dataBase);
            changeUserInformation.changeAUserInformation(whichManager);
        } else if (managerUsersOption == ManagerUsersOptions.REMOVE_A_USER) {
            RemoveAUser removeAUser = new RemoveAUser(dataBase);
            removeAUser.toRemoveUsers(whichManager);
        } else {
            ViewTheMostActiveUsers viewTheMostActiveUsers = new ViewTheMostActiveUsers(dataBase);
            viewTheMostActiveUsers.showActiveUsers(whichManager);
        }
    }
}