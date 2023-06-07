package ir.ac.kntu.admins.developers;

import ir.ac.kntu.admins.AdminPage;
import ir.ac.kntu.admins.AdminProfile;
import ir.ac.kntu.helpers.ConsoleColors;
import ir.ac.kntu.store.DataBase;

import static ir.ac.kntu.helpers.TextTypings.*;
import static ir.ac.kntu.helpers.TextTypings.incorrect;

public class DeveloperPage {

    private DataBase dataBase;

    private DeveloperOptions developerOptions;

    public DeveloperPage(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public void goToDeveloperPage(int whichDeveloper) {
        drawingLines();
        System.out.println(ConsoleColors.BLUE_BOLD + "******( DEVELOPER PAGE )******" + ConsoleColors.RESET);
        String nextChoose = whereToGo();
        switch (nextChoose) {
            case "1" -> showDeveloperOptions(whichDeveloper);
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
                goToDeveloperPage(whichDeveloper);
            }
        }
    }

    public void showDeveloperOptions(int whichDeveloper) {
        for (DeveloperOptions developerOptions1 : DeveloperOptions.values()) {
            System.out.print(developerOptions1.getValue() + ")");
            System.out.println(developerOptions1);
        }
        fromValue(getNumberFromOptions(), whichDeveloper);
    }

    public void fromValue(String value, int whichDeveloper) {
        for (DeveloperOptions e : DeveloperOptions.values()) {
            if (e.getValue().equals(value)) {
                this.developerOptions = e;
                goToOptions(whichDeveloper);
            }
        }
        incorrect();
        showDeveloperOptions(whichDeveloper);
    }

    public void goToOptions(int whichDeveloper) {
        if (developerOptions == DeveloperOptions.PROFILE) {
            AdminProfile adminProfile = new AdminProfile(dataBase);
            adminProfile.profile(whichDeveloper, dataBase.getDevelopers(), "DEVELOPER");
        } else if (developerOptions == DeveloperOptions.GAMES) {
            DeveloperGamePage developerGamePage = new DeveloperGamePage(dataBase);
            developerGamePage.gamesPage(whichDeveloper);
        } else if (developerOptions == DeveloperOptions.CHECK_INBOX) {
            CheckInbox checkInbox = new CheckInbox(dataBase);
            checkInbox.checkInbox(whichDeveloper);
        } else if (developerOptions == DeveloperOptions.SCHEDULED_EVENTS) {
            ScheduledEvents scheduledEvents = new ScheduledEvents(dataBase);
            scheduledEvents.events(whichDeveloper);
        } else if (developerOptions == DeveloperOptions.VIEW_FEEDBACK) {
            ViewFeedback viewFeedback = new ViewFeedback(dataBase);
            viewFeedback.viewFeedback(whichDeveloper);
        } else {
            AddDevelopersToGame addDevelopersToGame = new AddDevelopersToGame(dataBase);
            addDevelopersToGame.addDevelopers(whichDeveloper);
        }
    }
}