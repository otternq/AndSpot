package com.nickotter.andspot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.nickotter.andspot.adapter.SongAdapter;
import com.nickotter.andspot.dto.Song;

import android.app.ListFragment;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;

public class QueryTask extends AsyncTask<String, Void, ArrayList<Song>> {
	
	private static final String TAG = "QueryTask";
	
	private WeakReference<ListFragment> listReference;
	
	public QueryTask(ListFragment list) {
		listReference = new WeakReference<ListFragment>(list);
	}
	 
	@Override
	protected void onPostExecute( ArrayList<Song> result ) {
		
		if (result != null) {
		
			ListFragment list = listReference.get();
			
			SongAdapter adapter = new SongAdapter(
					list.getActivity(),
					result
			);
			
			list.setListAdapter(adapter);
		}
		
	}

	protected ArrayList<Song> doInBackground(String... params) {
		
		Log.v(TAG, params[0] + "/query?q=" + params[1]);
		
		URL conn = null;
		HttpURLConnection urlConn = null;
		
		ArrayList<Song> songs = new ArrayList<Song>();
		
		try {

			conn = new URL( params[0] + "/query?q=" + params[1] );
			
			urlConn = (HttpURLConnection) conn.openConnection();
			urlConn.setRequestMethod( "GET" );
			urlConn.connect();
			
			String JSONString = convertStreamToString(urlConn.getInputStream());
			
			Log.v(TAG, JSONString);
			
			JSONArray jObject = new JSONArray(JSONString);
			
			for (int i = 0; i < jObject.length(); i++) {
				
				JSONObject jo = jObject.getJSONObject(i);

				Song s = new Song();
				s.setName(jo.getString("name"));
				s.setUri(jo.getString("uri"));
				
				songs.add(s);
				
				Log.v(TAG, s.toString());
				
			}
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (urlConn != null) {
				urlConn.disconnect();
			}
		}
		
		return songs;
	}
	
	private String convertStreamToString(InputStream inputStream) throws IOException {
        if(inputStream != null) {
            Writer writer = new StringWriter();

            char[] buffer = new char[1024];

            try {

                Reader reader = new BufferedReader( new InputStreamReader(inputStream, "UTF-8"));

                int n;
                while((n = reader.read(buffer)) != -1) {
                    writer.write(buffer, 0, n);

                }
            } finally {
                inputStream.close();
            }
            return writer.toString();
        } else {
            return "";
        }

    }
	
}

