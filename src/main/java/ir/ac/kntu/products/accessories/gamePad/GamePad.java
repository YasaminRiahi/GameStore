package ir.ac.kntu.products.accessories.gamePad;

import ir.ac.kntu.products.accessories.Accessories;

public class GamePad extends Accessories {

    ConnectionType connectionType;

    DeviceType deviceType;

    public GamePad() {
        super();
    }

    public GamePad(String name, String description, double cost, int number, ConnectionType connectionType, DeviceType deviceType) {
        super(name, description, cost, number);
        this.connectionType = connectionType;
        this.deviceType = deviceType;
    }

    public ConnectionType getConnectionType() {
        return connectionType;
    }

    public void setConnectionType(ConnectionType connectionType) {
        this.connectionType = connectionType;
    }

    public DeviceType getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(DeviceType deviceType) {
        this.deviceType = deviceType;
    }
}