/*
 * to store list of vehicle we have to deliver the orders
 */
package online.bakery;

import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author salidotir
 */
public class Vehicle {
    static AtomicInteger atomicInteger = new AtomicInteger(0);
    private int vehicleId;
    private String plaque;
    private VehicleType vehicleType;
    private boolean isBusy;
    
    public Vehicle(VehicleType vehicleType, String plaque) {
        this.vehicleId = atomicInteger.incrementAndGet();
        this.plaque = plaque;
        this.vehicleType = vehicleType;
        this.isBusy = false;
    }

    /**
     * @return the vehicleId
     */
    public int getVehicleId() {
        return vehicleId;
    }

    /**
     * @param vehicleId the vehicleId to set
     */
    public boolean setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
        return true;
    }

    /**
     * @return the plaque
     */
    public String getPlaque() {
        return plaque;
    }

    /**
     * @param plaque the plaque to set
     */
    public boolean setPlaque(String plaque) {
        this.plaque = plaque;
        return true;
    }

    /**
     * @return the vehicleType
     */
    public VehicleType getVehicleType() {
        return vehicleType;
    }

    /**
     * @param vehicleType the vehicleType to set
     */
    public boolean setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
        return true;
    }

    /**
     * @return the isBusy
     */
    public boolean isIsBusy() {
        return isBusy;
    }

    /**
     * @param isBusy the isBusy to set
     */
    public void setIsBusy(boolean isBusy) {
        this.isBusy = isBusy;
    }
    
    public String getVehicleInformation() {
        String s = "";
        s += "id: " + vehicleId + "\n" +
                "type: " + vehicleType + "\n" + 
                "plaque: " + plaque + "\n" +
                "is busy: " + isBusy + "\n";
        return s;
    }
    
}
