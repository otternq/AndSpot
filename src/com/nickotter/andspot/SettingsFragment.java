package com.nickotter.andspot;

import android.os.Bundle;
import android.preference.PreferenceFragment;

public class SettingsFragment extends PreferenceFragment {

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       
        getActivity().getActionBar().hide();
        
        addPreferencesFromResource(R.xml.settings);
 
    }

}
