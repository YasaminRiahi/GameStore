package ir.ac.kntu.regularUsers.userLibrary;

import ir.ac.kntu.helpers.ConsoleColors;
import ir.ac.kntu.products.accessories.gamePad.GamePad;
import ir.ac.kntu.products.accessories.monitorGaming.MonitorGaming;
import ir.ac.kntu.regularUsers.RegularUserPage;
import ir.ac.kntu.regularUsers.Stopwatch1;
import ir.ac.kntu.regularUsers.userStore.*;
import ir.ac.kntu.store.DataBase;

import static ir.ac.kntu.helpers.TextTypings.*;
import static ir.ac.kntu.helpers.TextTypings.incorrect;

public class UserLibrary {

    private DataBase dataBase;

    public UserLibrary(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public void userLibrary(int userIndex, Stopwatch1 stopwatch1) {
        drawingLines();
        System.out.println(ConsoleColors.BLUE_BOLD + "******( LIBRARY )******" + ConsoleColors.RESET);
        String nextChoose = whereToGo();
        if (nextChoose.equals("1")) {
            ListOfUserProducts listOfUserProducts = new ListOfUserProducts(dataBase);
            listOfUserProducts.listOfProducts(userIndex,stopwatch1);
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


    public void showGameByDetails(int userIndex , int gameIndex) {
        System.out.println("1)Name : " + dataBase.getRegularUsers().get(userIndex).getMyGames().get(gameIndex).getName());
        System.out.println("2)Genre : " + dataBase.getRegularUsers().get(userIndex).getMyGames().get(gameIndex).getGenre());
        System.out.println("3)Description : " + dataBase.getRegularUsers().get(userIndex).getMyGames().get(gameIndex).getDescription());
        System.out.println("4)Rating : " + dataBase.getRegularUsers().get(userIndex).getMyGames().get(gameIndex).getRating());
        System.out.println("5)Number of rates : " + dataBase.getRegularUsers().get(userIndex).getMyGames().get(gameIndex).getNumberOfRates());
        System.out.println("6)Cost :" + dataBase.getRegularUsers().get(userIndex).getMyGames().get(gameIndex).getCost());
        System.out.println("7)Level :" + dataBase.getRegularUsers().get(userIndex).getMyGames().get(gameIndex).getGamesLevel());
        if (!dataBase.getRegularUsers().get(userIndex).getMyGames().get(gameIndex).isBeta()){
            System.out.println("7)Community :");
            int j = 1;
            for (int i = 0; i < dataBase.getRegularUsers().get(userIndex).getMyGames().get(gameIndex).getCommunity().size(); i++) {
                System.out.println("    " + j + ")" + dataBase.getRegularUsers().get(userIndex).getMyGames().get(gameIndex).getCommunity().get(i));
                j++;
            }
        }
    }

    public void showMonitorGamingByDetails(int index, MonitorGaming[] monitorGaming) {
        System.out.println("1)Name : " + monitorGaming[index].getName());
        System.out.println("2)Description : " +monitorGaming[index].getDescription());
        System.out.println("3)Cost : " + monitorGaming[index].getCost());
        System.out.println("4)Number : " + monitorGaming[index].getNumber());
        System.out.println("5)Screen size : " + monitorGaming[index].getScreenSize());
        System.out.println("6)Refresh rate :" + monitorGaming[index].getRefreshRate());
        System.out.println("7)Response time :" + monitorGaming[index].getResponseTime());
        System.out.println("8)Community :");
        int j = 1;
        for (int i = 0; i < monitorGaming[index].getCommunity().size(); i++) {
            System.out.println("    " + j + ")" + monitorGaming[index].getCommunity().get(i));
            j++;
        }
    }

    public void showGamePadByDetails(int index , GamePad[] gamePads) {
        System.out.println("1)Name : " + gamePads[index].getName());
        System.out.println("2)Description : " + gamePads[index].getDescription());
        System.out.println("3)Cost : " + gamePads[index].getCost());
        System.out.println("4)Number : " + gamePads[index].getNumber());
        System.out.println("5)Connection type : " + gamePads[index].getConnectionType());
        System.out.println("6)Device type :" + gamePads[index].getDeviceType());
        System.out.println("7)Community :");
        int j = 1;
        for (int i = 0; i < gamePads[index].getCommunity().size(); i++) {
            System.out.println("    " + j + ")" + gamePads[index].getCommunity().get(i));
            j++;
        }
    }
}
