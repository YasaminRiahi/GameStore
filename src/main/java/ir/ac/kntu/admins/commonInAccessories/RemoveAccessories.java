package ir.ac.kntu.admins.commonInAccessories;

import ir.ac.kntu.helpers.ConsoleColors;
import ir.ac.kntu.store.DataBase;

import static ir.ac.kntu.helpers.Scan.scanString;
import static ir.ac.kntu.helpers.TextTypings.*;
import static ir.ac.kntu.helpers.TextTypings.incorrect;

public class RemoveAccessories {

    private DataBase dataBase;

    private AccessoriesOption accessoriesOption;

    public RemoveAccessories(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public void removeAccessories(int whichUser, String typeOfAdmin) {
        drawingLines();
        System.out.println(ConsoleColors.BLUE_BOLD + "******( REMOVE ACCESSORIES )******" + ConsoleColors.RESET);
        String nextChoose = whereToGo();
        if (nextChoose.equals("1")) {
            showAccessoriesOption(whichUser, typeOfAdmin);
        } else if (nextChoose.equals("2")) {
            AccessoriesPage accessoriesPage = new AccessoriesPage(dataBase);
            accessoriesPage.goToAccessoriesPage(whichUser, typeOfAdmin);
        } else if (nextChoose.equals("3")) {
            drawingLines();
            exit();
        } else {
            incorrect();
            removeAccessories(whichUser,typeOfAdmin);
        }

    }

    public void showAccessoriesOption(int whichUser, String typeOfAdmin) {
        for (AccessoriesOption accessoriesOption1 : AccessoriesOption.values()) {
            System.out.print(accessoriesOption1.getValue() + ")");
            System.out.println(accessoriesOption1);
        }
        fromValueOfAccessories(getNumberFromOptions(), whichUser, typeOfAdmin);
    }

    public void fromValueOfAccessories(String value, int whichUser, String typeOfAdmin) {
        for (AccessoriesOption e : AccessoriesOption.values()) {
            if (e.getValue().equals(value)) {
                this.accessoriesOption = e;
                goToAccessoriesOptions(whichUser, typeOfAdmin);
            }
        }
        incorrect();
        showAccessoriesOption(whichUser, typeOfAdmin);
    }

    public void goToAccessoriesOptions(int whichUser, String typeOfAdmin) {
        if (accessoriesOption == AccessoriesOption.MONITOR_GAMING) {
            removeMonitorGaming(whichUser,typeOfAdmin);
        } else {
            removeGamePad(whichUser,typeOfAdmin);
        }
    }

    public void removeMonitorGaming(int whichUser, String typeOfAdmin) {
        drawingLines();
        System.out.println(ConsoleColors.BLUE_BOLD + "******( REMOVE MONITOR GAMING)******" + ConsoleColors.RESET);
        String nextChoose = whereToGo();
        if (nextChoose.equals("1")) {
            showMonitorGaming();
            String whichOne = scanString();
            if (Integer.parseInt(whichOne) - 1 >= dataBase.getMonitorGaming().size() || Integer.parseInt(whichOne) - 1 < 0) {
                incorrect();
                removeMonitorGaming(whichUser,typeOfAdmin);
            } else {
                if (typeOfAdmin.equals("SELLER")) {
                    if (!dataBase.getMonitorGaming().get(Integer.parseInt(whichOne) - 1).getSellers().
                            contains(dataBase.getSellers().get(whichUser))) {
                        notSeller();
                        removeMonitorGaming(whichUser,typeOfAdmin);
                    }
                }
                dataBase.getMonitorGaming().remove(dataBase.getMonitorGaming().get(Integer.parseInt(whichOne) - 1));
                System.out.println("Monitor gaming removed successfully!");
                removeAccessories(whichUser,typeOfAdmin);
            }
        } else if (nextChoose.equals("2")) {
            removeAccessories(whichUser, typeOfAdmin);
        } else if (nextChoose.equals("3")) {
            drawingLines();
            exit();
        } else {
            incorrect();
            removeMonitorGaming(whichUser,typeOfAdmin);
        }
    }

    public void removeGamePad(int whichUser, String typeOfAdmin) {
        drawingLines();
        System.out.println(ConsoleColors.BLUE_BOLD + "******( REMOVE GAME PAD )******" + ConsoleColors.RESET);
        String nextChoose = whereToGo();
        if (nextChoose.equals("1")) {
            showGamePads();
            String whichOne = scanString();
            if (Integer.parseInt(whichOne) - 1 >= dataBase.getGamePads().size() || Integer.parseInt(whichOne) - 1 < 0) {
                incorrect();
                removeGamePad(whichUser,typeOfAdmin);
            } else {
                if (typeOfAdmin.equals("SELLER")) {
                    if (!dataBase.getGamePads().get(Integer.parseInt(whichOne) - 1).getSellers().
                            contains(dataBase.getSellers().get(whichUser))) {
                        notSeller();
                        removeGamePad(whichUser,typeOfAdmin);
                    }
                }
                dataBase.getGamePads().remove(dataBase.getGamePads().get(Integer.parseInt(whichOne) - 1));
                System.out.println("Game pad removed successfully!");
                removeAccessories(whichUser,typeOfAdmin);
            }
        } else if (nextChoose.equals("2")) {
            removeAccessories(whichUser,typeOfAdmin);
        } else if (nextChoose.equals("3")) {
            drawingLines();
            exit();
        } else {
            incorrect();
            removeGamePad(whichUser,typeOfAdmin);
        }
    }

    public void showGamePads() {
        int j = 1;
        for (int i = 0; i < dataBase.getGamePads().size(); i++) {
            System.out.print(j + ")");
            System.out.println(dataBase.getGamePads().get(i).getName());
            j++;
        }
    }

    public void showMonitorGaming() {
        int j = 1;
        for (int i = 0; i < dataBase.getMonitorGaming().size(); i++) {
            System.out.print(j + ")");
            System.out.println(dataBase.getMonitorGaming().get(i).getName());
            j++;
        }
    }
}