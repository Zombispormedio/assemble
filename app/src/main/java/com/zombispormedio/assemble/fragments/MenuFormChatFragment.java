package com.zombispormedio.assemble.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.handlers.IOnClickHandler;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class MenuFormChatFragment extends Fragment {


    private IOnClickHandler backButtonListener;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_menu_form_chat, container, false);

        ButterKnife.bind(this, rootView);

        return rootView;
    }


    @OnClick(R.id.go_back_button)
    public void onBackButton(){
        if(backButtonListener!=null){
            backButtonListener.onClick();
        }
    }
    public void setBackButtonListener(IOnClickHandler backButtonListener) {
        this.backButtonListener = backButtonListener;
    }
}
