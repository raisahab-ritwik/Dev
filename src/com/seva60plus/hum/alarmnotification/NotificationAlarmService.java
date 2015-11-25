package com.seva60plus.hum.alarmnotification;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.json.JSONObject;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.os.Handler;
import android.os.IBinder;
import android.os.StrictMode;
import android.util.Log;
import android.widget.Toast;

import com.seva60plus.hum.network.PostDataStatices;
import com.seva60plus.hum.util.Contact;
import com.seva60plus.hum.util.DatabaseHandler;
import com.seva60plus.hum.util.GPSTracker;
import com.seva60plus.hum.util.UserFunctions;
import com.seva60plus.hum.util.Util;

public class NotificationAlarmService extends Service {
	private String TAG = "NotificationAlarmService";
	//MediaPlayer player;
	private Handler handler;
	private Thread myThread = null;
	private int i = 0;
	private DatabaseHandler db;

	private JSONObject json;
	private String code = "";

	private Boolean isInternetPresent = false;

	private String geoLocation = "";
	private String sharePhoneNo = "";

	private final String MY_PREFS_NAME = "MyPrefsFile";

	GPSTracker gps;

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {

		Log.d(TAG, "onCreate");

		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);

		db = new DatabaseHandler(this);

		SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
		sharePhoneNo = prefs.getString("UserPhoneNumber", "");

		handler = new Handler();

		Runnable myRunnableThread = new CountDownRunner();
		myThread = new Thread(myRunnableThread);

		//player = MediaPlayer.create(this, R.raw.heartbeat);
		//player.setLooping(true); // Set looping
	}

	@Override
	public void onDestroy() {
		//Toast.makeText(this, "My Service Stopped", Toast.LENGTH_LONG).show();
		Log.d(TAG, "onDestroy");
		//player.stop();
	}

	@Override
	public void onStart(Intent intent, int startid) {
		//Toast.makeText(this, "My Service Started", Toast.LENGTH_LONG).show();
		Log.d(TAG, "onStart");
		//player.start();
		myThread.start();

	}

	private void fireAlarm() {
		
		Log.i(TAG, "1 Alarm fired.");
		Intent intent = new Intent(this, PostDataStatices.class);
		intent.setAction("com.seva60Plus.alarm.ACTION");
		PendingIntent pendingIntent = PendingIntent.getBroadcast(NotificationAlarmService.this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
		Calendar calendar = Calendar.getInstance();
		//time 10:10:10
		calendar.set(Calendar.HOUR_OF_DAY, 10);
		calendar.set(Calendar.MINUTE, 10);
		calendar.set(Calendar.SECOND, 10);
		AlarmManager alarm = (AlarmManager) NotificationAlarmService.this.getSystemService(Context.ALARM_SERVICE);
		alarm.cancel(pendingIntent);
		alarm.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
	}

	private void fireAlarm2() {
		Log.i(TAG, "2 Alarm fired.");
		/**
		 * call broadcost reciver for send sms
		 */
		Intent intent = new Intent(this, PostDataStatices.class);
		intent.setAction("com.seva60Plus.alarm.ACTION");
		PendingIntent pendingIntent = PendingIntent.getBroadcast(NotificationAlarmService.this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
		Calendar calendar = Calendar.getInstance();
		//time 11:10:10
		calendar.set(Calendar.HOUR_OF_DAY, 11);
		calendar.set(Calendar.MINUTE, 10);
		calendar.set(Calendar.SECOND, 10);
		AlarmManager alarm = (AlarmManager) NotificationAlarmService.this.getSystemService(Context.ALARM_SERVICE);
		alarm.cancel(pendingIntent);
		alarm.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
	}

	private void fireAlarm3() {
		Log.i(TAG, "3 Alarm fired.");
		/**
		 * call broadcost reciver for send sms
		 */
		Intent intent = new Intent(this, PostDataStatices.class);
		intent.setAction("com.seva60Plus.alarm.ACTION");
		PendingIntent pendingIntent = PendingIntent.getBroadcast(NotificationAlarmService.this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
		Calendar calendar = Calendar.getInstance();
		//time 17:10:10
		calendar.set(Calendar.HOUR_OF_DAY, 17);
		calendar.set(Calendar.MINUTE, 10);
		calendar.set(Calendar.SECOND, 10);
		AlarmManager alarm = (AlarmManager) NotificationAlarmService.this.getSystemService(Context.ALARM_SERVICE);
		alarm.cancel(pendingIntent);
		alarm.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
	}

	private Location getLocation() {
		Location mLocation = null;
		gps = new GPSTracker(NotificationAlarmService.this);

		// check if GPS enabled    
		if (gps.canGetLocation()) {

			mLocation = gps.getLocation();
		} else {
			// can't get location
			// GPS or Network is not enabled
			// Ask user to enable GPS/network in settings
			gps.showSettingsAlert();
		}
		return mLocation;
	}

	private void getStatusData() {
		
		Log.i(TAG, "Sync Data ...");
		isInternetPresent = Util.isInternetAvailable(getApplicationContext());

		// check for Internet status
		if (isInternetPresent) {
			//InternetOK UserPhoneNumber

			try {

				List<Contact> contacts = db.getAllContactsStatus("2");

				System.out.println("LENGTH " + contacts.size());

				for (Contact cn : contacts) {
					String log = cn.getDate() + "--" + cn.getTime() + "--" + cn.getResult() + "--" + cn.getStaus() + "--" + cn.getMode() + "\n";
					// 		Writing Contacts to log
					// 		detailsText.append(log);
					Log.d("DATA: ", log + ":");

					try {

						UserFunctions userFunction = new UserFunctions();
						json = userFunction.sendStatisticsData(cn.getMode(), cn.getResult(), sharePhoneNo, cn.getDate(), cn.getTime());

//						code = json.getString("code");
						code = json.optString("code");// -- raisahab.ritwik

					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
						System.out.println(e.toString());
					}

					if (code.equals("100")) {
						System.out.println("POST Inserted......");
					} else {
						System.out.println("FAILD !!! POST Inserted......");
					}

				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				System.out.println(" failed!");
			}
		} else {
			//Internet NOt

		}
	}

	private class CountDownRunner implements Runnable {
		// @Override
		public void run() {
			while (!Thread.currentThread().isInterrupted()) {
				try {
					doWork();
					Thread.sleep(1000); // Pause of 1 Second
				} catch (InterruptedException e) {
					e.printStackTrace();
					Thread.currentThread().interrupt();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		@SuppressWarnings("deprecation")
		private void doWork() {
			runOnUiThread(new Runnable() {
				public void run() {
					try {
						//TextView txtCurrentTime= (TextView)findViewById(R.id.text_view);
						Date dt = new Date();

						int hours = dt.getHours();
						int minutes = dt.getMinutes();
						int seconds = dt.getSeconds();

						//						final Calendar c = Calendar.getInstance();
						//						int year = c.get(Calendar.YEAR);
						//						int month = c.get(Calendar.MONTH);
						//						int day = c.get(Calendar.DAY_OF_MONTH);

						//						int mlen = String.valueOf(month).length();
						//						int dlen = String.valueOf(day).length();

						String curTime = hours + ":" + minutes + ":" + seconds;
						//Log.i(TAG, "Current Time: " + curTime);

						for (i = 0; i < 24; i++) {

							String mHour = Integer.toString(i);
							String myTime = mHour + ":1:0";

							//System.out.println("Create step1 MyTime : "+myTime);

							if (myTime.contains(curTime)) {

								SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);

								geoLocation = prefs.getString("geoLocation", "");

								if (!geoLocation.equals("1")) {
									System.out.println("Location Not submitted due User Permission");
								} else {

									Location mLocation = getLocation();
									String MyLat = Double.toString(mLocation.getLatitude());
									String MyLang = Double.toString(mLocation.getLongitude());
									Log.i(TAG, "MYLAT1 : " + mLocation.getLatitude() + "\nMYLONG1 : " + mLocation.getLongitude());

									try {

										UserFunctions userFunction = new UserFunctions();
										json = userFunction.sendLatlang(sharePhoneNo, MyLat, MyLang, curTime);

										code = json.getString("code");

									} catch (Exception e) {
										// TODO: handle exception
										e.printStackTrace();
										System.out.println(e.toString());
									}
									if (i == 23) {
										i = 0;
										System.out.println("i value: " + i);
									} else {
										System.out.println("i value from else: " + i);
									}
								}
							}

						}

						if (curTime.contains("10:10:10")) {
							fireAlarm();
						} else if (curTime.contains("11:10:10")) {
							fireAlarm2();
						} else if (curTime.contains("17:10:10")) {
							fireAlarm3();
						} else if (curTime.contains("20:10:10")) {
							Toast.makeText(getApplicationContext(), "Syncing...", Toast.LENGTH_LONG).show();
							getStatusData();
						} else if (curTime.contains("2:10:10")) {
							Toast.makeText(getApplicationContext(), "Syncing...", Toast.LENGTH_LONG).show();
							getStatusData();
						} else if (curTime.contains("8:10:10")) {
							Toast.makeText(getApplicationContext(), "Syncing...", Toast.LENGTH_LONG).show();
							getStatusData();
						} else if (curTime.contains("14:10:10")) {
							Toast.makeText(getApplicationContext(), "Syncing...", Toast.LENGTH_LONG).show();
							getStatusData();
						} else if (curTime.contains("18:22:10")) {
							Toast.makeText(getApplicationContext(), "Syncing...", Toast.LENGTH_LONG).show();
							getStatusData();
						}
						else {

						}

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
	}

	private void runOnUiThread(Runnable runnable) {
		handler.post(runnable);
	}

	//------ UNUSED CODE
	//	public void syncNow() {
	//
	//		System.out.println("Sync : ");
	//		getStatusData();
	//		System.out.println("SyncEnd : ");
	//
	//	}
}
