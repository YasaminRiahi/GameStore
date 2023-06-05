package ir.ac.kntu.products.games;

import ir.ac.kntu.admins.Admin;
import ir.ac.kntu.products.Product;
import ir.ac.kntu.regularUsers.RegularUser;

import java.util.ArrayList;
import java.util.HashMap;

public class Games extends Product {

    private String genre;

    private double rating;

    private int numberOfRates;

    private double beginningRate;

    private int beginningNumber;

    private GamesLevel gamesLevel;

    private boolean isBeta;

    private HashMap<RegularUser, Double> usersRate = new HashMap<>();

    private ArrayList<Admin> developers;

    public Games(String name, String description, double cost, String genre, double rating, int numberOfRates,
                 GamesLevel gamesLevel) {
        super(name, description, cost);
        this.genre = genre;
        this.rating = rating;
        this.numberOfRates = numberOfRates;
        this.gamesLevel = gamesLevel;
        usersRate = new HashMap<>();
        developers = new ArrayList<>();
    }

    public void addRate(RegularUser user, double rating) {
        this.usersRate.put(user, rating);
    }

    public void updateRate() {
        double sum = getBeginningRate() * getBeginningNumber();
        numberOfRates = getBeginningNumber();
        for (double testRate : this.usersRate.values()) {
            sum += testRate;
            numberOfRates++;
        }
        this.setRating(sum / numberOfRates);
        setNumberOfRates(numberOfRates);
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getNumberOfRates() {
        return numberOfRates;
    }

    public void setNumberOfRates(int numberOfRates) {
        this.numberOfRates = numberOfRates;
    }

    public double getBeginningRate() {
        return beginningRate;
    }

    public void setBeginningRate(double beginningRate) {
        this.beginningRate = beginningRate;
    }

    public int getBeginningNumber() {
        return beginningNumber;
    }

    public void setBeginningNumber(int beginningNumber) {
        this.beginningNumber = beginningNumber;
    }

    public HashMap<RegularUser, Double> getUsersRate() {
        return usersRate;
    }

    public void setUsersRate(HashMap<RegularUser, Double> usersRate) {
        this.usersRate = usersRate;
    }

    public GamesLevel getGamesLevel() {
        return gamesLevel;
    }

    public void setGamesLevel(GamesLevel gamesLevel) {
        this.gamesLevel = gamesLevel;
    }

    public boolean isBeta() {
        return isBeta;
    }

    public void setBeta(boolean beta) {
        isBeta = beta;
    }

    public ArrayList<Admin> getDevelopers() {
        return developers;
    }

    public void setDevelopers(ArrayList<Admin> developers) {
        this.developers = developers;
    }

    public Games() {
        super();
        usersRate = new HashMap<>();
    }


}
