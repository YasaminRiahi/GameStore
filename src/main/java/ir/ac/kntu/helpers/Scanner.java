package ir.ac.kntu.helpers;


import ir.ac.kntu.products.accessories.gamePad.ConnectionType;
import ir.ac.kntu.products.accessories.gamePad.DeviceType;
import ir.ac.kntu.products.accessories.gamePad.GamePad;
import ir.ac.kntu.products.accessories.monitorGaming.MonitorGaming;
import ir.ac.kntu.products.games.Games;
import ir.ac.kntu.products.games.GamesLevel;
import ir.ac.kntu.regularUsers.RegularUser;
import ir.ac.kntu.store.DataBase;

import static ir.ac.kntu.helpers.TextTypings.getNumberFromOptions;


public class Scanner {

    public static String scanString() {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        return scanner.nextLine();
    }

    public static Integer scanInt() {
        int input;
        String isInput = scanString();
        if (isInput.matches("[0-9]+")) {
            input = Integer.parseInt(isInput);
            return input;
        }
        return null;
    }

    public static Double scanDouble() {
        double input;
        String isInput = scanString();
        if (isInput.matches("[0-9]+(.[0-9.])*")) {
            input = Double.parseDouble(isInput);
            return input;
        }
        return null;
    }

    public static RegularUser scanUsers(DataBase dataBase) {
        RegularUser newUser = new RegularUser();
        System.out.println("Enter username :");
        String username = scanString();
        if (duplicateUsername(dataBase, username) == 0) {
            newUser.setUserName(username);
            System.out.println("Enter user password :");
            String password = scanString();
            while (checkPassword(password) == 1) {
                System.out.println("Enter user password :");
                password = scanString();
            }
            newUser.setPassword(password);
            System.out.println("Enter user phone number :");
            newUser.setPhoneNumber(scanString());
            System.out.println("Enter user email :");
            newUser.setEmail(scanString());
            newUser.setWallet(0);
            newUser.setScore(0);
            return newUser;
        } else {
            System.out.println(ConsoleColors.RED + "Duplicate username!Enter another one!" + ConsoleColors.RESET);
            scanUsers(dataBase);
        }
        return newUser;
    }

    public static int duplicateUsername(DataBase dataBase, String username) {
        for (int i = 0; i < dataBase.getRegularUsers().size(); i++) {
            if (dataBase.getRegularUsers().get(i).getUserName().equals(username)) {
                return 1;
            }
        }
        return 0;
    }

    public static int checkPassword(String password) {
        if (!password.matches(".*" + "[A-Z]+" + ".*") ||
                !password.matches(".*" + "[a-z]+" + ".*") ||
                !password.matches(".*" + "[0-9]+" + ".*")) {
            System.out.println(ConsoleColors.RED + "Password must include at least 1 numbers , 1 capital and \n" +
                    "1 small letter!" + ConsoleColors.RESET);
            return 1;
        } else {
            if (password.length() < 8) {
                System.out.println(ConsoleColors.RED + "Password must include at least 8 letters!" + ConsoleColors.RESET);
                return 1;
            } else {
                return 0;
            }
        }
    }

    public static String scanNewName(DataBase dataBase, String newUsername) {
        while (duplicateUsername(dataBase, newUsername) == 1) {
            System.out.println(ConsoleColors.RED + "Duplicate username!Enter another one!" + ConsoleColors.RESET);
            System.out.println("Enter another username:");
            newUsername = scanString();
        }
        return newUsername;
    }

    public static String scanNewPass(String newPassword) {
        while (checkPassword(newPassword) == 1) {
            System.out.println("Enter another password:");
            newPassword = scanString();
        }
        return newPassword;
    }

    public static Games scanGames() {
        Games newGame = new Games();
        System.out.println("Enter game name :");
        newGame.setName(scanString());
        System.out.println("Enter game genre :");
        newGame.setGenre(scanString());
        System.out.println("Add some description :");
        newGame.setDescription(scanString());
        System.out.println("Game rating is :");
        double rating = scanDouble();
        newGame.setRating(rating);
        newGame.setBeginningRate(rating);
        System.out.println("Enter number of rates :");
        int numberOfRates = scanInt();
        newGame.setNumberOfRates(numberOfRates);
        newGame.setBeginningNumber(numberOfRates);
        System.out.println("Enter game cost :");
        newGame.setCost(scanDouble());
        System.out.println("Choose game level :");
        showGamesLevel(newGame);
        System.out.println("Is beta version ? true / false");
        String isBeta = scanString();
        if (isBeta.equals("true")) {
            newGame.setBeta(true);
        } else {
            newGame.setBeta(false);
        }
        System.out.println("Game added successfully!");
        return newGame;
    }

    public static MonitorGaming scanMonitorGaming() {
        MonitorGaming monitorGaming = new MonitorGaming();
        System.out.println("Enter monitor gaming name :");
        monitorGaming.setName(scanString());
        System.out.println("Add some description :");
        monitorGaming.setDescription(scanString());
        System.out.println("Enter monitor gaming cost :");
        monitorGaming.setCost(scanDouble());
        System.out.println("Enter number of this monitor gaming :");
        monitorGaming.setNumber(scanInt());
        System.out.println("Enter monitor gaming screen size :");
        monitorGaming.setScreenSize(scanDouble());
        System.out.println("Enter monitor gaming refresh rate :");
        monitorGaming.setRefreshRate(scanDouble());
        System.out.println("Enter monitor gaming response time :");
        monitorGaming.setResponseTime(scanDouble());
        System.out.println("Monitor gaming added successfully!");
        return monitorGaming;
    }

    public static GamePad scanGamePad() {
        GamePad gamePad = new GamePad();
        System.out.println("Enter game pad name :");
        gamePad.setName(scanString());
        System.out.println("Add some description :");
        gamePad.setDescription(scanString());
        System.out.println("Enter game pad cost :");
        gamePad.setCost(scanDouble());
        System.out.println("Enter number of this game pad :");
        gamePad.setNumber(scanInt());
        System.out.println("Choose connection type :");
        showGamePadConnections(gamePad);
        System.out.println("Choose device type :");
        showGamePadDevices(gamePad);
        System.out.println("Game pad added successfully!");
        return gamePad;
    }

    public static void showGamesLevel(Games newGame) {
        for (GamesLevel gamesLevel1 : GamesLevel.values()) {
            System.out.print(gamesLevel1.getValue() + ")");
            System.out.println(gamesLevel1);
        }
        fromValueLevel(getNumberFromOptions(), newGame);
    }

    public static void fromValueLevel(String value, Games newGame) {
        for (GamesLevel e : GamesLevel.values()) {
            if (e.getValue().equals(value)) {
                newGame.setGamesLevel(e);
            }
        }
    }

    public static void showGamePadConnections(GamePad gamePad) {
        for (ConnectionType connectionType : ConnectionType.values()) {
            System.out.print(connectionType.getValue() + ")");
            System.out.println(connectionType);
        }
        fromValueConnections(getNumberFromOptions(), gamePad);
    }

    public static void fromValueConnections(String value, GamePad gamePad) {
        for (ConnectionType e : ConnectionType.values()) {
            if (e.getValue().equals(value)) {
                gamePad.setConnectionType(e);
            }
        }
    }

    public static void showGamePadDevices(GamePad gamePad) {
        for (DeviceType deviceType : DeviceType.values()) {
            System.out.print(deviceType.getValue() + ")");
            System.out.println(deviceType);
        }
        fromValueDevices(getNumberFromOptions(), gamePad);
    }

    public static void fromValueDevices(String value, GamePad gamePad) {
        for (DeviceType e : DeviceType.values()) {
            if (e.getValue().equals(value)) {
                gamePad.setDeviceType(e);
            }
        }
    }
}