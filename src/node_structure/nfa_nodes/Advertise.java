package node_structure.nfa_nodes;

import node_structure.NFAHandler;
import node_structure.NFANode;
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
        getHandler().getRef().getKeyboard().typeString("Testing", true);
    }

    @Override
    public NFANode determine() {
        return receivedTrade ? getSuccess() : null;
    }


    @Override
    public void onMessage(final Message message) throws InterruptedException {
        if (!message.getType().equals(Message.MessageType.RECEIVE_TRADE)) return;
        getHandler().setCurrentTrader(message.getUsername());
        receivedTrade = true;
    }
}
