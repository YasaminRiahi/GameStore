package ir.ac.kntu.admins.developers;

import ir.ac.kntu.helpers.ConsoleColors;
import ir.ac.kntu.products.games.Games;
import ir.ac.kntu.store.DataBase;

import java.util.Collections;

import static ir.ac.kntu.helpers.Scanner.scanString;
import static ir.ac.kntu.helpers.TextTypings.*;
import static ir.ac.kntu.helpers.TextTypings.incorrect;

public class CheckingInbox {

    private DataBase dataBase;

    public CheckingInbox(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public void checkInbox(int whichDeveloper) {
        drawingLines();
        System.out.println(ConsoleColors.BLUE_BOLD + "******( CHECK INBOX )******" + ConsoleColors.RESET);
        String nextChoose = whereToGo();
        switch (nextChoose) {
            case "1" -> {
                check(whichDeveloper);
                checkInbox(whichDeveloper);
            }
            case "2" -> {
                DeveloperPage developerPage = new DeveloperPage(dataBase);
                developerPage.goToDeveloperPage(whichDeveloper);
            }
            case "3" -> {
                drawingLines();
                exit();
            }
            default -> {
                incorrect();
                checkInbox(whichDeveloper);
            }
        }
    }

    public void check(int whichDeveloper) {
        if (dataBase.getDevelopers().get(whichDeveloper).getInbox().size() == 0) {
            System.out.println("Your inbox is empty!");
            checkInbox(whichDeveloper);
        } else {
            int j = 1;
            for (int i = 0; i < dataBase.getDevelopers().get(whichDeveloper).getInbox().size(); i++) {
                System.out.println(j + ")" + dataBase.getDevelopers().get(whichDeveloper).getInbox().get(i).getName());
                j++;
            }
            System.out.println("Choose one of the games!");
            Games game = dataBase.getDevelopers().get(whichDeveloper).getInbox().get(Integer.parseInt(scanString()) - 1);
            acceptOrDecline(game, whichDeveloper);
        }
    }

    public void acceptOrDecline(Games games, int whichDeveloper) {
        System.out.println("Enter a number!");
        System.out.println("1)Accept it");
        System.out.println("2)Decline it");
        System.out.println("3)Go to previous page");
        switch (scanString()) {
            case "1" -> {
                dataBase.getDevelopers().get(whichDeveloper).getInbox().remove(games);
                dataBase.getDevelopers().get(whichDeveloper).getScheduledEvents().add(games);
                System.out.println("You accepted it successfully!");
            }
            case "2" -> {
                dataBase.getDevelopers().get(whichDeveloper).getInbox().remove(games);
                giveToAnother(games, whichDeveloper);
                System.out.println("You declined it successfully!");
            }
            case "3" -> checkInbox(whichDeveloper);
            default -> {
                incorrect();
                acceptOrDecline(games, whichDeveloper);
            }
        }
    }

    public void giveToAnother(Games games, int whichDeveloper) {
        Collections.sort(games.getDevelopers());
        int index = games.getDevelopers().indexOf(dataBase.getDevelopers().get(whichDeveloper));
        if (index == games.getDevelopers().size()) {
            dataBase.getDevelopers().get(0).getInbox().add(games);
        } else {
            dataBase.getDevelopers().get(index + 1).getInbox().add(games);
        }
    }
}