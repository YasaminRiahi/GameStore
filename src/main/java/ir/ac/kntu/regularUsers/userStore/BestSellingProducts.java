package ir.ac.kntu.regularUsers.userStore;

import ir.ac.kntu.helpers.ConsoleColors;
import ir.ac.kntu.products.Product;
import ir.ac.kntu.regularUsers.Stopwatch1;
import ir.ac.kntu.store.DataBase;
import java.util.ArrayList;
import java.util.Collections;
import static ir.ac.kntu.helpers.TextTypings.*;
import static ir.ac.kntu.helpers.TextTypings.incorrect;

public class BestSellingProducts {

    private DataBase dataBase ;

    public BestSellingProducts(DataBase dataBase){
        this.dataBase = dataBase;
    }

    public void bestSelling(int whichUser, Stopwatch1 stopwatch1) {
        drawingLines();
        System.out.println(ConsoleColors.BLUE_BOLD + "******( BEST-SELLING PRODUCTS )******" + ConsoleColors.RESET);
        String nextChoose = whereToGo();
        if (nextChoose.equals("1")) {
            showBests();
            bestSelling(whichUser,stopwatch1);
        } else if (nextChoose.equals("2")) {
            UserStore userStore = new UserStore(dataBase);
            userStore.userStore(whichUser, stopwatch1);
        } else if (nextChoose.equals("3")) {
            drawingLines();
            exit();
        } else {
            incorrect();
            bestSelling(whichUser,stopwatch1);
        }
    }

    public void showBests(){
        ArrayList<Product> products = new ArrayList<>();
        for (int i = 0 ; i < dataBase.getGames().size() ; i++){
            products.add(dataBase.getGames().get(i));
        }
        for (int i = 0 ; i < dataBase.getMonitorGaming().size() ; i++){
            products.add(dataBase.getMonitorGaming().get(i));
        }
        for (int i = 0 ; i < dataBase.getGamePads().size() ; i++) {
            products.add(dataBase.getGamePads().get(i));
        }
        Collections.sort(products);
        for (int i = 0 ; i < products.size() ; i++){
            System.out.println(products.get(i).getClass().getSimpleName() + "  ->  " + products.get(i).getName() +"  (number :"
                    + products.get(i).getNumberOfSoldItems()+")");
        }
    }
}
