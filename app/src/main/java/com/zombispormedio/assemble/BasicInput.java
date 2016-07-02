package com.zombispormedio.assemble;

import android.content.Context;
import android.util.Log;
import android.widget.EditText;

/**
 * Created by Master on 26/06/2016.
 */
public class BasicInput {
    private static final String TAG = "BasicInput";
    private Context ctx;
    private EditText input;
    private String empty_message;

    public BasicInput(Context ctx, EditText input, int empty_message){
        this.ctx=ctx;
        this.input=input;
        this.empty_message=ctx.getResources().getString(empty_message);


    }

    public boolean isEmpty(){

        return getValue().isEmpty();
    }

    public String getValue(){
        return input.getText().toString();
    }

    public String getEmptyMessage(){
        return empty_message;
    }

    public Context getContext(){
        return ctx;
    }

    public Boolean equals(BasicInput rp){
        return rp.getValue().equals(getValue());
    }



}
