package com.zombispormedio.assemble.utils;

/**
 * Created by Xavier Serrano on 31/07/2016.
 */
public class Utils {

    public static boolean presenceOf(Object obj){
        return obj!=null;
    }

    public static boolean presenceOf(String obj){
        boolean it_is=true;

        if(obj==null){
            it_is=false;
        }else{
            if(obj.isEmpty()){
                it_is=false;
            }
        }
        return it_is;
    }

}
