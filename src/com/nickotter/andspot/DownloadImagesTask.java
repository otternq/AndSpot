package com.nickotter.andspot;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

public class DownloadImagesTask extends AsyncTask<String, Integer, Bitmap> {
	private final WeakReference<ImageView> viewReference;
	 
	  public DownloadImagesTask( ImageView view ) {
	    viewReference = new WeakReference<ImageView>( view );
	  }
	 
	  @Override
	  protected void onPostExecute( Bitmap bitmap ) {
	    ImageView imageView = viewReference.get();
	    if( imageView != null ) {
	      imageView.setImageBitmap( bitmap );
	    }
	  }

	protected Bitmap doInBackground(String... params) {
		
		URLConnection conn = null;;
		try {
			conn = new URL( params[0] ).openConnection();
			conn.connect();
			return BitmapFactory.decodeStream( conn.getInputStream() );
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
