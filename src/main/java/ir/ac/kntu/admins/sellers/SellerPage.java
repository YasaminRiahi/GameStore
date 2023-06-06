package ir.ac.kntu.admins.sellers;

import ir.ac.kntu.admins.AdminPage;
import ir.ac.kntu.admins.AdminProfile;
import ir.ac.kntu.admins.commonInAccessories.AccessoriesPage;
import ir.ac.kntu.admins.managers.ManagerGamePage;
import ir.ac.kntu.admins.managers.ManagerOptions;
import ir.ac.kntu.admins.managers.ManagerUserPage;
import ir.ac.kntu.helpers.ConsoleColors;
import ir.ac.kntu.store.DataBase;

import static ir.ac.kntu.helpers.TextTypings.*;
import static ir.ac.kntu.helpers.TextTypings.incorrect;

public class SellerPage {

    private DataBase dataBase;

    private SellerOption sellerOption;

    public SellerPage(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public void goToSellerPage(int whichSeller) {
        drawingLines();
        System.out.println(ConsoleColors.BLUE_BOLD + "******( SELLER PAGE )******" + ConsoleColors.RESET);
        String nextChoose = whereToGo();
        if (nextChoose.equals("1")) {
            showSellerOptions(whichSeller);
        } else if (nextChoose.equals("2")) {
            AdminPage adminPage = new AdminPage(dataBase);
            adminPage.goToAdminPage();
        } else if (nextChoose.equals("3")) {
            drawingLines();
            exit();
        } else {
            incorrect();
            goToSellerPage(whichSeller);
        }
    }

    public void showSellerOptions(int whichSeller) {
        for (SellerOption sellerOption1 : sellerOption.values()) {
            System.out.print(sellerOption1.getValue() + ")");
            System.out.println(sellerOption1);
        }
        fromValue(getNumberFromOptions(), whichSeller);
    }

    public void fromValue(String value, int whichSeller) {
        for (SellerOption e : SellerOption.values()) {
            if (e.getValue().equals(value)) {
                this.sellerOption = e;
                goToOptions(whichSeller);
            }
        }
        incorrect();
        showSellerOptions(whichSeller);
    }

    public void goToOptions(int whichSeller) {
        if (sellerOption == SellerOption.PROFILE) {
            AdminProfile adminProfile = new AdminProfile(dataBase);
            adminProfile.profile(whichSeller, dataBase.getSellers(), "SELLER");
        } else {
            AccessoriesPage accessoriesPage = new AccessoriesPage(dataBase);
            accessoriesPage.goToAccessoriesPage(whichSeller,"SELLER");
        }
    }
}
