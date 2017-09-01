package node_structure.nfa_nodes;

import node_structure.NFAHandler;
import node_structure.NFANode;
import org.osbot.rs07.api.Trade;

public class AcceptInitialExchange extends NFANode {

    public AcceptInitialExchange(final NFAHandler handler) {
        super(handler, "Accepting trade");
    }

    @Override
    protected void action() {
        final Trade trade = getHandler().getRef().getTrade();
        if (!trade.getTheirOffers().contains("Coins") || !trade.didOtherAcceptTrade()) return;
        trade.acceptTrade();
        sleepUntil(trade::isSecondInterfaceOpen);
    }

    @Override
    protected NFANode determine() {
        if (getHandler().getRef().getTrade().isSecondInterfaceOpen()) return getSuccess();
        if (getHandler().getAllottedInTrade() > 15000) return getFailure();
        return null;
    }

    @Override
    protected void transition() {

    }
}
