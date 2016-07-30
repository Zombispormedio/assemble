package com.zombispormedio.assemble.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.zombispormedio.assemble.fragments.GatheringsTabFragment;
import com.zombispormedio.assemble.fragments.MessagesTabFragment;
import com.zombispormedio.assemble.fragments.TeamsTabFragment;

/**
 * Created by Xavier Serrano on 12/07/2016.
 */
public class HomeTabPagerAdapter extends FragmentStatePagerAdapter{

    private int numTabs;

    public HomeTabPagerAdapter(FragmentManager fm, int numTabs) {
        super(fm);

        this.numTabs=numTabs;
    }


    @Override
    public Fragment getItem(int position) {

        Fragment item=null;

        switch (position){
            case 0:
                item = new TeamsTabFragment();
                break;
            case 1:
                item= new GatheringsTabFragment();
                break;

            case 2:
                item= new MessagesTabFragment();
                break;
        }


        return item;
    }

    @Override
    public int getCount() {
        return numTabs;
    }
}
