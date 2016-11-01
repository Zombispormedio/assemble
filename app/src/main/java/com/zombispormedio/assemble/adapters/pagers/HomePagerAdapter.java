package com.zombispormedio.assemble.adapters.pagers;

import com.zombispormedio.assemble.fragments.ChatsFragment;
import com.zombispormedio.assemble.fragments.MeetingsFragment;
import com.zombispormedio.assemble.fragments.TeamsFragment;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Xavier Serrano on 12/07/2016.
 */
public class HomePagerAdapter extends FragmentStatePagerAdapter {

    private final int numTabs;

    public final static int TEAMS = 0;

    public final static int MEETINGS = 1;

    public final static int CHATS = 2;

    public HomePagerAdapter(FragmentManager fm, int numTabs) {
        super(fm);

        this.numTabs = numTabs;
    }


    @Nullable
    @Override
    public Fragment getItem(int position) {

        Fragment item = null;

        switch (position) {
            case TEAMS:
                item = new TeamsFragment();
                break;
            case MEETINGS:
                item = new MeetingsFragment();
                break;

            case CHATS:
                item = new ChatsFragment();
                break;
        }

        return item;
    }

    @Override
    public int getCount() {
        return numTabs;
    }
}
