package com.nickotter.andspot;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Fragment;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class PlayFragment extends Fragment {

	TextView spotURL;
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
		
		// Inflate the layout for this fragment
        View mRoot = inflater.inflate(R.layout.activity_play_fragment, container, false);
		
		spotURL = (TextView) mRoot.findViewById(R.id.spotURL);
		
		if (spotURL != null) {
			
			SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getActivity());
			String prefSpotURL = sharedPref.getString("prefSpotURL", "Please set your Spot URL in Settings");
			
			spotURL.setText(prefSpotURL);
			
		}
		
        
        return mRoot;
    }

}
