package bigPackage.Implementations;

import bigPackage.Interfaces.IHasFlatbed;
import bigPackage.Interfaces.ITurbo;
import bigPackage.models.AbstractModels.ACar;
import bigPackage.models.Saab95;
import bigPackage.models.Scania;
import bigPackage.models.Volvo240;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/*
* This class represents the Controller part in the MVC pattern.
* It's responsibilities is to listen to the View and responds in a appropriate manner by
* modifying the model state and the updating the view.
 */

public class CarController {
    // member fields:

    // The delay (ms) corresponds to 100 updates a sec (hz)
    private final int delay = 10;
    // The timer is started with an listener (see below) that executes the statements
    // each step between delays.
    private Timer timer = new Timer( delay, new TimerListener() );

    // The frame that represents this instance View of the MVC pattern
    CarView frame;
    // A list of ACars, modify if needed
    List<ACar> ACars = new ArrayList<>();

    //methods:

    public static void main( String[] args ) {
        // Instance of this class
        CarController cc = new CarController();

    }

    private CarController() {
        initCars();
        // Start a new view and send a reference of self
        frame = new CarView( "CarSim 1.0", this);
        // Start the timer
        timer.start();
    }

    public List<ACar> getCarList() {
        return ACars;
    }

    private void initCars() {
        ACars.add( new Volvo240() );
        ACars.add( new Scania() );
        ACars.add( new Saab95() );
        ACars.add( new Volvo240() );
        for ( int i = 0; i < ACars.size(); i++ ) {
            ACars.get( i ).getPosition()[0] += 100 * i;
        }
    }

    /* Each step the TimerListener moves all the ACars in the list and tells the
    * view to update its images. Change this method to your needs.
    * */
    private class TimerListener implements ActionListener {
        public void actionPerformed( ActionEvent e ) {
            for ( ACar car : ACars ) {
                car.move();
                int x = (int) Math.round( car.getPosition()[0] );
                x = x <= 800 - 100 ? ( x <= 0 ? 0 : x ) : 800 - 100;
                int y = (int) Math.round( car.getPosition()[1] );
                y = y <= 800 - 300 ? ( y <= 0 ? 0 : y ) : 800 - 300;

                //                frame.drawPanel.moveIt( x, y , car);
                // repaint() calls the paintComponent method of the panel
                outOfBounds( car );
            }
            frame.drawPanel.repaint();
        }

        private void outOfBounds( ACar car ) {
            int x = (int) Math.round( car.getPosition()[0] );
            int y = (int) Math.round( car.getPosition()[1] );
            if ( x > CarView.getMaxX() - 100 || y > CarView.getMaxY() - 60 || x < 0 || y < 0 ) {
                car.turnLeft();
                car.turnLeft();
            }
        }
    }

    // Calls the gas method for each car once
    void gas( int amount ) {
        double gas = ( (double) amount ) / 100;
        for ( ACar car : ACars ) {
            try {
                car.gas( gas );
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    void brake( int amount ) {
        double brake = ( (double) amount ) / 100;
        for ( ACar car : ACars ) {
            car.brake( brake );
        }
    }

    void stopEngine() {
        for ( ACar car : ACars ) {
            car.stopEngine();
        }
    }

    void startEngine() {
        for ( ACar car : ACars ) {
            car.startEngine();
        }
    }

    void setTurboOn() {
        for ( ACar car : ACars ) {
            if ( car instanceof ITurbo ) {
                ( (ITurbo) car ).setTurboOn();
            }
        }
    }

    void setTurboOff() {
        for ( ACar car : ACars ) {
            if ( car instanceof ITurbo ) {
                ( (ITurbo) car ).setTurboOff();
            }
        }
    }

    void raiseFlatbed() {
        for ( ACar car : ACars ) {
            if ( car instanceof IHasFlatbed ) {
                ( (IHasFlatbed) car ).raiseFlatbed();
            }
        }
    }

    void lowerFlatbed() {
        for ( ACar car : ACars ) {
            if ( car instanceof IHasFlatbed ) {
                ( (IHasFlatbed) car ).lowerFlatbed();
            }
        }
    }
}