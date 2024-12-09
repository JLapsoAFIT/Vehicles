package instance;

import core.vehicle.BlackBoard;
import org.dyn4j.geometry.Vector2;

import java.util.HashMap;

/**
 * A concrete class for Blackboard Communication between vehicles.
 */
public class VehicleBlackBoard extends BlackBoard {

    VehicleBlackBoard() {
        this.board = new HashMap<Integer, Vector2>();
    }
}
