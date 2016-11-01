package com.zombispormedio.assemble.net;

import com.zombispormedio.assemble.handlers.IPromiseHandler;
import com.zombispormedio.assemble.utils.StringUtils;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.util.regex.Pattern;

/**
 * Created by Xavier Serrano on 26/07/2016.
 */
public class Request {

    private final METHOD method;

    private final String url;

    @Nullable
    private HashMap<String, String> headers;

    @Nullable
    private String body;

    @Nullable
    private FileBody file;

    private IPromiseHandler handler;

    public Request(String url, METHOD method) {
        this.url = url;
        this.method = method;
        headers = null;
        body = null;
        file = null;
    }

    public METHOD getMethod() {
        return method;
    }

    public String getUrl() {
        return url;
    }

    public String getHeader(String key) {
        return headers.get(key);
    }

    @Nullable
    public HashMap<String, String> getHeaders() {
        return headers;
    }

    private void setHeaders(HashMap<String, String> headers) {
        this.headers = headers;
    }

    private void setHandler(IPromiseHandler handler) {
        this.handler = handler;
    }

    @Nullable
    public String getBody() {
        return body;
    }

    private void setBody(String body) {
        this.body = body;
    }

    @Nullable
    public FileBody getFile() {
        return file;
    }

    public void setFile(FileBody file) {
        this.file = file;
    }

    public IPromiseHandler getHandler() {
        return handler;
    }

    public void start() {
        if (ConnectionState.getInstance().isConnected()) {
            new AsyncRequest().execute(this);
        } else {
            handler.onNotConnected();
        }

    }


    public static class Builder {

        private String url;

        private METHOD method;

        @NonNull
        private final HashMap<String, Object> paramsMap;

        @Nullable
        private HashMap<String, String> headersMap;

        @Nullable
        private IPromiseHandler handler;

        @Nullable
        private String body;

        private FileBody file;

        public Builder() {
            paramsMap = new HashMap<>();
            headersMap = null;
            handler = null;
            body = null;
            method = METHOD.GET;
            url = "www.google.com";
        }

        @NonNull
        public Builder url(String url) {
            this.url = url;
            return this;
        }

        @NonNull
        public Builder method(METHOD method) {
            this.method = method;
            return this;
        }

        public void get() {
            this.method = METHOD.GET;
            build()
                    .start();
        }

        public void post() {
            this.method = METHOD.POST;
            build()
                    .start();
        }

        public void post(String body) {
            this.method = METHOD.POST;
            this.body = body;
            build()
                    .start();
        }

        public void post(FileBody file) {
            this.method = METHOD.POST;
            this.file = file;
            build()
                    .start();
        }

        public void patch() {
            this.method = METHOD.PATCH;
            build()
                    .start();
        }

        public void patch(String body) {
            this.method = METHOD.PATCH;
            this.body = body;
            build()
                    .start();
        }

        public void patch(FileBody file) {
            this.method = METHOD.PATCH;
            this.file = file;
            build()
                    .start();
        }

        public void put() {
            this.method = METHOD.PUT;
            build()
                    .start();

        }

        public void put(String body) {
            this.method = METHOD.PUT;
            this.body = body;
            build()
                    .start();
        }

        public void put(FileBody file) {
            this.method = METHOD.PUT;
            this.file = file;
            build()
                    .start();
        }

        public void delete() {
            this.method = METHOD.DELETE;
            build()
                    .start();
        }

        @NonNull
        public Builder params(String key, Object param) {
            paramsMap.put(key, param);
            return this;
        }

        public void removeParam(String k) {
            paramsMap.remove(k);
        }

        public void removeHeader(String k) {
            headersMap.remove(k);
        }

        public void clearParams() {
            paramsMap.clear();
        }

        public void clearHeaders() {
            headersMap.clear();
        }

        @NonNull
        public String[] paramsKeys() {
            return (String[]) paramsMap.keySet().toArray();
        }

        @NonNull
        public String[] headersKeys() {
            return (String[]) headersMap.keySet().toArray();
        }

        @NonNull
        public Builder headers(String k, String v) {
            if (headersMap == null) {
                headersMap = new HashMap<>();
            }

            headersMap.put(k, v);
            return this;
        }

        @NonNull
        public Builder body(String body) {
            this.body = body;
            return this;
        }

        @NonNull
        public Builder file(FileBody file) {
            this.file = file;
            return this;
        }

        private void buildUrl() {
            String result = url;
            Vector<String> query = new Vector<>();
            for (Map.Entry<String, Object> entry : paramsMap.entrySet()) {
                Object value = entry.getValue();
                String key = entry.getKey();
                String regexp = ":" + key;
                Pattern pattern = Pattern.compile(regexp);

                if (pattern.matcher(result).find()) {
                    result = result.replaceAll(regexp, value.toString());
                } else {
                    query.add(key + "=" + value);
                }

            }

            if (query.size() > 0) {
                result += "?" + StringUtils.join("&", query.toArray());

            }

            url = result.replaceAll("\\/:\\w+", "");


        }

        @NonNull
        public Builder handler(IPromiseHandler handler) {
            this.handler = handler;
            return this;
        }


        @NonNull
        public Request build() {
            if (paramsMap.size() > 0) {
                buildUrl();
            }

            Request req = new Request(url, method);

            if (body != null) {
                req.setBody(body);
            }

            if (headersMap != null) {
                req.setHeaders(headersMap);
            }

            if (handler != null) {
                req.setHandler(handler);
            }

            if (file != null) {
                req.setFile(file);
            }

            return req;
        }
    }


}
