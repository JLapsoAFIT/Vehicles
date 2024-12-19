package research.ants;
//-------------------------Project Imports-----------------------------
import core.State;

//-------------------------Maven Imports-------------------------------
import java.util.ArrayList;

public class EchoAgentState extends State {
    ArrayList<Resource> resourceList = new ArrayList();
    ArrayList<Ant> antList = new ArrayList();

    public void updateState(ArrayList<Resource> currResList, ArrayList<Ant> currAntList) {
        resourceList = currResList;
        antList = currAntList;
    }
}
