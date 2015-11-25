package com.seva60plus.hum.pillreminder;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import com.seva60plus.hum.reminder.taskreminder.ReminderAlert;
import com.seva60plus.hum.reminder.taskreminder.ReminderService;
import com.seva60plus.hum.reminder.taskreminder.WakeReminderIntentService;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Bundle;


public class RepeatingAlarm extends BroadcastReceiver{
	String ns = Context.NOTIFICATION_SERVICE;
	NotificationManager mNotificationManager;
	private static final int HELLO_ID = 1;
	
	//BBDD
		private Long mRowId;
		private PillsDbAdapter mDbHelper;

		String getRowID = "";
	@Override
	public void onReceive(Context context, Intent intent){

		//Long rowId = intent.getExtras().getLong(PillsDbAdapter.KEY_ROWID);

		//BBDD
				mDbHelper = new PillsDbAdapter(context);
				mDbHelper.open();

try {
	System.out.println("==========RECEIVED****");
	
	String weekday_name = new SimpleDateFormat("EEEE", Locale.ENGLISH).format(System.currentTimeMillis());
	
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+5:30"));
		Date currentLocalTime = cal.getTime();
		DateFormat date = new SimpleDateFormat("HH:mm"); 
		// you can get seconds by adding  "...:ss" to it
		//date.setTimeZone(TimeZone.getTimeZone("GMT+1:00")); 
		String localTime = date.format(currentLocalTime); 

		Cursor pillcursor = mDbHelper.fetchAllPillsHours();
		int totalRow = pillcursor.getCount();
		
		 // looping through all rows and adding to list
        if (pillcursor.moveToFirst()) {
            do {
            	
            	String idRow = pillcursor.getString(0);
            	String idRowHour = pillcursor.getString(1);
            	String idRowDay = pillcursor.getString(2);
            	System.out.println("@"+weekday_name+"#####"+idRow+":"+idRowHour+":"+idRowDay);
               
            	if(idRowDay.contains(weekday_name)){
            		
            		if(idRowHour.contains(localTime)){
            			
            			 getRowID = idRow;
            			System.out.println("!!!!!----GET ID---"+getRowID);
            		}
            		else{}
            	}
            	else{}
            	
            	
            } while (pillcursor.moveToNext());
        }
		
		//Long rowid = pillcursor.getLong(pillcursor.getColumnIndexOrThrow(PillsDbAdapter.RKEY_ROWID));
		
		System.out.println("*********RECEIVED****"+localTime+" :"+totalRow+":"+getRowID);
		

} catch (Exception e) {
	// TODO: handle exception
}

		/*d
		mNotificationManager= (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);



		int icon = R.drawable.ic_alert;
		CharSequence tickerText = TakeThePill.getAppName();
		Bundle extras = intent.getExtras();
		//String user=extras.getString("user");
		String user="";
		String pill=extras.getString("pill");
		Calendar calendar= Calendar.getInstance();
		String hour=calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE);
		System.out.println(hour);
		d*/

		/*Notification notification = new Notification(icon, tickerText, 0);


		CharSequence contentTitle =  "Pill Reminder";
		CharSequence contentText = pill + " - " + hour;
		Intent notificationIntent = new Intent(context, TakeThePill.class);
		notificationIntent.putExtra("user", user);
		notificationIntent.putExtra("pill", pill);
		notificationIntent.putExtra("hour", hour);
		PendingIntent contentIntent = PendingIntent.getActivity(context, 0, notificationIntent, 0);

		notification.setLatestEventInfo(context, contentTitle, contentText, contentIntent);

		notification.sound = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://com.seva60plus.seva60plus/raw/pill_sound");
		notification.defaults |= Notification.DEFAULT_LIGHTS ;
		notification.flags |=Notification.FLAG_AUTO_CANCEL;
		notification.flags |=Notification.FLAG_INSISTENT;

		long[] vibrate = {0,500,500,500};
		notification.vibrate = vibrate;


		if(TakeThePill.getAlarmsEnabled()) mNotificationManager.notify(HELLO_ID, notification);
		 */


		PillWakeReminderIntentService.acquireStaticLock(context);

		Intent i = new Intent(context, PillReminderService.class); 
		i.putExtra("rowID",getRowID);
		context.startService(i);

	}
}

