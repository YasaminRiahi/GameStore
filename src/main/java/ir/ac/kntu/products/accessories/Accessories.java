package ir.ac.kntu.products.accessories;

import ir.ac.kntu.admins.Admin;
import ir.ac.kntu.products.Product;

import java.util.ArrayList;

public class Accessories extends Product {

    private int number;

    ArrayList<Admin> sellers;

    public Accessories() {
        this.sellers =  new ArrayList<>();
    }

    public Accessories(String name, String description, double cost, int number) {
        super(name, description, cost);
        this.number = number;
        this.sellers = new ArrayList<>();
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
