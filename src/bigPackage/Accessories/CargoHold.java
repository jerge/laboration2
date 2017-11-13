package bigPackage.Accessories;



import bigPackage.models.AbstractModels.Car;
import bigPackage.models.AbstractModels.MotorisedVehicle;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * The type Cargo hold.
 */
public class CargoHold {

    /**
     * The reaching capability for the CargoHold in arbitrary units
     */
    private double range;
    /**
     * The max capacity the CargoHold can carry
     */
    private int capacity;
    /**
     * The order of unloading (first-in-last-out)
     */
    private boolean filo;
    /**
     * The list for the cargo
     */
    private Deque cargo;


    /**
     * Instantiates a new Cargo hold.
     *
     * @param filo     the order of unloading (first-in-last-out)
     * @param range    the range
     * @param capacity the capacity
     */
    public CargoHold(boolean filo, double range, int capacity){
        this.range = range;
        this.capacity = capacity;
        this.filo = filo;
        cargo = new ArrayDeque<Car>(capacity);
    }

    /**
     * Gets cargo.
     *
     * @return the cargo
     */
    public Deque getCargo() {
        return cargo;
    }

    /**
     * Uses Pythagoras Theorem to check if the <i>vehicle</i> is within range
     *
     * @param c  the vehicle to check
     * @param tp the transporter
     * @return True if the vehicle is in range of the current object
     */
    public boolean isWithinRange(Car c, MotorisedVehicle tp ){
        return Math.sqrt(Math.pow(c.getPosition()[0] - tp.getPosition()[0],2) +
                (Math.pow(c.getPosition()[1] - tp.getPosition()[1],2))) <= range;
    }

    /**
     * Load boolean.
     *
     * @param c   the car to load
     * @param tp  the transporter
     * @param iFD if the flatbed is down
     * @return True if the load was successful
     */
    public boolean load(Car c, MotorisedVehicle tp, boolean iFD){
        if (isWithinRange(c,tp)
                && !c.isOnTransport()
                && capacity >= cargo.size()
                && iFD
                && c != tp){
            addToCargo(c,tp);
            return true;
        }
        return false;
    }

    /**
     * Help method
     * @param c Car to load
     * @param tp Transporter to load onto
     */
    private void addToCargo(Car c, MotorisedVehicle tp){
        syncState(c,tp);
        c.stopEngine();
        c.setOnTransport(true);
        cargo.add(c);
    }

    /**
     * Unload car.
     *
     * @param iFD if the flatbed is down
     * @return the unloaded car
     */
    public Car unload(boolean iFD){
        if (!iFD){
            return null;
        }
        Car c;
        if (filo) {
            c = (Car)cargo.pollLast();
        } else {
            c = (Car)cargo.pollFirst();
        }
        c.setOnTransport(false);
        return c;
    }

    /**
     * Sync state.
     *
     * @param c  the car
     * @param tp the transporter
     */
    public void syncState(Car c, MotorisedVehicle tp){
        c.setPosition(tp.getPosition());
        c.setDirection(tp.getDirection());
    }
}
