package ir.ac.kntu.store;

import ir.ac.kntu.admins.Admin;
import ir.ac.kntu.products.accessories.gamePad.ConnectionType;
import ir.ac.kntu.products.accessories.gamePad.DeviceType;
import ir.ac.kntu.products.accessories.gamePad.GamePad;
import ir.ac.kntu.products.accessories.monitorGaming.MonitorGaming;
import ir.ac.kntu.products.games.Games;
import ir.ac.kntu.products.games.GamesLevel;
import ir.ac.kntu.regularUsers.RegularUser;

import java.util.ArrayList;
import java.util.HashMap;

public class DataBase {

    private ArrayList<Games> games;

    private ArrayList<Admin> managers;

    private ArrayList<Admin> developers;

    private ArrayList<Admin> sellers;

    private ArrayList<RegularUser> regularUsers;

    private ArrayList<GamePad> gamePads;

    private ArrayList<MonitorGaming> monitorGaming;

    public ArrayList<Games> getGames() {
        return games;
    }

    public void setGames(ArrayList<Games> games) {
        this.games = games;
    }

    public void addGames(Games game) {
        games.add(game);
    }

    public ArrayList<Admin> getManagers() {
        return managers;
    }

    public void setManagers(ArrayList<Admin> managers) {
        this.managers = managers;
    }

    public void addManagers(Admin admin) {
        managers.add(admin);
    }

    public ArrayList<Admin> getDevelopers() {
        return developers;
    }

    public void setDevelopers(ArrayList<Admin> developers) {
        this.developers = developers;
    }

    public void addDevelopers(Admin admin) {
        developers.add(admin);
    }

    public ArrayList<Admin> getSellers() {
        return sellers;
    }

    public void setSellers(ArrayList<Admin> sellers) {
        this.sellers = sellers;
    }

    public void addSellers(Admin admin) {
        sellers.add(admin);
    }

    public ArrayList<RegularUser> getRegularUsers() {
        return regularUsers;
    }

    public void setRegularUsers(ArrayList<RegularUser> regularUsers) {
        this.regularUsers = regularUsers;
    }

    public void addRegularUser(RegularUser regularUser) {
        this.regularUsers.add(regularUser);
    }

    public void removeRegularUser(RegularUser regularUser){
        regularUsers.remove(regularUser);
    }

    public ArrayList<GamePad> getGamePads() {
        return gamePads;
    }

    public void setGamePads(ArrayList<GamePad> gamePads) {
        this.gamePads = gamePads;
    }

    public void addGamePad(GamePad gamePad) {
        this.gamePads.add(gamePad);
    }

    public ArrayList<MonitorGaming> getMonitorGaming() {
        return monitorGaming;
    }

    public void setMonitorGaming(ArrayList<MonitorGaming> monitorGaming) {
        this.monitorGaming = monitorGaming;
    }

    public void addMonitorGaming(MonitorGaming monitorGaming) {
        this.monitorGaming.add(monitorGaming);
    }

    public DataBase() {
        this.managers = new ArrayList<>();
        this.developers = new ArrayList<>();
        this.sellers = new ArrayList<>();
        this.games = new ArrayList<>();
        this.regularUsers = new ArrayList<>();
        this.gamePads = new ArrayList<>();
        this.monitorGaming = new ArrayList<>();
    }

    public void addEveryThing() {
        addDefaultAdmins();
        addDefaultGames();
        addDefaultGamePad();
        addDefaultMonitorGaming();
        addDefaultUsers();
        addDefaultProducts();
        addDefaultFriends();
    }

    public void addDefaultAdmins() {
        Admin admin1 = new Admin("YasaminRiahi", "Y1274183871r", "091404369998", "ysminriahi@gmail.com");
        Admin admin2 = new Admin("BaharOrak", "Bahar821017", "09160340853", "baharorak@gmail.com");
        Admin admin3 = new Admin("RaziehRiahi", "Razii78Riahi", "09167594761", "raziriahi@gmail.com");
        Admin admin4 = new Admin("ShakibaMirzadeh", "Shakiba1352", "09132135049", "ShMirzadeh8962");
        Admin admin5 =new Admin("Hooman","Hooriahi49","091321899832","hooriahi@gmail.com");
        addManagers(admin1);
        addManagers(admin2);
        addDevelopers(admin3);
        addSellers(admin4);
        addSellers(admin5);
        addDevelopers(admin5);
    }

    public void addDefaultGames() {
        String description1 = "As a young boy, Link is tricked by Ganondorf, the King of the Gerudo Thieves...";
        ArrayList<String> community1 = new ArrayList<>();
        community1.add("Cool game!");
        Games game1 = new Games("The Legend Of Zelda",description1,60,"Action-Adventure",9.1,
                768,GamesLevel.LEVEL_3);
        game1.setCommunity(community1);
        game1.setBeta(false);
        games.add(game1);
        String description2 = "Hawk's back - with new technology, new pros and new tricks! THPS2, the legend rides on!…";
        ArrayList<String> community2 = new ArrayList<>();
        community2.add("Not exciting");
        Games game2 = new Games("TONY HAWK'S PRO SKATER 2",description2,30,"Sports-Alternative",
                6.6,116,GamesLevel.LEVEL_1);
        game2.setCommunity(community2);
        game2.setBeta(true);
        games.add(game2);
        String description3 = "Step into the Dark... As Carrington Institute's most promising new Agent..";;
        ArrayList<String> community3 = new ArrayList<>();
        community3.add("I love it!");
        Games game3 = new Games("PERFECT DARK",description3,50,"Action",8.8,511,
                GamesLevel.LEVEL_2);
        game3.setCommunity(community3);
        game3.setBeta(false);
        games.add(game3);
    }

    public void addDefaultGamePad() {
        GamePad gamePad1 = new GamePad();
        gamePad1.setName("DualSense 5");
        gamePad1.setDescription("Discover a deeper, highly immersive gaming experience that brings the action to life in\n" +
                " the palms of your hands. The DualSense wireless controller offers immersive haptic feedback,\n" +
                " dynamic adaptive triggers and a built-in microphone, all integrated into an iconic comfortable design.\n" +
                " Compared to DUALSHOCK 4 wireless controller. Available when feature is supported by game.");
        gamePad1.setCost(21.1);
        gamePad1.setConnectionType(ConnectionType.WIRELESS);
        gamePad1.setDeviceType(DeviceType.PLAY_STATION);
        gamePad1.setNumber(100);
        ArrayList<String> community1 = new ArrayList<>();
        community1.add("It's really cool!");
        gamePad1.setCommunity(community1);
        GamePad gamePad2 = new GamePad();
        gamePad2.setName("TSCO TG 170W");
        gamePad2.setDescription("Any important description!");
        gamePad2.setCost(15);
        gamePad2.setConnectionType(ConnectionType.WIRELESS);
        gamePad2.setDeviceType(DeviceType.PC);
        gamePad2.setNumber(50);
        ArrayList<String> community2 = new ArrayList<>();
        community2.add("Not bad!");
        gamePad2.setCommunity(community2);
        addGamePad(gamePad1);
        addGamePad(gamePad2);
    }

    public void addDefaultMonitorGaming() {
        MonitorGaming monitorGaming1 = new MonitorGaming();
        monitorGaming1.setName("LG UltraGear");
        monitorGaming1.setDescription("Get wrapped up in the action with the 27” LG UltraGear™ monitor and the stunning \n" +
                "picture quality of LG OLED technology. Gain a competitive advantage with an unprecedented 240Hz \n" +
                "refresh rate and 0.03ms response time on an OLED gaming monitor.");
        monitorGaming1.setCost(1000);
        monitorGaming1.setScreenSize(27);
        monitorGaming1.setRefreshRate(240);
        monitorGaming1.setResponseTime(0.03);
        monitorGaming1.setNumber(100);
        ArrayList<String> community1 = new ArrayList<>();
        community1.add("It's really cool!");
        monitorGaming1.setCommunity(community1);
        MonitorGaming monitorGaming2 = new MonitorGaming();
        monitorGaming2.setName("48'' UltraGear");
        monitorGaming2.setDescription("Console and PC gaming has never looked better. The 48GQ900 UltraGear OLED gaming \n" +
                "monitor features a 16:9 aspect ratio with 4K UHD resolution and supports DisplayPort 1.4 and HDMI 2.1\n" +
                " Switching between PC and console gaming is easy with the included remote.");
        monitorGaming2.setCost(1500);
        monitorGaming2.setScreenSize(48);
        monitorGaming2.setRefreshRate(120);
        monitorGaming2.setResponseTime(0.1);
        monitorGaming2.setNumber(101);
        ArrayList<String> community2 = new ArrayList<>();
        community2.add("Awesome but expensive");
        monitorGaming2.setCommunity(community2);
        monitorGaming.add(monitorGaming1);
        monitorGaming.add(monitorGaming2);
    }

    public void addDefaultUsers() {
        ArrayList<Games> user1Games = new ArrayList<>();
        user1Games.add(games.get(0));
        user1Games.add(games.get(2));
        RegularUser user1 = new RegularUser("Yasamin", "ysminR82", "09160340706",
                "ysminriahi@gmail.com", 1000, 30);
        user1.setMyGames(user1Games);
        addRegularUser(user1);
        ArrayList<Games> user2Games = new ArrayList<>();
        user2Games.add(games.get(0));
        user2Games.add(games.get(1));
        RegularUser user2 = new RegularUser("Kimia", "Kimia1234", "09123456743",
                "kimiasadeeghi83@gmail.com", 800, 10);
        user2.setMyGames(user2Games);
        addRegularUser(user2);
        ArrayList<Games> user3Games = new ArrayList<>();
        user3Games.add(games.get(1));
        RegularUser user3 = new RegularUser("Bahar", "Bahar821017", "09162345674",
                "baharOrak@gmail.com", 900, 50);
        user3.setMyGames(user3Games);
        addRegularUser(user3);
    }

    public void addDefaultProducts() {
        ArrayList<Games> user1Games = new ArrayList<>();
        user1Games.add(games.get(0));
        user1Games.add(games.get(2));
        regularUsers.get(0).setMyGames(user1Games);
        ArrayList<Games> user2Games = new ArrayList<>();
        user2Games.add(games.get(0));
        user2Games.add(games.get(1));
        regularUsers.get(1).setMyGames(user2Games);
        ArrayList<Games> user3Games = new ArrayList<>();
        user3Games.add(games.get(1));
        regularUsers.get(2).setMyGames(user3Games);
        HashMap<GamePad, Integer> user1GamePad = new HashMap<>();
        user1GamePad.put(gamePads.get(0), 1);
        gamePads.get(0).setNumber(gamePads.get(0).getNumber() - 1);
        regularUsers.get(0).setGamePad(user1GamePad);
        HashMap<MonitorGaming, Integer> user3MonitorGaming = new HashMap<>();
        user3MonitorGaming.put(monitorGaming.get(1), 1);
        monitorGaming.get(1).setNumber(monitorGaming.get(1).getNumber() - 1);
        regularUsers.get(2).setMonitorGaming(user3MonitorGaming);
    }

    public void addDefaultFriends() {
        ArrayList<RegularUser> friends1 = new ArrayList<>();
        friends1.add(regularUsers.get(1));
        friends1.add(regularUsers.get(2));
        regularUsers.get(0).setFriends(friends1);
        ArrayList<RegularUser> friends2 = new ArrayList<>();
        friends2.add(regularUsers.get(0));
        regularUsers.get(1).setFriends(friends2);
        ArrayList<RegularUser> friends3 = new ArrayList<>();
        friends3.add(regularUsers.get(0));
        regularUsers.get(2).setFriends(friends3);
    }
}