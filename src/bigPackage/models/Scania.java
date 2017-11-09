package bigPackage.models;

import bigPackage.Car;
import bigPackage.IFlatbed;
import bigPackage.Truck;

import java.awt.*;

public class Scania extends Truck implements IFlatbed {

    /**
     * A fixed factor which affects your acceleration
     */
    private final static double trimFactor = 1.25;
    private static double maxIncline = 70.0;
    private double flatbedIncline;

    public Scania(){
        nrDoors = 2;
        color = Color.red;
        enginePower = 730;
        modelName = "Scania R 730";
        maxIncline = 70.0;
        stopEngine();
    }

    /**
     * Raises the flatbed by a specified amount, up to a max of <i>maxIncline</i> degrees
     * @param value of degrees you want the flatbed to be raised
     */
    public void raiseFlatbed(double value){
        if(currentSpeed == 0){
            flatbedIncline = Math.min(flatbedIncline + value,maxIncline);
            flatbedDown = (flatbedIncline == 0.0);
        } else {
            throw new IllegalStateException("Can not lower flatbed while moving");
        }
    }

    /**
     * Lowers the flatbed by a specified amount, down to a minimum of 0 degrees
     * @param value of degrees you want the flatbed to be lowered
     */
    public void lowerFlatbed(double value){
        if(currentSpeed == 0){
            flatbedIncline = Math.max(flatbedIncline - value,0.0);
            flatbedDown = (flatbedIncline == 0.0);
        } else {
            throw new IllegalStateException("Can not lower flatbed while moving");
        }
    }

    /**
     * Calculates the acceleration rate of the car
     * The calculated value depends on trim factor and engine power
     * @return the calculated acceleration rate
     */
    protected double speedFactor(){
        return enginePower * 0.01 * trimFactor;
    }
}
