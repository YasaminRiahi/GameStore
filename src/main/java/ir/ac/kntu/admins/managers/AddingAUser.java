package ir.ac.kntu.admins.managers;

import ir.ac.kntu.helpers.ConsoleColors;
import ir.ac.kntu.regularUsers.RegularUser;
import ir.ac.kntu.store.DaoWriter;
import ir.ac.kntu.store.DataBase;

import static ir.ac.kntu.helpers.Scanner.scanUsers;
import static ir.ac.kntu.helpers.TextTypings.*;
import static ir.ac.kntu.helpers.TextTypings.incorrect;

public class AddingAUser {

    private DataBase dataBase;

    public AddingAUser(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public void toAddUsers(int whichManager) {
        drawingLines();
        System.out.println(ConsoleColors.BLUE_BOLD + "******( ADD USERS )******" + ConsoleColors.RESET);
        String nextChoose = whereToGo();
        switch (nextChoose) {
            case "1" -> {
                RegularUser newUser = scanUsers(dataBase);
                dataBase.getRegularUsers().add(newUser);
                System.out.println("User added successfully!");
                DaoWriter.writeData(dataBase);
                ManagerUserPage managerUserPage = new ManagerUserPage(dataBase);
                managerUserPage.usersPage(whichManager);
            }
            case "2" -> {
                ManagerUserPage managerUserPage = new ManagerUserPage(dataBase);
                managerUserPage.usersPage(whichManager);
            }
            case "3" -> {
                drawingLines();
                exit();
            }
            default -> {
                incorrect();
                toAddUsers(whichManager);
            }
        }
    }
}