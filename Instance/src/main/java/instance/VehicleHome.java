package instance;

import core.framework.SimulationBody;
import core.home.Home;
import org.dyn4j.geometry.Vector2;

import java.util.Arrays;

public class VehicleHome extends Home {
    Vehicles vehicles;
    private int vehicleCount;
    private VehicleHomeSocietalLearner vehicleHomeSocietalLearner = new VehicleHomeSocietalLearner();

    /**
     * Constructor require link back to World domain to spawn new Vehicles
     * @param v Parent
     */
    public VehicleHome(Vehicles v) {
        this.vehicles = v;
        pathStore = new int[50];
        Arrays.fill(pathStore,9);
        storeRelNav = new Vector2();
    }

    /**
     *
     */
    public boolean Step(){
        // TODO: Changed Home's energy decay to 0 for testing. Value must be tuned to world
        // energy *= 0.99;
        // If energy exceeds 100, Spawn a new Vehicle
        if (energy >= 100.0) {
            Spawn();
            energy -= 50.0; // Each Vehicle costs 50 energy.
        }

        vehicleCount = 0;
        for(SimulationBody v:vehicles.myVehicles){
            if (name.equals(((Vehicle)v).getHomeName()) && !v.getUserData().equals("Dead")) {
                vehicleCount++;
            }
        }

        if (vehicleCount == 0) {
            System.out.println("COLONY COLLAPSE!");
            System.out.println("Timestep: " + this.vehicles.timestep);
            return false;
        }

        return true;
    }

    /**
     * Load a new vehicle
     * Instantiate vehicle
     * Add vehicle to VehicleList
     * Add vehicle to world
     */
    public void Spawn() {
        // TODO: Expand Societal Learner...Only added spawn function for now.
        vehicleHomeSocietalLearner.spawn(this, vehicles);
    }
    /**
     * Provides a comma delimited string for logging on this home's
     * name, energy, and vehicle count
     *
     * @return status String
     */
    @Override
    public String statusString() {
        String result = new String(name+","+energy+","+vehicleCount+","+position.x+","+position.y);

        return result;
    }
}
