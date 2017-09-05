package node_structure.nfa_nodes;

import node_structure.NFAHandler;
import node_structure.NFANode;

public class AnnounceWin extends NFANode {
    private boolean announced;

    public AnnounceWin(final NFAHandler handler) {
        super(handler, "Announcing that we've won");
    }

    @Override
    protected void action() {
        getHandler().getRef().getKeyboard().typeString("Sorry, " + getHandler().getCurrentTrader() + ". I've won.");
        announced = true;
    }

    @Override
    protected NFANode determine() {
        return announced ? getSuccess() : null;
    }

    @Override
    protected void transition() {
        announced = false;
    }
}
