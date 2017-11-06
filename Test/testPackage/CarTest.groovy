package testPackage

import bigPackage.Car
import bigPackage.models.Saab95
import bigPackage.models.Volvo240

import java.awt.*
import org.testng.annotations.Test

class CarTest extends GroovyTestCase {

    @Test
    void testSaab95() {
        Car s = new Saab95()
        System.out.println(s != null)
    }

    @Test
    void testGetNrDoors() {
        Car c = new Saab95()
        System.out.println(c.getNrDoors() == 2)
    }

    @Test
    void testGetEnginePower() {
        Car c = new Saab95()
        System.out.println(c.getEnginePower() == 125)
    }

    @Test
    void testGetModelName() {
        Car c = new Saab95()
        System.out.println(c.getModelName() == "Saab95")
    }

    @Test
    void testGetCurrentSpeed() {
        Car c = new Volvo240()
        System.out.println(c.getCurrentSpeed() == (double)0)
    }

    @Test
    void testGetColor() {
        Car c = new Saab95()
        System.out.println(c.getColor() != (Color.black))
        System.out.println(c.getColor() == (Color.red))
    }

    @Test
    void testSetColor() {
        Car c = new Saab95()
        c.setColor(Color.blue)
        System.out.println(c.getColor() == Color.blue)
    }

    @Test
    void testStartEngine() {
        Car c = new Volvo240()
        c.startEngine()
        System.out.println(c.getCurrentSpeed() == (double)0.1)
    }

    @Test
    void testStopEngine() {
        Car c = new Saab95()
        c.startEngine()
        c.stopEngine()
        System.out.println(c.getCurrentSpeed() == (double)0)
    }

    @Test
    void testTurnLeft() {
        Car c = new Volvo240()
        c.turnLeft()
        System.out.println(c.getDirection() == Math.PI)
        c.turnLeft()
        System.out.println(c.getDirection() == 3*Math.PI/2)
        c.turnLeft()
        c.turnLeft()
        c.turnLeft()
        System.out.println(c.getDirection() == Math.PI)
    }

    @Test
    void testTurnRight() {
        Car c = new Volvo240()
        c.turnRight()
        System.out.println(c.getDirection() == 0)
        c.turnRight()
        System.out.println(c.getDirection() == 3*Math.PI/2)
        c.turnRight()
        c.turnRight()
        c.turnRight()
        System.out.println(c.getDirection() == 0)
    }

    @Test
    void testMove() {
        Car c = new Saab95()
        double a = c.getPosition()[0]
        double b = c.getPosition()[1]
        c.gas(0.3)
        c.move()
        double x = c.getPosition()[0]
        double y = c.getPosition()[1]

        System.out.println( a != x || b != y)
    }

    void testSetTurboOn() {
        Car c1 = new Saab95()
        Car c2 = new Saab95()
        c1.gas(0.5)
        c2.setTurboOn()
        c2.gas(0.5)
        System.out.println(c1.currentSpeed != c2.currentSpeed)
    }

    void testSetTurboOff() {
        Car c1 = new Saab95()
        Car c2 = new Saab95()
        c1.gas(0.5)
        c2.setTurboOn()
        c2.setTurboOff()
        c2.gas(0.5)
        System.out.println(c1.currentSpeed == c2.currentSpeed)
    }

    @Test
    void testGas() {
        Car c = new Volvo240()
        double a = c.getCurrentSpeed()
        c.gas(0.5)
        System.out.println(c.getCurrentSpeed() != a)

        Car c2 = new Saab95()
        c2.setTurboOn()
        c2.gas(10)
        double a2 = c2.getCurrentSpeed()
        System.out.println(c.getCurrentSpeed() != a2)

    }

    @Test
    void testIncrementSpeed() {
        Car v = new Volvo240()
        double a1 = v.getCurrentSpeed()
        v.incrementSpeed(0.2)
        System.out.println(a1 != v.getCurrentSpeed())

        Car s = new Saab95()
        double b1 = s.getCurrentSpeed()
        s.incrementSpeed(0.3)
        System.out.println(b1 != s.getCurrentSpeed())
    }

    @Test
    void testBrake() {
        Car c = new Volvo240()
        c.gas(0.5)
        double a = c.getCurrentSpeed()
        c.brake(0.8)
        System.out.println(c.getCurrentSpeed() != a)

        Car c1 = new Saab95()
        c1.gas(0.4)
        double a1 = c1.getCurrentSpeed()
        c1.brake(0.8)
        System.out.println(c1.getCurrentSpeed() != a1)
    }

    @Test
    void testDecrementSpeed() {
        Car v = new Volvo240()
        v.incrementSpeed(0.6)
        double a1 = v.getCurrentSpeed()
        v.decrementSpeed(0.4)
        System.out.println(a1 != v.getCurrentSpeed())


        Car s = new Saab95()
        s.incrementSpeed(0.8)
        double b1 = s.getCurrentSpeed()
        s.decrementSpeed(0.3)
        System.out.println(b1 != s.getCurrentSpeed())


    }


}
