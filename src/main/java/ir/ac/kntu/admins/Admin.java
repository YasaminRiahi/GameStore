package ir.ac.kntu.admins;

import ir.ac.kntu.products.games.Games;
import ir.ac.kntu.store.User;

import java.util.ArrayList;

public class Admin extends User implements Comparable<Admin> {

    private ArrayList<Games> developerGame;

    private ArrayList<Games> inbox;

    private ArrayList<Games> scheduledEvents;

    private ArrayList<String> reportMassage;

    public Admin(String userName, String password, String phoneNumber, String email) {
        super(userName, password, phoneNumber, email);
        this.developerGame = new ArrayList<>();
        this.inbox = new ArrayList<>();
        this.scheduledEvents = new ArrayList<>();
        this.reportMassage = new ArrayList<>();
    }

    public ArrayList<Games> getDeveloperGame() {
        return developerGame;
    }

    public ArrayList<Games> getInbox() {
        return inbox;
    }

    public ArrayList<Games> getScheduledEvents() {
        return scheduledEvents;
    }

    public ArrayList<String> getReportMassage() {
        return reportMassage;
    }

    @Override
    public int compareTo(Admin other) {
        int scoreComparisonResult = other.getScheduledEvents().size() - scheduledEvents.size();
        if (scoreComparisonResult != 0) {
            return scoreComparisonResult;
        }
        return other.getUserName().compareTo(this.getUserName());
    }
}