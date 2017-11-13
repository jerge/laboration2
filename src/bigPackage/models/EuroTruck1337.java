package bigPackage.models;

import bigPackage.Accessories.Flatbed;
import bigPackage.models.AbstractModels.Car;
import bigPackage.Accessories.CargoHold;
import bigPackage.Interfaces.ICarTransport;
import bigPackage.models.AbstractModels.Truck;

import java.awt.*;
import java.util.Deque;

/**
 * The type Euro truck 1337.
 */
public class EuroTruck1337 extends Truck implements ICarTransport{

    /**
     * A fixed factor which affects your acceleration
     */
    private final static double trimFactor = 1.25;
    private final double range = 5;
    private final int capacity = 10;
    /**
     * The truck's Flatbed
     */
    private Flatbed flatbed;
    /**
     * The truck's CargoHold
     */
    private CargoHold cargo;

    /**
     * Instantiates a new Euro truck 1337.
     */
    public EuroTruck1337(){
        nrDoors = 2;
        color = Color.red;
        enginePower = 1337;
        modelName = "Euro Truck 1337";
        flatbed = new Flatbed();
        cargo = new CargoHold(true, range, capacity);
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

    /**
     * Uses Pythagoras Theorem to check if the <i>vehicle</i> is within range
     *
     * @param c the vehicle to check
     * @return True if the vehicle is in range of the current object
     */
    public boolean isWithinRange(Car c){
        return Math.sqrt(Math.pow(c.getPosition()[0] - this.getPosition()[0],2) +
                (Math.pow(c.getPosition()[1] - this.getPosition()[1],2))) <= range;
    }

    public boolean load(Car c){
        return cargo.load(c,this,isFlatbedDown());
    }

    public Car unload(){
        return cargo.unload(isFlatbedDown());
    }

    /**
     * Syncs state for all cars in cargo when truck moves
     */
    @Override
    public void move(){
        super.move();
        for (Car c : (Deque<Car>)cargo.getCargo()) {
            cargo.syncState(c,this);
        }
    }

    protected double speedFactor(){
        return enginePower * 0.01 * trimFactor;
    }


}
