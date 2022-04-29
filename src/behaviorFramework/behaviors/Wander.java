package behaviorFramework.behaviors;

import behaviorFramework.Action;
import behaviorFramework.Behavior;

import Braitenburg.State;

import java.util.Random;


/**
 * 
 * @author Brian Woolley - for use by AFIT/ENG - CSCE623 and CSCE723
 */
 
public class Wander extends Behavior {
	public Action genAction(State state) {
        assert (state != null);

        Action action = new Action();
        Random rand = new Random();

        action.setLeftWheelVelocity(-8.0);
        action.setRightWheelVelocity(0.0);
        action.setVote( 10 ) ;

		return action;
	}
}