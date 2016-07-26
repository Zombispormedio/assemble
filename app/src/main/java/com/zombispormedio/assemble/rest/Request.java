package com.zombispormedio.assemble.rest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by Master on 26/07/2016.
 */
public class Request {

    private String method;
    private String url;
    private HashMap<String, String> headers;
    private JSONObject body;

    public Request(String url, String method) {
        this.url = url;
        this.method = method;
        headers=null;
        body=null;
    }

    public String getMethod() {
        return method;
    }

    public String getUrl() {
        return url;
    }

    public String getHeaders(String key) {
        return headers.get(key);
    }

    public String[] getHeadersKeys() {
        return (String[])headers.keySet().toArray();
    }

    private void setHeaders(HashMap<String, String> headers) {
        this.headers = headers;
    }


    public JSONObject getBody() {
        return body;
    }

    private void setBody(JSONObject body) {
        this.body = body;
    }

    public static class Builder{
        private String url;
        private String method;
        private HashMap<String, Object> paramsMap;
        private HashMap<String, String> headersMap;
        private JSONObject body;

        public Builder() {
            paramsMap= new HashMap<String, Object>();
            headersMap= null;
            body=null;
        }

        public Builder url(String url){
            this.url=url;
            return this;
        }

        public Builder method(String method){
            this.method=method;
            return this;
        }

        public Builder params(String key, String param){
            paramsMap.put(key, param);
            return this;
        }

        public Builder headers(String k, String v){
            if(headersMap==null)headersMap=new HashMap<String, String>();

            headersMap.put(k, v);
            return this;
        }

        public Builder body(JSONObject body)  {
            this.body=body;

            return this;
        }


        public Request build(){
            Request req=new Request(url, method);

            if(body!=null)
                req.setBody(body);

            if(headersMap!=null)
                req.setHeaders(headersMap);

            return req;
        }
    }


}
