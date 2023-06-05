package ir.ac.kntu.products;

import java.util.ArrayList;

public class Product {

    private String name;

    private String description;

    private double cost;

    private ArrayList<String> community;

    private ArrayList<String> feedback;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public ArrayList<String> getCommunity() {
        return community;
    }

    public void setCommunity(ArrayList<String> community) {
        this.community = community;
    }

    public ArrayList<String> getFeedback() {
        return feedback;
    }

    public void setFeedback(ArrayList<String> feedback) {
        this.feedback = feedback;
    }

    public Product() {
        this.community = new ArrayList<>();
        this.feedback = new ArrayList<>();
    }

    public Product(String name, String description, double cost) {
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.community = new ArrayList<>();
        this.feedback = new ArrayList<>();
    }
}
