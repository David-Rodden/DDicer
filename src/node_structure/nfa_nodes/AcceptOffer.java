package node_structure.nfa_nodes;

import node_structure.NFAHandler;
import node_structure.NFANode;
import org.osbot.rs07.api.Trade;
import org.osbot.rs07.api.ui.Message;
import org.osbot.rs07.listener.MessageListener;

public class AcceptOffer extends NFANode implements MessageListener {
    private boolean tradeAccepted;

    public AcceptOffer(final NFAHandler handler, final String description) {
        super(handler, "Accepting money offered");
        tradeAccepted = false;
    }

    @Override
    protected void action() {
        final Trade trade = getHandler().getRef().getTrade();
        if (!trade.didOtherAcceptTrade()) return;
        trade.acceptTrade();
        sleepUntil(() -> !trade.isCurrentlyTrading());
    }

    @Override
    public NFANode determine() {
        if (!getHandler().getRef().getTrade().isCurrentlyTrading())
            return tradeAccepted ? getSuccess() : getFailure();
        return null;
    }

    @Override
    public void transition() {
        tradeAccepted = false;
    }

    @Override
    public void onMessage(final Message message) throws InterruptedException {
        if (message.getType().equals(Message.MessageType.GAME) && message.getMessage().equals("Accepted trade."))
            tradeAccepted = true;
    }
}
