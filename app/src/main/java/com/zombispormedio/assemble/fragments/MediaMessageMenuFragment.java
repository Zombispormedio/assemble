package com.zombispormedio.assemble.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.annimon.stream.IntStream;
import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.handlers.IOnMenuItemClick;
import com.zombispormedio.assemble.handlers.IOnSubmitHandler;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class MediaMessageMenuFragment extends Fragment {

    private static final int[] buttons = new int[]{
            R.id.back_button,
            R.id.camera_button,
            R.id.video_button,
            R.id.audio_button,
            R.id.file_button,
            R.id.buzz_button,
            R.id.location_button,
            R.id.gallery_button
    };

    private IOnMenuItemClick listener;


    public static MediaMessageMenuFragment newInstance(IOnMenuItemClick menuItemOnClickListener) {

        MediaMessageMenuFragment fragment = new MediaMessageMenuFragment();
        fragment.setMenuItemOnClickListener(menuItemOnClickListener);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_media_message_menu, container, false);

        IntStream.of(buttons)
                .forEach(value -> applyOnClick(rootView, value));

        return rootView;
    }

    private void applyOnClick(View rootView, int id) {
        rootView.findViewById(id)
                .setOnClickListener(v -> listener.onClick(id));
    }


    public void setMenuItemOnClickListener(
            IOnMenuItemClick menuItemOnClickListener) {
        this.listener = menuItemOnClickListener;
    }
}
