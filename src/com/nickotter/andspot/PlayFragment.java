package com.nickotter.andspot;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Fragment;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class PlayFragment extends Fragment {

	TextView spotURL;
	ImageView albumArt;
	
	String prefSpotURL;
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
		
		getActivity().getActionBar().show();
		
		// Inflate the layout for this fragment
        View mRoot = inflater.inflate(R.layout.activity_play_fragment, container, false);
		
		spotURL = (TextView) mRoot.findViewById(R.id.spotURL);
		albumArt = (ImageView) mRoot.findViewById(R.id.playingImg);
		
		SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getActivity());
		prefSpotURL = sharedPref.getString("prefSpotURL", null);
		
		if (spotURL != null && prefSpotURL != null) {
			
			spotURL.setText(prefSpotURL);
			
			setImage();
			
			
		}
		
        
        return mRoot;
    }
	
	protected void setImage() {
		
		DownloadImagesTask dit = new DownloadImagesTask(albumArt);
		dit.execute(prefSpotURL + "/playing.png");
		
	}

}
