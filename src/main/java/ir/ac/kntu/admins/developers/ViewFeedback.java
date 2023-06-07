package ir.ac.kntu.admins.developers;

import ir.ac.kntu.admins.AdminPage;
import ir.ac.kntu.helpers.ConsoleColors;
import ir.ac.kntu.store.DataBase;

import static ir.ac.kntu.helpers.TextTypings.*;
import static ir.ac.kntu.helpers.TextTypings.incorrect;

public class ViewFeedback {

    private DataBase dataBase;

    public ViewFeedback(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public void viewFeedback(int whichDeveloper) {
        drawingLines();
        System.out.println(ConsoleColors.BLUE_BOLD + "******( FEEDBACK )******" + ConsoleColors.RESET);
        String nextChoose = whereToGo();
        if (nextChoose.equals("1")) {
            showFeedBack(whichDeveloper);
            viewFeedback(whichDeveloper);
        } else if (nextChoose.equals("2")) {
            DeveloperPage developerPage = new DeveloperPage(dataBase);
            developerPage.goToDeveloperPage(whichDeveloper);
        } else if (nextChoose.equals("3")) {
            drawingLines();
            exit();
        } else {
            incorrect();
            viewFeedback(whichDeveloper);
        }
    }

    public void showFeedBack(int whichDeveloper) {
        if (dataBase.getDevelopers().get(whichDeveloper).getDeveloperGame().size() != 0) {
            for (int i = 0; i < dataBase.getDevelopers().get(whichDeveloper).getDeveloperGame().size(); i++) {
                System.out.println(ConsoleColors.BLUE + "Game name : " + dataBase.getDevelopers().get(whichDeveloper).
                        getDeveloperGame().get(i).getName() + ConsoleColors.RESET);
                System.out.println("Game feedback : ");
                for (int j = 0; j < dataBase.getDevelopers().get(whichDeveloper).getDeveloperGame().get(i).getFeedback().size(); j++) {
                    System.out.println(dataBase.getDevelopers().get(whichDeveloper).getDeveloperGame().get(i).getFeedback().get(j));
                }
            }
        }
    }
}
