package com.zombispormedio.assemble.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.handlers.IOnSubmitHandler;
import com.zombispormedio.assemble.handlers.IOnClickHandler;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MessageFormFragment extends Fragment {

    @BindView(R.id.message_input)
    EditText messageInput;

    private IOnSubmitHandler<String> sendButtonListener;

    private IOnClickHandler plusButtonListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_message_form, container, false);

        ButterKnife.bind(this, rootView);

        return rootView;
    }

    @OnClick(R.id.send_button)
    public void onSend(){
        if(sendButtonListener!=null){
            sendButtonListener.onClick(getMessageValue());
        }
    }

    @OnClick(R.id.plus_button)
    public void onPlus(){
        if(plusButtonListener!=null){
            hideKeyboard();
            plusButtonListener.onClick();
        }
    }

   private void hideKeyboard() {
       messageInput.clearFocus();
       InputMethodManager im = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
       im.hideSoftInputFromWindow(getActivity().getWindow().getDecorView().getWindowToken(), 0);
   }

    private String getMessageValue(){
        String value=messageInput.getText().toString();

        messageInput.beginBatchEdit();
        messageInput.setText("");
        messageInput.endBatchEdit();

        return value;
    }


    public void setSendButtonListener(IOnSubmitHandler<String> sendButtonListener) {
        this.sendButtonListener = sendButtonListener;
    }

    public void setPlusButtonListener(IOnClickHandler plusButtonListener) {
        this.plusButtonListener = plusButtonListener;
    }
}
