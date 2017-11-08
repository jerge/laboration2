package bigPackage.models;

import bigPackage.Car;
import bigPackage.Flatbed;

import java.awt.*;

public class Scania extends Car implements Flatbed {

    /**
     * A fixed factor which affects your acceleration
     */
    private final static double trimFactor = 1.25;

    /**
     * The flatbeds current incline represented in degrees
     */
    private double flatbed;

    public Scania(){
        nrDoors = 2;
        color = Color.red;
        enginePower = 730;
        modelName = "Scania R 730";
        flatbed = 0.0;
        stopEngine();
    }

    /**
     * Raises the flatbed by a specified amount, up to a max of 70 degrees
     * @param degrees of degrees you want the flatbed to be raised
     */
    public void raiseFlatbed(double degrees){
        if(currentSpeed == 0){
            flatbed = Math.min(flatbed+degrees,70.0);
        } else {
          throw new IllegalStateException("Can not raise flatbed while moving");
        }
    }

    /**
     * Lowers the flatbed by a specified amount, down to a minimum of 0 degrees
     * @param value of degrees you want the flatbed to be lowered
     */
    public void lowerFlatbed(double value){
        if(currentSpeed == 0){
            flatbed = Math.max(flatbed - value,0);
        } else {
            throw new IllegalStateException("Can not lower flatbed while moving");
        }
    }

    public double getFlatbed() {
        return flatbed;
    }

    /**
     * Checks if the flatbed is fully lowered
     * @return true if allowed to move
     */
    public boolean isFlatbedDown(){
        return flatbed == 0.0;
    }

    /**
     * Calculates the acceleration rate of the car
     * The calculated value depends on trim factor and engine power
     * @return the calculated acceleration rate
     */
    protected double speedFactor(){
        return enginePower * 0.01 * trimFactor;
    }

    /**
     * Increases the speed depending on amount and speed factor
     * The new speed can not be increased above the engine power
     * @param amount a value between 0 and 1, more increments more
     */
    @Override
    protected void incrementSpeed(double amount){
        if(isFlatbedDown()) {
            currentSpeed = Math.min(getCurrentSpeed() + speedFactor() * amount, enginePower);
        }
        else{
            throw new IllegalArgumentException("Can't move when flatbed is not lowered");
        }
    }
}
