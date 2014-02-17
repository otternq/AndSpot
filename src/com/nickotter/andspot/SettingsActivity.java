package com.nickotter.andspot;

import android.app.Activity;
import android.os.Bundle;

public class SettingsActivity extends Activity {
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Analytics.settingsScreen(this);
        
        
        SettingsFragment settingsFragment = new SettingsFragment();
        
        // Add the fragment to the 'fragment_container' FrameLayout
        getFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, settingsFragment).commit();
        
    }

}
