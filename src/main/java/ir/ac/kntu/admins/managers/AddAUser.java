package ir.ac.kntu.admins.managers;

import ir.ac.kntu.helpers.ConsoleColors;
import ir.ac.kntu.products.games.Games;
import ir.ac.kntu.regularUsers.RegularUser;
import ir.ac.kntu.store.DataBase;
import ir.ac.kntu.userSearcher.UserSearcher;

import java.util.ArrayList;

import static ir.ac.kntu.helpers.Scan.scanString;
import static ir.ac.kntu.helpers.Scan.scanUsers;
import static ir.ac.kntu.helpers.TextTypings.*;
import static ir.ac.kntu.helpers.TextTypings.incorrect;

public class AddAUser {

    private DataBase dataBase;

    public AddAUser(DataBase dataBase){
        this.dataBase = dataBase;
    }

    public void toAddUsers(int whichManager) {
        drawingLines();
        System.out.println(ConsoleColors.BLUE_BOLD + "******( ADD A USER )******" + ConsoleColors.RESET);
        String nextChoose = whereToGo();
        if (nextChoose.equals("1")) {
            addUsers(whichManager);
        } else if (nextChoose.equals("2")) {
            ManagerUserPage managerUserPage = new ManagerUserPage(dataBase);
            managerUserPage.usersPage(whichManager);
        } else if (nextChoose.equals("3")) {
            drawingLines();
            exit();
        } else {
            incorrect();
            toAddUsers(whichManager);
        }
    }

    public void addUsers(int whichManager) {
        drawingLines();
        System.out.println(ConsoleColors.BLUE_BOLD + "******( ADD USERS )******" + ConsoleColors.RESET);
        String nextChoose = whereToGo();
        if (nextChoose.equals("1")) {
            RegularUser newUser = new RegularUser();
            newUser = scanUsers(dataBase);
            dataBase.getRegularUsers().add(newUser);
            System.out.println("User added successfully!");
            ManagerUserPage managerUserPage = new ManagerUserPage(dataBase);
            managerUserPage.usersPage(whichManager);
        } else if (nextChoose.equals("2")) {
            ManagerUserPage managerUserPage = new ManagerUserPage(dataBase);
            managerUserPage.usersPage(whichManager);
        } else if (nextChoose.equals("3")) {
            System.out.println("Finish!");
            drawingLines();
            exit();
        } else {
            incorrect();
            addUsers(whichManager);
        }
    }

}
