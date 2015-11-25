package com.seva60plus.hum.activity;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.regex.Pattern;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnShowListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.util.Patterns;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.seva60plus.hum.R;
import com.seva60plus.hum.customview.NoInternetPage;
import com.seva60plus.hum.util.UserFunctions;
import com.seva60plus.hum.util.Util;
import com.seva60plus.phonecontactfetch.ContactListActivityfetch;

public class Registration extends Activity {

	private final String MY_PREFS_NAME = "MyPrefsFile";
	private Button saveBtn, cancelBtn;
	private TextView headerTitleText, bylineText;
	private ImageView homeBtn;
	private RelativeLayout backBtn;
	private EditText saathi1_contact;
	private EditText saathi2_contact;
	//EditText phermaEdit;
	private String con1 = "";
	private String con2 = "";
	private String saathiName1 = "", saathiName2 = "", userName = "", userPhone = "", dateOfBirth = "", cityName = "", state = "", pinNum = "", emailID1 = "",
			emailID2 = "", geoLocation = "1", status = "1";

	private EditText name, userPhoneNum, dob, city, editState, sathi1Name, email1, sathi2Name, email2;
	private TextView phone1Code, phone3Code, phone2Code;

	private String phoneCode1 = "", phoneCode2 = "", phoneCode3 = "";
	private String ph1Code = "", ph2Code = "", ph3Code = "";

	private String result = "", messege = "";
	private String subId;
	private String isoCode, isoCodeS;

	private TelephonyManager tm;
	private String submit = "abc";
	private String emailMatch = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+.+[a-z]+";
	private String emailMatch1 = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+.+[a-z]+";
	private String sex;
	private RadioGroup rad;
	private RadioButton Male, Female;
	private RelativeLayout showSathi1, Sathi1Body, Sathi2Body, showSathi2;
	private ImageView sathiarrow1, sathiarrow2;
	private String decodeUser = "", decodeSaathi1 = "", decodeSaathi2 = "";
	private Bitmap userImage, saathi1Image, saathi2Image;

	private String sathitype = "0";
	private String sathitype2 = "0";

	private String regId;
	private int year;
	private int month;
	private int day;
	static final int DATE_PICKER_ID = 1111;
	private String output;
	private String code1, code2;
	private String imei;
	private static String key = "code";
	private TextView mDisplay;
	private String eMsg;
	private AsyncTask<Void, Void, Void> mRegisterTask;
	private String msgToast = "0";
	private String possibleEmail;
	private ImageView con_list;

	private Dialog progress;
	private AnimationDrawable progressAnimation;

	private Boolean isInternetPresent = false;
	private TextView check_text;
	private CheckBox check_agree;

	private RelativeLayout helpLay;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.registration_copy);

		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
		//imei

		TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		telephonyManager.getDeviceId();

		imei = telephonyManager.getDeviceId();
		Pattern emailPattern = Patterns.EMAIL_ADDRESS; // API level 8+
		Account[] accounts = AccountManager.get(getBaseContext()).getAccounts();
		for (Account account : accounts) {
			if (emailPattern.matcher(account.name).matches()) {
				possibleEmail = account.name;

			}
		}

		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			String friendid = extras.getString("friendid");
			String msg = extras.getString("msg_id");
			String notification_id = extras.getString("notificationid");
			mDisplay.setText(msg);
		}

		//------START------------For Progress Spinner--------------

		progress = new Dialog(Registration.this);
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
		helpLay = (RelativeLayout) findViewById(R.id.helpLay);
		helpLay.setVisibility(View.VISIBLE);
		helpLay.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent intObj = new Intent(Registration.this, HelpRegistrationPage.class);
				startActivity(intObj);

			}
		});

		con_list = (ImageView) findViewById(R.id.userPhoneImage);

		rad = (RadioGroup) findViewById(R.id.radioSex);
		Male = (RadioButton) findViewById(R.id.radioMale);
		Female = (RadioButton) findViewById(R.id.radioFemale);

		tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		subId = tm.getSubscriberId().toString();

		isoCodeS = tm.getSimCountryIso().toString().toUpperCase();

		String[] rl = this.getResources().getStringArray(R.array.CountryCodes);
		for (int i = 0; i < rl.length; i++) {
			String[] g = rl[i].split(",");
			if (g[1].trim().equals(isoCodeS.trim())) {
				isoCode = "+" + g[0];
				break;
			}
		}

		System.out.println(subId);

		initializeControls();

		initializeControls2();

		initializeControls3();

		//fetch contact

		con_list.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intCon = new Intent(Registration.this, ContactListActivityfetch.class);
				//startActivity(intCon);
				startActivityForResult(intCon, 71);
			}
		});

		//

		//------User Form-----------------------------
		name = (EditText) findViewById(R.id.nameEdit);
		userPhoneNum = (EditText) findViewById(R.id.userPhoneEdit);
		dob = (EditText) findViewById(R.id.dobEdit);
		//add1 = (EditText)findViewById(R.id.addressEdit);
		//add2 = (EditText)findViewById(R.id.address2Edit);
		city = (EditText) findViewById(R.id.cityEdit);
		editState = (EditText) findViewById(R.id.stateEdit);
		//pin = (EditText)findViewById(R.id.pinEdit);

		//-----------agree Text
		check_text = (TextView) findViewById(R.id.check_text);
		check_agree = (CheckBox) findViewById(R.id.check1);

		check_text.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (check_agree.isChecked()) {
					check_agree.setChecked(false);
				} else {
					check_agree.setChecked(true);
				}
			}
		});

		//------Saathi1 Form-----------------------------
		sathi1Name = (EditText) findViewById(R.id.sathi1NameEdit);
		email1 = (EditText) findViewById(R.id.emailEdit);
		//------Saathi2 Form-----------------------------
		sathi2Name = (EditText) findViewById(R.id.sathi2NameEdit);
		email2 = (EditText) findViewById(R.id.emailEdit2);

		////
		saathi1_contact = (EditText) findViewById(R.id.contactNum);

		saathi2_contact = (EditText) findViewById(R.id.contactNum2);

		//phermaEdit=(EditText)findViewById(R.id.allergicEdit);
		Sathi1Body = (RelativeLayout) findViewById(R.id.sathi1_body);
		Sathi2Body = (RelativeLayout) findViewById(R.id.sathi2_body);
		sathiarrow1 = (ImageView) findViewById(R.id.addsathi1arrow);

		phone1Code = (TextView) findViewById(R.id.userPhoneCode);
		phone2Code = (TextView) findViewById(R.id.contactNumCode);
		phone3Code = (TextView) findViewById(R.id.contactNum2Code);

		phone1Code.setText(isoCode);
		phone2Code.setText(isoCode);
		phone3Code.setText(isoCode);

		final Intent intent = new Intent(this, CountryCodeActivity1.class);
		phone1Code.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				startActivityForResult(intent, 91);
			}
		});

		phone2Code.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				startActivityForResult(intent, 92);
			}
		});
		phone3Code.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				startActivityForResult(intent, 93);
			}
		});

		int width = 16;
		int height = 28;
		RelativeLayout.LayoutParams parms = new RelativeLayout.LayoutParams(width, height);
		parms.setMargins(0, 0, 20, 0);
		parms.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		parms.addRule(RelativeLayout.CENTER_VERTICAL);
		sathiarrow1.setLayoutParams(parms);
		sathiarrow1.setBackgroundResource(R.drawable.add_sathi_arrow);

		sathiarrow2 = (ImageView) findViewById(R.id.addsathi1arrow2);

		int width1 = 16;
		int height1 = 28;
		RelativeLayout.LayoutParams parms1 = new RelativeLayout.LayoutParams(width1, height1);
		parms1.setMargins(0, 0, 20, 0);
		parms1.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		parms1.addRule(RelativeLayout.CENTER_VERTICAL);
		sathiarrow2.setLayoutParams(parms1);
		sathiarrow2.setBackgroundResource(R.drawable.add_sathi_arrow);

		showSathi1 = (RelativeLayout) findViewById(R.id.addsathiLay);
		showSathi1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (sathitype.equals("0")) {
					Sathi1Body.setVisibility(View.VISIBLE);
					int width = 28;
					int height = 16;
					RelativeLayout.LayoutParams parms = new RelativeLayout.LayoutParams(width, height);
					parms.setMargins(0, 0, 20, 0);
					parms.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
					parms.addRule(RelativeLayout.CENTER_VERTICAL);
					sathiarrow1.setLayoutParams(parms);
					sathiarrow1.setBackgroundResource(R.drawable.add_sathi_arrow_down);
					sathitype = "1";
				} else if (sathitype.equals("1")) {
					Sathi1Body.setVisibility(View.GONE);
					int width = 16;
					int height = 28;
					RelativeLayout.LayoutParams parms = new RelativeLayout.LayoutParams(width, height);
					parms.setMargins(0, 0, 20, 0);
					parms.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
					parms.addRule(RelativeLayout.CENTER_VERTICAL);
					sathiarrow1.setLayoutParams(parms);
					sathiarrow1.setBackgroundResource(R.drawable.add_sathi_arrow);
					sathitype = "0";
				} else {
					Sathi1Body.setVisibility(View.VISIBLE);
					int width = 28;
					int height = 16;
					RelativeLayout.LayoutParams parms = new RelativeLayout.LayoutParams(width, height);
					parms.setMargins(0, 0, 20, 0);
					parms.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
					parms.addRule(RelativeLayout.CENTER_VERTICAL);
					sathiarrow1.setLayoutParams(parms);
					sathiarrow1.setBackgroundResource(R.drawable.add_sathi_arrow_down);
					sathitype = "1";
				}

			}
		});

		showSathi2 = (RelativeLayout) findViewById(R.id.addsathiLay2);
		showSathi2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (sathitype2.equals("0")) {
					Sathi2Body.setVisibility(View.VISIBLE);
					int width1 = 28;
					int height1 = 16;
					RelativeLayout.LayoutParams parms1 = new RelativeLayout.LayoutParams(width1, height1);
					parms1.setMargins(0, 0, 20, 0);
					parms1.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
					parms1.addRule(RelativeLayout.CENTER_VERTICAL);
					sathiarrow2.setLayoutParams(parms1);
					sathiarrow2.setBackgroundResource(R.drawable.add_sathi_arrow_down);
					sathitype2 = "1";
				} else if (sathitype2.equals("1")) {
					Sathi2Body.setVisibility(View.GONE);
					int width1 = 16;
					int height1 = 28;
					RelativeLayout.LayoutParams parms1 = new RelativeLayout.LayoutParams(width1, height1);
					parms1.setMargins(0, 0, 20, 0);
					parms1.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
					parms1.addRule(RelativeLayout.CENTER_VERTICAL);
					sathiarrow2.setLayoutParams(parms1);
					sathiarrow2.setBackgroundResource(R.drawable.add_sathi_arrow);
					sathitype2 = "0";
				} else {
					Sathi2Body.setVisibility(View.VISIBLE);
					int width1 = 28;
					int height1 = 16;
					RelativeLayout.LayoutParams parms1 = new RelativeLayout.LayoutParams(width1, height1);
					parms1.setMargins(0, 0, 20, 0);
					parms1.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
					parms1.addRule(RelativeLayout.CENTER_VERTICAL);
					sathiarrow2.setLayoutParams(parms1);
					sathiarrow2.setBackgroundResource(R.drawable.add_sathi_arrow_down);
					sathitype2 = "1";
				}

			}
		});

		//-----end of initilize of editbox------

		final Calendar c = Calendar.getInstance();
		year = c.get(Calendar.YEAR);
		month = c.get(Calendar.MONTH);
		day = c.get(Calendar.DAY_OF_MONTH);

		StringBuilder superStringBuilder = new StringBuilder()
		// Month is 0 based, just add 1
				/*
				 * .append(month + 1).append("-").append(day).append("-")
				 * .append(year).append(" ");
				 */

				.append(day).append("/").append(month + 1).append("/").append(year).append(" ");

		output = superStringBuilder.toString();

		//	new GetUserDetails().execute();

		///------------New Dibyendu code 23.06.15

		SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
		userName = prefs.getString("UserName", "");
		userPhone = prefs.getString("UserPhoneNumber", "");
		dateOfBirth = prefs.getString("dob", "");
		sex = prefs.getString("sex", "");

		cityName = prefs.getString("city", "");
		state = prefs.getString("state", "");
		pinNum = prefs.getString("pin", "");
		saathiName1 = prefs.getString("saathi1CallName", "");
		saathiName2 = prefs.getString("saathi2CallName", "");
		emailID1 = prefs.getString("saathi1emailID", "");
		emailID2 = prefs.getString("saathi2emailID", "");
		con1 = prefs.getString("saathi1Call", "");
		con2 = prefs.getString("saathi2Call", "");

		geoLocation = prefs.getString("geoLocation", "");
		//con_phone  = prefs.getString("conph", "");

		/*
		 * pinNum = prefs.getString("pin", ""); decodeSaathi1 =
		 * prefs.getString("sathi_Image1", ""); decodeSaathi2 =
		 * prefs.getString("sathi_Image2", "");
		 */

		if (con1.equals("") || con2.equals("") || saathiName1.equals("") || saathiName2.equals("") || emailID1.equals("") || emailID1.equals("")) {
			isInternetPresent = Util.isInternetAvailable(Registration.this);

			// check for Internet status
			if (isInternetPresent) {

				new GetUserDetails().execute();
			} else {

				System.out.println("No internet");
				//Toast.makeText(getApplicationContext(), "You are not registered user", Toast.LENGTH_SHORT).show();
				//finish();
				// Internet connection is not present
				Intent i = new Intent(Registration.this, NoInternetPage.class);
				startActivity(i);
				overridePendingTransition(0, 0);
				finish();

			}
		} else {

			msgToast = "1";

			name.setText(userName);
			userPhoneNum.setText(userPhone);
			/*
			 * if(con_phone.equals("")){ userPhoneNum.setText(userPhone); }
			 * else{ userPhoneNum.setText(con_phone); }
			 */
			dob.setText(dateOfBirth);

			//add1.setText(address1);
			//add2.setText(address2);
			city.setText(cityName);
			editState.setText(state);

			sathi1Name.setText(saathiName1);
			email1.setText(emailID1);
			sathi2Name.setText(saathiName2);
			email2.setText(emailID2);
			saathi1_contact.setText(con1);
			saathi2_contact.setText(con2);

			if (geoLocation.equals("0")) {
				check_agree.setChecked(false);
			} else {
				check_agree.setChecked(true);
			}

		}
		headerTitleText = (TextView) findViewById(R.id.header_title);
		headerTitleText.setText("Seva60Plus");
		Typeface font = Typeface.createFromAsset(getAssets(), "openSansBold.ttf");
		headerTitleText.setTypeface(font);

		dob.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// On button click show datepicker dialog 
				showDialog(DATE_PICKER_ID);

			}

		});

		rad.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch (checkedId) {
				case R.id.radioMale:
					// do operations specific to this selection
					//Toast.makeText(getApplicationContext(), "English", Toast.LENGTH_SHORT).show();
					sex = "M";
					// Toast.makeText(getApplicationContext(), sex, Toast.LENGTH_SHORT).show();
					break;

				case R.id.radioFemale:
					// do operations specific to this selection
					//Toast.makeText(getApplicationContext(), "Italian", Toast.LENGTH_SHORT).show();
					sex = "F";
					//Toast.makeText(getApplicationContext(), sex, Toast.LENGTH_SHORT).show();
					break;

				}

			}
		});

		cancelBtn = (Button) findViewById(R.id.cancel);
		cancelBtn.setTypeface(font);
		cancelBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
				editor.putString("UserName", userName);
				editor.putString("UserPhoneNumber", userPhone);
				editor.putString("dob", dateOfBirth);
				editor.putString("sex", sex);

				editor.putString("city", cityName);
				editor.putString("state", state);
				editor.putString("pin", pinNum);
				editor.putString("saathi1CallName", saathiName1);
				editor.putString("saathi2CallName", saathiName2);
				editor.putString("saathi1emailID", emailID1);
				editor.putString("saathi2emailID", emailID2);
				editor.putString("saathi1Call", con1);
				editor.putString("saathi2Call", con2);

				editor.putString("sathi_Image1", decodeSaathi1);
				editor.putString("sathi_Image2", decodeSaathi2);
				editor.putString("geoLocation", geoLocation);
				editor.commit();

				finish();
				overridePendingTransition(0, R.anim.activity_close_translate);
			}
		});

		saveBtn = (Button) findViewById(R.id.save);
		saveBtn.setTypeface(font);
		saveBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				isInternetPresent = Util.isInternetAvailable(Registration.this);
				// check for Internet status
				if (isInternetPresent) {

					System.out.println("OK");

					userName = name.getText().toString();
					userPhone = userPhoneNum.getText().toString();
					dateOfBirth = dob.getText().toString();

					cityName = city.getText().toString();
					state = editState.getText().toString();

					if (check_agree.isChecked()) {

						geoLocation = "1";
					} else {
						geoLocation = "0";
					}

					//Saath1
					saathiName1 = sathi1Name.getText().toString();
					emailID1 = email1.getText().toString();
					//Saath2
					saathiName2 = sathi2Name.getText().toString();
					emailID2 = email2.getText().toString();

					con1 = saathi1_contact.getText().toString();
					con2 = saathi2_contact.getText().toString();

					phoneCode1 = phone1Code.getText().toString();
					phoneCode2 = phone2Code.getText().toString();
					phoneCode3 = phone3Code.getText().toString();
					if (userName.equals("") || userPhone.equals("") || dateOfBirth.equals("") || cityName.equals("") || saathiName1.equals("")
							|| emailID1.equals("") || con1.equals("")) {

						Toast.makeText(getApplicationContext(), "Please fill all fields", Toast.LENGTH_LONG).show();

					} else if (userName.startsWith(" ") || saathiName1.startsWith(" ")) {

						Toast.makeText(getApplicationContext(), "No space allowed in front of Name", Toast.LENGTH_LONG).show();

					} else if (phoneCode1.equals("") || phoneCode2.equals("")) {

						Toast.makeText(getApplicationContext(), "Please enter  country code ", Toast.LENGTH_LONG).show();

					} else if (userPhone.length() != 10 || con1.length() != 10) {

						Toast.makeText(getApplicationContext(), "Please enter 10 digit mobile no ", Toast.LENGTH_LONG).show();

					} else if (con1.equals(userPhone)) {
						Toast.makeText(getApplicationContext(), "Both saathi no. & hum no. can't be same ", Toast.LENGTH_LONG).show();
					} else if (emailID1.matches(emailMatch)) {

						try {
							String pherma = "", address1 = "", address2 = "";
							progress.show();
							System.out.println("Registration : " + regId + "\n" + imei + "\n" + possibleEmail + "\n" + userName + "\n" + userPhone + "\n"
									+ dateOfBirth + "\n" + sex + "\n" + address1 + "\n" + address2 + "\n" + cityName + "\n" + state + "\n" + pinNum + "\n"
									+ saathiName1 + "\n" + emailID1 + "\n" + saathiName2 + "\n" + emailID2 + "\n" + con1 + "\n" + con2 + "\n" + pherma);
							UserFunctions userFunction = new UserFunctions();
							//	JSONObject json = userFunction.registerUser(submit,subId,regId,imei,possibleEmail,userName,userPhone,dateOfBirth,sex,address1,address2,cityName,state,pinNum,saathiName1,emailID1,saathiName2,emailID2,con1,con2,pherma,encodeUser,encodeSaathi1,encodeSaathi2,geoLocation);
							JSONObject json = userFunction.registerUser(submit, subId, imei, possibleEmail, userName, phoneCode1, userPhone, dateOfBirth, sex,
									address1, cityName, state, pinNum, saathiName1, emailID1, saathiName2, emailID2, phoneCode2, con1, phoneCode3, con2,
									pherma, geoLocation, status);
							System.out.println("PostJSON: " + json);
						} catch (Exception e) {
							System.out.println("Failed" + e.toString());
							progress.dismiss();

						}

						//=====SharedPreferences======================	

						if (messege.equals("User does not exists with this sim no.")) {

						} else {

							SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
							editor.putString("UserName", userName);
							editor.putString("UserPhoneNumber", userPhone);
							editor.putString("dob", dateOfBirth);
							editor.putString("sex", sex);
							editor.putString("city", cityName);
							editor.putString("state", state);
							editor.putString("pin", pinNum);
							editor.putString("saathi1CallName", saathiName1);
							editor.putString("saathi2CallName", saathiName2);
							editor.putString("saathi1emailID", emailID1);
							editor.putString("saathi2emailID", emailID2);
							editor.putString("saathi1Call", con1);
							editor.putString("saathi2Call", con2);

							editor.putString("sathi_Image1", decodeSaathi1);
							editor.putString("sathi_Image2", decodeSaathi2);

							editor.putString("geoLocation", geoLocation);

							editor.commit();
						}

						//==SharedPreferences===============  

						if (msgToast.equals("0")) {

							SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
							editor.putString("regValue", "OK");
							editor.commit();

							Toast.makeText(getApplicationContext(), "Registered successfully", Toast.LENGTH_LONG).show();

							String sucessMsg = userName
									+ " has added you as a Saathi! Please install the Seva60Plus Saathi app from Google Play Store to monitor the well-being of "
									+ userName + " !\nhttps://play.google.com/store/apps/details?id=com.seva60plus.saathi&hl=en";

							System.out.println("SMS =" + "\n" + con1 + "\n" + con2 + "\n" + sucessMsg);

							SmsManager sm = SmsManager.getDefault();
							if (con2.equals("")) {

							} else {
								sm.sendTextMessage(con2, null, sucessMsg, null, null);
							}

							sm.sendTextMessage(con1, null, sucessMsg, null, null);

							Intent i = new Intent(Registration.this, DashboardActivity.class);
							startActivity(i);
							finish();

						} else if (msgToast.equals("1")) {
							Toast.makeText(getApplicationContext(), "Saved successfully", Toast.LENGTH_LONG).show();

							Intent i = new Intent(Registration.this, DashboardActivity.class);
							startActivity(i);
							finish();

						} else {
							Toast.makeText(getApplicationContext(), "Oops something went wrong", Toast.LENGTH_LONG).show();
							Intent i = new Intent(Registration.this, Registration.class);
							startActivity(i);
						}

					} else {

						Toast.makeText(getApplicationContext(), "Pleae fill valid mail", Toast.LENGTH_LONG).show();
					}

				} else {

					System.out.println("No internet");
					Intent i = new Intent(Registration.this, NoInternetPage.class);
					startActivity(i);
					overridePendingTransition(0, 0);
				}

			}
		});

		homeBtn = (ImageView) findViewById(R.id.menu_icon);

		homeBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				// Take action.
				Intent myIntent = new Intent(Registration.this, MenuLay.class);
				startActivity(myIntent);
				overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);

			}
		});
	}

	private void initializeControls() {
		//imVCature_pic=(ImageView)findViewById(R.id.imVCature_pic);
		//btnCapture=(Button)findViewById(R.id.btnCapture);
		/*
		 * btnCapture.setOnClickListener(new OnClickListener() {
		 * 
		 * @Override public void onClick(View v) {
		 * 
		 * Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
		 * 
		 * File file = new
		 * File(Environment.getExternalStorageDirectory()+File.separator +
		 * "img.jpg");
		 * 
		 * intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
		 * 
		 * startActivityForResult(intent, 1); } });
		 */

	}

	private void initializeControls2() {
		//imVCature_pic1=(ImageView)findViewById(R.id.imVCature_pic1);
		//btnCapture1=(Button)findViewById(R.id.btnCapture1);
		/*
		 * btnCapture1.setOnClickListener(new OnClickListener() {
		 * 
		 * @Override public void onClick(View v) {
		 * 
		 * Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
		 * File file1 = new
		 * File(Environment.getExternalStorageDirectory()+File.separator +
		 * "img.jpg"); intent.putExtra(MediaStore.EXTRA_OUTPUT,
		 * Uri.fromFile(file1)); startActivityForResult(intent, 3); } });
		 */

	}

	private void initializeControls3() {
		//imVCature_pic_user=(ImageView)findViewById(R.id.imVCature_picUser);
		//btnCaptureUser=(Button)findViewById(R.id.btnCaptureUser);
		/*
		 * btnCaptureUser.setOnClickListener(new OnClickListener() {
		 * 
		 * @Override public void onClick(View v) {
		 * 
		 * Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
		 * File file1 = new
		 * File(Environment.getExternalStorageDirectory()+File.separator +
		 * "img.jpg"); intent.putExtra(MediaStore.EXTRA_OUTPUT,
		 * Uri.fromFile(file1)); startActivityForResult(intent, 5); } });
		 */

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		//if request code is same we pass as argument in startActivityForResult

		if (requestCode == 91 && resultCode == Activity.RESULT_OK) {
			String countryCode = data.getStringExtra(CountryCodeActivity1.RESULT_CONTRYCODE);
			Toast.makeText(this, "You selected countrycode: " + countryCode, Toast.LENGTH_LONG).show();

			ph1Code = countryCode;
			phone1Code.setText("+" + countryCode);

		}
		if (requestCode == 92 && resultCode == Activity.RESULT_OK) {
			String countryCode = data.getStringExtra(CountryCodeActivity1.RESULT_CONTRYCODE);
			Toast.makeText(this, "You selected countrycode: " + countryCode, Toast.LENGTH_LONG).show();

			ph2Code = countryCode;
			phone2Code.setText("+" + countryCode);

		}
		if (requestCode == 93 && resultCode == Activity.RESULT_OK) {
			String countryCode = data.getStringExtra(CountryCodeActivity1.RESULT_CONTRYCODE);
			Toast.makeText(this, "You selected countrycode: " + countryCode, Toast.LENGTH_LONG).show();

			ph3Code = countryCode;
			phone3Code.setText("+" + countryCode);

		}
		if (requestCode == 71 && resultCode == Activity.RESULT_OK) {
			String phoneCode = data.getStringExtra(ContactListActivityfetch.RESULT_PHONECODE);
			//Toast.makeText(this, "You selected PhoneNo: " + phoneCode, Toast.LENGTH_LONG).show();

			userPhoneNum.setText("" + phoneCode);

		}

		if (requestCode == 1) {
			//create instance of File with same name we created before to get image from storage
			File file = new File(Environment.getExternalStorageDirectory() + File.separator + "img.jpg");
			//Crop the captured image using an other intent
			try {
				/* the user's device may not support cropping */
				cropCapturedImage(Uri.fromFile(file));
			} catch (ActivityNotFoundException aNFE) {
				//display an error message if user device doesn't support
				String errorMessage = "Sorry - your device doesn't support the crop action!";
				Toast toast = Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT);
				toast.show();
			}
		}

		if (requestCode == 3) {
			//create instance of File with same name we created before to get image from storage
			File file = new File(Environment.getExternalStorageDirectory() + File.separator + "img.jpg");
			Toast.makeText(getApplicationContext(), "From 2nd photo", Toast.LENGTH_LONG).show();
			//Crop the captured image using an other intent
			try {
				/* the user's device may not support cropping */
				cropCapturedImage1(Uri.fromFile(file));

			} catch (ActivityNotFoundException aNFE) {
				//display an error message if user device doesn't support
				String errorMessage = "Sorry - your device doesn't support the crop action!";
				Toast toast = Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT);
				toast.show();
			}
		}

		if (requestCode == 5) {
			//create instance of File with same name we created before to get image from storage
			File file = new File(Environment.getExternalStorageDirectory() + File.separator + "img.jpg");
			Toast.makeText(getApplicationContext(), "From Usr photo", Toast.LENGTH_LONG).show();
			//Crop the captured image using an other intent
			try {
				/* the user's device may not support cropping */
				cropCapturedImage2(Uri.fromFile(file));

			} catch (ActivityNotFoundException aNFE) {
				//display an error message if user device doesn't support
				String errorMessage = "Sorry - your device doesn't support the crop action!";
				Toast toast = Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT);
				toast.show();
			}
		}

		if (requestCode == 2) {
			//Create an instance of bundle and get the returned data
			Bundle extras = data.getExtras();
			//get the cropped bitmap from extras
			Bitmap thePic = extras.getParcelable("data");
			//set image bitmap to image view
			//imVCature_pic.setImageBitmap(thePic);
		}

		if (requestCode == 4) {
			//Create an instance of bundle and get the returned data
			Bundle extras = data.getExtras();
			//get the cropped bitmap from extras
			Bitmap thePic = extras.getParcelable("data");
			//set image bitmap to image view
			//imVCature_pic1.setImageBitmap(thePic);
		}

		if (requestCode == 6) {
			//Create an instance of bundle and get the returned data
			Bundle extras = data.getExtras();
			//get the cropped bitmap from extras
			Bitmap thePic = extras.getParcelable("data");
			//set image bitmap to image view
			//imVCature_pic_user.setImageBitmap(thePic);
		}

	}

	//create helping method cropCapturedImage(Uri picUri)
	public void cropCapturedImage(Uri picUri) {
		//call the standard crop action intent 
		Intent cropIntent = new Intent("com.android.camera.action.CROP");
		//indicate image type and Uri of image
		cropIntent.setDataAndType(picUri, "image/*");
		//set crop properties
		cropIntent.putExtra("crop", "true");
		//indicate aspect of desired crop
		cropIntent.putExtra("aspectX", 1);
		cropIntent.putExtra("aspectY", 1);
		//indicate output X and Y
		cropIntent.putExtra("outputX", 256);
		cropIntent.putExtra("outputY", 256);
		//retrieve data on return
		cropIntent.putExtra("return-data", true);
		//start the activity - we handle returning in onActivityResult
		startActivityForResult(cropIntent, 2);
	}

	public void cropCapturedImage1(Uri picUri1) {
		//call the standard crop action intent 
		Intent cropIntent1 = new Intent("com.android.camera.action.CROP");
		//indicate image type and Uri of image
		cropIntent1.setDataAndType(picUri1, "image/*");
		//set crop properties
		cropIntent1.putExtra("crop", "true");
		//indicate aspect of desired crop
		cropIntent1.putExtra("aspectX", 1);
		cropIntent1.putExtra("aspectY", 1);
		//indicate output X and Y
		cropIntent1.putExtra("outputX", 256);
		cropIntent1.putExtra("outputY", 256);
		//retrieve data on return
		cropIntent1.putExtra("return-data", true);
		//start the activity - we handle returning in onActivityResult
		startActivityForResult(cropIntent1, 4);
	}

	public void cropCapturedImage2(Uri picUri2) {
		//call the standard crop action intent 
		Intent cropIntent2 = new Intent("com.android.camera.action.CROP");
		//indicate image type and Uri of image
		cropIntent2.setDataAndType(picUri2, "image/*");
		//set crop properties
		cropIntent2.putExtra("crop", "true");
		//indicate aspect of desired crop
		cropIntent2.putExtra("aspectX", 1);
		cropIntent2.putExtra("aspectY", 1);
		//indicate output X and Y
		cropIntent2.putExtra("outputX", 256);
		cropIntent2.putExtra("outputY", 256);
		//retrieve data on return
		cropIntent2.putExtra("return-data", true);
		//start the activity - we handle returning in onActivityResult
		startActivityForResult(cropIntent2, 6);
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case DATE_PICKER_ID:

			// open datepicker dialog. 
			// set date picker for current date 
			// add pickerListener listner to date picker
			return new DatePickerDialog(this, pickerListener, year, month, day);
		}
		return null;
	}

	private DatePickerDialog.OnDateSetListener pickerListener = new DatePickerDialog.OnDateSetListener() {

		// when dialog box is closed, below method will be called.
		@Override
		public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay) {

			year = selectedYear;
			month = selectedMonth;
			day = selectedDay;

			// Show selected date 
			/*
			 * Output.setText(new StringBuilder().append(month + 1)
			 * .append("-").append(day).append("-").append(year) .append(" "));
			 */

			StringBuilder superStringBuilder = new StringBuilder()
			// Month is 0 based, just add 1
					/*
					 * .append(month + 1).append("-").append(day).append("-")
					 * .append(year).append(" ");
					 */

					.append(day).append("/").append(month + 1).append("/").append(year).append(" ");

			output = superStringBuilder.toString();

			dob.setText(output, TextView.BufferType.EDITABLE);

		}
	};

	//=======Image Encoding------

	/*
	 * public static String encodeTobase64(Bitmap image) { Bitmap immagex=image;
	 * ByteArrayOutputStream baos = new ByteArrayOutputStream();
	 * immagex.compress(Bitmap.CompressFormat.JPEG, 100, baos); byte[] b =
	 * baos.toByteArray(); String imageEncoded =
	 * Base64.encodeToString(b,Base64.DEFAULT);
	 * 
	 * // Log.e("LOOK", imageEncoded); return imageEncoded; }
	 */
	//===End of Encoding===
	//===Image Decoding====

	/*
	 * public static Bitmap decodeBase64(String imageCodeJson) { byte[]
	 * decodedString = Base64.decode(imageCodeJson, Base64.DEFAULT); Bitmap
	 * decodedByte = BitmapFactory.decodeByteArray(decodedString, 0,
	 * decodedString.length); return (decodedByte) ; }
	 * 
	 * //===End of Decoding======
	 */

	@Override
	public void onBackPressed() {

		SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
		editor.putString("UserName", userName);
		editor.putString("UserPhoneNumber", userPhone);
		editor.putString("dob", dateOfBirth);
		editor.putString("sex", sex);
		editor.putString("city", cityName);
		editor.putString("state", state);
		editor.putString("pin", pinNum);
		editor.putString("saathi1CallName", saathiName1);
		editor.putString("saathi2CallName", saathiName2);
		editor.putString("saathi1emailID", emailID1);
		editor.putString("saathi2emailID", emailID2);
		editor.putString("saathi1Call", con1);
		editor.putString("saathi2Call", con2);

		editor.putString("sathi_Image1", decodeSaathi1);
		editor.putString("sathi_Image2", decodeSaathi2);
		editor.putString("geoLocation", geoLocation);
		editor.commit();
		finish();
		overridePendingTransition(0, R.anim.activity_close_translate);

	}

	private class GetUserDetails extends AsyncTask<Void, Integer, String> {

		@Override
		protected void onPreExecute() {
			// setting progress bar to zero
			//progressBar.setProgress(0);
			//Caption = txtCaption.getText().toString();
			progress.show();
			super.onPreExecute();
		}

		@Override
		protected String doInBackground(Void... params) {
			// TODO Auto-generated method stub
			return getUser();
		}

		@SuppressWarnings("deprecation")
		private String getUser() {

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
			System.out.println("Json Sarted.... ");
			try {
				JSONArray jArray2 = new JSONArray(str2);
				json2 = jArray2.getJSONObject(0);
				code1 = json2.getString("code");
				System.out.println("Code: " + code1);
			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}

			//---------------------------------------------
			//fetch data from json api
			if (code1.equals("200")) {

				JSONObject json1 = null;
				String str = "";
				HttpResponse response;
				HttpClient myClient = new DefaultHttpClient(); //http://seva60plus.co.in/seva60PlusAndroidAPI/register
				HttpPost myConnection = new HttpPost("http://seva60plus.co.in/seva60PlusAndroidAPI/register/checkUserExists/" + subId);
				//  HttpPost myConnection = new HttpPost("http://seva60plus.co.in/seva60PlusAndroidAPI/register/checkUserExists/"+subId);

				try {
					response = myClient.execute(myConnection);
					str = EntityUtils.toString(response.getEntity(), "UTF-8");

				} catch (ClientProtocolException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				System.out.println("Json Sarted.... ");
				try {
					System.out.println("Try.. ");
					//JSONArray jArray = new JSONArray(str);
					JSONObject json = new JSONObject(str);
					messege = json.getString("msg");

					//messege = "User does not exists with this sim no.";
					if (messege.equals("User does not exists with this sim no.")) {

						msgToast = "0";
						Toast.makeText(getApplicationContext(), "Does Not Exists", Toast.LENGTH_LONG).show();
					} else {

						JSONArray jArray = json.getJSONArray("details");
						json1 = jArray.getJSONObject(0);

						userName = json1.getString("name");
						userPhone = json1.getString("phone");
						dateOfBirth = json1.getString("dob");
						sex = json1.getString("gender");

						cityName = json1.getString("city");
						state = json1.getString("state");
						pinNum = "";
						saathiName1 = json1.getString("sathi1_name");
						saathiName2 = json1.getString("sathi2_name");
						emailID1 = json1.getString("sathi1_email");
						emailID2 = json1.getString("sathi2_email");
						con1 = json1.getString("sathi1_contact_no");
						con2 = json1.getString("sathi2_contact_no");

						decodeUser = json1.getString("avatar");
						decodeSaathi1 = json1.getString("sathi1_image");
						decodeSaathi2 = json1.getString("sathi2_image");

						geoLocation = json1.getString("is_geo_enabled");
						System.out.println("Message :: " + messege);
						System.out.println("Data Getting.. ");
					}
					//  Toast.makeText(getApplicationContext(), sex, Toast.LENGTH_LONG).show();

				} catch (JSONException e) {
					progress.dismiss();

					e.printStackTrace();
					eMsg = e.toString();
					System.out.println("Data Getting Faild.. ");
				}

			} else {
				System.out.println("Seetiing Block error");
			}
			return eMsg;
		}

		@Override
		protected void onPostExecute(String result) {
			//Log.e(TAG, "Response from server: " + result);

			progress.dismiss();
			// showing the server response in an alert dialog
			//showAlert(result);

			//---------Decodeing Started---------------
			if (code1.equals("200")) {

				/*
				 * userImage = decodeBase64(decodeUser); saathi1Image =
				 * decodeBase64(decodeSaathi1); saathi2Image =
				 * decodeBase64(decodeSaathi2);
				 */

				//---------End of Decodeing---------

				//----  Set Data Into Text Fileds---------------

				if (messege.equals("User does not exists with this sim no.")) {

					msgToast = "0";

				} else {

					msgToast = "1";

					name.setText(userName);
					userPhoneNum.setText(userPhone);
					/*
					 * if(con_phone.equals("")){
					 * userPhoneNum.setText(userPhone); } else{
					 * userPhoneNum.setText(con_phone); }
					 */
					dob.setText(dateOfBirth);

					city.setText(cityName);
					editState.setText(state);

					sathi1Name.setText(saathiName1);
					email1.setText(emailID1);
					sathi2Name.setText(saathiName2);
					email2.setText(emailID2);
					saathi1_contact.setText(con1);
					saathi2_contact.setText(con2);

					System.out.println("---Gender...");

					if (sex.equals("M")) {
						Male.setChecked(true);
					} else {
						Female.setChecked(true);
					}

					if (geoLocation.equals("0")) {
						check_agree.setChecked(false);
					} else {
						check_agree.setChecked(true);
					}

				}
			} else {
				System.out.println("Seetiing Block error");
			}

			//---end of set data---

			super.onPostExecute(result);
		}

	}

	

	
	//-----------UNUSED STUB
	
//	private void checkNotNull(Object reference, String name) {
//		if (reference == null) {
//			throw new NullPointerException(getString(R.string.error_config, name));
//		}
//	}
	
	
	//	private final BroadcastReceiver mHandleMessageReceiver = new BroadcastReceiver() {
	//		@Override
	//		public void onReceive(Context context, Intent intent) {
	//			String newMessage = intent.getExtras().getString(EXTRA_MESSAGE);
	//			//  mDisplay.append(newMessage + "\n");
	//		}
	//	};
	
}