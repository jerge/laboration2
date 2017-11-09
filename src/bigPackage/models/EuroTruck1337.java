package bigPackage.models;

import bigPackage.Car;
import bigPackage.CargoHold;
import bigPackage.ICarTransport;
import bigPackage.Truck;

import java.awt.*;
import java.util.ArrayDeque;
import java.util.Deque;

public class EuroTruck1337 extends Truck implements ICarTransport{

    /**
     * A fixed factor which affects your acceleration
     */
    private final static double trimFactor = 1.25;
    private final double range = 5;
    private final int capacity = 10;
    private CargoHold cargo = new CargoHold(true, range, capacity);


    public EuroTruck1337(){
        nrDoors = 2;
        color = Color.red;
        enginePower = 1337;
        modelName = "EuroTruck1337";
        stopEngine();
    }

    public double getRange(){
        return range;
    }

    public int getCapacity(){
        return capacity;
    }

    /**
     * Uses Pythagoras Theorem to check if the <i>vehicle</i> is within range
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
