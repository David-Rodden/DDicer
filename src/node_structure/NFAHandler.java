package node_structure;


import node_structure.nfa_nodes.*;
import org.osbot.rs07.script.Script;

public class NFAHandler {
    public static final int INIT_ROLL = -1;
    private String currentTrader;
    private int currentRoll;
    private final Script ref;
    private NFANode pointer;
    private long tradeTimeStamp;

    public NFAHandler(final Script ref) {
        this.ref = ref;
    }

    public Script getRef() {
        return ref;
    }

    /**
     * Initializes the flow of the NFS nodes
     */
    public void init() {
        NFANode advertise = new Advertise(this), pointer = advertise;
        NFANode declineTrade = new DeclineTrade(this);
        declineTrade.setSuccess(pointer);
        pointer.setSuccess(pointer = new AcceptInitialExchange(this));
        pointer.setFailure(declineTrade);
        pointer.setSuccess(pointer = new AcceptOffer(this));
        pointer.setFailure(declineTrade);
        pointer.setSuccess(pointer = new RollDice(this));
        NFANode announceWin = new AnnounceWin(this);
        announceWin.setSuccess(advertise);
        pointer.setSuccess(announceWin);
        NFANode announceLoss = new AnnounceLoss(this);
        pointer.setFailure(pointer = announceLoss);
        pointer.setFailure(advertise);
        pointer.setSuccess(pointer = new TradeInitialWinnings(this));
        pointer.setFailure(announceLoss);
        pointer.setSuccess(pointer = new TradeWinnings(this));
        pointer.setFailure(announceLoss);
        pointer.setSuccess(pointer = null); //should be set to wait for other to accept when finished
        pointer.setSuccess(pointer = new AnnouncePayout(this));
        pointer.setSuccess(pointer = advertise);
        this.pointer = pointer;
    }

    public void run() {
        final NFANode determined = pointer.determine();
        if (determined != null) {
            pointer.transition();
            pointer = determined;
            return;
        }
        pointer.action();
    }

    public String getCurrentTrader() {
        return currentTrader;
    }

    public void setTradeTimeStamp() {
        tradeTimeStamp = System.currentTimeMillis();
    }

    public long getAllottedInTrade() {
        return System.currentTimeMillis() - tradeTimeStamp;
    }

    public void setCurrentTrader(final String currentTrader) {
        this.currentTrader = currentTrader;
    }

    public void resetCurrentTrader() {
        currentTrader = null;
    }

    public int getCurrentRoll() {
        return currentRoll;
    }

    public void setCurrentRoll(final int currentRoll) {
        this.currentRoll = currentRoll;
    }

}
