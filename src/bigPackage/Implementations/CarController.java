package bigPackage.Implementations;

import bigPackage.Interfaces.IHasFlatbed;
import bigPackage.Interfaces.ITurbo;
import bigPackage.models.AbstractModels.ACar;
import bigPackage.models.Saab95;
import bigPackage.models.Scania;
import bigPackage.models.Volvo240;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;
import java.util.Timer;

/*
* This class represents the Controller part in the MVC pattern.
* It's responsibilities is to listen to the View and responds in a appropriate manner by
* modifying the model state and the updating the view.
 */

public class CarController {
    // member fields:

    // The interval (ms) corresponds to 100 updates a sec (hz)
    private final int interval = 10;
    // The timer is started with an listener (see below) that executes the statements
    // each step between delays.
    //    private Timer timer = new Timer();

    // The frame that represents this instance View of the MVC pattern
    CarView frame;
    // A list of ACars, modify if needed
    List<ACar> ACars = new ArrayList<>();

    //methods:

    public static void main( String[] args ) {
        // Instance of this class
        List<ACar> carList = new ArrayList<>();
        carList.add( new Volvo240() );
        carList.add( new Scania() );
        carList.add( new Saab95() );
        carList.add( new Volvo240() );

        CarController cc = new CarController( carList );
        cc.frame = new CarView( "CarSim 1.0", cc.getCarList() );
        cc.startListen();
        cc.startTimer();

    }

    private CarController( List<ACar> carList ) {
        initCars( carList );
    }

    private void startTimer() {
        Timer timer = new Timer();
        timer.schedule( new TimerTask() {
            @Override
            public void run() {
                for ( ACar car : ACars ) {
                    car.move();
                    outOfBounds( car );
                }
                frame.drawPanel.repaint();
            }
        }, 0, interval );
    }

    private void startListen() {
        frame.gasButton.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed( ActionEvent e ) {
                gas( frame.gasAmount );
            }
        } );
        frame.brakeButton.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed( ActionEvent e ) {
                brake( frame.gasAmount );
            }
        } );
        frame.stopButton.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed( ActionEvent e ) {
                stopEngine();
            }
        } );
        frame.startButton.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed( ActionEvent e ) {
                startEngine();
            }
        } );
        frame.turboOnButton.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed( ActionEvent e ) {
                setTurboOn();
            }
        } );
        frame.turboOffButton.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed( ActionEvent e ) {
                setTurboOff();
            }
        } );
        frame.liftBedButton.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed( ActionEvent e ) {
                raiseFlatbed();
            }
        } );
        frame.lowerBedButton.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed( ActionEvent e ) {
                lowerFlatbed();
            }
        } );
    }

    public List<ACar> getCarList() {
        return ACars;
    }

    private void initCars( List<ACar> carList ) {
        for ( int i = 0; i < carList.size(); i++ ) {
            ACar car = carList.get( i );
            ACars.add( car );
            car.getPosition()[0] += 100 * i;
        }
    }

    private void outOfBounds( ACar car ) {
        int x = (int) Math.round( car.getPosition()[0] );
        int y = (int) Math.round( car.getPosition()[1] );
        if ( x > CarView.getMaxX() - 100 || y > CarView.getMaxY() - 60 || x < 0 || y < 0 ) {
            car.turnLeft();
            car.turnLeft();
        }
    }

    /* Each step the TimerListener moves all the ACars in the list and tells the
    * view to update its images. Change this method to your needs.
    * */
//        private class TimerListener implements ActionListener {
//            public void actionPerformed( ActionEvent e ) {
//                for ( ACar car : ACars ) {
//                    car.move();
//                    int x = (int) Math.round( car.getPosition()[0] );
//                    x = x <= 800 - 100 ? ( x <= 0 ? 0 : x ) : 800 - 100;
//                    int y = (int) Math.round( car.getPosition()[1] );
//                    y = y <= 800 - 300 ? ( y <= 0 ? 0 : y ) : 800 - 300;
//
//                    //                frame.drawPanel.moveIt( x, y , car);
//                    // repaint() calls the paintComponent method of the panel
//                    outOfBounds( car );
//                }
//                frame.drawPanel.repaint();
//            }
//
//        }

    // Calls the gas method for each car once
    private void gas( int amount ) {
        double gas = ( (double) amount ) / 100;
        for ( ACar car : ACars ) {
            try {
                car.gas( gas );
            } catch ( Exception ex ) {
                ex.printStackTrace();
            }
        }
    }

    private void brake( int amount ) {
        double brake = ( (double) amount ) / 100;
        for ( ACar car : ACars ) {
            car.brake( brake );
        }
    }

    private void stopEngine() {
        for ( ACar car : ACars ) {
            car.stopEngine();
        }
    }

    private void startEngine() {
        for ( ACar car : ACars ) {
            car.startEngine();
        }
    }

    private void setTurboOn() {
        for ( ACar car : ACars ) {
            if ( car instanceof ITurbo ) {
                ( (ITurbo) car ).setTurboOn();
            }
        }
    }

    private void setTurboOff() {
        for ( ACar car : ACars ) {
            if ( car instanceof ITurbo ) {
                ( (ITurbo) car ).setTurboOff();
            }
        }
    }

    private void raiseFlatbed() {
        for ( ACar car : ACars ) {
            if ( car instanceof IHasFlatbed ) {
                ( (IHasFlatbed) car ).raiseFlatbed();
            }
        }
    }

    private void lowerFlatbed() {
        for ( ACar car : ACars ) {
            if ( car instanceof IHasFlatbed ) {
                ( (IHasFlatbed) car ).lowerFlatbed();
            }
        }
    }
}