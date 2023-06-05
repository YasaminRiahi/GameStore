package ir.ac.kntu.admins.managers;

import ir.ac.kntu.helpers.ConsoleColors;
import ir.ac.kntu.store.DataBase;
import ir.ac.kntu.userSearcher.UserSearcher;

import static ir.ac.kntu.helpers.TextTypings.*;
import static ir.ac.kntu.helpers.TextTypings.incorrect;

public class RemoveAUser {

    private DataBase dataBase;

    public RemoveAUser(DataBase dataBase){
        this.dataBase = dataBase;
    }

    public void toRemoveUsers(int whichManager) {
        drawingLines();
        System.out.println(ConsoleColors.BLUE_BOLD + "******( REMOVE USERS )******" + ConsoleColors.RESET);
        String nextChoose = whereToGo();
        if (nextChoose.equals("1")) {
            UserSearcher userSearcher = new UserSearcher(dataBase);
            userSearcher.howToSearch("REMOVE_A_USER",whichManager);
        } else if (nextChoose.equals("2")) {
            ManagerUserPage managerUserPage = new ManagerUserPage(dataBase);
            managerUserPage.usersPage(whichManager);
        } else if (nextChoose.equals("3")) {
            drawingLines();
            exit();
        } else {
            incorrect();
            toRemoveUsers(whichManager);
        }
    }
}