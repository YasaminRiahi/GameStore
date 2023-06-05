package ir.ac.kntu.userSearcher;

import ir.ac.kntu.admins.managers.ManagerUsersOption;
import ir.ac.kntu.admins.managers.ViewUserInformation;
import ir.ac.kntu.store.DataBase;

import static ir.ac.kntu.helpers.TextTypings.getNumberFromOptions;
import static ir.ac.kntu.helpers.TextTypings.incorrect;

import ir.ac.kntu.helpers.ConsoleColors;
import ir.ac.kntu.userSearcher.UserSearcher;

import static ir.ac.kntu.helpers.TextTypings.*;

public class UserSearcher {

    private DataBase dataBase;

    private SearchOption searchOption;

    public UserSearcher(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public void howToSearch(String goBack, int whichUser) {
        drawingLines();
        System.out.println(ConsoleColors.BLUE_BOLD + "******( SHOW OPTIONS TO SEARCH )******" + ConsoleColors.RESET);
        String nextChoose = whereToGo();
        if (nextChoose.equals("1")) {
            showSearchOptions(whichUser);
        } else if (nextChoose.equals("2")) {
            if (goBack.equals("VIEW_USER_INFORMATION")) {
                ViewUserInformation viewUserInformation = new ViewUserInformation(dataBase);
                viewUserInformation.viewUsersInformation(whichUser);
            } else if (goBack.equals("")) {
                ;
            } else {
                ;
            }
        } else if (nextChoose.equals("3")) {
            drawingLines();
            exit();
        } else {
            incorrect();
            howToSearch(goBack, whichUser);
        }
    }


    public void showSearchOptions(int whichUser) {
        for (SearchOption searchOption1 : SearchOption.values()) {
            System.out.print(searchOption1.getValue() + ")");
            System.out.println(searchOption1);
        }
        fromValue(getNumberFromOptions(), whichUser);
    }

    public void fromValue(String value, int whichUser) {
        for (SearchOption e : SearchOption.values()) {
            if (e.getValue().equals(value)) {
                this.searchOption = e;
                goToOptions(whichUser);
            }
        }
        incorrect();
        showSearchOptions(whichUser);
    }

    public void goToOptions(int whichUser) {
        if (searchOption == SearchOption.SEARCH_BY_USERNAME) {
            ;
        } else if (searchOption == SearchOption.SEARCH_BY_EMAIL) {
            ;
        } else {
            ;
        }
    }
}
