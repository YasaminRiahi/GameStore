package ir.ac.kntu.regularUsers;

import ir.ac.kntu.products.accessories.gamePad.GamePad;
import ir.ac.kntu.products.accessories.monitorGaming.MonitorGaming;
import ir.ac.kntu.products.games.Games;
import ir.ac.kntu.store.User;
import java.util.ArrayList;
import java.util.HashMap;

public class RegularUser extends User {

    private double wallet;

    private ArrayList<Games> myGames;

    private ArrayList<RegularUser> friends;

    private ArrayList<RegularUser> requests;

    private HashMap<MonitorGaming,Integer> monitorGaming;

    private HashMap<GamePad, Integer> gamePad;

    private int score;

    public RegularUser(String userName, String password, String phoneNumber, String email, double wallet,int score) {
        super(userName, password, phoneNumber, email);
        this.wallet = wallet;
        this.score = score;
        this.myGames = new ArrayList<>();
        this.friends = new ArrayList<>();
        this.requests = new ArrayList<>();
        this.monitorGaming = new HashMap<>();
        this.gamePad = new HashMap<>();
    }

    public RegularUser() {
        this.myGames = new ArrayList<>();
        this.friends = new ArrayList<>();
        this.requests = new ArrayList<>();
        this.monitorGaming = new HashMap<>();
        this.gamePad = new HashMap<>();
    }

    public double getWallet() {
        return wallet;
    }

    public void setWallet(double wallet) {
        this.wallet = wallet;
    }

    public ArrayList<Games> getMyGames() {
        return myGames;
    }

    public void setMyGames(ArrayList<Games> myGames) {
        this.myGames = myGames;
    }

    public void addMyGames(Games game) {
        this.myGames.add(game);
    }

    public ArrayList<RegularUser> getFriends() {
        return friends;
    }

    public void setFriends(ArrayList<RegularUser> friends) {
        this.friends = friends;
    }

    public void addFriends(RegularUser friend){
        this.friends.add(friend);
    }

    public ArrayList<RegularUser> getRequests() {
        return requests;
    }

    public void setRequests(ArrayList<RegularUser> requests) {
        this.requests = requests;
    }

    public void addRequests(RegularUser request){
        this.requests.add(request);
    }

    public HashMap<MonitorGaming, Integer> getMonitorGaming() {
        return monitorGaming;
    }

    public void setMonitorGaming(HashMap<MonitorGaming, Integer> monitorGaming) {
        this.monitorGaming = monitorGaming;
    }

    public HashMap<GamePad, Integer> getGamePad() {
        return gamePad;
    }

    public void setGamePad(HashMap<GamePad, Integer> gamePad) {
        this.gamePad = gamePad;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
