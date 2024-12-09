package core.vehicle;

import org.dyn4j.geometry.Vector2;

import java.util.HashMap;

/**
 * An abstract class for Blackboard Communication.
 */
public abstract class BlackBoard {
  protected HashMap<Integer, Vector2> board;

  /**
   * Adds a message to the BlackBoard
   * @param id The message identifier
   * @param msg The message content
   */
  public void setMessage(int id, Vector2 msg){
    board.put(id,msg);
  }

  /**
   * Retrieves a message by id
   * @param id The message identifier
   * @return The message data associated with the message id
   */
  public Vector2 getMessage(int id) {
    return board.get(id);
  }
}
