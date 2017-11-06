package testPackage

import bigPackage.Car
import bigPackage.models.Scania

//import bigPackage.models.Saab95
//import bigPackage.models.Volvo240

import java.awt.*
import static java.lang.System.out;
import org.testng.annotations.Test

class TruckTest extends GroovyTestCase {

    @Test
    void testRaiseFlatbed() {
        Scania s = new Scania()
        s.raiseFlatbed(45)
        out.println(s.getFlatbed() == 45)

        s.raiseFlatbed(45)
        out.println(s.getFlatbed() == 70)

    }

    @Test
    void testLowerFlatbed() {
        Scania s = new Scania()
        s.raiseFlatbed(45)
        s.lowerFlatbed(35)
        out.println(s.getFlatbed() == 10)

        s.lowerFlatbed(35)
        out.println(s.getFlatbed() == 0)

    }

    @Test
    void testIncrementSpeed() {
        Scania v = new Scania()
        double a1 = v.getCurrentSpeed()
        v.incrementSpeed(0.2)
        out.println(a1 != v.getCurrentSpeed())

        boolean b = false
        try{
            v.raiseFlatbed(50)
        } catch(IllegalStateException e) {
            b = true
        }
        out.println(b)
    }
}
