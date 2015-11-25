package com.seva60plus.hum.activity;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.seva60plus.hum.R;
import com.seva60plus.hum.alarmnotification.NotificationAlarmService;
import com.seva60plus.hum.customview.NoInternetPage;
import com.seva60plus.hum.customview.SlowInternetPage;
import com.seva60plus.hum.network.PostDataStatices;
import com.seva60plus.hum.util.Util;

public class SplashActivity extends Activity {

	private Button go, termsBtn, privacyBtn;
	private Boolean isInternetPresent = false;
	private String code1 = "";
	private String subId;
	private TelephonyManager tm;
	private String regValue = "";
	private static final String MY_PREFS_NAME = "MyPrefsFile";

	private String eMsg = "";
	private ProgressDialog progress;

	//===============================
	/* Added by raisahab.ritwik */
	private Context mContext;
	private String TAG = "SplashActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash_activity);
		//Log.v(null, null);
		mContext = SplashActivity.this;

		stopService(new Intent(this, NotificationAlarmService.class));
		startService(new Intent(this, NotificationAlarmService.class));

		Thread myThread = null;

		Runnable myRunnableThread = new CountDownRunner();
		myThread = new Thread(myRunnableThread);
		myThread.start();

		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
		tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		subId = tm.getSubscriberId().toString();

		SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
		regValue = prefs.getString("regValue", "");

		ImageView logo = (ImageView) findViewById(R.id.iv_app_logo);
		logo.setBackgroundResource(R.drawable.logo_animation);

		AnimationDrawable frameAnimation = (AnimationDrawable) logo.getBackground();

		// Start the animation (looped playback by default).
		frameAnimation.start();

		//-------START------------For Progress Spinner--------------

		progress = new ProgressDialog(SplashActivity.this);
		progress.setCancelable(true);
		progress.setMessage("Please wait..");
		progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);

		//-------END-----------For Progress Spinner--------------   

		go = (Button) findViewById(R.id.btn_tap_to_enter);
		go.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				progress.show();
				new CheckUser().execute();

			}
		});

		if (progress.isShowing()) {
			//is running
			new Handler().postDelayed(new Runnable() {
				@Override
				public void run() {

					// not getting-----!
					if (progress.isShowing()) {
						System.out.println("Pro=====1");
						progress.dismiss();

						Intent intObj = new Intent(SplashActivity.this, SlowInternetPage.class);
						startActivity(intObj);
						overridePendingTransition(0, 0);
					} else {

					}

				}
			}, 20000);
		} else {
			//----Get it Done
			System.out.println("Pro=====2");
		}

		//=============== -------end Loader timing ====26.08.15

		termsBtn = (Button) findViewById(R.id.btn_terms_of_service);
		//	go.setTypeface(font);
		termsBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				// Take action.
				Intent myIntent = new Intent(SplashActivity.this, Terms.class);
				startActivity(myIntent);

			}
		});

		privacyBtn = (Button) findViewById(R.id.btn_privacy_policy);
		//	go.setTypeface(font);
		privacyBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				// Take action.
				Intent myIntent = new Intent(SplashActivity.this, Privacy.class);
				startActivity(myIntent);
			}
		});

	}
	/**
	 * call broadcost reciver for send sms
	 */
	private void fireAlarm() {
		
		Intent intent = new Intent(this, PostDataStatices.class);
		intent.setAction("com.seva60Plus.alarm.ACTION");
		PendingIntent pendingIntent = PendingIntent.getBroadcast(SplashActivity.this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 11);
		calendar.set(Calendar.MINUTE, 10);
		calendar.set(Calendar.SECOND, 10);
		AlarmManager alarm = (AlarmManager) SplashActivity.this.getSystemService(Context.ALARM_SERVICE);
		alarm.cancel(pendingIntent);
		alarm.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
	}
	/**
	 * call broadcost reciver for send sms
	 */
	private void fireAlarm2() {
		
		Intent intent = new Intent(this, PostDataStatices.class);
		intent.setAction("com.seva60Plus.alarm.ACTION");
		PendingIntent pendingIntent = PendingIntent.getBroadcast(SplashActivity.this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 17);
		calendar.set(Calendar.MINUTE, 10);
		calendar.set(Calendar.SECOND, 10);
		AlarmManager alarm = (AlarmManager) SplashActivity.this.getSystemService(Context.ALARM_SERVICE);
		alarm.cancel(pendingIntent);
		alarm.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
	}
	/**
	 * call broadcost reciver for send sms
	 */
	private void fireAlarm3() {
		
		Intent intent = new Intent(this, PostDataStatices.class);
		intent.setAction("com.seva60Plus.alarm.ACTION");
		PendingIntent pendingIntent = PendingIntent.getBroadcast(SplashActivity.this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 21);
		calendar.set(Calendar.MINUTE, 10);
		calendar.set(Calendar.SECOND, 10);
		AlarmManager alarm = (AlarmManager) SplashActivity.this.getSystemService(Context.ALARM_SERVICE);
		alarm.cancel(pendingIntent);
		alarm.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
	}

	private class CountDownRunner implements Runnable {
		// @Override
		public void run() {
			while (!Thread.currentThread().isInterrupted()) {
				try {
					doWork();
					Thread.sleep(1000); // Pause of 1 Second
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				} catch (Exception e) {
				}
			}
		}

		@SuppressWarnings("deprecation")
		private void doWork() {
			runOnUiThread(new Runnable() {
				public void run() {
					try {
						
						Date dt = new Date();

						int hours = dt.getHours();
						int minutes = dt.getMinutes();
						int seconds = dt.getSeconds();
						String curTime = hours + ":" + minutes + ":" + seconds;

						if (curTime.contains("11:10:10")) {
							System.out.println("Time IS1 : " + curTime);
							fireAlarm();
						} else if (curTime.contains("17:10:10")) {
							System.out.println("Time IS2 : " + curTime);
							fireAlarm2();
						} else if (curTime.contains("21:10:10")) {
							System.out.println("Time IS3 : " + curTime);
							fireAlarm3();
						} else {

						}

					} catch (Exception e) {
					}
				}
			});
		}
	}

	//----------------Check
	//-----------Get Saathi List
	private class CheckUser extends AsyncTask<Void, Integer, String> {

		@Override
		protected void onPreExecute() {
			progress.show();
			super.onPreExecute();
		}

		@Override
		protected String doInBackground(Void... params) {
			return checkUser();
		}

		private String checkUser() {
			
			try {
				
				if (!regValue.equals("OK")) {
					
					isInternetPresent = Util.isInternetAvailable(mContext);
					// check for Internet status
					Log.i(TAG, "Reg Value: " + regValue);

					if (isInternetPresent) {

						JSONObject json2 = null;
						String str2 = "";
						HttpResponse response2;
						HttpClient myClient2 = new DefaultHttpClient();
						HttpPost myConnection2 = new HttpPost("http://seva60plus.co.in/seva60PlusAndroidAPI/register/checkUserRegistered/" + subId);

						try {
							response2 = myClient2.execute(myConnection2);
							str2 = EntityUtils.toString(response2.getEntity(), "UTF-8");

						} catch (ClientProtocolException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						}
						System.out.println("Json Sarted.... "+str2);
						try {
							JSONArray jArray2 = new JSONArray(str2);
							json2 = jArray2.getJSONObject(0);
							code1 = json2.getString("code");
							System.out.println("Code: " + code1);
						} catch (Exception e) {
							e.printStackTrace();
						}

						if (code1.equals("200")) {
							// Registered
							SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
							editor.putString("regValue", "OK");
							editor.commit();

							Intent myIntent = new Intent(SplashActivity.this, DashboardActivity.class);
							startActivity(myIntent);
						} else {
							// not Registered
							Intent myIntent = new Intent(SplashActivity.this, Registration.class);
							startActivity(myIntent);
						}

					} else {
						// Internet connection is not present
						Intent i = new Intent(SplashActivity.this, NoInternetPage.class);
						startActivity(i);
						overridePendingTransition(0, 0);

					}

				} else {
					
					Intent myIntent = new Intent(SplashActivity.this, DashboardActivity.class);
					startActivity(myIntent);

				}

			} catch (Exception e) {
				eMsg = e.toString();
			}

			return eMsg;
		}

		@Override
		protected void onPostExecute(String result) {
			progress.dismiss();

			super.onPostExecute(result);
		}

	}


}
