package courses.sample.behaviors;
//-------------------------Project Imports-----------------------------
import courses.sample.MyState;
import core.State;
import core.behaviorFramework.Action;
import core.behaviorFramework.Behavior;

//-------------------------Maven Imports-------------------------------

// to include it as an import and also cast state to it
// to make calls (see below).

/**
 * This is a do nothing sample behavior.  It will always vote to stop the
 * motion of the vehicle.
 * <p>
 * This also shows how to load and store memory into your own state object
 *
 */

public class MyNoOp extends Behavior {

    /**
     * Does nothing but places a value into the Sample vehicles
     * state memory.
     *
     * @param state MyState an extension of State
     * @return an empty action
     */
    public Action genAction(State state) {
        assert (state != null);

        Action action = new Action();
        action.name = "MyNoOp";

        // This is a do nothing behavior...what did you expect!
        action.setLeftWheelVelocity(0.0);
        action.setRightWheelVelocity(0.0);

        // But if you want, you can still get and put things in your State for later
        // Don't forget the import ^^^
        // AND, there is no way around being specific. The class must be present at compile.
        // So, if you use a JSONVehicle, make sure that you include a 'state' attribute
        // for this example is would be
        //      state: "Sample.MyState"
        ((MyState)state).setMyMemory(45);

        // Make sure to vote if you want a chance to be picked.
        action.setVote(1);

        return action;
    }
}