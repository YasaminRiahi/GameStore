package ir.ac.kntu.regularUsers.userStore;

import ir.ac.kntu.helpers.ConsoleColors;
import ir.ac.kntu.regularUsers.Stopwatch1;
import ir.ac.kntu.regularUsers.userStore.UserStore;
import ir.ac.kntu.store.DataBase;

import static ir.ac.kntu.helpers.Scan.scanString;
import static ir.ac.kntu.helpers.TextTypings.*;
import static ir.ac.kntu.helpers.TextTypings.incorrect;

public class ListOfProducts {

    private DataBase dataBase;

    public ListOfProducts(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public void listOfProducts(int whichUser, Stopwatch1 stopwatch1) {
        drawingLines();
        System.out.println(ConsoleColors.BLUE_BOLD + "******( LIST OF PRODUCTS )******" + ConsoleColors.RESET);
        String nextChoose = whereToGo();
        if (nextChoose.equals("1")) {
            showProducts();
            choose(whichUser, stopwatch1);
        } else if (nextChoose.equals("2")) {
            UserStore userStore = new UserStore(dataBase);
            userStore.userStore(whichUser, stopwatch1);
        } else if (nextChoose.equals("3")) {
            drawingLines();
            exit();
        } else {
            incorrect();
            listOfProducts(whichUser, stopwatch1);
        }
    }

    public void showProducts() {
        System.out.println(ConsoleColors.PURPLE_BOLD + "GAMES:" + ConsoleColors.RESET);
        int j = 1;
        for (int i = 0; i < dataBase.getGames().size(); i++) {
            int length = 5 + dataBase.getGames().get(i).getName().length();
            drawForGames(length);
            System.out.print(ConsoleColors.BLUE + "*" + ConsoleColors.RESET);
            System.out.print(+j + ")");
            System.out.print(dataBase.getGames().get(i).getName());
            System.out.println(ConsoleColors.BLUE + "*" + ConsoleColors.RESET);
            drawForGames(length);
            j++;
        }
        j = 1;
        System.out.println(ConsoleColors.PURPLE_BOLD + "GAMING MONITORS:" + ConsoleColors.RESET);
        for (int i = 0; i < dataBase.getMonitorGaming().size(); i++) {
            int length = 5 + dataBase.getMonitorGaming().get(i).getName().length();
            drawForAccessories(length);
            System.out.print(ConsoleColors.BLUE + "|" + ConsoleColors.RESET);
            System.out.print(+j + ")");
            System.out.print(dataBase.getMonitorGaming().get(i).getName());
            System.out.println(ConsoleColors.BLUE + "|" + ConsoleColors.RESET);
            drawForAccessories(length);
            j++;
        }
        System.out.println(ConsoleColors.PURPLE_BOLD + "GAMING PADS:" + ConsoleColors.RESET);
        j = 1;
        for (int i = 0; i < dataBase.getGamePads().size(); i++) {
            int length = 5 + dataBase.getGamePads().get(i).getName().length();
            drawForAccessories(length);
            System.out.print(ConsoleColors.BLUE + "|" + ConsoleColors.RESET);
            System.out.print(+j + ")");
            System.out.print(dataBase.getGamePads().get(i).getName());
            System.out.println(ConsoleColors.BLUE + "|" + ConsoleColors.RESET);
            drawForAccessories(length);
            j++;
        }
    }

    public void choose(int whichUser, Stopwatch1 stopwatch1) {
        System.out.println("Which product do you want to choose?");
        System.out.println("1)Games");
        System.out.println("2)Monitor Gaming");
        System.out.println("3)Game pad");
        String whichProduct = scanString();
        UserStore userStore = new UserStore(dataBase);
        switch (whichProduct) {
            case "1":
                System.out.println("Which game?");
                int whichGame = Integer.parseInt(scanString()) - 1;
                userStore.showGameByDetails(whichGame);
                if (userStore.checkHavingGame(whichUser, whichGame) == 0) {
                    userStore.buyGame(whichUser, whichGame);
                }
                listOfProducts(whichUser, stopwatch1);
                break;
            case "2":
                System.out.println("Which monitor gaming?");
                int whichMonitor = Integer.parseInt(scanString()) - 1;
                userStore.showMonitorGamingByDetails(whichMonitor);
                userStore.buyMonitor(whichUser, whichMonitor);
                listOfProducts(whichUser, stopwatch1);
                break;
            case "3":
                System.out.println("Which game pad?");
                int whichPad = Integer.parseInt(scanString()) - 1;
                userStore.showGamePadByDetails(whichPad);
                userStore.buyPad(whichUser, whichPad);
                listOfProducts(whichUser, stopwatch1);
                break;
            default:
                incorrect();
                showProducts();
                break;
        }
    }
}
