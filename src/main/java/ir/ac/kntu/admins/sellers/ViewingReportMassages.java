package ir.ac.kntu.admins.sellers;

import ir.ac.kntu.admins.developers.DeveloperPage;
import ir.ac.kntu.helpers.ConsoleColors;
import ir.ac.kntu.store.DataBase;

import static ir.ac.kntu.helpers.TextTypings.*;
import static ir.ac.kntu.helpers.TextTypings.incorrect;

public class ViewingReportMassages {

    private DataBase dataBase;

    public ViewingReportMassages(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public void viewReportMassage(int whichSeller) {
        drawingLines();
        System.out.println(ConsoleColors.BLUE_BOLD + "******( REPORT MASSAGE )******" + ConsoleColors.RESET);
        String nextChoose = whereToGo();
        switch (nextChoose) {
            case "1" -> {
                showMassages(whichSeller);
                viewReportMassage(whichSeller);
            }
            case "2" -> {
                SellerPage sellerPage = new SellerPage(dataBase);
                sellerPage.goToSellerPage(whichSeller);
            }
            case "3" -> {
                drawingLines();
                exit();
            }
            default -> {
                incorrect();
                viewReportMassage(whichSeller);
            }
        }
    }

    public void showMassages(int whichSeller) {
        for (int i = 0; i < dataBase.getSellers().get(whichSeller).getReportMassage().size(); i++) {
            System.out.println(dataBase.getSellers().get(whichSeller).getReportMassage().get(i));
        }
    }
}
