package com.nickotter.andspot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class ProgressBarTask extends AsyncTask<String, ProgressBar, String> {
	
	private static final String TAG = "ProgressBarTask";
	
	private Thread progressBarThread;
	private ProgressBar progressBar;
	
	public ProgressBarTask( ProgressBar progressBar, Thread progressBarThread ) {
	    this.progressBar = progressBar;
	    this.progressBarThread = progressBarThread;
	}
	 
	@Override
	protected void onPostExecute( String result ) {
	    if( progressBar != null ) {
	      progressBar.setMax((int) Math.floor(Float.parseFloat(result)));
	      progressBarThread.start();
	      
	    }
	}

	protected String doInBackground(String... params) {
		
		Log.v(TAG, params[0]);
		
		URL conn = null;
		HttpURLConnection urlConn = null;
		
		try {
			conn = new URL( params[0] );
			
			urlConn = (HttpURLConnection) conn.openConnection();
			urlConn.setRequestMethod( "GET" );
			
			InputStream is = urlConn.getInputStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(is));
			String line;
			StringBuffer response = new StringBuffer(); 
			while((line = rd.readLine()) != null) {
				response.append(line);
			}
			rd.close();
			return response.toString();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (urlConn != null) {
				urlConn.disconnect();
			}
		}
		
		
		
		return "5";
	}
}

