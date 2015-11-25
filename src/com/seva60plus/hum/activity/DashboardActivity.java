package com.seva60plus.hum.activity;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnShowListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.drawable.AnimationDrawable;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.seva60plus.hum.R;
import com.seva60plus.hum.customview.NoInternetPage;
import com.seva60plus.hum.mediacentre.MediaCentreDashBoard;
import com.seva60plus.hum.nearby.NearByDashboard;
import com.seva60plus.hum.pillreminder.TakeThePill;
import com.seva60plus.hum.reminder.taskreminder.ReminderListActivity;
import com.seva60plus.hum.sathi.Sathicall;
import com.seva60plus.hum.sharelocation.MapShareActivity;
import com.seva60plus.hum.specialoffers.OffersListActivity;
import com.seva60plus.hum.util.CheckUpdates;
import com.seva60plus.hum.util.Util;
import com.seva60plus.hum.utilities.Utilities;
import com.seva60plus.hum.wellbeing.WelbingActivityWater;

public class DashboardActivity extends Activity {

	private final String MY_PREFS_NAME = "MyPrefsFile";

	private RelativeLayout pill, sathi, location, about, utilities, knowledge, nearby, reminder, welbing;
	private RelativeLayout backBtn;
	private ImageView menuIcon;

	//---by Dibyendu
	// flag for Internet connection status
	private Boolean isInternetPresent = false;

	private LocationManager lm;

	//ProgressDialog progress;

	private Dialog progress;
	private AnimationDrawable progressAnimation;

	//------End

	private String curVersion = "";
	private PackageInfo pInfo;
	private Context mContext;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dashboard);

		mContext = DashboardActivity.this;
		lm = (LocationManager) getSystemService(LOCATION_SERVICE);// by Dibyendu

		//------START------------For Progress Spinner--------------

		progress = new Dialog(DashboardActivity.this);
		progress.getWindow().setBackgroundDrawableResource(R.drawable.spinner_dialog_backround);

		//Remove the Title
		progress.getWindow().requestFeature(Window.FEATURE_NO_TITLE);

		//progress.setTitle("");

		//Set the View of the Dialog - Custom
		progress.setContentView(R.layout.custom_progress_dialog);

		//Set the title of the Dialog
		//dialog.setTitle("Title...");

		ImageView progressSpinner = (ImageView) progress.findViewById(R.id.progressSpinner);

		//Set the background of the image - In this case an animation (/res/anim folder)
		progressSpinner.setBackgroundResource(R.anim.spinner_progress_animation);

		//Get the image background and attach the AnimationDrawable to it.
		progressAnimation = (AnimationDrawable) progressSpinner.getBackground();

		//Start the animation after the dialog is displayed.
		progress.setOnShowListener(new OnShowListener() {
			@Override
			public void onShow(DialogInterface dialog) {
				progressAnimation.start();
			}

		});

		progress.setCanceledOnTouchOutside(false);
		//-------END-----------For Progress Spinner--------------   

		//*****************Check Updates

		isInternetPresent = Util.isInternetAvailable(mContext);

		// check for Internet status
		if (isInternetPresent) {

			try {
				pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
			} catch (NameNotFoundException e) {
				e.printStackTrace();
			}
			curVersion = pInfo.versionName;
			String newVersion = checkUpdates();

			SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
			editor.putString("prvNewVersion", newVersion);
			editor.commit();

			System.out.println("cVersion: " + curVersion + "\n newVersion: " + newVersion);
			if (Double.parseDouble(curVersion)!=Double.parseDouble(newVersion)) {

				Intent intObj = new Intent(DashboardActivity.this, CheckUpdates.class);
				startActivity(intObj);
				//}
			} else {
				// low version

			}

		} else {
			//no Internet

		}

		//*******************End Check Updates

		menuIcon = (ImageView) findViewById(R.id.menu_icon);
		menuIcon.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent intObj = new Intent(DashboardActivity.this, MenuLay.class);
				startActivity(intObj);
				overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);

				//finish();
			}
		});

		RelativeLayout offer = (RelativeLayout) findViewById(R.id.offer_btn);
		offer.setBackgroundResource(R.drawable.offer_animation);

		AnimationDrawable frameAnimation = (AnimationDrawable) offer.getBackground();

		// Start the animation (looped playback by default).
		frameAnimation.start();

		offer.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(DashboardActivity.this, OffersListActivity.class);
				startActivity(i);
				overridePendingTransition(0, 0);
			}
		});

		pill = (RelativeLayout) findViewById(R.id.pill_btn);
		pill.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent i = new Intent(DashboardActivity.this, TakeThePill.class);
				startActivity(i);
				overridePendingTransition(0, 0);
				//finish();
			}
		});

		sathi = (RelativeLayout) findViewById(R.id.sathi_btn);
		sathi.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent i = new Intent(DashboardActivity.this, Sathicall.class);
				startActivity(i);
				overridePendingTransition(0, 0);
				//finish();
			}
		});

		welbing = (RelativeLayout) findViewById(R.id.well_bing_btn);
		welbing.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent i = new Intent(DashboardActivity.this, WelbingActivityWater.class);
				startActivity(i);
				overridePendingTransition(0, 0);
				//finish();
			}
		});

		location = (RelativeLayout) findViewById(R.id.location_btn);
		location.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				isInternetPresent = Util.isInternetAvailable(mContext);

				// check for Internet status
				if (isInternetPresent) {
					// Internet Connection is Present

					if (lm.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
						// GPS Connection is Present

						Intent i = new Intent(DashboardActivity.this, MapShareActivity.class);
						startActivity(i);
						overridePendingTransition(0, 0);

					} else {
						final Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
						startActivity(intent);
						overridePendingTransition(0, 0);
					}

				} else {
					// Internet connection is not present
					Intent i = new Intent(DashboardActivity.this, NoInternetPage.class);
					startActivity(i);
					overridePendingTransition(0, 0);

				}

				//Toast.makeText(getApplicationContext(), "Coming Soon!", Toast.LENGTH_LONG).show();
			}
		});

		nearby = (RelativeLayout) findViewById(R.id.nearby_btn);
		nearby.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// get Internet status
				isInternetPresent = Util.isInternetAvailable(mContext);

				// check for Internet status
				if (isInternetPresent) {
					// Internet Connection is Present

					if (lm.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
						// GPS Connection is Present

						/*
						 * Intent i = new
						 * Intent(Dashboard.this,MainActivityMap.class);
						 * i.putExtra("iarea", "5000"); i.putExtra("iChoice",
						 * "abc"); i.putExtra("spinner", "1"); startActivity(i);
						 * overridePendingTransition(0, 0);
						 */

						progress.dismiss();
						Intent i = new Intent(DashboardActivity.this, NearByDashboard.class);
						startActivity(i);
						overridePendingTransition(0, 0);

					} else {
						final Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
						startActivity(intent);
						overridePendingTransition(0, 0);
					}

				}

				else {
					// Internet connection is not present
					Intent i = new Intent(DashboardActivity.this, NoInternetPage.class);
					startActivity(i);
					//overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);

				}

				//Toast.makeText(getApplicationContext(), "Coming Soon!", Toast.LENGTH_LONG).show();
			}
		});

		about = (RelativeLayout) findViewById(R.id.seva60_btn);
		about.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent i = new Intent(DashboardActivity.this, AboutSeva60.class);
				startActivity(i);
				overridePendingTransition(0, 0);
				//finish();
			}
		});

		knowledge = (RelativeLayout) findViewById(R.id.knownledge_base_btn);
		knowledge.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent i = new Intent(DashboardActivity.this, MediaCentreDashBoard.class);
				startActivity(i);

				//Toast.makeText(getApplicationContext(), "Coming Soon!", Toast.LENGTH_LONG).show();
			}
		});

		utilities = (RelativeLayout) findViewById(R.id.utilities_btn);
		utilities.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent i = new Intent(DashboardActivity.this, Utilities.class);
				startActivity(i);
				overridePendingTransition(0, 0);
				//finish();
			}
		});

		reminder = (RelativeLayout) findViewById(R.id.money_reminder_btn);
		reminder.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				/*
				 * Intent i = new Intent(Dashboard.this,MainActivity.class);
				 * startActivity(i); overridePendingTransition(0, 0);
				 * //finish();
				 */

				Intent i = new Intent(DashboardActivity.this, ReminderListActivity.class);
				startActivity(i);
				overridePendingTransition(0, 0);

			}
		});

		backBtn = (RelativeLayout) findViewById(R.id.back_settings);

		backBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				Intent i = new Intent(DashboardActivity.this, Registration.class);
				startActivity(i);
				overridePendingTransition(R.anim.slide_in_right, R.anim.slide_leave_right);

			}
		});

	}

	private String checkUpdates() {
		String version = "";
		JSONObject json2 = null;
		String str2 = "";
		HttpResponse response2;
		HttpClient myClient2 = new DefaultHttpClient();
		HttpPost myConnection2 = new HttpPost("http://seva60plus.co.in/seva60PlusAndroidAPI/dbversion");

		try {
			response2 = myClient2.execute(myConnection2);
			str2 = EntityUtils.toString(response2.getEntity(), "UTF-8");

		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Json Sarted.... "+"\n"+str2);
		try {
			json2 = new JSONObject(str2);
			version = json2.getString("version");
			System.out.println("Code: " + version);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return version;

	}
}
