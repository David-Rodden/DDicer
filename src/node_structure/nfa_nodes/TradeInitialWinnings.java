package node_structure.nfa_nodes;

import node_structure.NFAHandler;
import node_structure.NFANode;
import org.osbot.rs07.api.model.Player;

public class TradeInitialWinnings extends NFANode {
    public TradeInitialWinnings(final NFAHandler handler) {
        super(handler, "Trading winnings to " + handler.getCurrentTrader());
    }

    @Override
    protected void action() {
        final Player player = getHandler().getRef().getPlayers().closest(getHandler().getCurrentTrader());
        if (!player.exists()) return;
        player.interact("Trade with");
        sleepUntil(() -> getHandler().getRef().getTrade().isFirstInterfaceOpen());
    }

    @Override
    protected NFANode determine() {
        if (getHandler().getRef().getTrade().isFirstInterfaceOpen()) return getSuccess();
        return getHandler().getAllottedInTrade() > 15000 ? getFailure() : null;
    }

    @Override
    protected void transition() {
        getHandler().setTradeTimeStamp();
    }
}
