package ir.ac.kntu.admins;

import ir.ac.kntu.helpers.ConsoleColors;

import java.util.ArrayList;

import static ir.ac.kntu.helpers.Scanner.*;
import static ir.ac.kntu.helpers.TextTypings.drawingLines;
import static ir.ac.kntu.helpers.TextTypings.noAccount;

public class UsernameAndPasswordChecker {

    public UsernameAndPasswordChecker() {

    }

    public int checkUsernameAndPassword(ArrayList<Admin> admins) {
        drawingLines();
        System.out.println(ConsoleColors.BLUE_BOLD + "******( USERNAME/PASSWORD )******" + ConsoleColors.RESET);
        System.out.println("Enter your username:");
        String username = scanString();
        System.out.println("Enter your password:");
        String password = scanString();
        return toFind(admins, username, password);
    }

    public int toFind(ArrayList<Admin> admins, String username, String password) {
        for (int i = 0; i < admins.size(); i++) {
            if (admins.get(i).getUserName().equals(username) && admins.get(i).getPassword().equals(password)) {
                System.out.println("You're logged in successfully!!");
                return i;
            }
        }
        noAccount();
        return -1;
    }
}