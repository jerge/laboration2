package bigPackage.models;
import bigPackage.Car;

import java.awt.*;

public class Saab95 extends Car {

    private boolean turboOn;

    /**
     * Constructor with hardcoded values
     */
    public Saab95(){
        nrDoors = 2;
        color = Color.red;
        enginePower = 125;
        turboOn = false;
        modelName = "Saab95";
        stopEngine();
    }

    /**
     * Turns on turbo
     */
    public void setTurboOn(){
        turboOn = true;
    }

    /**
     * Turns off turbo
     */
    public void setTurboOff(){
        turboOn = false;
    }

    /**
     * Calculates the acceleration rate of the car
     * The calculated value depends on turbo's state and engine power
     * @return the calculated acceleration rate
     */
    private double speedFactor(){
        double turbo = 1;
        if(turboOn) turbo = 1.3;
        return enginePower * 0.01 * turbo;
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