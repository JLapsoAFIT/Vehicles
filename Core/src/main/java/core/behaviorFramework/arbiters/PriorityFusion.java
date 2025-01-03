package core.behaviorFramework.arbiters;
//-------------------------Project Imports-----------------------------
import core.behaviorFramework.Action;
import core.behaviorFramework.ArbitrationUnit;

//-------------------------Maven Imports-------------------------------
import java.util.ArrayList;
import java.util.Collection;

/**
 * This arbiter builds an action that selects each sub-action based on the
 * highest priority (weight) (if voted)
 *
 * If a high priority behavior submits an action, the specified actions
 * are used and any unspecified sub-actions are taken from the behavior
 * with the next-highest priority.
 *
 * The new action is given the highest overall vote value.
 *
 * Action components: LeftWheelVelocity, RightWheelVelocity, Grab
 *
 */
public class PriorityFusion extends ArbitrationUnit {

	public PriorityFusion (ArrayList<Double> weightList) {
		super();
		this.setWeights(weightList);
	}

	// Overloaded constructor that allows for default weight values
	public PriorityFusion() {
		this(null);
	}

	public Action evaluate(Collection<Action> actionSet) {
		Action action = new Action();
		int actionIndex = 0;

		double maxPriority	       = 0.0;
		double priorityLeftWheel   = 0.0;
		double priorityRightWheel  = 0.0;
		double priorityPickup      = 0.0;
		double priorityDrop        = 0.0;

		for(Action a : actionSet) {
			if (a.getVote() > 0.0) {
				double priority = w.get(actionIndex);
				
				// Set the overall vote equal to the maxPriority
				if (priority > maxPriority) action.setVote(a.getVote());
				
				// Set as the highest priority for velocity so far
				if (a.getLeftWheelVelocity() != 0.0 && priority > priorityLeftWheel)
				{
					action.setLeftWheelVelocity(a.getLeftWheelVelocity());
					priorityLeftWheel = priority;
				}
				
				// Set as the highest priority for turnRate so far
				if (a.getRightWheelVelocity() != 0.0 && priority > priorityRightWheel)
				{
					action.setRightWheelVelocity(a.getRightWheelVelocity());
					priorityRightWheel = priority;
				}
				
				// Set as the highest priority for gunRotation so far
				if (a.getPickup() != null && priority > priorityPickup)
				{
					action.setPickup(a.getPickup());
					priorityPickup = priority;
				}

				if (a.getDrop() && priority > priorityDrop)
				{
					action.setDrop(true);
					priorityDrop = priority;
				}

				actionIndex++;
			}
		}

		// Return the highest priority action with a non-zero vote
		//
		// return a noOp if no action in the set votes
		return action;
	}
}
