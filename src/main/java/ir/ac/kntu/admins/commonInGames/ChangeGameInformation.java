package ir.ac.kntu.admins.commonInGames;

import ir.ac.kntu.admins.developers.DeveloperGamePage;
import ir.ac.kntu.admins.managers.ManagerGamePage;
import ir.ac.kntu.helpers.ConsoleColors;
import ir.ac.kntu.store.DataBase;

import java.util.ArrayList;

import static ir.ac.kntu.helpers.Scan.*;
import static ir.ac.kntu.helpers.TextTypings.*;

public class ChangeGameInformation {

    private DataBase dataBase;

    private EditingGamesOptions editingGameOptions;

    public ChangeGameInformation(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public void changeGamesInformation(int whichUser, String typeOfAdmin) {
        drawingLines();
        System.out.println(ConsoleColors.BLUE_BOLD + "******( CHANGE GAMES INFORMATION )******" + ConsoleColors.RESET);
        String nextChoose = whereToGo();
        switch (nextChoose) {
            case "1" -> showEditingGamesOptions(whichUser, typeOfAdmin);
            case "2" -> goBack(whichUser, typeOfAdmin);
            case "3" -> {
                drawingLines();
                exit();
            }
            default -> {
                incorrect();
                changeGamesInformation(whichUser, typeOfAdmin);
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
            changeByList(whichManager, typeOfAdmin);
        } else {
            changBySearch(whichManager, typeOfAdmin);
        }
    }

    public void changeByList(int whichUser, String typeOfAdmin) {
        drawingLines();
        System.out.println(ConsoleColors.BLUE_BOLD + "******( CHANGE INFORMATION BY LIST )******" + ConsoleColors.RESET);
        String nextChoose = whereToGo();
        switch (nextChoose) {
            case "1" -> {
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
                    whichOption();
                    toChange(Integer.parseInt(whichGame) - 1);
                    System.out.println("Game changed successfully!");
                    changeGamesInformation(whichUser, typeOfAdmin);
                }
            }
            case "2" -> changeGamesInformation(whichUser, typeOfAdmin);
            case "3" -> {
                drawingLines();
                exit();
            }
            default -> {
                incorrect();
                changeByList(whichUser, typeOfAdmin);
            }
        }
    }

    public void changBySearch(int whichUser, String typeOfAdmin) {
        drawingLines();
        System.out.println(ConsoleColors.BLUE_BOLD + "******( CHANGE INFORMATION BY SEARCH )******" + ConsoleColors.RESET);
        String nextChoose = whereToGo();
        switch (nextChoose) {
            case "1" -> searchOptionToChange(whichUser, typeOfAdmin);
            case "2" -> changeGamesInformation(whichUser, typeOfAdmin);
            case "3" -> {
                drawingLines();
                exit();
            }
            default -> {
                incorrect();
                changBySearch(whichUser, typeOfAdmin);
            }
        }
    }

    public void searchOptionToChange(int whichUser, String typeOfAdmin) {
        System.out.println("Enter name:");
        String name = scanString();
        ArrayList<Integer> foundGames = searchGame(name);
        if (foundGames.size() == 0) {
            System.out.println(ConsoleColors.RED + "No result!Try again" + ConsoleColors.RESET);
            changBySearch(whichUser, typeOfAdmin);
        } else {
            showFoundGames(foundGames);
            String whichGame = scanString();
            if (Integer.parseInt(whichGame) - 1 >= foundGames.size() || Integer.parseInt(whichGame) - 1 < 0) {
                incorrect();
                searchOptionToChange(whichUser, typeOfAdmin);
            } else {
                if (typeOfAdmin.equals("DEVELOPER")) {
                    if (!dataBase.getGames().get(Integer.parseInt(whichGame) - 1).getDevelopers().
                            contains(dataBase.getDevelopers().get(whichUser))) {
                        notDeveloper();
                        changBySearch(whichUser, typeOfAdmin);
                    }
                }
                whichOption();
                toChange(Integer.parseInt(whichGame) - 1);
                System.out.println("Game changed successfully!");
                changeGamesInformation(whichUser, typeOfAdmin);
            }
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

    public void toChange(int gameIndex) {
        String which = scanString();
        switch (Integer.parseInt(which)) {
            case 1 -> {
                toChangeWhat("name");
                dataBase.getGames().get(gameIndex).setName(scanString());
            }
            case 2 -> {
                toChangeWhat("genre");
                dataBase.getGames().get(gameIndex).setGenre(scanString());
            }
            case 3 -> {
                toChangeWhat("description");
                dataBase.getGames().get(gameIndex).setDescription(scanString());
            }
            case 4 -> {
                toChangeWhat("rating");
                double rating = scanDouble();
                dataBase.getGames().get(gameIndex).setRating(rating);
                dataBase.getGames().get(gameIndex).setBeginningRate(rating);
            }
            case 5 -> {
                toChangeWhat("number of rates");
                int numberOfRates = scanInt();
                dataBase.getGames().get(gameIndex).setNumberOfRates(numberOfRates);
                dataBase.getGames().get(gameIndex).setBeginningNumber(numberOfRates);
            }
            case 6 -> {
                toChangeWhat("cost");
                dataBase.getGames().get(gameIndex).setCost(scanDouble());
            }
            default -> {
                incorrect();
                toChange(gameIndex);
            }
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