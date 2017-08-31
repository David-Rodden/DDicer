package node_structure;


import org.osbot.rs07.script.Script;

public class NFAHandler {
    private String currentTrader;
    private final Script ref;

    public NFAHandler(final Script ref) {
        this.ref = ref;
    }

    public Script getRef() {
        return ref;
    }

    public String getCurrentTrader() {
        return currentTrader;
    }

    public void setCurrentTrader(final String currentTrader) {
        this.currentTrader = currentTrader;
    }
}
