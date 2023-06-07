package ir.ac.kntu.regularUsers.userStore;

import ir.ac.kntu.helpers.ConsoleColors;
import ir.ac.kntu.regularUsers.Stopwatch1;
import ir.ac.kntu.store.DataBase;

import java.util.ArrayList;

import static ir.ac.kntu.helpers.Scan.scanString;
import static ir.ac.kntu.helpers.TextTypings.*;

public class SearchInProducts {

    private DataBase dataBase;

    private ProductSearchOptions productSearchOptions;

    public SearchInProducts(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public void searchInProducts(int userIndex, Stopwatch1 stopwatch1) {
        drawingLines();
        System.out.println(ConsoleColors.BLUE_BOLD + "******( SEARCH IN PRODUCTS )******" + ConsoleColors.RESET);
        String nextChoose = whereToGo();
        if (nextChoose.equals("1")) {
            showSearchOptions(userIndex, stopwatch1);
        } else if (nextChoose.equals("2")) {
            UserStore userStore = new UserStore(dataBase);
            userStore.userStore(userIndex, stopwatch1);
        } else if (nextChoose.equals("3")) {
            drawingLines();
            exit();
        } else {
            incorrect();
            searchInProducts(userIndex, stopwatch1);
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
            searchInAllProducts(whichUser, stopwatch1);
        } else if (productSearchOptions == ProductSearchOptions.SEARCH_IN_ALL_ACCESSORIES) {
            searchInAllAccessories(whichUser, stopwatch1);
        } else if (productSearchOptions == ProductSearchOptions.SEARCH_IN_GAMES) {
            searchInGames(whichUser, stopwatch1);
        } else if (productSearchOptions == ProductSearchOptions.SEARCH_IN_GAMING_MONITORS) {
            searchInMonitors(whichUser,stopwatch1);
        } else {
            searchInPads(whichUser,stopwatch1);
        }
    }

    public ArrayList searchGameByName(String name) {
        ArrayList<Integer> indexes = new ArrayList<Integer>();
        for (int i = 0; i < dataBase.getGames().size(); i++) {
            if (dataBase.getGames().get(i).getName().toLowerCase().startsWith(name.toLowerCase())) {
                indexes.add(i);
            }
        }
        return indexes;
    }

    public ArrayList searchMonitorByName(String name) {
        ArrayList<Integer> indexes = new ArrayList<Integer>();
        for (int i = 0; i < dataBase.getMonitorGaming().size(); i++) {
            if (dataBase.getMonitorGaming().get(i).getName().toLowerCase().startsWith(name.toLowerCase())) {
                indexes.add(i);
            }
        }
        return indexes;
    }

    public ArrayList searchPadByName(String name) {
        ArrayList<Integer> indexes = new ArrayList<Integer>();
        for (int i = 0; i < dataBase.getGamePads().size(); i++) {
            if (dataBase.getGamePads().get(i).getName().toLowerCase().startsWith(name.toLowerCase())) {
                indexes.add(i);
            }
        }
        return indexes;
    }

    public void searchInAllProducts(int whichUser, Stopwatch1 stopwatch1) {
        drawingLines();
        System.out.println(ConsoleColors.BLUE_BOLD + "******( SEARCH IN All PRODUCTS )******" + ConsoleColors.RESET);
        String nextChoose = whereToGo();
        if (nextChoose.equals("1")) {
            System.out.println("Enter name:");
            String name = scanString();
            showFoundProducts(searchGameByName(name), searchMonitorByName(name), searchPadByName(name));
            choose(whichUser, stopwatch1, searchGameByName(name), searchMonitorByName(name), searchPadByName(name));
        } else if (nextChoose.equals("2")) {
            searchInProducts(whichUser, stopwatch1);
        } else if (nextChoose.equals("3")) {
            drawingLines();
            exit();
        } else {
            incorrect();
            searchInAllProducts(whichUser, stopwatch1);
        }
    }

    public void searchInAllAccessories(int whichUser, Stopwatch1 stopwatch1) {
        drawingLines();
        System.out.println(ConsoleColors.BLUE_BOLD + "******( SEARCH IN All ACCESSORIES )******" + ConsoleColors.RESET);
        String nextChoose = whereToGo();
        if (nextChoose.equals("1")) {
            System.out.println("Enter name:");
            String name = scanString();
            showFoundProducts(new ArrayList<Integer>(), searchMonitorByName(name), searchPadByName(name));
            choose(whichUser, stopwatch1, new ArrayList<Integer>(), searchMonitorByName(name), searchPadByName(name));
        } else if (nextChoose.equals("2")) {
            searchInProducts(whichUser, stopwatch1);
        } else if (nextChoose.equals("3")) {
            drawingLines();
            exit();
        } else {
            incorrect();
            searchInAllProducts(whichUser, stopwatch1);
        }
    }

    public void searchInGames(int whichUser, Stopwatch1 stopwatch1) {
        drawingLines();
        System.out.println(ConsoleColors.BLUE_BOLD + "******( SEARCH IN GAMES )******" + ConsoleColors.RESET);
        String nextChoose = whereToGo();
        if (nextChoose.equals("1")) {
            System.out.println("Enter name:");
            String name = scanString();
            showFoundProducts(searchGameByName(name), new ArrayList<Integer>(), new ArrayList<Integer>());
            choose(whichUser, stopwatch1, searchGameByName(name), new ArrayList<Integer>(), new ArrayList<Integer>());
        } else if (nextChoose.equals("2")) {
            searchInProducts(whichUser, stopwatch1);
        } else if (nextChoose.equals("3")) {
            drawingLines();
            exit();
        } else {
            incorrect();
            searchInAllProducts(whichUser, stopwatch1);
        }
    }

    public void searchInMonitors(int whichUser, Stopwatch1 stopwatch1) {
        drawingLines();
        System.out.println(ConsoleColors.BLUE_BOLD + "******( SEARCH IN MONITORS )******" + ConsoleColors.RESET);
        String nextChoose = whereToGo();
        if (nextChoose.equals("1")) {
            System.out.println("Enter name:");
            String name = scanString();
            showFoundProducts(new ArrayList<Integer>(), searchMonitorByName(name), new ArrayList<Integer>());
            choose(whichUser, stopwatch1, new ArrayList<Integer>(), searchMonitorByName(name), new ArrayList<Integer>());
        } else if (nextChoose.equals("2")) {
            searchInProducts(whichUser, stopwatch1);
        } else if (nextChoose.equals("3")) {
            drawingLines();
            exit();
        } else {
            incorrect();
            searchInAllProducts(whichUser, stopwatch1);
        }
    }

    public void searchInPads(int whichUser, Stopwatch1 stopwatch1) {
        drawingLines();
        System.out.println(ConsoleColors.BLUE_BOLD + "******( SEARCH IN PADS )******" + ConsoleColors.RESET);
        String nextChoose = whereToGo();
        if (nextChoose.equals("1")) {
            System.out.println("Enter name:");
            String name = scanString();
            showFoundProducts(new ArrayList<Integer>(), new ArrayList<Integer>(), searchPadByName(name));
            choose(whichUser, stopwatch1, new ArrayList<Integer>(), new ArrayList<Integer>(), searchPadByName(name));
        } else if (nextChoose.equals("2")) {
            searchInProducts(whichUser, stopwatch1);
        } else if (nextChoose.equals("3")) {
            drawingLines();
            exit();
        } else {
            incorrect();
            searchInAllProducts(whichUser, stopwatch1);
        }
    }

    public void showFoundProducts(ArrayList<Integer> foundGames, ArrayList<Integer> foundMonitors, ArrayList<Integer> foundPads) {
        int j = 1;
        if (foundGames.size() != 0) {
            System.out.println(ConsoleColors.PURPLE + "Games :" + ConsoleColors.RESET);
            for (int i = 0; i < foundGames.size(); i++) {
                System.out.print(+j + ")");
                System.out.println(dataBase.getGames().get(foundGames.get(i)).getName());
                j++;
            }
        }
        j = 1;
        if (foundMonitors.size() != 0) {
            System.out.println(ConsoleColors.PURPLE + "Gaming monitors :" + ConsoleColors.RESET);
            for (int i = 0; i < foundMonitors.size(); i++) {
                System.out.print(+j + ")");
                System.out.println(dataBase.getMonitorGaming().get(foundMonitors.get(i)).getName());
                j++;
            }
        }
        j = 1;
        if (foundPads.size() != 0) {
            System.out.println(ConsoleColors.PURPLE + "Game pads :" + ConsoleColors.RESET);
            for (int i = 0; i < foundPads.size(); i++) {
                System.out.print(+j + ")");
                System.out.println(dataBase.getGamePads().get(foundPads.get(i)).getName());
                j++;
            }
        }
    }

    public void choose(int whichUser, Stopwatch1 stopwatch1, ArrayList<Integer> foundGames, ArrayList<Integer> foundMonitors,
                       ArrayList<Integer> foundPads) {
        if (foundGames.size() != 0 && foundMonitors.size() != 0 && foundPads.size() != 0) {
            System.out.println("Which product do you want to choose?");
            System.out.println("1)Games");
            System.out.println("2)Monitor Gaming");
            System.out.println("3)Game pad");
            String whichProduct = scanString();
            chooseFromAll(whichUser, stopwatch1, foundGames, foundMonitors, foundPads, whichProduct);
        } else if (foundGames.size() == 0 && foundMonitors.size() != 0 && foundPads.size() != 0) {
            System.out.println("Which product do you want to choose?");
            System.out.println("1)Monitor Gaming");
            System.out.println("2)Game pad");
            String whichProduct = scanString();
            chooseFromMOrP(whichUser, stopwatch1, foundMonitors, foundPads, whichProduct);
        } else if (foundGames.size() != 0 && foundMonitors.size() == 0 && foundPads.size() != 0) {
            System.out.println("Which product do you want to choose?");
            System.out.println("1)Games");
            System.out.println("2)Game pad");
            String whichProduct = scanString();
            chooseFromGOrP(whichUser, stopwatch1, foundMonitors, foundPads, whichProduct);
        } else if (foundGames.size() != 0 && foundMonitors.size() != 0 && foundPads.size() == 0) {
            System.out.println("Which product do you want to choose?");
            System.out.println("1)Games");
            System.out.println("2)Monitor Gaming");
            String whichProduct = scanString();
            chooseFromGOrM(whichUser, stopwatch1, foundGames, foundMonitors, whichProduct);
        } else if (foundGames.size() != 0 && foundMonitors.size() == 0 && foundPads.size() == 0) {
            chooseFromGames(whichUser, stopwatch1, foundGames);
        } else if (foundGames.size() == 0 && foundMonitors.size() != 0 && foundPads.size() == 0) {
            chooseFromMonitors(whichUser, stopwatch1, foundMonitors);
        } else if (foundGames.size() == 0 && foundMonitors.size() == 0 && foundPads.size() != 0) {
            chooseFromPads(whichUser, stopwatch1, foundPads);
        } else {
            System.out.println(ConsoleColors.RED + "No result!Try again" + ConsoleColors.RESET);
        }
        searchInProducts(whichUser, stopwatch1);
    }

    public void chooseFromAll(int whichUser, Stopwatch1 stopwatch1, ArrayList<Integer> foundGames, ArrayList<Integer> foundMonitors,
                              ArrayList<Integer> foundPads, String whichProduct) {
        UserStore userStore = new UserStore(dataBase);
        switch (whichProduct) {
            case "1":
                System.out.println("Which game?");
                int whichGame = foundGames.get(Integer.parseInt(scanString()) - 1);
                userStore.showGameByDetails(whichGame);
                if (userStore.checkHavingGame(whichUser, whichGame) == 0) {
                    userStore.buyGame(whichUser, whichGame);
                }
                searchInProducts(whichUser, stopwatch1);
                break;
            case "2":
                System.out.println("Which monitor gaming?");
                int whichMonitor = foundMonitors.get(Integer.parseInt(scanString()) - 1);
                userStore.showMonitorGamingByDetails(whichMonitor);
                userStore.buyMonitor(whichUser, whichMonitor);
                searchInProducts(whichUser, stopwatch1);
                break;
            case "3":
                System.out.println("Which game pad?");
                int whichPad = foundPads.get(Integer.parseInt(scanString()) - 1);
                userStore.showGamePadByDetails(whichPad);
                userStore.buyPad(whichUser, whichPad);
                searchInProducts(whichUser, stopwatch1);
                break;
            default:
                incorrect();
                searchInProducts(whichUser, stopwatch1);
                break;
        }
    }

    public void chooseFromMOrP(int whichUser, Stopwatch1 stopwatch1, ArrayList<Integer> foundMonitors,
                               ArrayList<Integer> foundPads, String whichProduct) {
        UserStore userStore = new UserStore(dataBase);
        switch (whichProduct) {
            case "1":
                System.out.println("Which monitor gaming?");
                int whichMonitor = foundMonitors.get(Integer.parseInt(scanString()) - 1);
                userStore.showMonitorGamingByDetails(whichMonitor);
                userStore.buyMonitor(whichUser, whichMonitor);
                searchInProducts(whichUser, stopwatch1);
                break;
            case "2":
                System.out.println("Which game pad?");
                int whichPad = foundPads.get(Integer.parseInt(scanString()) - 1);
                userStore.showGamePadByDetails(whichPad);
                userStore.buyPad(whichUser, whichPad);
                searchInProducts(whichUser, stopwatch1);
                break;
            default:
                incorrect();
                searchInProducts(whichUser, stopwatch1);
                break;
        }
    }

    public void chooseFromGOrP(int whichUser, Stopwatch1 stopwatch1, ArrayList<Integer> foundGames,
                               ArrayList<Integer> foundPads, String whichProduct) {
        UserStore userStore = new UserStore(dataBase);
        switch (whichProduct) {
            case "1":
                System.out.println("Which game?");
                int whichGame = foundGames.get(Integer.parseInt(scanString()) - 1);
                userStore.showGameByDetails(whichGame);
                if (userStore.checkHavingGame(whichUser, whichGame) == 0) {
                    userStore.buyGame(whichUser, whichGame);
                }
                searchInProducts(whichUser, stopwatch1);
                break;
            case "2":
                System.out.println("Which game pad?");
                int whichPad = foundPads.get(Integer.parseInt(scanString()) - 1);
                userStore.showGamePadByDetails(whichPad);
                userStore.buyPad(whichUser, whichPad);
                searchInProducts(whichUser, stopwatch1);
                break;
            default:
                incorrect();
                searchInProducts(whichUser, stopwatch1);
                break;
        }
    }

    public void chooseFromGOrM(int whichUser, Stopwatch1 stopwatch1, ArrayList<Integer> foundGames, ArrayList<Integer> foundMonitors,
                               String whichProduct) {
        UserStore userStore = new UserStore(dataBase);
        switch (whichProduct) {
            case "1":
                System.out.println("Which game?");
                int whichGame = foundGames.get(Integer.parseInt(scanString()) - 1);
                userStore.showGameByDetails(whichGame);
                if (userStore.checkHavingGame(whichUser, whichGame) == 0) {
                    userStore.buyGame(whichUser, whichGame);
                }
                searchInProducts(whichUser, stopwatch1);
                break;
            case "2":
                System.out.println("Which monitor gaming?");
                int whichMonitor = foundMonitors.get(Integer.parseInt(scanString()) - 1);
                userStore.showMonitorGamingByDetails(whichMonitor);
                userStore.buyMonitor(whichUser, whichMonitor);
                searchInProducts(whichUser, stopwatch1);
                break;
            default:
                incorrect();
                searchInProducts(whichUser, stopwatch1);
                break;
        }
    }

    public void chooseFromGames(int whichUser, Stopwatch1 stopwatch1, ArrayList<Integer> foundGames) {
        UserStore userStore = new UserStore(dataBase);
        System.out.println("Which game?");
        int whichGame = foundGames.get(Integer.parseInt(scanString()) - 1);
        userStore.showGameByDetails(whichGame);
        if (userStore.checkHavingGame(whichUser, whichGame) == 0) {
            userStore.buyGame(whichUser, whichGame);
        }
        searchInProducts(whichUser, stopwatch1);
    }

    public void chooseFromMonitors(int whichUser, Stopwatch1 stopwatch1, ArrayList<Integer> foundMonitors) {
        UserStore userStore = new UserStore(dataBase);
        System.out.println("Which monitor gaming?");
        int whichMonitor = foundMonitors.get(Integer.parseInt(scanString()) - 1);
        userStore.showMonitorGamingByDetails(whichMonitor);
        userStore.buyMonitor(whichUser, whichMonitor);
        searchInProducts(whichUser, stopwatch1);
    }

    public void chooseFromPads(int whichUser, Stopwatch1 stopwatch1, ArrayList<Integer> foundPads) {
        UserStore userStore = new UserStore(dataBase);
        System.out.println("Which game pad?");
        int whichPad = foundPads.get(Integer.parseInt(scanString()) - 1);
        userStore.showGamePadByDetails(whichPad);
        userStore.buyPad(whichUser, whichPad);
        searchInProducts(whichUser, stopwatch1);

    }
}