package vehicles;
//-------------------------Project Imports-----------------------------
import core.Home;
import core.framework.SimulationBody;
import core.framework.SimulationFrame;
import core.knowledgeFramework.learners.SocietalLearner;
//-------------------------Maven Imports-------------------------------


public class VehicleHomeSocietalLearner extends SocietalLearner {
    /**
     * Load a new vehicle
     * Instantiate vehicle
     * Add vehicle to VehicleList
     * Add vehicle to world
     */
    @Override
    public void spawn(Home home, SimulationFrame vehicles) {
        String fileName = "data//Agent4.json";
        JSONVehicle vehicle =null;
        boolean reused = false;

        for (SimulationBody v: ((Vehicles) vehicles).myVehicles) {
            if (v.getUserData().equals("Dead")) {
                vehicle = ((JSONVehicle)v); // WARNING: If Spawning, all vehicles assumed to be JSON.
                reused = true;
                vehicle.setEnergy(100.0);
                vehicle.translateToOrigin();
                break;
            }
        }
        if (vehicle == null && ((Vehicles) vehicles).myVehicles.size() <= ((Vehicles) vehicles).MAX_VEHICLE_COUNT)
            vehicle = new JSONVehicle();
        if (vehicle == null) // No dead Vehicles and reached max number don't spawn
            return;

        vehicle.initialize((Vehicles) vehicles, fileName, "Ant"); // Halts on failure, needs world for State initialization

        // Not adding scan lines for new vehicles.
        vehicle.setDrawScanLines(false);

        //TODO: Will need an identifier from KDWorld
        String newName = vehicle.getUserData() + Integer.toString(((Vehicles) vehicles).timestep);
        vehicle.setUserData(newName);

        // Setting new position near Home location...
        double distance = 2.6;
        double rotation = 0.0;
        rotation = Math.random()*(2*Math.PI)-Math.PI;

        vehicle.translate(distance, 0.0);
        vehicle.rotate(rotation, home.position.x, home.position.y);
        vehicle.setEnabled(true); // reactivate being part of collisions

        // Set this vehicles Home.
        vehicle.setHome(home);

        // Need to add the new Vehicle to the myVehicle list.
        if (!reused) {
            ((Vehicles) vehicles).myVehicles.add(vehicle);
            ((Vehicles) vehicles).getWorld().addBody(vehicle);
        }
    }
}
