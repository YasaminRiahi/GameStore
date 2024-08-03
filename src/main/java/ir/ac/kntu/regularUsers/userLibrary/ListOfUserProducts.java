package ir.ac.kntu.regularUsers.userLibrary;

import ir.ac.kntu.helpers.ConsoleColors;
import ir.ac.kntu.products.accessories.gamePad.GamePad;
import ir.ac.kntu.products.accessories.monitorGaming.MonitorGaming;
import ir.ac.kntu.products.games.Games;
import ir.ac.kntu.regularUsers.Stopwatch1;
import ir.ac.kntu.regularUsers.userStore.UserStore;
import ir.ac.kntu.store.DaoWriter;
import ir.ac.kntu.store.DataBase;

import static ir.ac.kntu.helpers.Scanner.scanDouble;
import static ir.ac.kntu.helpers.Scanner.scanString;
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
        switch (nextChoose) {
            case "1" -> {
                showProducts(whichUser);
                choose(whichUser, stopwatch1);
            }
            case "2" -> {
                UserStore userStore = new UserStore(dataBase);
                userStore.userStore(whichUser, stopwatch1);
            }
            case "3" -> {
                drawingLines();
                exit();
            }
            default -> {
                incorrect();
                listOfProducts(whichUser, stopwatch1);
            }
        }
    }

    public void showProducts(int whichUser) {
        if (dataBase.getRegularUsers().get(whichUser).getMyGames().size() != 0) {
            System.out.println(ConsoleColors.PURPLE_BOLD + "GAMES:" + ConsoleColors.RESET);
            int j = 1;
            for (int i = 0; i < dataBase.getRegularUsers().get(whichUser).getMyGames().size(); i++) {
                int length = 5 + dataBase.getRegularUsers().get(whichUser).getMyGames().get(i).getName().length();
                drawForGames(length);
                System.out.print(ConsoleColors.BLUE + "*" + ConsoleColors.RESET);
                System.out.print(j + ")"+dataBase.getRegularUsers().get(whichUser).getMyGames().get(i).getName());
                System.out.println(ConsoleColors.BLUE + "*" + ConsoleColors.RESET);
                drawForGames(length);
                j++;
            }
        }
        if (dataBase.getRegularUsers().get(whichUser).getMonitorGaming().size() != 0) {
            int j = 1;
            System.out.println(ConsoleColors.PURPLE_BOLD + "GAMING MONITORS:" + ConsoleColors.RESET);
            MonitorGaming[] monitorGaming = dataBase.getRegularUsers().get(whichUser).getMonitorGaming().keySet().toArray(new MonitorGaming[0]);
            for (MonitorGaming gaming : monitorGaming) {
                int length = 5 + gaming.getName().length();
                drawForAccessories(length);
                System.out.print(ConsoleColors.BLUE + "|" + ConsoleColors.RESET);
                System.out.print(j + ")" + gaming.getName());
                System.out.println(ConsoleColors.BLUE + "|" + ConsoleColors.RESET);
                drawForGames(length);
                j++;
            }
        }
        if (dataBase.getRegularUsers().get(whichUser).getGamePad().size() != 0) {
            int j = 1;
            System.out.println(ConsoleColors.PURPLE_BOLD + "GAME PADS:" + ConsoleColors.RESET);
            GamePad[] gamePads = dataBase.getRegularUsers().get(whichUser).getGamePad().keySet().toArray(new GamePad[0]);
            for (GamePad gamePad : gamePads) {
                int length = 5 + gamePad.getName().length();
                drawForAccessories(length);
                System.out.print(ConsoleColors.BLUE + "|" + ConsoleColors.RESET);
                System.out.print(j + ")" + gamePad.getName());
                System.out.println(ConsoleColors.BLUE + "|" + ConsoleColors.RESET);
                drawForGames(length);
                j++;
            }
        }
    }

    public void choose(int whichUser, Stopwatch1 stopwatch1) {
        System.out.println("Which product do you want to choose?");
        System.out.println("1)Games");
        System.out.println("2)Monitor Gaming");
        System.out.println("3)Game pad");
        String whichProduct = scanString();
        UserLibrary userLibrary = new UserLibrary(dataBase);
        switch (whichProduct) {
            case "1" -> {
                System.out.println("Which game?");
                int whichGame = Integer.parseInt(scanString()) - 1;
                userLibrary.showGameByDetails(whichUser, whichGame);
                if (dataBase.getRegularUsers().get(whichUser).getMyGames().get(whichGame).isBeta()) {
                    addFeedbackOrRate(whichUser, whichGame, stopwatch1);
                } else {
                    addCommunityOrRate(whichUser, whichGame, stopwatch1);
                }
                listOfProducts(whichUser, stopwatch1);
            }
            case "2" -> {
                System.out.println("Which monitor gaming?");
                int whichMonitor = Integer.parseInt(scanString()) - 1;
                userLibrary.showMonitorGamingByDetails(whichMonitor, dataBase.getRegularUsers().get(whichUser).
                        getMonitorGaming().keySet().toArray(new MonitorGaming[0]));
                addCommunityOrReportMonitor(whichUser, dataBase.getRegularUsers().get(whichUser).
                        getMonitorGaming().keySet().toArray(new MonitorGaming[0])[whichMonitor], stopwatch1);
                listOfProducts(whichUser, stopwatch1);
            }
            case "3" -> {
                System.out.println("Which game pad?");
                int whichPad = Integer.parseInt(scanString()) - 1;
                userLibrary.showGamePadByDetails(whichPad, dataBase.getRegularUsers().get(whichUser).getGamePad().
                        keySet().toArray(new GamePad[0]));
                addCommunityOrReportPad(whichUser, dataBase.getRegularUsers().get(whichUser).getGamePad().
                        keySet().toArray(new GamePad[0])[whichPad], stopwatch1);
                listOfProducts(whichUser, stopwatch1);
            }
            default -> {
                incorrect();
                showProducts(whichUser);
            }
        }
    }

    public void addCommunityOrRate(int whichUser, int whichGame, Stopwatch1 stopwatch1) {
        switch (communityOrRate()) {
            case "1" -> gameCommunity(whichUser, whichGame, stopwatch1);
            case "2" -> rateGame(whichUser, whichGame, stopwatch1);
            case "3" -> listOfProducts(whichUser, stopwatch1);
            default -> {
                incorrect();
                listOfProducts(whichUser, stopwatch1);
            }
        }
    }

    public void addFeedbackOrRate(int whichUser, int whichGame, Stopwatch1 stopwatch1) {
        switch (feedbackOrRate()) {
            case "1" -> gameFeedback(whichUser, whichGame, stopwatch1);
            case "2" -> rateGame(whichUser, whichGame, stopwatch1);
            case "3" -> listOfProducts(whichUser, stopwatch1);
            default -> {
                incorrect();
                listOfProducts(whichUser, stopwatch1);
            }
        }
    }

    public void gameCommunity(int userIndex, int gameIndex, Stopwatch1 stopwatch1) {
        drawingLines();
        System.out.println(ConsoleColors.BLUE_BOLD + "******( GAME COMMUNITY )******" + ConsoleColors.RESET);
        String nextChoose = whereToGo();
        Games game = dataBase.getRegularUsers().get(userIndex).getMyGames().get(gameIndex);
        switch (nextChoose) {
            case "1" -> {
                System.out.println("Enter your idea:");
                game.getCommunity().add(scanString());
                System.out.println("Your idea added successfully!");
                DaoWriter.writeData(dataBase);
                listOfProducts(userIndex, stopwatch1);
            }
            case "2" -> listOfProducts(userIndex, stopwatch1);
            case "3" -> {
                drawingLines();
                exit();
            }
            default -> {
                incorrect();
                gameCommunity(gameIndex, userIndex, stopwatch1);
            }
        }
    }

    public void gameFeedback(int userIndex, int gameIndex, Stopwatch1 stopwatch1) {
        drawingLines();
        System.out.println(ConsoleColors.BLUE_BOLD + "******( GAME FEEDBACK )******" + ConsoleColors.RESET);
        String nextChoose = whereToGo();
        Games game = dataBase.getRegularUsers().get(userIndex).getMyGames().get(gameIndex);
        switch (nextChoose) {
            case "1" -> {
                System.out.println("Enter your idea:");
                game.getFeedback().add(scanString());
                System.out.println("Your idea added successfully!");
                DaoWriter.writeData(dataBase);
                listOfProducts(userIndex, stopwatch1);
            }
            case "2" -> listOfProducts(userIndex, stopwatch1);
            case "3" -> {
                drawingLines();
                exit();
            }
            default -> {
                incorrect();
                gameFeedback(userIndex, gameIndex, stopwatch1);
            }
        }
    }

    public void rateGame(int userIndex, int gameIndex, Stopwatch1 stopwatch1) {
        drawingLines();
        System.out.println(ConsoleColors.BLUE_BOLD + "******( SET YOUR RATE )******" + ConsoleColors.RESET);
        String nextChoose = whereToGo();
        Games game = dataBase.getRegularUsers().get(userIndex).getMyGames().get(gameIndex);
        switch (nextChoose) {
            case "1" -> {
                System.out.println("Enter your rate:");
                game.addRate(dataBase.getRegularUsers().get(userIndex), scanDouble());
                game.updateRate();
                System.out.println("You rated successfully!");
                DaoWriter.writeData(dataBase);
                listOfProducts(userIndex, stopwatch1);
            }
            case "2" -> listOfProducts(userIndex, stopwatch1);
            case "3" -> {
                drawingLines();
                exit();
            }
            default -> {
                incorrect();
                rateGame(gameIndex, userIndex, stopwatch1);
            }
        }
    }

    public void addCommunityOrReportPad(int whichUser, GamePad gamePad, Stopwatch1 stopwatch1) {
        switch (communityOrReport()) {
            case "1" -> gamePadCommunity(whichUser, gamePad, stopwatch1);
            case "2" -> gamePadReport(whichUser, gamePad, stopwatch1);
            case "3" -> listOfProducts(whichUser, stopwatch1);
            default -> {
                incorrect();
                listOfProducts(whichUser, stopwatch1);
            }
        }
    }

    public void gamePadCommunity(int whichUser, GamePad gamePad, Stopwatch1 stopwatch1) {
        drawingLines();
        System.out.println(ConsoleColors.BLUE_BOLD + "******( GAME PAD COMMUNITY )******" + ConsoleColors.RESET);
        String nextChoose = whereToGo();
        switch (nextChoose) {
            case "1" -> {
                System.out.println("Enter your idea:");
                gamePad.getCommunity().add(scanString());
                System.out.println("Your idea added successfully!");
                DaoWriter.writeData(dataBase);
                listOfProducts(whichUser, stopwatch1);
            }
            case "2" -> listOfProducts(whichUser, stopwatch1);
            case "3" -> {
                drawingLines();
                exit();
            }
            default -> {
                incorrect();
                gamePadCommunity(whichUser, gamePad, stopwatch1);
            }
        }
    }

    public void gamePadReport(int whichUser, GamePad gamePad, Stopwatch1 stopwatch1) {
        drawingLines();
        System.out.println(ConsoleColors.BLUE_BOLD + "******( GAME PAD REPORT )******" + ConsoleColors.RESET);
        String nextChoose = whereToGo();
        switch (nextChoose) {
            case "1" -> {
                System.out.println("Enter your report massage:");
                gamePad.getSellers().get(0).getReportMassage().add(gamePad.getName() + "  ->  "+scanString());
                System.out.println("Your massage sent successfully!");
                DaoWriter.writeData(dataBase);
                listOfProducts(whichUser, stopwatch1);
            }
            case "2" -> listOfProducts(whichUser, stopwatch1);
            case "3" -> {
                drawingLines();
                exit();
            }
            default -> {
                incorrect();
                gamePadReport(whichUser, gamePad, stopwatch1);
            }
        }
    }

    public void addCommunityOrReportMonitor(int whichUser, MonitorGaming monitorGaming, Stopwatch1 stopwatch1) {
        switch (communityOrReport()) {
            case "1" -> monitorCommunity(whichUser, monitorGaming, stopwatch1);
            case "2" -> monitorReport(whichUser, monitorGaming, stopwatch1);
            case "3" -> listOfProducts(whichUser, stopwatch1);
            default -> {
                incorrect();
                listOfProducts(whichUser, stopwatch1);
            }
        }
    }

    public void monitorCommunity(int whichUser, MonitorGaming monitorGaming, Stopwatch1 stopwatch1) {
        drawingLines();
        System.out.println(ConsoleColors.BLUE_BOLD + "******( MONITOR GAMING COMMUNITY )******" + ConsoleColors.RESET);
        String nextChoose = whereToGo();
        switch (nextChoose) {
            case "1" -> {
                System.out.println("Enter your idea:");
                monitorGaming.getCommunity().add(scanString());
                System.out.println("Your idea added successfully!");
                DaoWriter.writeData(dataBase);
                listOfProducts(whichUser, stopwatch1);
            }
            case "2" -> listOfProducts(whichUser, stopwatch1);
            case "3" -> {
                drawingLines();
                exit();
            }
            default -> {
                incorrect();
                monitorCommunity(whichUser, monitorGaming, stopwatch1);
            }
        }
    }

    public void monitorReport(int whichUser, MonitorGaming monitorGaming, Stopwatch1 stopwatch1) {
        drawingLines();
        System.out.println(ConsoleColors.BLUE_BOLD + "******( MONITOR GAMING REPORT )******" + ConsoleColors.RESET);
        String nextChoose = whereToGo();
        switch (nextChoose) {
            case "1" -> {
                System.out.println("Enter your report massage:");
                monitorGaming.getSellers().get(0).getReportMassage().add(monitorGaming.getName() +"  ->  "+ scanString());
                System.out.println("Your massage sent successfully!");
                DaoWriter.writeData(dataBase);
                listOfProducts(whichUser, stopwatch1);
            }
            case "2" -> listOfProducts(whichUser, stopwatch1);
            case "3" -> {
                drawingLines();
                exit();
            }
            default -> {
                incorrect();
                monitorReport(whichUser, monitorGaming, stopwatch1);
            }
        }
    }
}