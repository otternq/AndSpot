package com.nickotter.andspot;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.app.Fragment;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class PlayFragment extends Fragment implements OnClickListener, Runnable {

	private static final String TAG = "PlayFragment";

	ImageView albumArt;
	
	ProgressBar progressBar;
	
	ImageButton backButton;
	ImageButton pauseButton;
	ImageButton playButton;
	ImageButton nextButton;
	
	String prefSpotURL;
	private OnPlaybackListener mCallback;
	private Thread progressTask;
	
	public interface OnPlaybackListener {
		public void updateText();
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
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
		
		getActivity().findViewById(R.id.fragment_container).setPadding(0, 0, 0, 0);
		
		// Inflate the layout for this fragment		
		View mRoot = inflater.inflate(R.layout.activity_play_fragment, container, false);
	        
		albumArt = (ImageView) mRoot.findViewById(R.id.playingImg);
		
		progressBar = (ProgressBar) mRoot.findViewById(R.id.progress);
		progressBar.setProgress(0);

		backButton = (ImageButton) mRoot.findViewById(R.id.back);
		pauseButton = (ImageButton) mRoot.findViewById(R.id.pause);
		playButton = (ImageButton) mRoot.findViewById(R.id.play);
		nextButton = (ImageButton) mRoot.findViewById(R.id.next);
		
		backButton.setOnClickListener(this);
		pauseButton.setOnClickListener(this);
		playButton.setOnClickListener(this);
		nextButton.setOnClickListener(this);
		
		pauseButton.setVisibility(View.GONE);
		
		SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getActivity());
		prefSpotURL = sharedPref.getString("prefSpotURL", null);
		
		update();
        
        return mRoot;
    }
	
	protected void update() {
		if (prefSpotURL != null) {
			setImage();
			setProgress();
		}
	}
	
	protected void setProgress() {
		
		progressTask = new Thread(this);
		
		ProgressBarTask dit = new ProgressBarTask(progressBar, progressTask);
		dit.execute(prefSpotURL + "/seconds-left");
	}
	
	@Override
    public void run() {
        int currentPosition= 0;
        while (currentPosition < progressBar.getMax()) {
            try {
                Thread.sleep(1000);
                currentPosition++;
            } catch (InterruptedException e) {
                return;
            } catch (Exception e) {
                return;
            }            
            progressBar.setProgress(currentPosition);
            
        }
        
        update();
        mCallback.updateText();
    }
	
	protected void setImage() {
		
		DownloadImagesTask dit = new DownloadImagesTask(albumArt);
		dit.execute(prefSpotURL + "/playing.png");
		
		DownloadIconTask dt = new DownloadIconTask(getActivity().getActionBar());
		dt.execute(prefSpotURL + "/playing.png");
		
	}

	@Override
	public void onClick(View v) {
		
		Audio audioEndpoint = null;
		
		switch(v.getId()) {
			case R.id.back:
				audioEndpoint = new Audio();
				audioEndpoint.execute(prefSpotURL +"/back", "PUT");
				
				Analytics.clickBack(getActivity());
				
				update();
				mCallback.updateText();
			break;
			case R.id.next:
				audioEndpoint = new Audio();
				audioEndpoint.execute(prefSpotURL +"/next", "PUT");
				
				Analytics.clickNext(getActivity());
				
				update();
				mCallback.updateText();
			break;
			case R.id.pause:
				audioEndpoint = new Audio();
				audioEndpoint.execute(prefSpotURL +"/pause", "PUT");
				
				Analytics.clickPause(getActivity());
				
				pauseButton.setVisibility(View.GONE);
				playButton.setVisibility(View.VISIBLE);
			break;
			case R.id.play:
				audioEndpoint = new Audio();
				audioEndpoint.execute(prefSpotURL +"/play", "PUT");
				
				Analytics.clickPlay(getActivity());
				
				pauseButton.setVisibility(View.VISIBLE);
				playButton.setVisibility(View.GONE);
			break;
			default:
				Log.v(TAG, "No case for given id");
			break;
			
		}
		
	}

}
