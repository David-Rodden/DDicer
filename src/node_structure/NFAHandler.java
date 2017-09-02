package node_structure;


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

    public int getCurrentRoll() {
        return currentRoll;
    }

    public void setCurrentRoll(final int currentRoll) {
        this.currentRoll = currentRoll;
    }
}
