package node_structure;


import org.osbot.rs07.script.Script;

import java.util.Timer;

public class NFAHandler {
    private String currentTrader;
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
}
