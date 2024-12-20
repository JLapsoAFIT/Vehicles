package core.behaviorFramework;
//-------------------------Project Imports-----------------------------

//-------------------------Maven Imports-------------------------------
/**
 * Action objects are used by behaviors to pass their recommended actions
 * up the behavior framework.  This Action class wrappers an existing one to
 * provide a vote field used by arbiters to select an action for execution
 * or submission to a higher level of the framework.
 */
public class Action extends core.Action {

	public String name;

	//--------------------------------------------------Vote
	private double fVote;
	public double getVote() {
		return fVote;
	}
	public void setVote(double i) {
		fVote = i;
	}

	//--------------------------------------------------Clear/Reset
	public void clear() {
		this.setRightWheelVelocity(0.0);
		this.setLeftWheelVelocity(0.0);
		this.setVote(0.0);
	}

	public Action () {
		fVote = 0.0;
	}

	public Action(Action a) {
		//super(a);
		this.fVote = a.fVote;
		this.name = a.name;
	}

}