package ir.ac.kntu.regularUsers.userLibrary;

import ir.ac.kntu.helpers.ConsoleColors;
import ir.ac.kntu.products.accessories.gamePad.GamePad;
import ir.ac.kntu.products.accessories.monitorGaming.MonitorGaming;
import ir.ac.kntu.regularUsers.Stopwatch1;
import ir.ac.kntu.regularUsers.userStore.UserStore;
import ir.ac.kntu.store.DataBase;

import java.util.ArrayList;

import static ir.ac.kntu.helpers.TextTypings.*;
import static ir.ac.kntu.helpers.TextTypings.incorrect;

public class ListOfUserProducts {

    private DataBase dataBase;

    public ListOfUserProducts(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public void listOfProducts(int whichUser, Stopwatch1 stopwatch1) {
        drawingLines();
        System.out.println(ConsoleColors.BLUE_BOLD + "******( LIST OF USER PRODUCTS )******" + ConsoleColors.RESET);
        String nextChoose = whereToGo();
        if (nextChoose.equals("1")) {
            showProducts(whichUser);
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

    public void showProducts(int whichUser) {
        if (dataBase.getRegularUsers().get(whichUser).getMyGames().size() != 0){
            System.out.println(ConsoleColors.PURPLE_BOLD + "GAMES:" + ConsoleColors.RESET);
            int j = 1;
            for (int i = 0; i < dataBase.getRegularUsers().get(whichUser).getMyGames().size(); i++) {
                int length = 5 + dataBase.getRegularUsers().get(whichUser).getMyGames().get(i).getName().length();
                drawForGames(length);
                System.out.print(ConsoleColors.BLUE + "*" + ConsoleColors.RESET);
                System.out.print(+j + ")");
                System.out.print(dataBase.getRegularUsers().get(whichUser).getMyGames().get(i).getName());
                System.out.println(ConsoleColors.BLUE + "*" + ConsoleColors.RESET);
                drawForGames(length);
                j++;
            }
        }
        if (dataBase.getRegularUsers().get(whichUser).getMonitorGaming().size() != 0) {
            int j = 1;
            System.out.println(ConsoleColors.PURPLE_BOLD + "GAMING MONITORS:" + ConsoleColors.RESET);
            MonitorGaming[] monitorGaming =dataBase.getRegularUsers().get(whichUser).getMonitorGaming().keySet().toArray(new MonitorGaming[0]);
            for (int i = 0 ; i < monitorGaming.length ; i++){
                int length = 5 + monitorGaming[i].getName().length();
                drawForAccessories(length);
                System.out.print(ConsoleColors.BLUE + "|" + ConsoleColors.RESET);
                System.out.print(+j + ")");
                System.out.print(monitorGaming[i].getName());
                System.out.println(ConsoleColors.BLUE + "|" + ConsoleColors.RESET);
                drawForGames(length);
                j++;
            }
        }
        if (dataBase.getRegularUsers().get(whichUser).getGamePad().size() != 0) {
            int j = 1;
            System.out.println(ConsoleColors.PURPLE_BOLD + "GAME PADS:" + ConsoleColors.RESET);
            GamePad[] gamePads =dataBase.getRegularUsers().get(whichUser).getGamePad().keySet().toArray(new GamePad[0]);
            for (int i = 0 ; i < gamePads.length ; i++){
                int length = 5 + gamePads[i].getName().length();
                drawForAccessories(length);
                System.out.print(ConsoleColors.BLUE + "|" + ConsoleColors.RESET);
                System.out.print(+j + ")");
                System.out.print(gamePads[i].getName());
                System.out.println(ConsoleColors.BLUE + "|" + ConsoleColors.RESET);
                drawForGames(length);
                j++;
            }
        }
    }

}
