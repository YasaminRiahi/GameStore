package ir.ac.kntu.userSearcher;

import ir.ac.kntu.admins.managers.ChangeUserInformation;
import ir.ac.kntu.admins.managers.RemoveAUser;
import ir.ac.kntu.admins.managers.ViewUserInformation;
import ir.ac.kntu.store.DataBase;

import static ir.ac.kntu.helpers.TextTypings.getNumberFromOptions;
import static ir.ac.kntu.helpers.TextTypings.incorrect;

import ir.ac.kntu.helpers.ConsoleColors;

import static ir.ac.kntu.helpers.TextTypings.*;

public class UserSearcher {

    private DataBase dataBase;

    private SearchOptions searchOption;

    public UserSearcher(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public void howToSearch(String goBack, int whichUser) {
        drawingLines();
        System.out.println(ConsoleColors.BLUE_BOLD + "******( SHOW OPTIONS TO SEARCH )******" + ConsoleColors.RESET);
        String nextChoose = whereToGo();
        if (nextChoose.equals("1")) {
            showSearchOptions(goBack, whichUser);
        } else if (nextChoose.equals("2")) {
            if (goBack.equals("VIEW_USER_INFORMATION")) {
                ViewUserInformation viewUserInformation = new ViewUserInformation(dataBase);
                viewUserInformation.viewUsersInformation(whichUser);
            } else if (goBack.equals("CHANGE_A_USER_INFORMATION")) {
                ChangeUserInformation changeUserInformation = new ChangeUserInformation(dataBase);
                changeUserInformation.changeAUserInformation(whichUser);
            } else {
                RemoveAUser removeAUser = new RemoveAUser(dataBase);
                removeAUser.toRemoveUsers(whichUser);
            }
        } else if (nextChoose.equals("3")) {
            drawingLines();
            exit();
        } else {
            incorrect();
            howToSearch(goBack, whichUser);
        }
    }


    public void showSearchOptions(String goBack, int whichUser) {
        for (SearchOptions searchOption1 : SearchOptions.values()) {
            System.out.print(searchOption1.getValue() + ")");
            System.out.println(searchOption1);
        }
        fromValue(getNumberFromOptions(), whichUser, goBack);
    }

    public void fromValue(String value, int whichUser, String goBack) {
        for (SearchOptions e : SearchOptions.values()) {
            if (e.getValue().equals(value)) {
                this.searchOption = e;
                goToOptions(goBack, whichUser);
            }
        }
        incorrect();
        showSearchOptions(goBack, whichUser);
    }

    public void goToOptions(String goBack, int whichUser) {
        if (searchOption == SearchOptions.SEARCH_BY_USERNAME) {
            UsernameSearcher usernameSearching = new UsernameSearcher(dataBase);
            usernameSearching.searchByUsername(goBack, whichUser);
        } else if (searchOption == SearchOptions.SEARCH_BY_EMAIL) {
            EmailSearcher emailSearcher = new EmailSearcher(dataBase);
            emailSearcher.searchByEmail(goBack, whichUser);
        } else {
            PhoneNumberSearcher phoneNumberSearcher = new PhoneNumberSearcher(dataBase);
            phoneNumberSearcher.searchByPhoneNumber(goBack,whichUser);
        }
    }
}