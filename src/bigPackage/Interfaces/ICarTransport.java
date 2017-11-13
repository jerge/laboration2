package bigPackage.Interfaces;

import bigPackage.models.AbstractModels.Car;

/**
 * The interface Car transport.
 */
public interface ICarTransport {
//    Deque<Car> cargo = new ArrayDeque<>();

    /**
     * Loads car onto transporter's cargo.
     *
     * @param c the c
     * @return True if load was successful
     */
//    boolean isWithinRange(Car c);
    boolean load(Car c);

    /**
     * Unload car from transporter's cargo.
     *
     * @return the car unloaded
     */
    Car unload();

    /**
     * Gets range.
     *
     * @return the range
     */
    double getRange();

    /**
     * Gets capacity.
     *
     * @return the capacity
     */
    int getCapacity();


}
