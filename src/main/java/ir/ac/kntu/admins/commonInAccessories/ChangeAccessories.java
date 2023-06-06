package ir.ac.kntu.admins.commonInAccessories;

import ir.ac.kntu.helpers.ConsoleColors;
import ir.ac.kntu.store.DataBase;

import java.util.ArrayList;

import static ir.ac.kntu.helpers.Scan.*;
import static ir.ac.kntu.helpers.TextTypings.*;
import static ir.ac.kntu.helpers.TextTypings.incorrect;

public class ChangeAccessories {

    private DataBase dataBase;

    private AccessoriesOption accessoriesOption;

    public ChangeAccessories(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public void changeAccessories(int whichUser, String typeOfAdmin) {
        drawingLines();
        System.out.println(ConsoleColors.BLUE_BOLD + "******( CHANGE ACCESSORIES )******" + ConsoleColors.RESET);
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
            changeAccessories(whichUser, typeOfAdmin);
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
            changeMonitorGaming(whichUser, typeOfAdmin);
        } else {
            changeGamePad(whichUser, typeOfAdmin);
        }
    }

    public void changeMonitorGaming(int whichUser, String typeOfAdmin) {
        drawingLines();
        System.out.println(ConsoleColors.BLUE_BOLD + "******( CHANGE MONITOR GAMING )******" + ConsoleColors.RESET);
        String nextChoose = whereToGo();
        if (nextChoose.equals("1")) {
            showMonitorGaming();
            String whichOne = scanString();
            if (Integer.parseInt(whichOne) - 1 >= dataBase.getMonitorGaming().size() || Integer.parseInt(whichOne) - 1 < 0) {
                incorrect();
                changeMonitorGaming(whichUser,typeOfAdmin);
            } else {
                if (typeOfAdmin.equals("SELLER")) {
                    if (!dataBase.getMonitorGaming().get(Integer.parseInt(whichOne) - 1).getSellers().
                            contains(dataBase.getSellers().get(whichUser))) {
                        notSeller();
                        changeMonitorGaming(whichUser,typeOfAdmin);
                    }
                }
                whichMonitorGamingOption();
                toChangeMonitorGaming(Integer.parseInt(whichOne) - 1);
                System.out.println("Monitor gaming changed successfully!");
                changeAccessories(whichUser,typeOfAdmin);
            }
        } else if (nextChoose.equals("2")) {
            changeAccessories(whichUser, typeOfAdmin);
        } else if (nextChoose.equals("3")) {
            drawingLines();
            exit();
        } else {
            incorrect();
            changeMonitorGaming(whichUser, typeOfAdmin);
        }
    }

    public void changeGamePad(int whichUser, String typeOfAdmin) {
        drawingLines();
        System.out.println(ConsoleColors.BLUE_BOLD + "******( CHANGE GAME PAD )******" + ConsoleColors.RESET);
        String nextChoose = whereToGo();
        if (nextChoose.equals("1")) {
            showGamePads();
            String whichOne = scanString();
            if (Integer.parseInt(whichOne) - 1 >= dataBase.getGamePads().size() || Integer.parseInt(whichOne) - 1 < 0) {
                incorrect();
                changeGamePad(whichUser,typeOfAdmin);
            } else {
                if (typeOfAdmin.equals("SELLER")) {
                    if (!dataBase.getGamePads().get(Integer.parseInt(whichOne) - 1).getSellers().
                            contains(dataBase.getSellers().get(whichUser))) {
                        notSeller();
                        changeGamePad(whichUser,typeOfAdmin);
                    }
                }
                whichGamePadOption();
                toChangeGamePad(Integer.parseInt(whichOne) - 1);
                System.out.println("Game pad changed successfully!");
                changeAccessories(whichUser,typeOfAdmin);
            }
        } else if (nextChoose.equals("2")) {
            changeAccessories(whichUser,typeOfAdmin);
        } else if (nextChoose.equals("3")) {
            drawingLines();
            exit();
        } else {
            incorrect();
            changeGamePad(whichUser,typeOfAdmin);
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

    public void whichGamePadOption() {
        System.out.println("1)Name");
        System.out.println("2)Description");
        System.out.println("3)Cost");
        System.out.println("5)Number");
        System.out.println("6)Connection type");
        System.out.println("7)Device type");
    }

    public void toChangeGamePad(int index) {
        String which = scanString();
        switch (Integer.parseInt(which)) {
            case 1:
                toChangeWhat("name");
                dataBase.getGamePads().get(index).setName(scanString());
                break;
            case 2:
                toChangeWhat("description");
                dataBase.getGamePads().get(index).setDescription(scanString());
                break;
            case 3:
                toChangeWhat("cost");
                dataBase.getGamePads().get(index).setCost(scanDouble());
                break;
            case 4:
                toChangeWhat("number");
                dataBase.getGamePads().get(index).setNumber(scanInt());
                break;
            case 5:
                System.out.println("Choose connection type :");
                showGamePadConnections(dataBase.getGamePads().get(index));
                break;
            case 6:
                System.out.println("Choose device type :");
                showGamePadDevices(dataBase.getGamePads().get(index));
                break;
            default:
                incorrect();
                toChangeGamePad(index);
        }
    }

    public void whichMonitorGamingOption() {
        System.out.println("1)Name");
        System.out.println("2)Description");
        System.out.println("3)Cost");
        System.out.println("4)Number");
        System.out.println("4)Screen size");
        System.out.println("5)Refresh rate");
        System.out.println("6)Response time");
    }

    public void toChangeMonitorGaming(int index) {
        String which = scanString();
        switch (Integer.parseInt(which)) {
            case 1:
                toChangeWhat("name");
                dataBase.getMonitorGaming().get(index).setName(scanString());
                break;
            case 2:
                toChangeWhat("description");
                dataBase.getMonitorGaming().get(index).setDescription(scanString());
                break;
            case 3:
                toChangeWhat("cost");
                dataBase.getMonitorGaming().get(index).setCost(scanDouble());
                break;
            case 4:
                toChangeWhat("number");
                dataBase.getMonitorGaming().get(index).setNumber(scanInt());
                break;
            case 5:
                toChangeWhat("screen size");
                dataBase.getMonitorGaming().get(index).setScreenSize(scanDouble());
                break;
            case 6:
                toChangeWhat("refresh rate");
                dataBase.getMonitorGaming().get(index).setRefreshRate(scanDouble());
                break;
            case 7:
                toChangeWhat("response time");
                dataBase.getMonitorGaming().get(index).setResponseTime(scanDouble());
                break;
            default:
                incorrect();
                toChangeGamePad(index);
        }
    }
}