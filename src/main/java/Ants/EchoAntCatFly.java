package Ants;

/**
 * This interaction model is based on Holland's original Echo interactions.  However, it uses
 * the Hamming Distance calculation proposed in Smith and Bedau's paper on Echo.
 */
public class EchoAntCatFly extends InteractionModel{

    /**
     * Returns the probability, 1 - HammingDistance/Tag length, that an agent will win a fight.
     * @param offense
     * @param defense
     * @return
     */
    public double likelyWinner(String offense, String defense) {
        double ret = 0;
        // First, we need to find the smallest string, as they may not be the same size
        String s1 = offense;
        String s2 = defense;
        int minLen = offense.length();
        if(defense.length() < minLen) {
            s1 = defense;
            s2 = offense;
            minLen = defense.length();
        }
        int countDiff = 0;
        for(int i = 0; i < minLen; i++) {
            if(s1.charAt(i) != s2.charAt(i)) {
                countDiff++;
            }
        }
        ret = 1 - countDiff/minLen; // todo:  introduce a bonus for longer tags.
        return ret;
    }

    /**
     * Checks to see if the condition sent in matches the interaction tag sent in.
     * This drives the interaction between agents in the Echo World.
     *
     * @param agent1Condition
     * @param agent2InteractionTag
     * @return
     */
    public boolean canInteract(String agent1Condition, String agent2InteractionTag) {
        /**
        if(agent1Condition.length() > agent2InteractionTag.length()) {
            return false; // the condition is too long to match the interaction tag
        }
        else {
            for (int i = 0; i < agent1Condition.length(); i++) {
                if (agent1Condition.charAt(i) != agent2InteractionTag.charAt(i)) {
                    return false; // does not meet the interactionTag 'prefix'
                }
            }
        }
         */

        // I've noticed a huge reduction in interactions with the strict matching.  I am going ro
        // reduce this to just the first letter of the interaction tag, meaning, if the first letter
        // of the interaction tag is matched by the combat, trading, or mating tag, there can be
        // an interaction
        if (agent1Condition.charAt(0) != agent2InteractionTag.charAt(0)) {
            return false; // does not meet the interactionTag 'prefix'
        }
        return true; // if we get here, huzzah, they can interact
    }
}
