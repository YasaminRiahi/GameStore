package ir.ac.kntu.admins.commonInAccessories;

import ir.ac.kntu.admins.managers.ManagerPage;
import ir.ac.kntu.helpers.ConsoleColors;
import ir.ac.kntu.store.DataBase;

import static ir.ac.kntu.helpers.Scan.scanGamePad;
import static ir.ac.kntu.helpers.Scan.scanMonitorGaming;
import static ir.ac.kntu.helpers.TextTypings.*;
import static ir.ac.kntu.helpers.TextTypings.incorrect;

public class AddAccessories {

    private DataBase dataBase;

    private AccessoriesOption accessoriesOption;

    public AddAccessories(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public void addAccessories(int whichUser, String typeOfAdmin) {
        drawingLines();
        System.out.println(ConsoleColors.BLUE_BOLD + "******( ADD ACCESSORIES )******" + ConsoleColors.RESET);
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
            addAccessories(whichUser, typeOfAdmin);
        }

    }

    public void showAccessoriesOption(int whichUser, String typeOfAdmin) {
        for (AccessoriesOption accessoriesOption1 : AccessoriesOption.values()) {
            System.out.print(accessoriesOption1.getValue() + ")");
            System.out.println(accessoriesOption1);
        }
        fromValue(getNumberFromOptions(), whichUser, typeOfAdmin);
    }

    public void fromValue(String value, int whichUser, String typeOfAdmin) {
        for (AccessoriesOption e : AccessoriesOption.values()) {
            if (e.getValue().equals(value)) {
                this.accessoriesOption = e;
                goToOptions(whichUser, typeOfAdmin);
            }
        }
        incorrect();
        showAccessoriesOption(whichUser, typeOfAdmin);
    }

    public void goToOptions(int whichUser, String typeOfAdmin) {
        if (accessoriesOption == AccessoriesOption.MONITOR_GAMING) {
            addMonitorGaming(whichUser, typeOfAdmin);
        } else {
            addGamePad(whichUser, typeOfAdmin);
        }
    }

    public void addMonitorGaming(int whichUser, String typeOfAdmin) {
        drawingLines();
        System.out.println(ConsoleColors.BLUE_BOLD + "******( ADD Monitor Gaming )******" + ConsoleColors.RESET);
        String nextChoose = whereToGo();
        if (nextChoose.equals("1")) {
            dataBase.addMonitorGaming(scanMonitorGaming());
            if (typeOfAdmin.equals("SELLER")) {
                dataBase.getMonitorGaming().get(dataBase.getMonitorGaming().size() - 1).addSellers(dataBase.getSellers().
                        get(whichUser));
            }
            addMonitorGaming(whichUser, typeOfAdmin);
        } else if (nextChoose.equals("2")) {
            addAccessories(whichUser, typeOfAdmin);
        } else if (nextChoose.equals("3")) {
            drawingLines();
            exit();
        } else {
            incorrect();
            addAccessories(whichUser, typeOfAdmin);
        }
    }

    public void addGamePad(int whichUser, String typeOfAdmin) {
        drawingLines();
        System.out.println(ConsoleColors.BLUE_BOLD + "******( ADD Game Pad )******" + ConsoleColors.RESET);
        String nextChoose = whereToGo();
        if (nextChoose.equals("1")) {
            dataBase.addGamePad(scanGamePad());
            if (typeOfAdmin.equals("SELLER")) {
                dataBase.getGamePads().get(dataBase.getGamePads().size() - 1).addSellers(dataBase.getSellers().
                        get(whichUser));
            }
            addGamePad(whichUser, typeOfAdmin);
        } else if (nextChoose.equals("2")) {
            addAccessories(whichUser, typeOfAdmin);
        } else if (nextChoose.equals("3")) {
            drawingLines();
            exit();
        } else {
            incorrect();
            addAccessories(whichUser, typeOfAdmin);
        }
    }
}
