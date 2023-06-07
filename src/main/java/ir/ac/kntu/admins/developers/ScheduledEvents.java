package ir.ac.kntu.admins.developers;

import ir.ac.kntu.helpers.ConsoleColors;
import ir.ac.kntu.products.games.Games;
import ir.ac.kntu.store.DataBase;

import static ir.ac.kntu.helpers.Scan.scanString;
import static ir.ac.kntu.helpers.TextTypings.*;
import static ir.ac.kntu.helpers.TextTypings.incorrect;

public class ScheduledEvents {


    private DataBase dataBase;

    public ScheduledEvents(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public void events(int whichDeveloper) {
        drawingLines();
        System.out.println(ConsoleColors.BLUE_BOLD + "******( SCHEDULED EVENTS )******" + ConsoleColors.RESET);
        String nextChoose = whereToGo();
        if (nextChoose.equals("1")) {
            checkEvents(whichDeveloper);
            events(whichDeveloper);
        } else if (nextChoose.equals("2")) {
            DeveloperPage developerPage = new DeveloperPage(dataBase);
            developerPage.goToDeveloperPage(whichDeveloper);
        } else if (nextChoose.equals("3")) {
            drawingLines();
            exit();
        } else {
            incorrect();
            events(whichDeveloper);
        }
    }

    public void checkEvents(int whichDeveloper) {
        if (dataBase.getDevelopers().get(whichDeveloper).getScheduledEvents().size() == 0) {
            System.out.println("You don't have any scheduled events");
            events(whichDeveloper);
        } else {
            int j = 1;
            for (int i = 0; i < dataBase.getDevelopers().get(whichDeveloper).getScheduledEvents().size(); i++) {
                System.out.println(j + ")" + dataBase.getDevelopers().get(whichDeveloper).getScheduledEvents().get(i).getName());
                j++;
            }
            System.out.println("Choose one of the games!");
            Games game = dataBase.getDevelopers().get(whichDeveloper).getScheduledEvents().get(Integer.parseInt(scanString()) - 1);
            fixOrNo(game, whichDeveloper);
        }
    }

    public void fixOrNo(Games games, int whichDeveloper) {
        System.out.println("Enter a number!");
        System.out.println("1)You fix it");
        System.out.println("3)Go to previous page");
        switch (scanString()) {
            case "1":
                dataBase.getDevelopers().get(whichDeveloper).getScheduledEvents().remove(games);
                break;
            case "2":
                events(whichDeveloper);
                break;
            default:
                incorrect();
                fixOrNo(games, whichDeveloper);
        }
    }
}
