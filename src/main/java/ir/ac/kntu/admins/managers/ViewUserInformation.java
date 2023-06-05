package ir.ac.kntu.admins.managers;

import ir.ac.kntu.store.DataBase;
import ir.ac.kntu.admins.AdminPage;
import ir.ac.kntu.admins.AdminProfile;
import ir.ac.kntu.helpers.ConsoleColors;
import ir.ac.kntu.userSearcher.UserSearcher;

import static ir.ac.kntu.helpers.TextTypings.*;

public class ViewUserInformation {

    private DataBase dataBase;

    public ViewUserInformation(DataBase dataBase){
        this.dataBase = dataBase;
    }

    public void viewUsersInformation(int whichManager) {
        drawingLines();
        System.out.println(ConsoleColors.BLUE_BOLD + "******( VIEW USERS INFORMATION )******" + ConsoleColors.RESET);
        String nextChoose = whereToGo();
        if (nextChoose.equals("1")) {
            UserSearcher userSearcher = new UserSearcher(dataBase);
            userSearcher.howToSearch("VIEW_USER_INFORMATION",whichManager);
        } else if (nextChoose.equals("2")) {
            ManagerUserPage managerUserPage = new ManagerUserPage(dataBase);
            managerUserPage.usersPage(whichManager);
        } else if (nextChoose.equals("3")) {
            drawingLines();
            exit();
        } else {
            incorrect();
            viewUsersInformation(whichManager);
        }
    }

}
