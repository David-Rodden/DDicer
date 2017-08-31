package node_structure;

public abstract class NFANode {
    private NFANode success, failure;
    private NFAHandler handler;

    public NFANode(final NFAHandler handler) {
        this.handler = handler;
    }

    protected NFAHandler getHandler() {
        return handler;
    }

    public abstract void action();

    public abstract NFANode determine();

    protected void setSuccess(final NFANode success) {
        this.success = success;
    }

    protected void setFailure(final NFANode failure) {
        this.failure = failure;
    }

    public NFANode getSuccess() {
        return success;
    }

    public NFANode getFailure() {
        return failure;
    }

    protected void setTrader(final String name) {
    }
}
