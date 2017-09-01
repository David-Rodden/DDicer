package node_structure;

import node_structure.nfa_nodes.SleepAction;

import java.util.function.BooleanSupplier;

public abstract class NFANode {
    private NFANode success, failure;
    private NFAHandler handler;
    private String description;

    protected NFANode(final NFAHandler handler, final String description) {
        this.handler = handler;
        this.description = description;
    }

    protected NFAHandler getHandler() {
        return handler;
    }

    protected abstract void action();

    protected abstract NFANode determine();

    protected abstract void transition();

    protected void setSuccess(final NFANode success) {
        this.success = success;
    }

    protected void setFailure(final NFANode failure) {
        this.failure = failure;
    }

    protected NFANode getSuccess() {
        return success;
    }

    protected NFANode getFailure() {
        return failure;
    }

    protected void sleepUntil(final BooleanSupplier condition) {
        new SleepAction(condition, 3500).sleep();
    }
}
