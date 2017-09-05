package node_structure.nfa_nodes;

import node_structure.NFAHandler;
import node_structure.NFANode;
import org.osbot.rs07.api.Trade;

public class DeclineTrade extends NFANode {
    public DeclineTrade(final NFAHandler handler) {
        super(handler, "Other is idling, declining trade");
    }

    @Override
    protected void action() {
        final Trade trade = getHandler().getRef().getTrade();
        trade.declineTrade();
        sleepUntil(trade::isCurrentlyTrading);
    }

    @Override
    protected NFANode determine() {
        return !getHandler().getRef().getTrade().isCurrentlyTrading() ? getSuccess() : null;
    }

    @Override
    protected void transition() {

    }
}
