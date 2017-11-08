package bigPackage;

import java.util.ArrayDeque;
import java.util.Deque;

public interface CarTransport {
    Deque<Car> cargo = new ArrayDeque<>();
    int range = 5;
    int capacity = 1;

    boolean load(Car c);
    Car unload();


}
