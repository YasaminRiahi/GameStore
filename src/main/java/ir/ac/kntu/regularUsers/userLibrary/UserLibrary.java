package ir.ac.kntu.regularUsers.userLibrary;

import ir.ac.kntu.helpers.ConsoleColors;
import ir.ac.kntu.regularUsers.RegularUserPage;
import ir.ac.kntu.regularUsers.Stopwatch1;
import ir.ac.kntu.regularUsers.userStore.*;
import ir.ac.kntu.store.DataBase;

import static ir.ac.kntu.helpers.TextTypings.*;
import static ir.ac.kntu.helpers.TextTypings.incorrect;

public class UserLibrary {

    private DataBase dataBase;

    private UserLibraryOptions userLibraryOptions;

    public UserLibrary(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public void userLibrary(int userIndex, Stopwatch1 stopwatch1) {
        drawingLines();
        System.out.println(ConsoleColors.BLUE_BOLD + "******( LIBRARY )******" + ConsoleColors.RESET);
        String nextChoose = whereToGo();
        if (nextChoose.equals("1")) {
            showUserLibraryOptions(userIndex, stopwatch1);
        } else if (nextChoose.equals("2")) {
            RegularUserPage regularUserPage = new RegularUserPage(dataBase);
            regularUserPage.userAccess(userIndex, stopwatch1);
        } else if (nextChoose.equals("3")) {
            drawingLines();
            exit();
        } else {
            incorrect();
            userLibrary(userIndex, stopwatch1);
        }
    }

    public void showUserLibraryOptions(int whichUser, Stopwatch1 stopwatch1) {
        for (UserLibraryOptions userLibraryOptions1 : UserLibraryOptions.values()) {
            System.out.print(userLibraryOptions1.getValue() + ")");
            System.out.println(userLibraryOptions1);
        }
        fromValue(getNumberFromOptions(), whichUser, stopwatch1);
    }

    public void fromValue(String value, int whichUser, Stopwatch1 stopwatch1) {
        for (UserLibraryOptions e : UserLibraryOptions.values()) {
            if (e.getValue().equals(value)) {
                this.userLibraryOptions = e;
                goToOptions(whichUser, stopwatch1);
            }
        }
        incorrect();
        showUserLibraryOptions(whichUser, stopwatch1);
    }

    public void goToOptions(int whichUser, Stopwatch1 stopwatch1) {
        if (userLibraryOptions == UserLibraryOptions.LIST_OF_YOUR_PRODUCTS) {
            ListOfUserProducts listOfUserProducts = new ListOfUserProducts(dataBase);
            listOfUserProducts.listOfProducts(whichUser,stopwatch1);
        } else if (userLibraryOptions == UserLibraryOptions.SEARCH_IN_YOUR_PRODUCTS) {
            ;
        } else {
            ;
        }
    }
}
