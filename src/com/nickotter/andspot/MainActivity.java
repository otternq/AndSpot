package com.nickotter.andspot;

import com.nickotter.andspot.PlayFragment.OnPlaybackListener;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;

public class MainActivity extends Activity implements OnPlaybackListener
{
    private static final String TAG = "MainActivity";
	
    protected String prefSpotURL;
	
	protected PlayFragment playFragment;

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        
        Analytics.playScreen(this);
        
        setContentView(R.layout.main);
        
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
		prefSpotURL = sharedPref.getString("prefSpotURL", null);
		
		handleIntent(getIntent());
    }
    
    @Override
    public void onResume() {
    	super.onResume();
    	update();
    	
    	if (playFragment != null) {
    		this.playFragment.setImage();
    	}
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_actions, menu);
        
        // Associate searchable configuration with the SearchView
        SearchManager searchManager =
               (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));
        
        return super.onCreateOptionsMenu(menu);
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
        	case R.id.search:
        		Analytics.clickSearch(this);
        		return true;
            case R.id.settings:
                openSettings();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    
    @Override
    protected void onNewIntent(Intent intent) {
    	Log.v(TAG, "onNewIntent");
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {

    	Log.v(TAG, "handleIntent");
    	
        if(Intent.ACTION_MAIN.equals(intent.getAction())) {
        	
        	update();
            openPlay();
        	
        } else if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
        
        	Log.v(TAG, "handleIntent - ACTION_SEARCH");
            String query = intent.getStringExtra(SearchManager.QUERY);
            openSearch(query);
        }
    }
    
    public void update() {
    	updateText();
    	updateIcon();
    }
    
    public void updateText() {
		
    	if (prefSpotURL != null) {
			SongTitle getTitle = new SongTitle(this);
			getTitle.execute(prefSpotURL + "/playing");
    	}
		
	}
    
    public void updateIcon() {
    	DownloadIconTask dt = new DownloadIconTask(getActionBar());
		dt.execute(prefSpotURL + "/playing.png");
    }
    
    protected void openPlay() {
        playFragment = new PlayFragment();
        
        // Add the fragment to the 'fragment_container' FrameLayout
        getFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, playFragment).commit();
    	
    }
    
    protected void openSettings() {
    	
    	Intent intent = new Intent(this, SettingsActivity.class);
    	startActivity(intent);
    	
    }
    
    protected void openSearch(String query) {
    	
    	Log.v(TAG, "openSearch - query: " + query);
    	
    	Bundle args = new Bundle();
    	args.putString("query", query);
    	
    	SearchFragment searchFragment = new SearchFragment();
    	searchFragment.setArguments(args);
    	
    	getFragmentManager().beginTransaction()
    	.replace(R.id.fragment_container, searchFragment).commit();
    	
    }
}
