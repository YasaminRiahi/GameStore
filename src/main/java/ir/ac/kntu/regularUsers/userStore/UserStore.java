package ir.ac.kntu.regularUsers.userStore;

import ir.ac.kntu.helpers.ConsoleColors;
import ir.ac.kntu.products.games.Games;
import ir.ac.kntu.products.games.GamesLevel;
import ir.ac.kntu.regularUsers.*;
import ir.ac.kntu.store.DaoWriter;
import ir.ac.kntu.store.DataBase;

import static ir.ac.kntu.helpers.TextTypings.*;
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
        switch (nextChoose) {
            case "1" -> showUserStoreOptions(userIndex, stopwatch1);
            case "2" -> {
                RegularUserPage regularUserPage = new RegularUserPage(dataBase);
                regularUserPage.userAccess(userIndex, stopwatch1);
            }
            case "3" -> {
                drawingLines();
                exit();
            }
            default -> {
                incorrect();
                userStore(userIndex, stopwatch1);
            }
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
            ListOfProducts listOfProducts = new ListOfProducts(dataBase);
            listOfProducts.listOfProducts(whichUser, stopwatch1);
        } else if (userStoreOptions == UserStoreOptions.SEARCH_IN_PRODUCTS) {
            SearchingInProducts searchInProducts = new SearchingInProducts(dataBase);
            searchInProducts.searchInProducts(whichUser,stopwatch1);
        } else if (userStoreOptions == UserStoreOptions.SHOW_PRODUCTS_BY_FILTERING_COST) {
            FilteringCostOfProducts filteringCostOfProducts = new FilteringCostOfProducts(dataBase);
            filteringCostOfProducts.filteringProducts(whichUser,stopwatch1);
        } else if (userStoreOptions == UserStoreOptions.SHOW_BEST_SELLING_PRODUCTS) {
            BestSellingProducts bestSellingProducts = new BestSellingProducts(dataBase);
            bestSellingProducts.bestSelling(whichUser,stopwatch1);
        } else {
            CostOfAList costOfAList = new CostOfAList(dataBase);
            costOfAList.listCost(whichUser,stopwatch1);
        }
    }

    public void showGameByDetails(int gameIndex) {
        System.out.println("1)Name : " + dataBase.getGames().get(gameIndex).getName());
        System.out.println("2)Genre : " + dataBase.getGames().get(gameIndex).getGenre());
        System.out.println("3)Description : " + dataBase.getGames().get(gameIndex).getDescription());
        System.out.println("4)Rating : " + dataBase.getGames().get(gameIndex).getRating());
        System.out.println("5)Number of rates : " + dataBase.getGames().get(gameIndex).getNumberOfRates());
        System.out.println("6)Cost :" + dataBase.getGames().get(gameIndex).getCost());
        System.out.println("7)Level :" + dataBase.getGames().get(gameIndex).getGamesLevel());
        if (!dataBase.getGames().get(gameIndex).isBeta()){
            System.out.println("7)Community :");
            int j = 1;
            for (int i = 0; i < dataBase.getGames().get(gameIndex).getCommunity().size(); i++) {
                System.out.println("    " + j + ")" + dataBase.getGames().get(gameIndex).getCommunity().get(i));
                j++;
            }
        }
    }

    public void showMonitorGamingByDetails(int index) {
        System.out.println("1)Name : " + dataBase.getMonitorGaming().get(index).getName());
        System.out.println("2)Description : " + dataBase.getMonitorGaming().get(index).getDescription());
        System.out.println("3)Cost : " + dataBase.getMonitorGaming().get(index).getCost());
        System.out.println("4)Number : " + dataBase.getMonitorGaming().get(index).getNumber());
        System.out.println("5)Screen size : " + dataBase.getMonitorGaming().get(index).getScreenSize());
        System.out.println("6)Refresh rate :" + dataBase.getMonitorGaming().get(index).getRefreshRate());
        System.out.println("7)Response time :" + dataBase.getMonitorGaming().get(index).getResponseTime());
        System.out.println("8)Community :");
        int j = 1;
        for (int i = 0; i < dataBase.getMonitorGaming().get(index).getCommunity().size(); i++) {
            System.out.println("    " + j + ")" + dataBase.getMonitorGaming().get(index).getCommunity().get(i));
            j++;
        }
    }

    public void showGamePadByDetails(int index) {
        System.out.println("1)Name : " + dataBase.getGamePads().get(index).getName());
        System.out.println("2)Description : " + dataBase.getGamePads().get(index).getDescription());
        System.out.println("3)Cost : " + dataBase.getGamePads().get(index).getCost());
        System.out.println("4)Number : " + dataBase.getGamePads().get(index).getNumber());
        System.out.println("5)Connection type : " + dataBase.getGamePads().get(index).getConnectionType());
        System.out.println("6)Device type :" + dataBase.getGamePads().get(index).getDeviceType());
        System.out.println("7)Community :");
        int j = 1;
        for (int i = 0; i < dataBase.getGamePads().get(index).getCommunity().size(); i++) {
            System.out.println("    " + j + ")" + dataBase.getGamePads().get(index).getCommunity().get(i));
            j++;
        }
    }

    public int checkHavingGame(int userIndex, int gameIndex) {
        if (dataBase.getRegularUsers().get(userIndex).getMyGames().contains(dataBase.getGames().get(gameIndex))) {
            return 1;
        }
        return 0;
    }

    public int checkHavingMonitor(int userIndex, int monitorIndex) {
        if (dataBase.getRegularUsers().get(userIndex).getMonitorGaming().containsKey(dataBase.getMonitorGaming().get(monitorIndex))) {
            return 1;
        }
        return 0;
    }

    public int checkHavingPad(int userIndex, int padIndex) {
        if (dataBase.getRegularUsers().get(userIndex).getGamePad().containsKey(dataBase.getGamePads().get(padIndex))) {
            return 1;
        }
        return 0;
    }

    public void buyGame(int userIndex, int gameIndex) {
        if (wantBuy().equals("1")) {
            double currentBalance = dataBase.getRegularUsers().get(userIndex).getWallet();
            double cost = dataBase.getGames().get(gameIndex).getCost();
            if (canBuy(userIndex, gameIndex) == 0) {
                cost = cost - ((discount(gameIndex) / 100) * cost);
                if (currentBalance < cost) {
                    System.out.println(ConsoleColors.RED + "Your balance is not enough!" + ConsoleColors.RESET);
                } else {
                    dataBase.getRegularUsers().get(userIndex).getMyGames().add(dataBase.getGames().get(gameIndex));
                    dataBase.getRegularUsers().get(userIndex).setWallet(currentBalance - cost);
                    dataBase.getGames().get(userIndex).setNumberOfSoldItems(dataBase.getGames().get(userIndex).getNumberOfSoldItems() + 1);
                    System.out.println("Game bought successfully!");
                    DaoWriter.writeData(dataBase);
                }
            } else {
                System.out.println(ConsoleColors.RED + "You can't buy this game because of game level and your score!"
                        + ConsoleColors.RESET);
            }
        }
    }

    public int canBuy(int userIndex, int gameIndex) {
        Games game = dataBase.getGames().get(gameIndex);
        RegularUser user = dataBase.getRegularUsers().get(userIndex);
        if (game.getGamesLevel() == GamesLevel.LEVEL_2 && user.getScore() < 20) {
            return 1;
        } else if (game.getGamesLevel() == GamesLevel.LEVEL_3 && user.getScore() < 50) {
            return 1;
        } else if (game.getGamesLevel() == GamesLevel.LEVEL_4 && user.getScore() < 100) {
            return 1;
        }
        return 0;
    }

    public int discount(int gameIndex) {
        Games game = dataBase.getGames().get(gameIndex);
        if (game.getGamesLevel() == GamesLevel.LEVEL_1) {
            return 0;
        } else if (game.getGamesLevel() == GamesLevel.LEVEL_2) {
            return 10;
        } else if (game.getGamesLevel() == GamesLevel.LEVEL_3) {
            return 20;
        }
        return 30;
    }

    public void buyMonitor(int userIndex, int index) {
        if (wantBuy().equals("1")) {
            double currentBalance = dataBase.getRegularUsers().get(userIndex).getWallet();
            double cost = dataBase.getMonitorGaming().get(index).getCost();
            if (currentBalance < cost) {
                System.out.println(ConsoleColors.RED + "Your balance is not enough!" + ConsoleColors.RESET);
            } else {
                if (checkHavingMonitor(userIndex, index) == 1) {
                    int number = dataBase.getRegularUsers().get(userIndex).getMonitorGaming().
                            get(dataBase.getMonitorGaming().get(index));
                    dataBase.getRegularUsers().get(userIndex).getMonitorGaming().
                            put(dataBase.getMonitorGaming().get(index), number + 1);
                } else {
                    dataBase.getRegularUsers().get(userIndex).getMonitorGaming().
                            put(dataBase.getMonitorGaming().get(index), 1);
                }
                dataBase.getMonitorGaming().get(index).setNumberOfSoldItems(dataBase.getMonitorGaming().get(index).getNumberOfSoldItems() + 1);
                dataBase.getMonitorGaming().get(index).setNumber(dataBase.getMonitorGaming().get(index).getNumber());
                dataBase.getRegularUsers().get(userIndex).setWallet(currentBalance - cost);
                System.out.println("Gaming monitor bought successfully!");
                DaoWriter.writeData(dataBase);
            }
        }
    }

    public void buyPad(int userIndex, int index) {
        if (wantBuy().equals("1")) {
            double currentBalance = dataBase.getRegularUsers().get(userIndex).getWallet();
            double cost = dataBase.getGamePads().get(index).getCost();
            if (currentBalance < cost) {
                System.out.println(ConsoleColors.RED + "Your balance is not enough!" + ConsoleColors.RESET);
            } else {
                if (checkHavingPad(userIndex, index) == 1) {
                    int number = dataBase.getRegularUsers().get(userIndex).getGamePad().
                            get(dataBase.getGamePads().get(index));
                    dataBase.getRegularUsers().get(userIndex).getGamePad().
                            put(dataBase.getGamePads().get(index), number + 1);
                } else {
                    dataBase.getRegularUsers().get(userIndex).getGamePad().
                            put(dataBase.getGamePads().get(index), 1);
                }
                dataBase.getGamePads().get(index).setNumberOfSoldItems(dataBase.getGamePads().get(index).getNumberOfSoldItems() + 1);
                dataBase.getGamePads().get(index).setNumber(dataBase.getGamePads().get(index).getNumber() - 1);
                dataBase.getRegularUsers().get(userIndex).setWallet(currentBalance - cost);
                System.out.println("Game pad bought successfully!");
                DaoWriter.writeData(dataBase);
            }
        }
    }
}