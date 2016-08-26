package com.zombispormedio.assemble.utils;

import android.app.AlertDialog;
import android.content.Context;

import android.content.DialogInterface;
import android.os.Handler;
import android.os.Looper;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import com.orhanobut.logger.Logger;
import com.zombispormedio.assemble.handlers.ISuccessHandler;
import com.zombispormedio.assemble.models.UserProfile;
import com.zombispormedio.assemble.views.IBaseProfileView;
import com.zombispormedio.assemble.views.IBaseView;
import com.zombispormedio.assemble.views.IProfileView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


/**
 * Created by Xavier Serrano on 26/06/2016.
 */
public final class AndroidUtils {


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


    public static DialogInterface.OnClickListener createDialogClickListener(final ISuccessHandler listener){
        return new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                switch (i){
                    case DialogInterface.BUTTON_POSITIVE:
                        listener.onSuccess();
                        break;


                }
            }
        };
    }

    public static void runOnUiThread(final Runnable runnable) {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            new Handler(Looper.getMainLooper()).post(runnable);
        } else {
            runnable.run();
        }
    }

    public static class InputLayoutHelper{
        private EditText input;
        private TextInputLayout layout;

        public InputLayoutHelper(EditText input, TextInputLayout layout) {
            this.input = input;
            this.layout = layout;
        }

        public EditText getInput() {
            return input;
        }

        public TextInputLayout getLayout() {
            return layout;
        }

        public void hide(){
            input.setVisibility(View.GONE);
            layout.setVisibility(View.GONE);
        }

        public void show(){
            input.setVisibility(View.VISIBLE);
            layout.setVisibility(View.VISIBLE);
        }

        public String getValue(){
            return input.getText()
                    .toString();
        }

        public void setError(String message){
            input.setError(message);
        }
    }

    public static void fillProfile(IBaseProfileView ctx, UserProfile profile){
        if(ctx!=null && profile !=null){

            if(Utils.presenceOf(profile.username)){
                ctx.setUsername(StringUtils.capitalize(profile.username));
            }else{
                ctx.setUsername("");
            }

            if(Utils.presenceOf(profile.location)){
                ctx.setLocation(profile.location);
            }else{
                ctx.setLocation("");
            }

            if(Utils.presenceOf(profile.bio)){
                ctx.setBio(profile.bio);
            }else{
                ctx.setBio("");
            }

            if(Utils.presenceOf(profile.birth_date)){
                try {
                    ctx.setBirthDate(DateUtils.format(DateUtils.SIMPLE_SLASH_FORMAT, profile.birth_date));
                } catch (Exception e) {
                    Logger.d(e.getMessage());
                    ctx.setBirthDate("");
                }

            }else{
                ctx.setBirthDate("");
            }
        }
    }

    public static void setupNoScrollList(Context ctx,RecyclerView list){
        list.setLayoutManager(new LinearLayoutManager(ctx, LinearLayoutManager.VERTICAL, false){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        list.setItemAnimator(new DefaultItemAnimator());

        list.addItemDecoration(new DividerItemDecoration(ctx, DividerItemDecoration.VERTICAL_LIST));
    }


}
