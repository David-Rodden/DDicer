package node_structure.nfa_nodes;

import node_structure.NFAHandler;
import node_structure.NFANode;
import org.osbot.rs07.api.Trade;

public class TradeWinnings extends NFANode {
    protected TradeWinnings(final NFAHandler handler) {
        super(handler, "Trading winnings to " + handler.getCurrentTrader());
    }

    @Override
    protected void action() {
        final Trade trade = getHandler().getRef().getTrade();
        if (trade.getOurOffers().getItem("Coins") == null) {
            trade.offer("Coins", 1000);
            sleepUntil(() -> trade.getOurOffers().getItem("Coins") != null);
            return;
        }
        if (trade.didOtherAcceptTrade()) trade.acceptTrade();
        sleepUntil(trade::isSecondInterfaceOpen);
    }

    @Override
    protected NFANode determine() {
        final Trade trade = getHandler().getRef().getTrade();
        if (trade.isSecondInterfaceOpen()) return getSuccess();
        return !trade.isCurrentlyTrading() || getHandler().getAllottedInTrade() > 15000 ? getFailure() : null;
    }

    @Override
    protected void transition() {
        getHandler().setTradeTimeStamp();
    }
}
