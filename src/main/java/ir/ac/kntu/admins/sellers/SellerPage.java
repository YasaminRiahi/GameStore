package ir.ac.kntu.admins.sellers;

import ir.ac.kntu.admins.AdminPage;
import ir.ac.kntu.admins.AdminProfile;
import ir.ac.kntu.admins.commonInAccessories.AccessoriesPage;
import ir.ac.kntu.helpers.ConsoleColors;
import ir.ac.kntu.store.DataBase;

import static ir.ac.kntu.helpers.TextTypings.*;
import static ir.ac.kntu.helpers.TextTypings.incorrect;

public class SellerPage {

    private DataBase dataBase;

    private SellerOptions sellerOption;

    public SellerPage(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public void goToSellerPage(int whichSeller) {
        drawingLines();
        System.out.println(ConsoleColors.BLUE_BOLD + "******( SELLER PAGE )******" + ConsoleColors.RESET);
        String nextChoose = whereToGo();
        switch (nextChoose) {
            case "1" -> showSellerOptions(whichSeller);
            case "2" -> {
                AdminPage adminPage = new AdminPage(dataBase);
                adminPage.goToAdminPage();
            }
            case "3" -> {
                drawingLines();
                exit();
            }
            default -> {
                incorrect();
                goToSellerPage(whichSeller);
            }
        }
    }

    public void showSellerOptions(int whichSeller) {
        for (SellerOptions sellerOption1 : SellerOptions.values()) {
            System.out.print(sellerOption1.getValue() + ")");
            System.out.println(sellerOption1);
        }
        fromValue(getNumberFromOptions(), whichSeller);
    }

    public void fromValue(String value, int whichSeller) {
        for (SellerOptions e : SellerOptions.values()) {
            if (e.getValue().equals(value)) {
                this.sellerOption = e;
                goToOptions(whichSeller);
            }
        }
        incorrect();
        showSellerOptions(whichSeller);
    }

    public void goToOptions(int whichSeller) {
        if (sellerOption == SellerOptions.PROFILE) {
            AdminProfile adminProfile = new AdminProfile(dataBase);
            adminProfile.profile(whichSeller, dataBase.getSellers(), "SELLER");
        } else if (sellerOption == SellerOptions.ACCESSORIES){
            AccessoriesPage accessoriesPage = new AccessoriesPage(dataBase);
            accessoriesPage.goToAccessoriesPage(whichSeller, "SELLER");
        } else {
            ViewingReportMassages viewingReportMassages = new ViewingReportMassages(dataBase);
            viewingReportMassages.viewReportMassage(whichSeller);
        }
    }
}