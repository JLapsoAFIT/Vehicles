package courses.sample.behaviors;
//-------------------------Project Imports-----------------------------
import core.State;
import core.behaviorFramework.Action;
import core.behaviors.Wander;

//-------------------------Maven Imports-------------------------------
import java.util.Random;

public class MyWander extends Wander {
    private final double ST_DEV = 0.2; // Size of the normal distribution
    private int limiter = 5;
    private final int LIMITER_MAX = 5;

    // Vote = 1

    /**
     * <p>Returns an action that is randomly sampled around the current
     * velocity.</p>
     *
     * <p>Vote = 1</p>
     *
     * @param state State uses stored past velocities
     * @return a random action
     */
    @Override
    public Action genAction(State state) {
        assert (state != null);

        Action action = new Action();
        Random rand = new Random();

        action.name = new String("Wander");
        action.setLeftWheelVelocity(state.getLeftWheelVelocity());
        action.setRightWheelVelocity(state.getRightWheelVelocity());
        limiter++;

        // Adjust the current wheel velocities via a normal distribution around
        // their current velocity: rand.nextGaussian()*st_dev+mean;
        if (limiter > LIMITER_MAX) {
            double left = rand.nextGaussian() * ST_DEV + state.getLeftWheelVelocity();
            double right = rand.nextGaussian() * ST_DEV + state.getRightWheelVelocity();
            if (left <= 0.0)  // Behavior: we want the vehicle always moving forward
                left = 0.1;
            if (right <= 0.0)
                right = 0.1;
            action.setLeftWheelVelocity(left);
            action.setRightWheelVelocity(right);
            limiter = 0;
        }
        action.setVote( 1 ) ;

        return action;
    }
}
