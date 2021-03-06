package com.zombispormedio.assemble.fragments;


import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.activities.SettingsActivity;
import com.zombispormedio.assemble.controllers.SettingsController;
import com.zombispormedio.assemble.views.fragments.ISettingsFragmentView;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;


public class SettingsFragment extends PreferenceFragment implements ISettingsFragmentView {

    private SettingsController ctrl;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.fragment_settings);

        Preference signOutPref = findPreference("settings_signout");

        SettingsActivity activity = (SettingsActivity) getActivity();
        ctrl = activity.getController();

        ctrl.setFragmentView(this);

        signOutPref.setOnPreferenceClickListener(preference -> {
            ctrl.signout();
            return false;
        });

    }

}
