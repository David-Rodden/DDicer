package node_structure.nfa_nodes;

import node_structure.NFAHandler;
import node_structure.NFANode;

public class AnnounceLoss extends NFANode {
    private boolean announced;

    public AnnounceLoss(final NFAHandler handler) {
        super(handler, "Announcing that we've lost");
    }

    @Override
    protected void action() {
        getHandler().getRef().getKeyboard().typeString("Congratulations - I've rolled " + getHandler().getCurrentRoll());
        announced = true;
    }

    @Override
    protected NFANode determine() {
        if (!getHandler().getRef().getPlayers().closest(getHandler().getCurrentTrader()).exists()) return getFailure();
        return announced ? getSuccess() : null;
    }

    @Override
    protected void transition() {
        announced = false;
    }
}
