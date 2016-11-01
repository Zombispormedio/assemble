package com.zombispormedio.assemble.utils;

import com.zombispormedio.assemble.handlers.SuccessHandler;
import com.zombispormedio.assemble.handlers.WorkerHandler;

import android.os.AsyncTask;
import android.support.annotation.NonNull;

/**
 * Created by Xavier Serrano on 07/10/2016.
 */

public class ResourceTask extends AsyncTask<WorkerHandler, Void, SuccessHandler> {


    @Override
    protected SuccessHandler doInBackground(WorkerHandler... params) {
        WorkerHandler worker = params[0];
        worker.done();
        return worker.getHandler();
    }

    @Override
    protected void onPostExecute(@NonNull SuccessHandler successHandler) {
        super.onPostExecute(successHandler);
        successHandler.onSuccess();
    }

    public static class Builder {

        private SuccessHandler handler;

        private WorkerHandler worker;

        @NonNull
        public Builder setHandler(SuccessHandler handler) {
            this.handler = handler;
            return this;
        }

        @NonNull
        public Builder setWorker(WorkerHandler worker) {
            this.worker = worker;
            return this;
        }

        public void start() {
            worker.setHandler(handler);
            new ResourceTask().execute(worker);
        }
    }
}
