package com.zombispormedio.assemble.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.zombispormedio.assemble.fragments.MeetingsFragment;
import com.zombispormedio.assemble.fragments.ChatsFragment;
import com.zombispormedio.assemble.fragments.TeamsFragment;

/**
 * Created by Xavier Serrano on 12/07/2016.
 */
public class HomeViewPagerAdapter extends FragmentStatePagerAdapter {

    private int numTabs;

    public HomeViewPagerAdapter(FragmentManager fm, int numTabs) {
        super(fm);

        this.numTabs = numTabs;
    }


    @Override
    public Fragment getItem(int position) {

        Fragment item = null;

        switch (position) {
            case 0:
                item = new TeamsFragment();
                break;
            case 1:
                item = new MeetingsFragment();
                break;

            case 2:
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
