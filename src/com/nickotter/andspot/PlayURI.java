package com.nickotter.andspot;

import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.os.AsyncTask;
import android.util.Log;

public class PlayURI extends AsyncTask<String, String, String> {

	
	private static final String TAG = "PlayURI";

	protected String doInBackground(String... params) {
		
		Log.v(TAG, params[0] + " " + params[1]);
		
		URL conn = null;
		HttpURLConnection urlConn = null;
		
		try {
			conn = new URL( params[0] + "/play-uri" );
			
			urlConn = (HttpURLConnection) conn.openConnection();
			
			urlConn.setReadTimeout(10000);
			urlConn.setConnectTimeout(15000);
			
			urlConn.setRequestMethod( "POST" );
			urlConn.setDoOutput(true);

			String urlParameters = "uri="+ params[1];
			DataOutputStream wr = new DataOutputStream(urlConn.getOutputStream());
			wr.writeBytes(urlParameters);
			wr.flush();
			wr.close();
	 
			int responseCode = urlConn.getResponseCode();
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

