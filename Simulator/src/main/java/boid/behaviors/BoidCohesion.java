package boid.behaviors;

import boid.BoidState;
import core.SensedObject;
import core.State;
import core.behaviorFramework.Action;
import core.behaviorFramework.Behavior;
import org.dyn4j.geometry.Vector2;

import java.util.List;
import java.util.Map;

/**
 * <p>Causes the vehicle to veer away from obstacles.</p>
 */
public class BoidCohesion extends Behavior {

    /**
     * Activation threshold (distance < DISTANCE_LIMIT)
     */
    private final int DISTANCE_LIMIT = 3;
    /**
     * Limit the angles of concern to those in front
     */
    private final int ANGLE_LIMIT = 75;
    // Vote = 1
    // Motor outs are 0.8 and 0.05

    /**
     * <p>Turn toward the center of mass of the boid's neighbors. <br>
     * Action outputs are for steer and thrust.</p>
     *
     * <p>Vote = 1 if vehicle is within the distance threshold.<br>
     * Vote = 0 otherwise</p>
     *
     * @param state current vehicle state
     * @return an action to turn away from obstacles
     */
    public Action genAction(State state) {
        assert (state != null);

        Action action = new Action();
        List<SensedObject> sensedObjects = state.getSensedObjects();

        action.name = new String("BoidCohesion");
        action.setVote(0);

        Vector2 CoM = new Vector2(); // This is (0,0).

        // Iterate through our Neighbors and calculate Center of Mass
        int count = ((BoidState)state).neighborList.size();
        for(Map.Entry<Integer, BoidState.Neighbor> neighbor: ((BoidState)state).neighborList.entrySet() ) {
            Vector2 nVec = Vector2.create(neighbor.getValue().distance, neighbor.getValue().angle);
            CoM.add(nVec);
        }

        double thrust = state.getVelocity().getMagnitude();
        double steer = state.getHeading();
        if(count > 0) { // Only if there are neighbors
            CoM.divide(count);
            thrust = CoM.getMagnitude();
            steer = CoM.getDirection();
//            System.out.println(CoM +", " + thrust + ", " + steer + ", " + state.getHeading());
            action.setVote(1);
        }

        action.setRightWheelVelocity(thrust);
        action.setLeftWheelVelocity(steer);

        return action;
    }
}
