package com.zombispormedio.assemble.services.api;


import android.text.TextUtils;

import com.zombispormedio.assemble.handlers.IServiceHandler;


import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.Vector;
import java.util.regex.Pattern;

/**
 * Created by Master on 25/07/2016.
 */
public class API {

    public static final String BASE_URL = "https://assemble-api.herokuapp.com/";

    public static class REST{

        public static void get(String url, Params params, IServiceHandler<JSONObject> handler){



        }


        public static class Params{
            private HashMap<String, Object> map;
            public Params() {
                map= new HashMap<String, Object>();
            }

            public void add(String k, Object v){
                map.put(k,v);
            }

            public void remove(String k){
                map.remove(k);
            }

            public Set<String> keys(){
                return map.keySet();
            }

            public void clear(){
                map.clear();
            }

            public String buildURL(String url){
                String result=url;
                Vector<String> params=new Vector<String>();

                for (Map.Entry<String,Object> entry : map.entrySet()) {
                    Object value = entry.getValue();
                     String key = entry.getKey();
                    String regexp=":"+key;
                    Pattern pattern = Pattern.compile(regexp);

                    System.out.println(value);
                    System.out.println(key);

                    System.out.println(pattern.matcher(result).find());
                    if(pattern.matcher(result).find()){
                        result.replaceAll(regexp, value.toString());
                    }else{
                        params.add(key+"="+value);
                    }



                }



                if(params.size()>0){
                    result+="?";

                }
                return result;
            }

        }

    }

}
