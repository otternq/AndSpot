package com.nickotter.andspot;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

public class Audio extends AsyncTask<String, String, String> {

	
	private static final String TAG = "Audio";

	protected String doInBackground(String... params) {
		
		Log.v(TAG, params[0] + " " + params[1]);
		
		URL conn = null;
		HttpURLConnection urlConn = null;
		
		try {
			conn = new URL( params[0] );
			
			urlConn = (HttpURLConnection) conn.openConnection();
			urlConn.setRequestMethod( params[1] );
			
			urlConn.getResponseCode();
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
		
		
		
		return "Worked";
	}
}

