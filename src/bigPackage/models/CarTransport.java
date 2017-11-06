package bigPackage.models;

import bigPackage.Car;
import bigPackage.Flatbed;

import java.util.ArrayDeque;
import java.util.Deque;

public abstract class CarTransport extends Car implements Flatbed {

    protected Deque<Car> cargo = new ArrayDeque<>();
    private int range = 5;
    private boolean rampOut;

}
