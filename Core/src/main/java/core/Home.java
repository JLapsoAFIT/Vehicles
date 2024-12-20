package core;
//-------------------------Project Imports-----------------------------
import core.framework.SimulationBody;


//-------------------------Maven Imports-------------------------------
import org.dyn4j.geometry.Vector2;

import java.awt.*;
import java.util.Arrays;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class Home extends SimulationBody {
    public String name;
    public Vector2 position;
    public double energy;
    public SimulationBody body; // Just in case we want to modify the body in someway
    public Color color;
    public int[] pathStore;
    public Vector2 storeRelNav;
    protected Logger homeLogStream;



    /**
     * Provides a comma delimited string for logging on this home's
     * name, energy, and vehicle count
     *
     * @return status String
     */
    public String statusString() {
//        String result = new String(name+","+energy+","+vehicleCount+","+position.x+","+position.y);
        String result = new String(name+","+energy+","+position.x+","+position.y);

        return result;
    }

    public int[] receivePath(int [] incomingPath) {
        int [] sendPath;
        sendPath = Arrays.copyOf(pathStore,50);
        pathStore = Arrays.copyOf(incomingPath,50);

        return sendPath;
    }

    public Vector2 receiveRelativePoint( Vector2 rel) {
        Vector2 sendRelNav = new Vector2(storeRelNav);
        storeRelNav.set(rel);

        return sendRelNav;
    }

    public double getEnergy() {
        return this.energy;
    }

    public void setEnergy(double energy) {
        this.energy = energy;
    }

    @Override
    public Color getColor() {
        return this.color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public SimulationBody getBody() {
        return this.body;
    }

    public void setBody(SimulationBody body) {
        this.body = body;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Vector2 getPosition() {
        return this.position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public void logMessage(Level level, String message) {
        homeLogStream.log(level, message);
    }

    public void setLevel(Level level) {
        homeLogStream.setLevel(level);
    }

    public void setHandler(FileHandler handler) {
        homeLogStream.addHandler(handler);
    }
}
