package bigPackage.models;

import bigPackage.Accessories.CargoHold;
import bigPackage.Accessories.Flatbed;
import bigPackage.Interfaces.ICarTransport;
import bigPackage.Interfaces.IHasFlatbed;
import bigPackage.models.AbstractModels.Car;
import bigPackage.models.AbstractModels.MotorisedVehicle;

import java.awt.*;
import java.util.Deque;

/**
 * The type Ferry master 420.
 */
public class FerryMaster420 extends MotorisedVehicle implements ICarTransport, IHasFlatbed {

    private final static double trimFactor = 25;
    private final double range = 20;
    private final int capacity = 100;
    private Flatbed flatbed;
    private CargoHold cargo;

    /**
     * Instantiates a new Ferry master 420.
     */
    public FerryMaster420() {
        color = Color.green;
        enginePower = 420;
        modelName = "Ferry Master 420";
        flatbed = new Flatbed();
        cargo = new CargoHold(false, range, capacity);
        stopEngine();
    }

    public Flatbed getFlatbed(){
        return flatbed;
    }

    public double getRange(){
        return range;
    }

    public int getCapacity(){
        return capacity;
    }

    public double getFlatbedIncline() {
        return flatbed.getFlatbedIncline();
    }

    public double getMaxIncline() {
        return flatbed.getMaxIncline();
    }

    public boolean isFlatbedDown() {
        return flatbed.isFlatbedDown();
    }

    public void raiseFlatbed() {
        flatbed.raiseFlatbed(currentSpeed);
    }

    public void lowerFlatbed() {
        flatbed.lowerFlatbed(currentSpeed);
    }

    public boolean load(Car c){
        return cargo.load(c,this,isFlatbedDown());
    }

    public Car unload(){
        return cargo.unload(isFlatbedDown());
    }

    protected double speedFactor(){
        return enginePower * 0.01 * trimFactor;
    }

    @Override
    protected void incrementSpeed(double amount){
        if(isFlatbedDown()) {
            super.incrementSpeed(amount);
        }
        else{
            throw new IllegalArgumentException("Can't move when flatbed is not lowered");
        }
    }

    @Override
    public void move(){
        super.move();
        for (Car c : (Deque<Car>)cargo.getCargo()) {
            cargo.syncState(c,this);
        }
    }

}
