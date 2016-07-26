package com.zombispormedio.assemble.rest;

import com.zombispormedio.assemble.handlers.IPromiseHandler;

import com.zombispormedio.assemble.utils.StringUtils;


import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.util.regex.Pattern;

/**
 * Created by Master on 26/07/2016.
 */
public class Request {

    private METHOD method;
    private String url;
    private HashMap<String, String> headers;
    private String body;

    private IPromiseHandler handler;

    public Request(String url, METHOD method) {
        this.url = url;
        this.method = method;
        headers=null;
        body=null;
    }

    public METHOD getMethod() {
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

    private void setHandler(IPromiseHandler handler) {
        this.handler = handler;
    }

    public String getBody() {
        return body;
    }

    private void setBody(String body) {
        this.body = body;
    }

    public IPromiseHandler getHandler() {
        return handler;
    }

    public static class Builder{
        private String url;
        private METHOD method;
        private HashMap<String, Object> paramsMap;
        private HashMap<String, String> headersMap;
        private IPromiseHandler handler;
        private String body;

        public Builder() {
            paramsMap= new HashMap<String, Object>();
            headersMap= null;
            handler=null;
            body=null;
            method= METHOD.GET;
            url="www.google.com";
        }

        public Builder url(String url){
            this.url=url;
            return this;
        }

        public Builder method(METHOD method){
            this.method=method;
            return this;
        }

        public Builder params(String key, Object param){
            paramsMap.put(key, param);
            return this;
        }

        public void removeParam(String k){
            paramsMap.remove(k);
        }
        public void removeHeader(String k){
            headersMap.remove(k);
        }

        public void clearParams(){
            paramsMap.clear();
        }
        public void clearHeaders(){
            headersMap.clear();
        }

        public String[] paramsKeys(){
            return (String[]) paramsMap.keySet().toArray();
        }

        public String[] headersKeys(){
            return (String[]) headersMap.keySet().toArray();
        }

        public Builder headers(String k, String v){
            if(headersMap==null)headersMap=new HashMap<String, String>();

            headersMap.put(k, v);
            return this;
        }

        public Builder body(String body)  {
            this.body=body;
            return this;
        }

        private void buildURL(){
            String result=url;
            Vector<String> query=new Vector<String>();
            for (Map.Entry<String,Object> entry : paramsMap.entrySet()) {
                Object value = entry.getValue();
                String key = entry.getKey();
                String regexp=":"+key;
                Pattern pattern = Pattern.compile(regexp);


                if(pattern.matcher(result).find()){
                    result=result.replaceAll(regexp, value.toString());
                }else{
                    query.add(key+"="+value);
                }

            }

            if(query.size()>0){
                result+="?"+ StringUtils.join("&", query.toArray());

            }

            url=result.replaceAll("\\/:\\w+", "");


        }

        public Builder handler(IPromiseHandler handler){
            this.handler=handler;
            return this;
        }


        public Request build(){
            if(paramsMap.size()>0)
                buildURL();

            Request req=new Request(url, method);

            if(body!=null)
                req.setBody(body);

            if(headersMap!=null)
                req.setHeaders(headersMap);

            if(handler!=null)
                req.setHandler(handler);

            return req;
        }
    }


}
