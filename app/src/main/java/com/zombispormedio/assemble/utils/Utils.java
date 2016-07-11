package com.zombispormedio.assemble.utils;

import android.content.Context;

import android.widget.Toast;


/**
 * Created by Master on 26/06/2016.
 */
public final class Utils {


    public static void showAlert(Context ctx, int msg){
        Toast toast=Toast.makeText(ctx, ctx.getResources().getString(msg), Toast.LENGTH_SHORT);
        toast.show();
    }

    public static void showAlert(Context ctx, String msg){
        Toast toast=Toast.makeText(ctx, msg, Toast.LENGTH_SHORT);
        toast.show();
    }


}
