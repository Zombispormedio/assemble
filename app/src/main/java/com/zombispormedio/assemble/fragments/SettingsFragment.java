package com.zombispormedio.assemble.fragments;


import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;

import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.activities.SettingsActivity;
import com.zombispormedio.assemble.controllers.SettingsController;
import com.zombispormedio.assemble.views.ISettingsFragmentView;


public class SettingsFragment extends PreferenceFragment implements ISettingsFragmentView {

    private SettingsController ctrl;


    public SettingsFragment() {

    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.fragment_settings);

        Preference signoutPref = findPreference("settings_signout");

        SettingsActivity activity = (SettingsActivity) getActivity();
        ctrl = activity.getController();

        ctrl.setFragmentView(this);

        signoutPref.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                ctrl.signout();
                return false;
            }
        });

    }

}
