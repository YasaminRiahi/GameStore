package ir.ac.kntu.regularUsers.userStore;

import ir.ac.kntu.helpers.ConsoleColors;
import ir.ac.kntu.regularUsers.Stopwatch1;
import ir.ac.kntu.store.DataBase;

import static ir.ac.kntu.helpers.Scan.scanString;
import static ir.ac.kntu.helpers.TextTypings.*;
import static ir.ac.kntu.helpers.TextTypings.incorrect;

public class CostOfAList {

    private DataBase dataBase;

    public CostOfAList(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public void listCost(int whichUser, Stopwatch1 stopwatch1) {
        drawingLines();
        System.out.println(ConsoleColors.BLUE_BOLD + "******( CALCULATE COST OF A LIST )******" + ConsoleColors.RESET);
        String nextChoose = whereToGo();
        if (nextChoose.equals("1")) {
            calculateList(whichUser, stopwatch1,new ListOfProducts(dataBase));
        } else if (nextChoose.equals("2")) {
            UserStore userStore = new UserStore(dataBase);
            userStore.userStore(whichUser, stopwatch1);
        } else if (nextChoose.equals("3")) {
            drawingLines();
            exit();
        } else {
            incorrect();
            listCost(whichUser, stopwatch1);
        }
    }

    public void calculateList(int whichUser, Stopwatch1 stopwatch1, ListOfProducts listOfProducts) {
        drawingLines();
        double cost = 0;
        System.out.println(ConsoleColors.BLUE_BOLD + "******( CHOOSE LIST )******" + ConsoleColors.RESET);
        String nextChoose = whereToGo();
        if (nextChoose.equals("1")) {
            listOfProducts.showProducts();
            do {
                System.out.println("Which product do you want to choose?");
                System.out.println("1)Games");
                System.out.println("2)Monitor Gaming");
                System.out.println("3)Game pad");
                switch (scanString()) {
                    case "1":
                        System.out.println("Which game?");
                        int whichGame = Integer.parseInt(scanString()) - 1;
                        cost += dataBase.getGames().get(whichGame).getCost();
                        break;
                    case "2":
                        System.out.println("Which monitor gaming?");
                        int whichMonitor = Integer.parseInt(scanString()) - 1;
                        cost += dataBase.getMonitorGaming().get(whichMonitor).getCost();
                        break;
                    default:
                        System.out.println("Which game pad?");
                        int whichPad = Integer.parseInt(scanString()) - 1;
                        cost += dataBase.getMonitorGaming().get(whichPad).getCost();
                        break;
                }
            } while (continueOrNo().equals("1"));
        } else if (nextChoose.equals("2")) {
            listCost(whichUser, stopwatch1);
        } else if (nextChoose.equals("3")) {
            drawingLines();
            exit();
        } else {
            incorrect();
            calculateList(whichUser, stopwatch1,listOfProducts);
        }
        System.out.println(ConsoleColors.PURPLE_BOLD + "Total cost :" + cost + ConsoleColors.RESET);
        listCost(whichUser,stopwatch1);
    }
}
