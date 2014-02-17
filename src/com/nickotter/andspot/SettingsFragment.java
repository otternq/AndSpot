package com.nickotter.andspot;

import android.os.Bundle;
import android.preference.PreferenceFragment;

public class SettingsFragment extends PreferenceFragment {

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        Analytics.settingsScreen(getActivity());
        
        addPreferencesFromResource(R.xml.settings);
        
        getActivity().findViewById(R.id.fragment_container).setPadding(0, 100, 0, 0);
 
    }

}
