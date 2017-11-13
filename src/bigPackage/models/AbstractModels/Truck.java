package bigPackage.models.AbstractModels;

import bigPackage.Interfaces.IHasFlatbed;

/**
 * The type Truck.
 */
public abstract class Truck extends Car implements IHasFlatbed {

    public double getFlatbedIncline(){
        return getFlatbed().getFlatbedIncline();
    }
    public double getMaxIncline(){
        return getFlatbed().getMaxIncline();
    }
    public boolean isFlatbedDown() { return getFlatbed().isFlatbedDown(); }

    public void raiseFlatbed(){
        getFlatbed().raiseFlatbed(currentSpeed);
    }

    public void lowerFlatbed(){
        getFlatbed().lowerFlatbed(currentSpeed);
    }

    /**
     * Increases the speed depending on amount and speed factor
     * The new speed can not be increased above the engine power
     * @param amount a value between 0 and 1, more increments more
     */
    @Override
    protected void incrementSpeed(double amount){
        if(isFlatbedDown()) {
            super.incrementSpeed(amount);
        }
        else{
            throw new IllegalArgumentException("Can't move when flatbed is not lowered");
        }
    }

}
