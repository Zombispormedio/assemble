package com.zombispormedio.assemble.utils;



/**
 * Created by Master on 25/07/2016.
 */
public class StringUtils {

    public static String join(String delimiter, String[] in){
        String out="";

        if(in.length>0){
            StringBuilder buffer= new StringBuilder();
            buffer.append(in[0]);

            for(int i=1; i< in.length; i++){
                buffer.append(delimiter);
                buffer.append(in[i]);
            }

            out=buffer.toString();
        }

        return out;
    }
}
