package ir.ac.kntu.products.accessories.monitorGaming;

import ir.ac.kntu.products.Product;
import ir.ac.kntu.products.accessories.Accessories;

public class MonitorGaming extends Accessories {

    private double screenSize;

    private double refreshRate;

    private double responseTime;

    public MonitorGaming(String name, String description, double cost, int number, double screenSize, double refreshRate,
                         double responseTime) {
        super(name, description, cost, number);
        this.screenSize = screenSize;
        this.refreshRate = refreshRate;
        this.responseTime = responseTime;
    }

    public MonitorGaming() {
        super();
    }

    public double getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(double screenSize) {
        this.screenSize = screenSize;
    }

    public double getRefreshRate() {
        return refreshRate;
    }

    public void setRefreshRate(double refreshRate) {
        this.refreshRate = refreshRate;
    }

    public double getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(double responseTime) {
        this.responseTime = responseTime;
    }
}
