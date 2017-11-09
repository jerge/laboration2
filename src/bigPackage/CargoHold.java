package bigPackage;



import java.util.ArrayDeque;
import java.util.Deque;

public class CargoHold {

    private double range;
    private int capacity;
    private boolean filo;
    private Deque cargo;


    public CargoHold(boolean filo, double range, int capacity){
        this.range = range;
        this.capacity = capacity;
        this.filo = filo;
        cargo = new ArrayDeque<Car>(capacity);
    }

    public Deque getCargo() {
        return cargo;
    }

    /**
     * Uses Pythagoras Theorem to check if the <i>vehicle</i> is within range
     * @param c the vehicle to check
     * @return True if the vehicle is in range of the current object
     */
    public boolean isWithinRange(Car c, MotorisedVehicle tp ){
        return Math.sqrt(Math.pow(c.getPosition()[0] - tp.getPosition()[0],2) +
                (Math.pow(c.getPosition()[1] - tp.getPosition()[1],2))) <= range;
    }

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

    private void addToCargo(Car c, MotorisedVehicle tp){
        syncState(c,tp);
        c.stopEngine();
        c.setOnTransport(true);
        cargo.add(c);
    }

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

    public void syncState(Car c, MotorisedVehicle tp){
        c.setPosition(tp.getPosition());
        c.setDirection(tp.getDirection());
    }
}
