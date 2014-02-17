package com.nickotter.andspot;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainActivity extends Activity
{
    protected String prefSpotURL;

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        
        Analytics.playScreen(this);
        
        setContentView(R.layout.main);
        
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
		prefSpotURL = sharedPref.getString("prefSpotURL", null);
		
		setText();
        openPlay();
    }
    
    @Override
    public void onResume() {
    	super.onResume();
    	setText();
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_actions, menu);
        
        return super.onCreateOptionsMenu(menu);
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.settings:
                openSettings();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    
    protected void setText() {
		
    	if (prefSpotURL != null) {
			SongTitle getTitle = new SongTitle(this);
			getTitle.execute(prefSpotURL + "/playing");
    	}
		
	}
    
    protected void openPlay() {
        PlayFragment playFragment = new PlayFragment();
        
        // Add the fragment to the 'fragment_container' FrameLayout
        getFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, playFragment).commit();
    	
    }
    
    protected void openSettings() {
    	
    	Intent intent = new Intent(this, SettingsActivity.class);
    	startActivity(intent);
    	
    }
}
