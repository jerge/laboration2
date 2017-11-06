package bigPackage;

import java.awt.*;

public abstract class Car implements Movable{

    /**
     * The number of doors of the car
     */
    protected int nrDoors; // Number of doors on the car

    /**
     * Current engine power of the car
     * Calculated with an arbitrary unit
     */
    protected double enginePower; // Engine power of the car

    /**
     * Current speed of the car
     * Calculated with an arbitrary unit
     */
    protected double currentSpeed; // The current speed of the car

    /**
     * The car's current color
     */
    protected Color color; // Color of the car

    /**
     * The car's model name
     */
    protected String modelName;

    /**
     * The position of the car in the universe
     * <i>position</i> = [X,Y]
     */
    private double[] position = new double[]{0,0};

    /**
     * The direction the car is facing, in radians
     * The direction is calculated according to the unit circle
     */
    private double direction = Math.PI/2;  // Radians -> according to unit circle

    /**
     * @return the number of doors of the vehicle
     */
    public int getNrDoors(){
        return nrDoors;
    }

    /**
     * @return car's engine power in arbitrary form
     */
    public double getEnginePower(){
        return enginePower;
    }

    public String getModelName() {
        return modelName;
    }

    /**
     * @return current speed in arbitrary form
     */
    public double getCurrentSpeed(){
        return currentSpeed;
    }

    public double[] getPosition(){
        return position;
    }

    public double getDirection() { return direction; }

    /**
     * @return color of the car
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

    /**
     * Sets the current speed to 0.1
     */
    public void startEngine(){
        currentSpeed = 0.1;
    }

    /**
     * Sets the current speed to 0
     */
    public void stopEngine(){
        currentSpeed = 0;
    }

    /**
     * Changes the car's direction by one quarter of a revolution to the left
     */
    @Override
    public void turnLeft() {
        direction += Math.PI/2;
        direction = direction % (Math.PI*2);
    }

    /**
     * Changes the car's direction by one quarter of a revolution to the right
     */
    @Override
    public void turnRight() {
        direction -= Math.PI/2;
        direction = (direction + Math.PI*2) % (Math.PI*2);
    }

    /**
     * Changes the car's current position according to it's speed and direction
     * The amount is calculated via simple trigonometry
     */
    @Override
    public void move() {
        position[0] += Math.cos(direction) * currentSpeed;
        position[1] += Math.sin(direction) * currentSpeed;
    }

    /**
     * Abstract method that increments speed depending on amount and model type
     * @param amount a value between 0 and 1, more increments more
     */
    protected abstract void incrementSpeed(double amount);

    /**
     * Abstract method that decrements speed depending on amount and model type
     * @param amount a value between 0 and 1, more decrements more
     */
    protected abstract void decrementSpeed(double amount);

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
