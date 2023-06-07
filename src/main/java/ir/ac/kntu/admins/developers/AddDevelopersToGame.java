package ir.ac.kntu.admins.developers;

import ir.ac.kntu.helpers.ConsoleColors;
import ir.ac.kntu.store.DataBase;

import java.util.ArrayList;

import static ir.ac.kntu.helpers.Scan.scanString;
import static ir.ac.kntu.helpers.TextTypings.*;
import static ir.ac.kntu.helpers.TextTypings.incorrect;

public class AddDevelopersToGame {

    private DataBase dataBase;

    public AddDevelopersToGame(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public void addDevelopers(int whichDeveloper) {
        drawingLines();
        System.out.println(ConsoleColors.BLUE_BOLD + "******( ADD DEVELOPERS )******" + ConsoleColors.RESET);
        String nextChoose = whereToGo();
        switch (nextChoose) {
            case "1" -> {
                if (dataBase.getDevelopers().get(whichDeveloper).getDeveloperGame().size() == 0) {
                    System.out.println(ConsoleColors.RED + "You don't have any games!" + ConsoleColors.RESET);
                    DeveloperPage developerPage = new DeveloperPage(dataBase);
                    developerPage.goToDeveloperPage(whichDeveloper);
                }
                System.out.println("Choose one of your games :");
                showGames(whichDeveloper);
                String whichGame = scanString();
                if (Integer.parseInt(whichGame) - 1 >= dataBase.getDevelopers().get(whichDeveloper).getDeveloperGame().size() ||
                        Integer.parseInt(whichGame) - 1 < 0) {
                    incorrect();
                    addDevelopers(whichDeveloper);
                } else {
                    int indexOfGame = Integer.parseInt(whichGame) - 1;
                    ArrayList<Integer> indexes = showOtherDevelopers(whichDeveloper, indexOfGame);
                    if (indexes.size() != 0) {
                        String whichOne = scanString();
                        dataBase.getDevelopers().get(whichDeveloper).getDeveloperGame().get(indexOfGame).
                                addDeveloper(dataBase.getDevelopers().get(indexes.get(Integer.parseInt(whichOne) - 1)));
                        System.out.println("Developer Added successfully!");
                    }
                    addDevelopers(whichDeveloper);
                }
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
                addDevelopers(whichDeveloper);
            }
        }
    }

    public void showGames(int whichDeveloper) {
        int j = 1;
        for (int i = 0; i < dataBase.getDevelopers().get(whichDeveloper).getDeveloperGame().size(); i++) {
            System.out.print(j + ")");
            System.out.println(dataBase.getDevelopers().get(whichDeveloper).getDeveloperGame().get(i).getName());
            j++;
        }
    }

    public ArrayList showOtherDevelopers(int whichDeveloper, int indexOfGame) {
        System.out.println("Choose one of these developers : ");
        ArrayList<Integer> indexes = new ArrayList<>();
        int j = 1;
        for (int i = 0; i < dataBase.getDevelopers().size(); i++) {
            if (i != whichDeveloper &&
                    !dataBase.getGames().get(indexOfGame).getDevelopers().contains(dataBase.getDevelopers().get(i))) {
                System.out.print(j + ")");
                System.out.println(dataBase.getDevelopers().get(i).getUserName());
                indexes.add(i);
                j++;
            }
        }
        return indexes;
    }
}