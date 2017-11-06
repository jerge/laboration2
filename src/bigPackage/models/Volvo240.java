package bigPackage.models;

import bigPackage.Car;

import java.awt.*;

public class Volvo240 extends Car {

    /**
     * A fixed factor which affects your acceleration
     */
    private final static double trimFactor = 1.25;


    /**
     * Constructor with hardcoded values
     */
    public Volvo240(){
        nrDoors = 4;
        color = Color.black;
        enginePower = 100;
        modelName = "Volvo240";
        stopEngine();
    }

    /**
     * Calculates the acceleration rate of the car
     * The calculated value depends on trim factor and engine power
     * @return the calculated acceleration rate
     */
    private double speedFactor(){
        return enginePower * 0.01 * trimFactor;
    }

    /**
     * Increases the speed depending on amount and speed factor
     * The new speed can not be increased above the engine power
     * @param amount a value between 0 and 1, more increments more
     */
    @Override
    protected void incrementSpeed(double amount){
        currentSpeed = Math.min(getCurrentSpeed() + speedFactor() * amount,enginePower);
    }

    /**
     * Decreases the speed depending on amount and speed factor
     * The new speed can not be lowered below 0
     * @param amount a value between 0 and 1, more decrements more
     */
    @Override
    protected void decrementSpeed(double amount){
        currentSpeed = Math.max(getCurrentSpeed() - speedFactor() * amount,0);
    }

}