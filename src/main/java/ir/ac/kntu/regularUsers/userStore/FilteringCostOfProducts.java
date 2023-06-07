package ir.ac.kntu.regularUsers.userStore;

import ir.ac.kntu.helpers.ConsoleColors;
import ir.ac.kntu.regularUsers.Stopwatch1;
import ir.ac.kntu.store.DataBase;

import java.util.ArrayList;

import static ir.ac.kntu.helpers.Scanner.scanDouble;
import static ir.ac.kntu.helpers.TextTypings.*;
import static ir.ac.kntu.helpers.TextTypings.incorrect;

public class FilteringCostOfProducts {

    private DataBase dataBase;

    private ProductSearchOptions productSearchOptions;

    public FilteringCostOfProducts(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public void filteringProducts(int userIndex, Stopwatch1 stopwatch1) {
        drawingLines();
        System.out.println(ConsoleColors.BLUE_BOLD + "******( FILTERING COST OF PRODUCTS )******" + ConsoleColors.RESET);
        String nextChoose = whereToGo();
        switch (nextChoose) {
            case "1" -> showSearchOptions(userIndex, stopwatch1);
            case "2" -> {
                UserStore userStore = new UserStore(dataBase);
                userStore.userStore(userIndex, stopwatch1);
            }
            case "3" -> {
                drawingLines();
                exit();
            }
            default -> {
                incorrect();
                filteringProducts(userIndex, stopwatch1);
            }
        }
    }

    public void showSearchOptions(int whichUser, Stopwatch1 stopwatch1) {
        for (ProductSearchOptions productSearchOptions1 : ProductSearchOptions.values()) {
            System.out.print(productSearchOptions1.getValue() + ")");
            System.out.println(productSearchOptions1);
        }
        fromValue(getNumberFromOptions(), whichUser, stopwatch1);
    }

    public void fromValue(String value, int whichUser, Stopwatch1 stopwatch1) {
        for (ProductSearchOptions e : ProductSearchOptions.values()) {
            if (e.getValue().equals(value)) {
                this.productSearchOptions = e;
                goToOptions(whichUser, stopwatch1);
            }
        }
        incorrect();
        showSearchOptions(whichUser, stopwatch1);
    }

    public void goToOptions(int whichUser, Stopwatch1 stopwatch1) {
        if (productSearchOptions == ProductSearchOptions.SEARCH_IN_ALL_PRODUCTS) {
            filteringAllProducts(whichUser, stopwatch1, new SearchingInProducts(dataBase));
        } else if (productSearchOptions == ProductSearchOptions.SEARCH_IN_ALL_ACCESSORIES) {
            filteringAllAccessories(whichUser, stopwatch1, new SearchingInProducts(dataBase));
        } else if (productSearchOptions == ProductSearchOptions.SEARCH_IN_GAMES) {
            filteringGames(whichUser, stopwatch1, new SearchingInProducts(dataBase));
        } else if (productSearchOptions == ProductSearchOptions.SEARCH_IN_GAMING_MONITORS) {
            filteringMonitors(whichUser, stopwatch1, new SearchingInProducts(dataBase));
        } else {
            filteringPads(whichUser, stopwatch1, new SearchingInProducts(dataBase));
        }
    }

    public void filteringAllProducts(int whichUser, Stopwatch1 stopwatch1, SearchingInProducts searchInProducts) {
        drawingLines();
        System.out.println(ConsoleColors.BLUE_BOLD + "******( FILTERING All PRODUCTS )******" + ConsoleColors.RESET);
        String nextChoose = whereToGo();
        switch (nextChoose) {
            case "1" -> {
                System.out.println("Enter beginning cost and termination cost :");
                double beginningCost = scanDouble();
                double terminationCost = scanDouble();
                searchInProducts.showFoundProducts(searchGameByFiltering(beginningCost, terminationCost),
                        searchMonitorsByFiltering(beginningCost, terminationCost), searchPadsByFiltering(beginningCost, terminationCost));
                searchInProducts.choose(whichUser, stopwatch1, searchGameByFiltering(beginningCost, terminationCost),
                        searchMonitorsByFiltering(beginningCost, terminationCost), searchPadsByFiltering(beginningCost, terminationCost));
                filteringProducts(whichUser, stopwatch1);
            }
            case "2" -> filteringProducts(whichUser, stopwatch1);
            case "3" -> {
                drawingLines();
                exit();
            }
            default -> {
                incorrect();
                filteringAllProducts(whichUser, stopwatch1, searchInProducts);
            }
        }
    }

    public void filteringAllAccessories(int whichUser, Stopwatch1 stopwatch1, SearchingInProducts searchInProducts) {
        drawingLines();
        System.out.println(ConsoleColors.BLUE_BOLD + "******( Filtering All ACCESSORIES )******" + ConsoleColors.RESET);
        String nextChoose = whereToGo();
        switch (nextChoose) {
            case "1" -> {
                System.out.println("Enter beginning cost and termination cost :");
                double beginningCost = scanDouble();
                double terminationCost = scanDouble();
                searchInProducts.showFoundProducts(new ArrayList<Integer>(), searchMonitorsByFiltering(beginningCost, terminationCost),
                        searchPadsByFiltering(beginningCost, terminationCost));
                searchInProducts.choose(whichUser, stopwatch1, new ArrayList<Integer>(), searchMonitorsByFiltering(beginningCost, terminationCost),
                        searchPadsByFiltering(beginningCost, terminationCost));
                filteringProducts(whichUser, stopwatch1);
            }
            case "2" -> filteringProducts(whichUser, stopwatch1);
            case "3" -> {
                drawingLines();
                exit();
            }
            default -> {
                incorrect();
                filteringAllAccessories(whichUser, stopwatch1, searchInProducts);
            }
        }
    }

    public void filteringGames(int whichUser, Stopwatch1 stopwatch1, SearchingInProducts searchInProducts) {
        drawingLines();
        System.out.println(ConsoleColors.BLUE_BOLD + "******( FILTERING GAMES )******" + ConsoleColors.RESET);
        String nextChoose = whereToGo();
        switch (nextChoose) {
            case "1" -> {
                System.out.println("Enter beginning cost and termination cost :");
                double beginningCost = scanDouble();
                double terminationCost = scanDouble();
                searchInProducts.showFoundProducts(searchGameByFiltering(beginningCost, terminationCost), new ArrayList<Integer>(),
                        new ArrayList<Integer>());
                searchInProducts.choose(whichUser, stopwatch1, searchGameByFiltering(beginningCost, terminationCost), new ArrayList<Integer>(),
                        new ArrayList<Integer>());
                filteringProducts(whichUser, stopwatch1);
            }
            case "2" -> filteringProducts(whichUser, stopwatch1);
            case "3" -> {
                drawingLines();
                exit();
            }
            default -> {
                incorrect();
                filteringGames(whichUser, stopwatch1, searchInProducts);
            }
        }
    }

    public void filteringMonitors(int whichUser, Stopwatch1 stopwatch1, SearchingInProducts searchInProducts) {
        drawingLines();
        System.out.println(ConsoleColors.BLUE_BOLD + "******( FILTERING GAMING MONITORS )******" + ConsoleColors.RESET);
        String nextChoose = whereToGo();
        switch (nextChoose) {
            case "1" -> {
                System.out.println("Enter beginning cost and termination cost :");
                double beginningCost = scanDouble();
                double terminationCost = scanDouble();
                searchInProducts.showFoundProducts(searchGameByFiltering(beginningCost, terminationCost), new ArrayList<Integer>(),
                        new ArrayList<Integer>());
                searchInProducts.choose(whichUser, stopwatch1, searchGameByFiltering(beginningCost, terminationCost), new ArrayList<Integer>(),
                        new ArrayList<Integer>());
                filteringProducts(whichUser, stopwatch1);
            }
            case "2" -> filteringProducts(whichUser, stopwatch1);
            case "3" -> {
                drawingLines();
                exit();
            }
            default -> {
                incorrect();
                filteringMonitors(whichUser, stopwatch1, searchInProducts);
            }
        }
    }

    public void filteringPads(int whichUser, Stopwatch1 stopwatch1, SearchingInProducts searchInProducts) {
        drawingLines();
        System.out.println(ConsoleColors.BLUE_BOLD + "******( FILTERING GAME PADS )******" + ConsoleColors.RESET);
        String nextChoose = whereToGo();
        switch (nextChoose) {
            case "1" -> {
                System.out.println("Enter beginning cost and termination cost :");
                double beginningCost = scanDouble();
                double terminationCost = scanDouble();
                searchInProducts.showFoundProducts(searchGameByFiltering(beginningCost, terminationCost), new ArrayList<Integer>(),
                        new ArrayList<Integer>());
                searchInProducts.choose(whichUser, stopwatch1, searchGameByFiltering(beginningCost, terminationCost), new ArrayList<Integer>(),
                        new ArrayList<Integer>());
                filteringProducts(whichUser, stopwatch1);
            }
            case "2" -> filteringProducts(whichUser, stopwatch1);
            case "3" -> {
                drawingLines();
                exit();
            }
            default -> {
                incorrect();
                filteringPads(whichUser, stopwatch1, searchInProducts);
            }
        }
    }

    public ArrayList searchGameByFiltering(double beginningCost, double terminationCost) {
        ArrayList<Integer> indexes = new ArrayList<Integer>();
        for (int i = 0; i < dataBase.getGames().size(); i++) {
            if (dataBase.getGames().get(i).getCost() >= beginningCost &&
                    dataBase.getGames().get(i).getCost() <= terminationCost) {
                indexes.add(i);
            }
        }
        return indexes;
    }

    public ArrayList searchMonitorsByFiltering(double beginningCost, double terminationCost) {
        ArrayList<Integer> indexes = new ArrayList<Integer>();
        for (int i = 0; i < dataBase.getMonitorGaming().size(); i++) {
            if (dataBase.getMonitorGaming().get(i).getCost() >= beginningCost &&
                    dataBase.getMonitorGaming().get(i).getCost() <= terminationCost) {
                indexes.add(i);
            }
        }
        return indexes;
    }

    public ArrayList searchPadsByFiltering(double beginningCost, double terminationCost) {
        ArrayList<Integer> indexes = new ArrayList<Integer>();
        for (int i = 0; i < dataBase.getGamePads().size(); i++) {
            if (dataBase.getGamePads().get(i).getCost() >= beginningCost &&
                    dataBase.getGamePads().get(i).getCost() <= terminationCost) {
                indexes.add(i);
            }
        }
        return indexes;
    }
}