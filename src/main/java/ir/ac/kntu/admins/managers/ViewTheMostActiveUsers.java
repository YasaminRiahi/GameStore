package ir.ac.kntu.admins.managers;

import ir.ac.kntu.helpers.ConsoleColors;
import ir.ac.kntu.regularUsers.RegularUser;
import ir.ac.kntu.store.DataBase;

import static ir.ac.kntu.helpers.TextTypings.*;
import static ir.ac.kntu.helpers.TextTypings.incorrect;

public class ViewTheMostActiveUsers {

    private DataBase dataBase;


    public ViewTheMostActiveUsers(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public void showActiveUsers(int whichUser) {
        drawingLines();
        System.out.println(ConsoleColors.BLUE_BOLD + "******( ADD USERS )******" + ConsoleColors.RESET);
        String nextChoose = whereToGo();
        switch (nextChoose) {
            case "1" -> {
                dataBase.getRegularUsers().sort(RegularUser::compareTo);
                for (int i = 0; i < dataBase.getRegularUsers().size(); i++) {
                    System.out.println(i + 1 + ")" + dataBase.getRegularUsers().get(i).getUserName() + " (score : " +
                            dataBase.getRegularUsers().get(i).getScore() + ") ");
                }
                ManagerUserPage managerUserPage = new ManagerUserPage(dataBase);
                managerUserPage.usersPage(whichUser);
            }
            case "2" -> {
                ManagerUserPage managerUserPage = new ManagerUserPage(dataBase);
                managerUserPage.usersPage(whichUser);
            }
            case "3" -> {
                drawingLines();
                exit();
            }
            default -> {
                incorrect();
                showActiveUsers(whichUser);
            }
        }
    }
}