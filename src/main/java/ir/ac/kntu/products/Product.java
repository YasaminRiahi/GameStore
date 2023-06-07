package ir.ac.kntu.products;

import ir.ac.kntu.regularUsers.RegularUser;

import java.util.ArrayList;

public class Product implements Comparable<Product>{

    private String name;

    private String description;

    private double cost;

    private ArrayList<String> community;

    private ArrayList<String> feedback;

    private int numberOfSoldItems;

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

    public int getNumberOfSoldItems() {
        return numberOfSoldItems;
    }

    public void setNumberOfSoldItems(int numberOfSoldItems) {
        this.numberOfSoldItems = numberOfSoldItems;
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
        this.numberOfSoldItems = 0;
    }

    @Override
    public int compareTo(Product other) {
        int scoreComparisonResult = other.numberOfSoldItems - numberOfSoldItems;
        if (scoreComparisonResult != 0) {
            return scoreComparisonResult;
        }
        return other.getName().compareTo(this.getName());
    }
}
