package com.zombispormedio.assemble.adapters.pagers;

import com.zombispormedio.assemble.fragments.FriendRequestsListFragment;
import com.zombispormedio.assemble.fragments.FriendsListFragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Xavier Serrano on 11/09/2016.
 */
public class FriendsPagerAdapter extends FragmentStatePagerAdapter {

    private int numPages;

    public FriendsPagerAdapter(FragmentManager fm, int numPages) {
        super(fm);
        this.numPages = numPages;
    }

    @Override
    public Fragment getItem(int position) {

        Fragment item = null;

        switch (position) {
            case 0:
                item = new FriendsListFragment();
                break;
            case 1:
                item = new FriendRequestsListFragment();
                break;
        }

        return item;
    }

    @Override
    public int getCount() {
        return numPages;
    }
}
