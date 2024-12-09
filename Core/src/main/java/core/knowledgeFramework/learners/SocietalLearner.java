package core.knowledgeFramework.learners;

import core.framework.SimulationBody;
import core.framework.SimulationFrame;
import core.home.Home;

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
