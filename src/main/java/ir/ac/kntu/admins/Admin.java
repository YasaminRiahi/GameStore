package ir.ac.kntu.admins;

import ir.ac.kntu.products.games.Games;
import ir.ac.kntu.store.User;

import java.util.ArrayList;

public class Admin extends User {

    private TypeOfAdmins typeOfAdmins;

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

    public void setDeveloperGame(ArrayList<Games> developerGame) {
        this.developerGame = developerGame;
    }

    public TypeOfAdmins getTypeOfAdmins() {
        return typeOfAdmins;
    }

    public void setTypeOfAdmins(TypeOfAdmins typeOfAdmins) {
        this.typeOfAdmins = typeOfAdmins;
    }

    public ArrayList<Games> getInbox() {
        return inbox;
    }

    public void setInbox(ArrayList<Games> inbox) {
        this.inbox = inbox;
    }

    public ArrayList<Games> getScheduledEvents() {
        return scheduledEvents;
    }

    public void setScheduledEvents(ArrayList<Games> scheduledEvents) {
        this.scheduledEvents = scheduledEvents;
    }

    public ArrayList<String> getReportMassage() {
        return reportMassage;
    }

    public void setReportMassage(ArrayList<String> reportMassage) {
        this.reportMassage = reportMassage;
    }
}
