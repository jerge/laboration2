package bigPackage;

public interface IFlatbed {

    boolean flatbedDown = false;

    double flatbedIncline = 0.0;

    double maxIncline = 90.0;

    boolean raiseFlatbed();

    boolean lowerFlatbed();

    boolean isFlatbedDown();
}
