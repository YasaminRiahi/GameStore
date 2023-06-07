package ir.ac.kntu.admins.commonInGames;

import ir.ac.kntu.admins.developers.DeveloperGamePage;
import ir.ac.kntu.admins.managers.ManagerGamePage;
import ir.ac.kntu.helpers.ConsoleColors;
import ir.ac.kntu.store.DataBase;

import java.util.ArrayList;

import static ir.ac.kntu.helpers.Scanner.*;
import static ir.ac.kntu.helpers.TextTypings.*;

public class RemovingGame {

    private DataBase dataBase;

    private EditingGamesOptions editingGameOptions;

    public RemovingGame(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public void removeGames(int whichUser, String typeOfAdmin) {
        drawingLines();
        System.out.println(ConsoleColors.BLUE_BOLD + "******( REMOVE GAMES )******" + ConsoleColors.RESET);
        String nextChoose = whereToGo();
        switch (nextChoose) {
            case "1" -> showEditingGamesOptions(whichUser, typeOfAdmin);
            case "2" -> goBack(whichUser, typeOfAdmin);
            case "3" -> {
                System.out.println("Finish!");
                drawingLines();
                exit();
            }
            default -> {
                incorrect();
                removeGames(whichUser, typeOfAdmin);
            }
        }
    }

    public void removeByList(int whichUser, String typeOfAdmin) {
        drawingLines();
        System.out.println(ConsoleColors.BLUE_BOLD + "******( REMOVE GAMES BY LIST )******" + ConsoleColors.RESET);
        String nextChoose = whereToGo();
        switch (nextChoose) {
            case "1" -> {
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
                    removeGames(whichUser, typeOfAdmin);
                }
            }
            case "2" -> removeGames(whichUser, typeOfAdmin);
            case "3" -> {
                drawingLines();
                exit();
            }
            default -> {
                incorrect();
                removeByList(whichUser, typeOfAdmin);
            }
        }
    }

    public void removeBySearch(int whichUser, String typeOfAdmin) {
        drawingLines();
        System.out.println(ConsoleColors.BLUE_BOLD + "******( REMOVE GAMES BY SEARCH )******" + ConsoleColors.RESET);
        String nextChoose = whereToGo();
        switch (nextChoose) {
            case "1" -> searchOptionToRemove(whichUser, typeOfAdmin);
            case "2" -> removeGames(whichUser, typeOfAdmin);
            case "3" -> {
                drawingLines();
                exit();
            }
            default -> {
                incorrect();
                removeBySearch(whichUser, typeOfAdmin);
            }
        }
    }

    public void searchOptionToRemove(int whichUser, String typeOfAdmin) {
        System.out.println("Enter name:");
        String name = scanString();
        ArrayList foundGames = searchGame(name);
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
                removeGames(whichUser, typeOfAdmin);
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
            removeBySearch(whichManager, typeOfAdmin);
        }
    }

    public void goBack(int whichUser, String typeOfAdmin) {
        if (typeOfAdmin.equals("MANAGER")) {
            ManagerGamePage managerGamePage = new ManagerGamePage(dataBase);
            managerGamePage.gamesPage(whichUser);
        } else if (typeOfAdmin.equals("DEVELOPER")) {
            DeveloperGamePage developerGamePage = new DeveloperGamePage(dataBase);
            developerGamePage.gamesPage(whichUser);
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
        ArrayList<Integer> indexes = new ArrayList<>();
        for (int i = 0; i < dataBase.getGames().size(); i++) {
            if (dataBase.getGames().get(i).getName().toLowerCase().startsWith(name.toLowerCase())) {
                indexes.add(i);
            }
        }
        return indexes;
    }

    public void showFoundGames(ArrayList<Integer> foundGames) {
        int j = 1;
        for (Integer foundGame : foundGames) {
            System.out.print(j + ")");
            System.out.println(dataBase.getGames().get(foundGame).getName());
            j++;
        }
    }
}