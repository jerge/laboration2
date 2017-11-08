package bigPackage.models;

import bigPackage.Car;
import bigPackage.CarTransport;
import bigPackage.Flatbed;
import bigPackage.Turbo;

import java.awt.*;

public class Truck1337 extends Car implements Flatbed, CarTransport {

    /**
     * A fixed factor which affects your acceleration
     */
    private final static double trimFactor = 1.25;

    private double flatbed;

    public void raiseFlatbed(double value){
        if(currentSpeed == 0){
            flatbed = Math.min(flatbed+value,1.0);
        } else {
            throw new IllegalStateException("Can not raise flatbed while moving");
        }
    }

    public void lowerFlatbed(double value){
        if(currentSpeed == 0){
            flatbed = Math.max(flatbed-value,0);
        } else {
            throw new IllegalStateException("Can not lower flatbed while moving");
        }
    }

    public double getFlatbed(){
        return flatbed;
    }

    public boolean isFlatbedDown(){
        return flatbed == 0.0;
    }

    protected double speedFactor(){
        return enginePower * 0.01 * trimFactor;
    }

    /**
     * Abstract method that increments speed depending on amount and model type
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
