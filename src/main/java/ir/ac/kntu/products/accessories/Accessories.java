package ir.ac.kntu.products.accessories;

import ir.ac.kntu.products.Product;

public class Accessories extends Product {

    private int number;

    public Accessories() {
    }

    public Accessories(String name, String description, double cost, int number) {
        super(name, description, cost);
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
