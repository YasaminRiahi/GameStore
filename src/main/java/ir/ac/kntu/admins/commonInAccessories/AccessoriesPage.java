package ir.ac.kntu.admins.commonInAccessories;

import ir.ac.kntu.admins.managers.ManagerPage;
import ir.ac.kntu.admins.sellers.SellerPage;
import ir.ac.kntu.helpers.ConsoleColors;
import ir.ac.kntu.store.DataBase;

import static ir.ac.kntu.helpers.TextTypings.*;
import static ir.ac.kntu.helpers.TextTypings.incorrect;

public class AccessoriesPage {

    private DataBase dataBase;

    private AccessoriesPageOptions accessoriesPageOptions;

    public AccessoriesPage(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public void goToAccessoriesPage(int whichUser, String typeOfAdmin) {
        drawingLines();
        System.out.println(ConsoleColors.BLUE_BOLD + "******( ACCESSORIES PAGE )******" + ConsoleColors.RESET);
        String nextChoose = whereToGo();
        switch (nextChoose) {
            case "1" -> showAccessoriesPageOption(whichUser, typeOfAdmin);
            case "2" -> goBack(whichUser, typeOfAdmin);
            case "3" -> {
                drawingLines();
                exit();
            }
            default -> {
                incorrect();
                goToAccessoriesPage(whichUser, typeOfAdmin);
            }
        }

    }

    public void goBack(int whichUser, String typeOfAdmin) {
        if (typeOfAdmin.equals("MANAGER")) {
            ManagerPage managerPage = new ManagerPage(dataBase);
            managerPage.goToManagerPage(whichUser);
        } else if (typeOfAdmin.equals("SELLER")) {
            SellerPage sellerPage = new SellerPage(dataBase);
            sellerPage.goToSellerPage(whichUser);
        }
    }

    public void showAccessoriesPageOption(int whichUser, String typeOfAdmin) {
        for (AccessoriesPageOptions accessoriesPageOptions1 : AccessoriesPageOptions.values()) {
            System.out.print(accessoriesPageOptions1.getValue() + ")");
            System.out.println(accessoriesPageOptions1);
        }
        fromValue(getNumberFromOptions(), whichUser, typeOfAdmin);
    }

    public void fromValue(String value, int whichUser, String typeOfAdmin) {
        for (AccessoriesPageOptions e : AccessoriesPageOptions.values()) {
            if (e.getValue().equals(value)) {
                this.accessoriesPageOptions = e;
                goToOptions(whichUser, typeOfAdmin);
            }
        }
        incorrect();
        showAccessoriesPageOption(whichUser, typeOfAdmin);
    }

    public void goToOptions(int whichUser, String typeOfAdmin) {
        if (accessoriesPageOptions == AccessoriesPageOptions.ADD_ACCESSORIES) {
            AddAccessories addAccessories = new AddAccessories(dataBase);
            addAccessories.addAccessories(whichUser, typeOfAdmin);
        } else if (accessoriesPageOptions == AccessoriesPageOptions.CHANGE_ACCESSORIES) {
            ChangeAccessories changeAccessories = new ChangeAccessories(dataBase);
            changeAccessories.changeAccessories(whichUser, typeOfAdmin);
        } else {
            RemoveAccessories removeAccessories = new RemoveAccessories(dataBase);
            removeAccessories.removeAccessories(whichUser, typeOfAdmin);
        }
    }
}