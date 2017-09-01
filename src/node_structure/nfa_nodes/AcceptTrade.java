package node_structure.nfa_nodes;

import node_structure.NFAHandler;
import node_structure.NFANode;
import org.osbot.rs07.api.Trade;
import org.osbot.rs07.api.model.Player;

import java.util.Optional;

public class AcceptTrade extends NFANode {
    private Player target;

    public AcceptTrade(final NFAHandler handler) {
        super(handler);
    }

    @Override
    public void action() {
        final Trade trade = getHandler().getRef().getTrade();
        if (!trade.getTheirOffers().contains("Coins") || !trade.didOtherAcceptTrade()) return;
        trade.acceptTrade();
        sleepUntil(trade::isSecondInterfaceOpen);
    }

    @Override
    public NFANode determine() {
        if (getHandler().getRef().getTrade().isSecondInterfaceOpen()) return getSuccess();
        if (getHandler().getAllottedInTrade() > 15000) return getFailure();
        return null;
    }
}