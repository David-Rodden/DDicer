package node_structure.nfa_nodes;

import node_structure.NFAHandler;
import node_structure.NFANode;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

public class RollDice extends NFANode {
    private Random random;

    public RollDice(final NFAHandler handler) {
        super(handler, "Rolling 0 - 100");
        try {
            random = SecureRandom.getInstance("SHA1PRNG");
        } catch (NoSuchAlgorithmException e) {
            getHandler().getRef().logger.error("Unable to get SHA1PRNG instance");
        }
    }

    @Override
    protected void action() {
        getHandler().setCurrentRoll(random.nextInt(100));
    }

    @Override
    protected NFANode determine() {
        final int roll = getHandler().getCurrentRoll();
        if (roll == NFAHandler.INIT_ROLL) return null;
        return roll > 70 ? getFailure() : getSuccess();
    }

    @Override
    protected void transition() {

    }
}
