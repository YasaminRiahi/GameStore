package ir.ac.kntu.admins;

import ir.ac.kntu.helpers.ConsoleColors;
import ir.ac.kntu.store.DataBase;

import static ir.ac.kntu.helpers.Scan.scanString;
import static ir.ac.kntu.helpers.TextTypings.*;

public class ToEditGame {

    private DataBase dataBase;

    public ToEditGame(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public void changeByList(int whichUser, String typeOfAdmin) {
        drawingLines();
        System.out.println(ConsoleColors.BLUE_BOLD + "******( CHANGE INFORMATION BY LIST )******" + ConsoleColors.RESET);
        String nextChoose = whereToGo();
        if (nextChoose.equals("1")) {
            showGames();
            String whichGame = scanString();
            if (Integer.parseInt(whichGame) - 1 >= dataBase.getGames().size() || Integer.parseInt(whichGame) - 1 < 0) {
                incorrect();
                changeByList(whichUser, typeOfAdmin);
            } else {
                if (typeOfAdmin.equals("DEVELOPER")) {
                    if (!dataBase.getGames().get(Integer.parseInt(whichGame) - 1).getDevelopers().
                            contains(dataBase.getDevelopers().get(whichUser))) {
                        notDeveloper();
                        changeByList(whichUser, typeOfAdmin);
                    }
                }
                this.whichOption();
                ChangeGameInformation changeGameInformation = new ChangeGameInformation(dataBase);
                changeGameInformation.toChange(Integer.parseInt(whichGame) - 1);
                System.out.println("Game changed successfully!");
                changeGameInformation.changeGamesInformation(whichUser, typeOfAdmin);
            }
        } else if (nextChoose.equals("2")) {
            ChangeGameInformation changeGameInformation = new ChangeGameInformation(dataBase);
            changeGameInformation.changeGamesInformation(whichUser, typeOfAdmin);
        } else if (nextChoose.equals("3")) {
            System.out.println("Finish!");
            drawingLines();
            exit();
        } else {
            incorrect();
            changeByList(whichUser, typeOfAdmin);
        }
    }

    public void showGames() {
        int j = 1;
        for (int i = 0; i < dataBase.getGames().size(); i++) {
            System.out.print(j + ")");
            System.out.println(dataBase.getGames().get(i).getName());
            j++;
        }
    }

    public void whichOption() {
        System.out.println("1)Name");
        System.out.println("2)Genre");
        System.out.println("3)Description");
        System.out.println("4)Rating");
        System.out.println("5)Number of rates");
        System.out.println("6)Cost");
    }
}
