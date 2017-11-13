package bigPackage.models.AbstractModels;

/**
 * The type Car.
 */
public abstract class Car extends MotorisedVehicle {

    /**
     * The number of doors of the car
     */
    protected static int nrDoors; // Number of doors on the car

    /**
     * Get nr doors int.
     *
     * @return the number of doors of the vehicle
     */
    public int getNrDoors(){
        return nrDoors;
    }


}
