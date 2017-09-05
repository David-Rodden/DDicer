package node_structure.nfa_nodes;

import node_structure.NFAHandler;
import node_structure.NFANode;

public class AnnouncePayout extends NFANode {
    private boolean announced;

    public AnnouncePayout(final NFAHandler handler) {
        super(handler, "Announcing that we've paid");
    }

    @Override
    protected void action() {
        getHandler().getRef().getKeyboard().typeString("Paid " + getHandler().getCurrentTrader());
        announced = true;
    }

    @Override
    protected NFANode determine() {
        return announced ? getSuccess() : null;
    }

    @Override
    protected void transition() {
        announced = false;
        getHandler().resetCurrentTrader();
    }
}
