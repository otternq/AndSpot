package com.nickotter.andspot;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

public class SongTitle extends AsyncTask<String, Activity, String> {

	
	private static final String TAG = "SongTitle";
	
	private Activity activity = null;
	
	public SongTitle(Activity temp) {
		this.activity = temp;
	}
	
	@Override
	protected void onPostExecute(String result) {
		
		Pattern datePatt = Pattern.compile("Now playing (.*) by (.*)");
		Matcher m = datePatt.matcher(result);
		if (m.matches()) {
			
			this.activity.getActionBar().setTitle(m.group(1));
			
			if (m.groupCount() == 2) {
				this.activity.getActionBar().setSubtitle(m.group(2));
			}
			
			
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
			
			urlConn.getResponseCode();
			
			BufferedReader in = new BufferedReader(
			        new InputStreamReader(urlConn.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
	 
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}

			in.close();
	 
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
		
		
		
		return "Worked";
	}
}


