package bigPackage;

public abstract class Truck extends Car implements IFlatbed {

    /**
     * The flatbeds current incline represented in degrees
     */
    protected boolean flatbedDown;


    /**
     * Raises the flatbed
     */
    public boolean raiseFlatbed(){
        if(currentSpeed == 0.0){
            flatbedDown = false;
        } else {
            throw new IllegalStateException("Can not raise flatbed while moving");
        }
        return flatbedDown;
    }

    /**
     * Lowers the flatbed
     */
    public boolean lowerFlatbed(){
        if(currentSpeed == 0){
            flatbedDown = true;
//            flatbed = Math.max(flatbed - value,0.0);
        } else {
            throw new IllegalStateException("Can not lower flatbed while moving");
        }
        return flatbedDown;
    }

    /**
     * Checks if the flatbed is fully lowered
     * @return true if allowed to move
     */
    public boolean isFlatbedDown(){
        return flatbedDown;
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
