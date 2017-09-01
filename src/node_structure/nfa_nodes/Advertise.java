package node_structure.nfa_nodes;

import node_structure.NFAHandler;
import node_structure.NFANode;
import org.osbot.rs07.api.model.Player;
import org.osbot.rs07.api.ui.Message;
import org.osbot.rs07.listener.MessageListener;

public class Advertise extends NFANode implements MessageListener {
    private boolean receivedTrade;

    public Advertise(final NFAHandler handler) {
        super(handler);
        this.receivedTrade = false;
    }

    @Override
    public void action() {
        if (!receivedTrade) {
            getHandler().getRef().getKeyboard().typeString("Testing", true);
            return;
        }
        final Player current = getHandler().getRef().getPlayers().closest(getHandler().getCurrentTrader());
        if (current == null) {
            receivedTrade = false;
            return;
        }
        current.interact("Trade with");
        sleepUntil(() -> getHandler().getRef().getTrade().isFirstInterfaceOpen());
    }

    @Override
    public NFANode determine() {
        if (!getHandler().getRef().getTrade().isFirstInterfaceOpen()) return null;
        receivedTrade = false;
        getHandler().setTradeTimeStamp();
        return getSuccess();
    }


    @Override
    public void onMessage(final Message message) throws InterruptedException {
        if (!message.getType().equals(Message.MessageType.RECEIVE_TRADE)) return;
        getHandler().setCurrentTrader(message.getUsername());
        receivedTrade = true;
    }
}