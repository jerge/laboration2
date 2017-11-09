package bigPackage;

public interface ICarTransport {
//    Deque<Car> cargo = new ArrayDeque<>();

//    boolean isWithinRange(Car c);
    boolean load(Car c);
    Car unload();

    double getRange();
    int getCapacity();


}
