package ir.ac.kntu.regularUsers;

import ir.ac.kntu.helpers.ConsoleColors;
import ir.ac.kntu.store.DataBase;

import static ir.ac.kntu.helpers.TextTypings.*;

import java.util.ArrayList;

import static ir.ac.kntu.helpers.TextTypings.drawingLines;

public class UserStore {

    private DataBase dataBase;

    private UserStoreOptions userStoreOptions;

    public UserStore(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public void userStore(int userIndex, Stopwatch1 stopwatch1) {
        drawingLines();
        System.out.println(ConsoleColors.BLUE_BOLD + "******( STORE )******" + ConsoleColors.RESET);
        String nextChoose = whereToGo();
        if (nextChoose.equals("1")) {
            showUserStoreOptions(userIndex, stopwatch1);
        } else if (nextChoose.equals("2")) {
            RegularUserPage regularUserPage = new RegularUserPage(dataBase);
            regularUserPage.userAccess(userIndex, stopwatch1);
        } else if (nextChoose.equals("3")) {
            drawingLines();
            exit();
        } else {
            incorrect();
            userStore(userIndex, stopwatch1);
        }
    }

    public void showUserStoreOptions(int whichUser, Stopwatch1 stopwatch1) {
        for (UserStoreOptions userStoreOptions1 : UserStoreOptions.values()) {
            System.out.print(userStoreOptions1.getValue() + ")");
            System.out.println(userStoreOptions1);
        }
        fromValue(getNumberFromOptions(), whichUser, stopwatch1);
    }

    public void fromValue(String value, int whichUser, Stopwatch1 stopwatch1) {
        for (UserStoreOptions e : UserStoreOptions.values()) {
            if (e.getValue().equals(value)) {
                this.userStoreOptions = e;
                goToOptions(whichUser, stopwatch1);
            }
        }
        incorrect();
        showUserStoreOptions(whichUser, stopwatch1);
    }

    public void goToOptions(int whichUser, Stopwatch1 stopwatch1) {
        if (userStoreOptions == UserStoreOptions.LIST_OF_PRODUCTS) {
            ;
        } else if (userStoreOptions == UserStoreOptions.SEARCH_IN_PRODUCTS) {
            ;
        } else if (userStoreOptions == UserStoreOptions.SHOW_PRODUCTS_BY_FILTERING_COST) {
            ;
        } else if (userStoreOptions == UserStoreOptions.SHOW_BEST_SELLING_PRODUCTS) {
            ;
        } else {
            ;
        }
    }

//    public String whichOption() {
//        System.out.println("Choose what you want?");
//        System.out.println("1)List of games");
//        System.out.println("2)Search in games");
//        System.out.println("3)Show games by filtering cost");
//        return scanString();
//    }
//
//    public void listOfGames(Store store, int userIndex) {
//        drawingLines();
//        System.out.println(ConsoleColors.BLUE_BOLD + "******( LIST OF GAMES )******" + ConsoleColors.RESET);
//        String nextChoose = whereToGo();
//        if (nextChoose.equals("1")) {
//            showGames(store);
//            String whichGame = scanString();
//            if (Integer.parseInt(whichGame) - 1 >= store.getGames().size() || Integer.parseInt(whichGame) - 1 < 0) {
//                incorrect();
//                listOfGames(store, userIndex);
//            } else {
//                int gameIndex = Integer.parseInt(whichGame) - 1;
//                showGameByDetails(store, gameIndex, userIndex, 1);
//                if (checkHaving(store, userIndex, gameIndex) == 0) {
//                    switch (wantBuy()) {
//                        case "1":
//                            buyGame(store, userIndex, gameIndex);
//                            break;
//                        case "2":
//                            listOfGames(store, userIndex);
//                            break;
//                        default:
//                            incorrect();
//                            listOfGames(store, userIndex);
//                    }
//                } else {
//                    userStore(store, userIndex);
//                }
//            }
//        } else if (nextChoose.equals("2")) {
//            userStore(store, userIndex);
//        } else if (nextChoose.equals("3")) {
//            System.out.println("Finish!");
//            drawingLines();
//            exit();
//        } else {
//            incorrect();
//            listOfGames(store, userIndex);
//        }
//    }
//
//    public void searchInGames(Store store, int userIndex) {
//        drawingLines();
//        System.out.println(ConsoleColors.BLUE_BOLD + "******( SEARCH IN GAMES )******" + ConsoleColors.RESET);
//        String nextChoose = whereToGo();
//        if (nextChoose.equals("1")) {
//            System.out.println("Enter game name :");
//            ArrayList<Integer> foundGames = searchGameByName(scanString(), store);
//            if (foundGames.size() == 0) {
//                System.out.println(ConsoleColors.RED + "No result!Try again" + ConsoleColors.RESET);
//                searchInGames(store, userIndex);
//            } else {
//                showFoundGames(foundGames, store);
//                String whichGame = scanString();
//                if (Integer.parseInt(whichGame) - 1 >= foundGames.size() || Integer.parseInt(whichGame) - 1 < 0) {
//                    incorrect();
//                    searchInGames(store, userIndex);
//                } else {
//                    int gameIndex = foundGames.get(Integer.parseInt(whichGame) - 1);
//                    showGameByDetails(store, gameIndex, userIndex, 2);
//                    if (checkHaving(store, userIndex, gameIndex) == 0) {
//                        switch (wantBuy()) {
//                            case "1":
//                                buyGame(store, userIndex, gameIndex);
//                                break;
//                            case "2":
//                                listOfGames(store, userIndex);
//                                break;
//                            default:
//                                incorrect();
//                                searchInGames(store, userIndex);
//                        }
//                    } else {
//                        userStore(store, userIndex);
//                    }
//                }
//            }
//        } else if (nextChoose.equals("2")) {
//            userStore(store, userIndex);
//        } else if (nextChoose.equals("3")) {
//            System.out.println("Finish!");
//            drawingLines();
//            exit();
//        } else {
//            incorrect();
//            searchInGames(store, userIndex);
//        }
//    }
//
//    public void showGamesByFilteringCost(Store store, int userIndex) {
//        drawingLines();
//        System.out.println(ConsoleColors.BLUE_BOLD + "******( SHOW GAMES BY FILTERING COST )******" +
//                ConsoleColors.RESET);
//        String nextChoose = whereToGo();
//        if (nextChoose.equals("1")) {
//            System.out.println("Enter beginning cost and termination cost :");
//            ArrayList<Integer> foundGames = searchGameByFiltering(store, scanDouble(), scanDouble());
//            if (foundGames.size() == 0) {
//                System.out.println(ConsoleColors.RED + "No result!Try again" + ConsoleColors.RESET);
//                showGamesByFilteringCost(store, userIndex);
//            } else {
//                showFoundGames(foundGames, store);
//                String whichGame = scanString();
//                if (Integer.parseInt(whichGame) - 1 >= foundGames.size() || Integer.parseInt(whichGame) - 1 < 0) {
//                    incorrect();
//                    showGamesByFilteringCost(store, userIndex);
//                } else {
//                    int gameIndex = foundGames.get(Integer.parseInt(whichGame) - 1);
//                    showGameByDetails(store, gameIndex, userIndex, 3);
//                    if (checkHaving(store, userIndex, gameIndex) == 0) {
//                        switch (wantBuy()) {
//                            case "1":
//                                buyGame(store, userIndex, gameIndex);
//                                break;
//                            case "2":
//                                listOfGames(store, userIndex);
//                                break;
//                            default:
//                                incorrect();
//                                searchInGames(store, userIndex);
//                        }
//                    } else {
//                        userStore(store, userIndex);
//                    }
//                }
//            }
//        } else if (nextChoose.equals("2")) {
//            userStore(store, userIndex);
//        } else if (nextChoose.equals("3")) {
//            System.out.println("Finish!");
//            drawingLines();
//            exit();
//        } else {
//            incorrect();
//            showGamesByFilteringCost(store, userIndex);
//        }
//    }
//
//    public void showGames(Store store) {
//        System.out.println("Choose a game:");
//        int j = 1;
//        for (int i = 0; i < store.getGames().size(); i++) {
//            System.out.print(+j + ")");
//            System.out.println(store.getGames().get(i).getName());
//            j++;
//        }
//    }
//
//    public void showGameByDetails(Store store, int gameIndex, int userIndex, int goBack) {
//        drawingLines();
//        System.out.println(ConsoleColors.BLUE_BOLD + "******( SHOW GAMES BY DETAILS )******" + ConsoleColors.RESET);
//        String nextChoose = whereToGo();
//        if (nextChoose.equals("1")) {
//            System.out.println("1)Name : " + store.getGames().get(gameIndex).getName());
//            System.out.println("2)Genre : " + store.getGames().get(gameIndex).getGenre());
//            System.out.println("3)Description : " + store.getGames().get(gameIndex).getDescription());
//            System.out.println("4)Rating : " + store.getGames().get(gameIndex).getRating());
//            System.out.println("5)Number of rates : " + store.getGames().get(gameIndex).getNumberOfRates());
//            System.out.println("6)Cost :" + store.getGames().get(gameIndex).getCost());
//            System.out.println("7)Community :");
//            int j = 1;
//            for (int i = 0; i < store.getGames().get(gameIndex).getCommunity().size(); i++) {
//                System.out.println("    " + j + ")" + store.getGames().get(gameIndex).getCommunity().get(i));
//                j++;
//            }
//        } else if (nextChoose.equals("2")) {
//            if (goBack == 1) {
//                listOfGames(store, userIndex);
//            } else if (goBack == 2) {
//                searchInGames(store, userIndex);
//            } else {
//                showGamesByFilteringCost(store, userIndex);
//            }
//        } else if (nextChoose.equals("3")) {
//            System.out.println("Finish!");
//            drawingLines();
//            exit();
//        } else {
//            incorrect();
//            showGameByDetails(store, gameIndex, userIndex, goBack);
//        }
//    }
//
//    public int checkHaving(Store store, int userIndex, int gameIndex) {
//        for (int i = 0; i < store.getUsers().get(userIndex).getMyGames().size(); i++) {
//            if (store.getUsers().get(userIndex).getMyGames().get(i) == store.getGames().get(gameIndex)) {
//                return 1;
//            }
//        }
//        return 0;
//    }
//
//    public void buyGame(Store store, int userIndex, int gameIndex) {
//        double currentBalance = store.getUsers().get(userIndex).getWallet();
//        double cost = store.getGames().get(gameIndex).getCost();
//        if (currentBalance < cost) {
//            System.out.println(ConsoleColors.RED + "Your balance is not enough!" + ConsoleColors.RESET);
//        } else {
//            store.getUsers().get(userIndex).getMyGames().add(store.getGames().get(gameIndex));
//            store.getUsers().get(userIndex).setWallet(currentBalance - cost);
//            System.out.println("Game bought successfully!");
//        }
//        userStore(store, userIndex);
//    }
//
//    public ArrayList searchGameByName(String name, Store store) {
//        ArrayList<Integer> indexes = new ArrayList<Integer>();
//        for (int i = 0; i < store.getGames().size(); i++) {
//            if (store.getGames().get(i).getName().toLowerCase().startsWith(name.toLowerCase())) {
//                indexes.add(i);
//            }
//        }
//        return indexes;
//    }
//
//    public ArrayList searchGameByFiltering(Store store, double beginningCost, double terminationCost) {
//        ArrayList<Integer> indexes = new ArrayList<Integer>();
//        for (int i = 0; i < store.getGames().size(); i++) {
//            if (store.getGames().get(i).getCost() >= beginningCost &&
//                    store.getGames().get(i).getCost() <= terminationCost) {
//                indexes.add(i);
//            }
//        }
//        return indexes;
//    }
//
//    public void showFoundGames(ArrayList<Integer> foundGames, Store store) {
//        int j = 1;
//        for (int i = 0; i < foundGames.size(); i++) {
//            System.out.print(+j + ")");
//            System.out.println(store.getGames().get(foundGames.get(i)).getName());
//            j++;
//        }
//    }
}
