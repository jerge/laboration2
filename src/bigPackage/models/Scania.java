package bigPackage.models;

import bigPackage.Accessories.GradualFlatbed;
import bigPackage.Interfaces.IHasFlatbed;
import bigPackage.models.AbstractModels.Truck;

import java.awt.*;

/**
 * The type Scania.
 */
public class Scania extends Truck implements IHasFlatbed {

    /**
     * A fixed factor which affects your acceleration
     */
    private final static double trimFactor = 1.25;

    private GradualFlatbed flatbed;

    /**
     * Instantiates a new Scania.
     */
    public Scania(){
        nrDoors = 2;
        color = Color.red;
        enginePower = 730;
        modelName = "Scania R 730";
        flatbed = new GradualFlatbed(70.0);
        stopEngine();
    }

    public GradualFlatbed getFlatbed(){
        return flatbed;
    }

    /**
     * Raises the flatbed by a specified amount, up to a max of <i>maxIncline</i> degrees
     *
     * @param value of degrees you want the flatbed to be raised
     */
    public void raiseFlatbed(double value){
        flatbed.raiseFlatbed(currentSpeed,value);
    }

    /**
     * Lowers the flatbed by a specified amount, down to a minimum of 0 degrees
     *
     * @param value of degrees you want the flatbed to be lowered
     */
    public void lowerFlatbed(double value){
        flatbed.lowerFlatbed(currentSpeed,value);
    }

    /**
     * Calculates the acceleration rate of the car
     * The calculated value depends on trim factor and engine power
     * @return the calculated acceleration rate
     */
    protected double speedFactor(){
        return (enginePower * 0.01 * trimFactor);
    }
}
