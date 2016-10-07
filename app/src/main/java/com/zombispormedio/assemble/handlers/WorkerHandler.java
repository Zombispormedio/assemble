package com.zombispormedio.assemble.handlers;

/**
 * Created by Xavier Serrano on 07/10/2016.
 */

public class WorkerHandler implements IWorkerHandler {

    private SuccessHandler handler;



    @Override
    public void done() {

    }

    public SuccessHandler getHandler() {
        return handler;
    }

    public void setHandler(SuccessHandler handler) {
        this.handler = handler;
    }
}
