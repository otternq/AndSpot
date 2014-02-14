package com.nickotter.andspot;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import android.app.ActionBar;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.widget.ImageView;

public class DownloadIconTask extends AsyncTask<String, ActionBar, BitmapDrawable> {
	private final ActionBar actionBar;
	 
	  public DownloadIconTask( ActionBar temp ) {
	    actionBar = temp;
	  }
	 
	  @Override
	  protected void onPostExecute( BitmapDrawable bitmap ) {
	    if( actionBar != null ) {
	      actionBar.setIcon(bitmap);
	    }
	  }

	@SuppressWarnings("deprecation")
	protected BitmapDrawable doInBackground(String... params) {
		
		URLConnection conn = null;;
		try {
			conn = new URL( params[0] ).openConnection();
			conn.connect();
			return new BitmapDrawable( conn.getInputStream() );
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}
}

