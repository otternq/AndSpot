package com.nickotter.andspot;

import java.lang.Thread.UncaughtExceptionHandler;

import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyApplication extends Application implements UncaughtExceptionHandler {
    private PendingIntent intent;
    
    private UncaughtExceptionHandler defaultUEH;

	@Override
    public void onCreate() {
        super.onCreate();
        
        defaultUEH = Thread.getDefaultUncaughtExceptionHandler();
        
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

	@Override
	public void uncaughtException(Thread thread, Throwable ex) {

		Log.e("MyApplication", "Uncaught exception is: ", ex);
		// log it & phone home.
		
		// re-throw critical exception further to the os (important)
        defaultUEH.uncaughtException(thread, ex);
		
	}
}
