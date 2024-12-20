package core;

//-------------------------Project Imports-----------------------------
import core.framework.SimulationBody;

//-------------------------Maven Imports-----------------------------
import org.dyn4j.dynamics.joint.WeldJoint;
import org.dyn4j.geometry.*;

import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * An abstract class for Vehicle Simulation Bodies.
 */

public abstract class VehicleBody extends SimulationBody {

    //--------------Vehicle Constants--------------------------
    protected final double MAX_VELOCITY = 1.2; // arbitrary right now
    protected final double MAX_ANGULAR_VELOCITY = 2; // how fast we can turn
    protected final int SENSOR_RANGE = 20; // how far the line casts go
    protected final double ANGULAR_DAMPENING = 2;

    //--------------Vehicle Components--------------------------
    protected Vector2 leftWheelLocation;
    protected Vector2 rightWheelLocation;
    protected Vector2 leftSensorLocation;
    protected Vector2 rightSensorLocation;
    protected WeldJoint<SimulationBody> gripper;
    protected Logger vehicleLogStream;


    //--------------Vehicle PID Constants-----------------------
    protected double kProportional;  //Proportional
    protected double kIntegral;  //Integral
    protected double kDerivative;  //Derivative

    //---------------Vehicle Characteristics--------------------
    protected boolean steerDrive; // false = differential drive, true = steer+thrust drive
    protected State state;
    protected String name;
    protected String treeDesc;
    protected String lastAction;
    protected double energy;
    protected double energyUsage;

    //----------------Vehicle Communication---------------------
    protected BlackBoard blackBoard;

    //----------------Vehicle Sensor----------------------------
    // array of values to "sweep" across -- hand jammed to get a reasonable sweep that doesn't eat too much processing time
    // -10 to 120 degrees in steps of 5 degrees (added +/- 7.5, +/- 2.5
    protected double[] sweepValues;
    protected boolean drawScanLines;  // true: Visualize RayCasting

    //-------------------------Abstract Methods------------------
    /**
     * Must be overloaded. Current action is no op.<br>
     * Called before render to get action for the current time step.<br>
     *
     * @return return a noop action
     */
    public abstract Action decideAction(); //Graphics2D g)

    /**
     * Provides vehicles status update in a string for logging
     * name, energy, ___
     * @return status String
     */
    public abstract String statusString();

    public abstract boolean sense();

    public abstract void act(Action a);

    //-------------------------Public Methods----------------------
    /**
     * Converts the baseVehicle's Transform matrix (cos and sin) into a
     * vehicle heading. <br>
     * Matches the return from baseVehicle.getLinearVelocity.getDirection()<br>
     * 0 = x, counter-clockwise +0 to PI, clockwise -0 to PI
     *
     * @return vehicle heading
     */
    public double convertTransformToHeading() {
        double sin = this.getTransform().getSint();
        int ssign = (0>sin)?-1:1;
        double cos = this.getTransform().getCost();
        int csign = (0>cos)?-1:1;

        double asin = Math.asin(sin);
        double acos = Math.acos(cos);
        double dir = Math.PI/2.0;

        if (csign == 1 && ssign == -1) { // Quadrant I
            dir = (Math.PI/2.0) - acos;
        } else if (csign == 1 && ssign == 1) { // Quadrant II
            dir = acos + (Math.PI/2.0);
        } else if (csign == -1 && ssign == -1) { //Quadrant IV
            dir = (Math.PI/2.0) - acos;
        } else if (csign == -1 && ssign == 1) { // Quadrant IV
            dir = -(Math.PI/2.0)-asin;
        }

        return dir;
    }

    //-----------------------Vehicle Setters--------------------------------
    /**
     * Turn on sensor scan line drawing for visual debugging purposes.
     * @param val
     */
    public void setDrawScanLines(boolean val) {drawScanLines = val;}

    public void setEnergy(double energy) { this.energy = energy; }

    public void setName(String vehicleName) {
        name = vehicleName;
        setUserData(name);
    }

    public void setGripper(WeldJoint<SimulationBody> gripper) {
        this.gripper = gripper;
    }

    //-----------------------Vehicle Getters--------------------------------

    public double getEnergy() { return this.energy; }

    public String getName() { return this.name; }

    public State getState() { return this.state;}

    public WeldJoint<SimulationBody> getGripper() { return this.gripper;}

    public void logMessage(Level level, String message) {
        vehicleLogStream.log(level, message);
    }

    public void setLevel(Level level) {
        vehicleLogStream.setLevel(level);
    }

    public void setHandler(FileHandler handler) {
        vehicleLogStream.addHandler(handler);
    }
}
