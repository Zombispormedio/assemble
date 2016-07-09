package com.zombispormedio.assemble.utils;

import android.content.Context;

import android.widget.Toast;

import com.zombispormedio.assemble.BasicInput;


/**
 * Created by Master on 26/06/2016.
 */
public final class Tools {
    private static final String TAG = "Tools";

    public static void showAlert(Context ctx, int msg){
        Toast toast=Toast.makeText(ctx, ctx.getResources().getString(msg), Toast.LENGTH_SHORT);
        toast.show();
    }

    public static void showAlert(Context ctx, String msg){
        Toast toast=Toast.makeText(ctx, msg, Toast.LENGTH_SHORT);
        toast.show();
    }


    public static boolean isEmptyForm(BasicInput... params){
        Boolean found =false;

        for(BasicInput input :params){

            if(input.isEmpty()){
                found=true;

                showAlert(input.getContext(), input.getEmptyMessage());
                break;
            }
        }
        return found;
    }



    public static Boolean InputEquals(BasicInput input1, BasicInput input2, int msg){
        Boolean eq=input1.equals(input2);

        if(!eq){
            showAlert(input1.getContext(), msg);
        }

        return eq;
    }




}
