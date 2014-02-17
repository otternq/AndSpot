package com.nickotter.andspot;

import android.content.Context;

import com.google.analytics.tracking.android.EasyTracker;
import com.google.analytics.tracking.android.Fields;
import com.google.analytics.tracking.android.MapBuilder;
import com.google.analytics.tracking.android.Tracker;

public class Analytics {
	
	private static void screen(Context context, String name) {
		// May return null if EasyTracker has not yet been initialized with a property
		// ID.
		Tracker easyTracker = EasyTracker.getInstance(context);
	
		// This screen name value will remain set on the tracker and sent with
		// hits until it is set to a new value or to null.
		easyTracker.set(Fields.SCREEN_NAME, name);
	
		easyTracker.send(MapBuilder
		    .createAppView()
		    .build()
		);
	}
	
	public static void event(Context context, String category, String action, String label) {
		// May return null if a EasyTracker has not yet been initialized with a
		// property ID.
		EasyTracker easyTracker = EasyTracker.getInstance(context);

		// MapBuilder.createEvent().build() returns a Map of event fields and values
		// that are set and sent with the hit.
		easyTracker.send(MapBuilder
		    .createEvent(category,     // Event category (required)
		                 action,  // Event action (required)
		                 label,   // Event label
		                 null)            // Event value
		    .build()
		);
	}
	
	public static void playScreen(Context context) {
		Analytics.screen(context, "Play");
	}
	
	public static void settingsScreen(Context context) {
		Analytics.screen(context, "Settings");
	}
	
	public static void clickPlay(Context context) {
		Analytics.event(context, "Controlls", "ButtonClick", "Play");
	}
	
	public static void clickPause(Context context) {
		Analytics.event(context, "Controlls", "ButtonClick", "Pause");
	}
	
	public static void clickBack(Context context) {
		Analytics.event(context, "Controlls", "ButtonClick", "Back");
	}
	
	public static void clickNext(Context context) {
		Analytics.event(context, "Controlls", "ButtonClick", "Next");
	}
	
	
	
	
	

}
