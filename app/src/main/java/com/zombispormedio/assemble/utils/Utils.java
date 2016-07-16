package com.zombispormedio.assemble.utils;

import android.app.AlertDialog;
import android.content.Context;

import android.content.DialogInterface;
import android.widget.Toast;

import com.zombispormedio.assemble.listeners.IListener;


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

    public static AlertDialog.Builder createConfirmDialog(Context ctx, String msg, String positiveLabel, String negativeLabel, DialogInterface.OnClickListener listener){
        AlertDialog.Builder builder = new  AlertDialog.Builder(ctx);

        builder.setMessage(msg)
                .setPositiveButton(positiveLabel, listener)
                .setNegativeButton(negativeLabel, listener);



        return builder;
    }


    public static DialogInterface.OnClickListener createDialogClickListener(final IListener listener){
        return new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                switch (i){
                    case DialogInterface.BUTTON_POSITIVE:
                        listener.onSuccess();
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        listener.onError();
                        break;
                }
            }
        };
    }


}
