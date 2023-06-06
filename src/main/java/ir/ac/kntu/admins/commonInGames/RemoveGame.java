package ir.ac.kntu.admins.commonInGames;

import ir.ac.kntu.admins.managers.ManagerGamePage;
import ir.ac.kntu.helpers.ConsoleColors;
import ir.ac.kntu.store.DataBase;

import java.util.ArrayList;

import static ir.ac.kntu.helpers.Scan.*;
import static ir.ac.kntu.helpers.TextTypings.*;

public class RemoveGame {

    private DataBase dataBase;

    private EditingGamesOptions editingGameOptions;

    public RemoveGame(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public void removeGames(int whichUser, String typeOfAdmin) {
        drawingLines();
        System.out.println(ConsoleColors.BLUE_BOLD + "******( REMOVE GAMES )******" + ConsoleColors.RESET);
        String nextChoose = whereToGo();
        if (nextChoose.equals("1")) {
            showEditingGamesOptions(whichUser, typeOfAdmin);
        } else if (nextChoose.equals("2")) {
            goBack(whichUser, typeOfAdmin);
        } else if (nextChoose.equals("3")) {
            System.out.println("Finish!");
            drawingLines();
            exit();
        } else {
            incorrect();
            removeGames(whichUser, typeOfAdmin);
        }

    }

    public void removeByList(int whichUser, String typeOfAdmin) {
        drawingLines();
        System.out.println(ConsoleColors.BLUE_BOLD + "******( REMOVE GAMES BY LIST )******" + ConsoleColors.RESET);
        String nextChoose = whereToGo();
        if (nextChoose.equals("1")) {
            showGames();
            String whichGame = scanString();
            if (Integer.parseInt(whichGame) - 1 >= dataBase.getGames().size() || Integer.parseInt(whichGame) - 1 < 0) {
                incorrect();
                removeByList(whichUser, typeOfAdmin);
            } else {
                if (typeOfAdmin.equals("DEVELOPER")) {
                    if (!dataBase.getGames().get(Integer.parseInt(whichGame) - 1).getDevelopers().
                            contains(dataBase.getDevelopers().get(whichUser))) {
                        notDeveloper();
                        removeByList(whichUser, typeOfAdmin);
                    }
                }
                dataBase.getGames().remove(Integer.parseInt(whichGame) - 1);
                System.out.println("Game removed successfully!");
                removeGames(whichUser,typeOfAdmin);
            }
        } else if (nextChoose.equals("2")) {
            removeGames(whichUser, typeOfAdmin);
        } else if (nextChoose.equals("3")) {
            drawingLines();
            exit();
        } else {
            incorrect();
            removeByList(whichUser, typeOfAdmin);
        }
    }

    public void removeBySearch(int whichUser, String typeOfAdmin) {
        drawingLines();
        System.out.println(ConsoleColors.BLUE_BOLD + "******( REMOVE GAMES BY SEARCH )******" + ConsoleColors.RESET);
        String nextChoose = whereToGo();
        if (nextChoose.equals("1")) {
            searchOptionToRemove(whichUser, typeOfAdmin);
        } else if (nextChoose.equals("2")) {
            removeGames(whichUser, typeOfAdmin);
        } else if (nextChoose.equals("3")) {
            drawingLines();
            exit();
        } else {
            incorrect();
            removeBySearch(whichUser, typeOfAdmin);
        }
    }

    public void searchOptionToRemove(int whichUser, String typeOfAdmin) {
        System.out.println("Enter name:");
        String name = scanString();
        ArrayList<Integer> foundGames = searchGame(name);
        if (foundGames.size() == 0) {
            System.out.println(ConsoleColors.RED + "No result!Try again" + ConsoleColors.RESET);
            removeBySearch(whichUser, typeOfAdmin);
        } else {
            showFoundGames(foundGames);
            String whichGame = scanString();
            if (Integer.parseInt(whichGame) - 1 >= foundGames.size() || Integer.parseInt(whichGame) - 1 < 0) {
                incorrect();
                removeBySearch(whichUser, typeOfAdmin);
            } else {
                if (typeOfAdmin.equals("DEVELOPER")) {
                    if (!dataBase.getGames().get(Integer.parseInt(whichGame) - 1).getDevelopers().
                            contains(dataBase.getDevelopers().get(whichUser))) {
                        notDeveloper();
                        removeByList(whichUser, typeOfAdmin);
                    }
                }
                dataBase.getGames().remove(Integer.parseInt(whichGame) - 1);
                System.out.println("Game removed successfully!");
                removeGames(whichUser,typeOfAdmin);
            }
        }
    }

    public void showEditingGamesOptions(int whichUser, String typeOfAdmin) {
        for (EditingGamesOptions editingGameOptions1 : EditingGamesOptions.values()) {
            System.out.print(editingGameOptions1.getValue() + ")");
            System.out.println(editingGameOptions1);
        }
        fromValue(getNumberFromOptions(), whichUser, typeOfAdmin);
    }

    public void fromValue(String value, int whichUser, String typeOfAdmin) {
        for (EditingGamesOptions e : EditingGamesOptions.values()) {
            if (e.getValue().equals(value)) {
                this.editingGameOptions = e;
                goToOptions(whichUser, typeOfAdmin);
            }
        }
        incorrect();
        showEditingGamesOptions(whichUser, typeOfAdmin);
    }

    public void goToOptions(int whichManager, String typeOfAdmin) {
        if (editingGameOptions == EditingGamesOptions.BY_LIST_OF_GAMES) {
            removeByList(whichManager, typeOfAdmin);
        } else {
            removeBySearch(whichManager,typeOfAdmin);
        }
    }

    public void goBack(int whichUser, String typeOfAdmin) {
        if (typeOfAdmin.equals("MANAGER")) {
            ManagerGamePage managerGamePage = new ManagerGamePage(dataBase);
            managerGamePage.gamesPage(whichUser);
        } else if (typeOfAdmin.equals("DEVELOPER")) {
            ;
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

    public ArrayList searchGame(String name) {
        ArrayList<Integer> indexes = new ArrayList<Integer>();
        for (int i = 0; i < dataBase.getGames().size(); i++) {
            if (dataBase.getGames().get(i).getName().toLowerCase().startsWith(name.toLowerCase())) {
                indexes.add(i);
            }
        }
        return indexes;
    }

    public void showFoundGames(ArrayList<Integer> foundGames) {
        int j = 1;
        for (int i = 0; i < foundGames.size(); i++) {
            System.out.print(+j + ")");
            System.out.println(dataBase.getGames().get(foundGames.get(i)).getName());
            j++;
        }
    }
}
