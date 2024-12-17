package core.knowledgeFramework.learners;

import core.framework.SimulationFrame;
import core.Home;

/**
 * An abstract class for "Life Long" societal learning.
 */
public abstract class SocietalLearner {

    /**
     * Load a new vehicle
     * Instantiate vehicle
     * Add vehicle to VehicleList
     * Add vehicle to world
     */
    public abstract void spawn(Home home, SimulationFrame frame);
}
