package bigPackage.models;

import bigPackage.models.AbstractModels.Car;

import java.awt.*;

/**
 * The type Volvo 240.
 */
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
    protected double speedFactor(){
        return enginePower * 0.01 * trimFactor;
    }

}