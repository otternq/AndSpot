package com.nickotter.andspot;

import com.nickotter.andspot.PlayFragment.OnPlaybackListener;
import com.nickotter.andspot.dto.Song;

import android.app.Activity;
import android.app.ListFragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class SearchFragment extends ListFragment {
	
	private static final String TAG = "SearchFragment";
	private String prefSpotURL;
	private OnPlaybackListener mCallback;

	@Override
	  public void onActivityCreated(Bundle savedInstanceState) {
		
		SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getActivity());
		prefSpotURL = sharedPref.getString("prefSpotURL", null);
		
		Log.v(TAG, "onActivityCreated");
		
		super.onActivityCreated(savedInstanceState);
		
		Bundle args = getArguments();
		
		getActivity().findViewById(R.id.fragment_container).setPadding(0, 100, 0, 0);
		
		QueryTask qt = new QueryTask(this);
		qt.execute(
			prefSpotURL, 
			args.getString("query")
		);
	  }

	  @Override
	  public void onListItemClick(ListView l, View v, int position, long id) {
	    Song song = (Song) getListAdapter().getItem(position);
	    Log.v(TAG, song.getName() + " " + song.getUri());
	    
	    Analytics.clickSong(getActivity());
	    
	    PlayURI pu = new PlayURI();
	    pu.execute(
	    		prefSpotURL, 
	    		song.getUri()
	    );
	    
	    mCallback.update();
	    
	    getActivity().finish();
	  }
	  
	  @Override
	    public void onAttach(Activity activity) {
	        super.onAttach(activity);
	        
	        // This makes sure that the container activity has implemented
	        // the callback interface. If not, it throws an exception
	        try {
	            mCallback = (OnPlaybackListener) activity;
	        } catch (ClassCastException e) {
	            throw new ClassCastException(activity.toString()
	                    + " must implement OnHeadlineSelectedListener");
	        }
	    }

}
