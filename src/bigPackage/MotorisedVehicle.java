package bigPackage;

import java.awt.*;

public abstract class MotorisedVehicle implements Movable {

    /**
     * Current speed of the vehicle
     * Calculated with an arbitrary unit
     */
    protected double currentSpeed; // The current speed of the car

    /**
     * The vehicle's current color
     */
    protected Color color; // Color of the car

    /**
     * The vehicle's model name
     */
    protected String modelName;

    /**
     * The position of the vehicle in the universe
     * <i>position</i> = [X,Y]
     */
    private double[] position = new double[]{0,0};

    /**
     * The direction the vehicle is facing, in radians
     * The direction is calculated according to the unit circle
     */
    private double direction = Math.PI/2;


    /**
     * Current engine power of the car
     * Calculated with an arbitrary unit
     */
    protected double enginePower; // Engine power of the car

    public String getModelName() {
        return modelName;
    }

    /**
     * @return current speed in arbitrary units
     */
    public double getCurrentSpeed(){
        return currentSpeed;
    }

    public double[] getPosition(){
        return position;
    }

    public double getDirection() { return direction; }

    /**
     * @return car's engine power in arbitrary form
     */
    public double getEnginePower(){
        return enginePower;
    }


    /**
     * @return color of the vehicle
     */
    public Color getColor(){
        return color;
    }

    /**
     * @param clr the color to set
     */
    public void setColor(Color clr){
        color = clr;
    }

    public void stopEngine(){
        currentSpeed = 0;
    }

    public void startEngine(){
        currentSpeed = 0.1;
    }

    /**
     * Changes the vehicle's direction by one quarter of a revolution to the left
     */
    @Override
    public void turnLeft() {
        direction += Math.PI/2;
        direction = direction % (Math.PI*2);
    }

    /**
     * Changes the vehicle's direction by one quarter of a revolution to the right
     */
    @Override
    public void turnRight() {
        direction -= Math.PI/2;
        direction = (direction + Math.PI*2) % (Math.PI*2);
    }

    /**
     * Changes the vehicle's current position according to it's speed and direction
     * The amount is calculated via simple trigonometry
     */
    @Override
    public void move() {
        position[0] += Math.cos(direction) * currentSpeed;
        position[1] += Math.sin(direction) * currentSpeed;
    }

    /**
     * Calculates the acceleration rate of the car
     * The calculated value depends on turbo's state and engine power
     * @return the calculated acceleration rate
     */
    protected abstract double speedFactor();

    /**
     * Abstract method that increments speed depending on amount and model type
     * @param amount a value between 0 and 1, more increments more
     */
    protected void incrementSpeed(double amount){
        currentSpeed = Math.min(getCurrentSpeed() + speedFactor() * amount,enginePower);
    }

    /**
     * Decreases the speed depending on amount and speed factor
     * The new speed can not be lowered below 0
     * @param amount a value between 0 and 1, more decrements more
     */
    protected void decrementSpeed(double amount){
        currentSpeed = Math.max(getCurrentSpeed() - speedFactor() * amount,0);
    }

    /**
     * Calls the incrementSpeed() method from the subclass, depending on model name
     * @param amount is the value you want to increase your speed with
     */
    public void gas(double amount){
        if(amount > 0 && amount <= 1) {
            incrementSpeed(amount);
        } else{
            throw new IllegalArgumentException("Gas only accepts values from 0-1");
        }
    }

    /**
     * Calls the decrementSpeed() method from the subclass, depending on model name
     * @param amount is the value you want to increase your speed with
     */
    public void brake(double amount){
        if(amount > 0 && amount <= 1) {
            decrementSpeed(amount);
        } else{
            throw new IllegalArgumentException("Break only accepts values from 0-1");
        }
    }

}
