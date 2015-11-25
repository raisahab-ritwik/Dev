package com.seva60plus.hum.utilities.weather;

import com.seva60plus.hum.R;
import com.seva60plus.hum.activity.MenuLay;
import com.seva60plus.hum.activity.Registration;
import com.seva60plus.hum.util.ConnectionDetector;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class WeatherLocationActivity extends Activity implements OnClickListener {
	String greg = "locationActivity";

	Activity locationActivity = this;
	private AutoCompleteTextView tvEnterSity;
	private EditText lat;
	private EditText lon;
	Intent intent = new Intent();
	
	//---by Dibyendu
		 // flag for Internet connection status
	    Boolean isInternetPresent = false;
	     
	    // Connection detector class
	    ConnectionDetector cd;
	    

		ImageView backBtn,menuIcon;
		RelativeLayout backSetup;

	public void onCreate(Bundle savedInstanceState) {
		WeatherMyLog.d(greg, "onCreate");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.weather_activity_location);

		cd = new ConnectionDetector(getApplicationContext());// by Dibyendu
		
		 LinearLayout banner=(LinearLayout)findViewById(R.id.footerLay);
	        
 		 banner.setOnClickListener(new View.OnClickListener() {
 		        @Override
 		        public void onClick(View view) {
 		            // Take action.
 		       /* 	Intent myIntent = new Intent(getActivity(), AdBanner.class);
 		        	myIntent.putExtra("banner_value", "2");
 		        	startActivity(myIntent);
 		        	*/
 		        	/*Intent i = new Intent(Intent.ACTION_VIEW, 
 						       Uri.parse("http://www.homers.in"));
 						startActivity(i);
 		        	*/
 		        }
 		    });
 		 
 		  
 		backBtn=(ImageView)findViewById(R.id.back);
         
 		backBtn.setOnClickListener(new View.OnClickListener() {
 	        @Override
 	        public void onClick(View view) {
 	            // Take action.
 	        	
 	        	
 	        	finish();
 	        	
 	        }
 	    });
 		backSetup=(RelativeLayout)findViewById(R.id.back_settings);
 	   
 	 	backSetup.setOnClickListener(new View.OnClickListener() {
 	       @Override
 	       public void onClick(View view) {
 	       /*	 isInternetPresent = cd.isConnectingToInternet();
 	 			 
 	               // check for Internet status
 	           if (isInternetPresent) {
 	            */       // Internet Connection is Present
 	           // Take action.
 	       	Intent i = new Intent(getApplicationContext(),Registration.class);
 	       	startActivity(i);
 	       	overridePendingTransition(R.anim.slide_in_right, R.anim.slide_leave_right);
 	       	//finish();
 	        /*   }
 	           else {
 	               // Internet connection is not present
 	           	Intent i = new Intent(Utilities.this,NoInternetPage.class);
 	           	startActivity(i);
 	 				overridePendingTransition(0, 0);
 	           	
 	           }*/
 	       	
 	       }
 	   });
 	 	
 	 	menuIcon = (ImageView)findViewById(R.id.menu_icon);
 	 	menuIcon.setOnClickListener(new OnClickListener() {
 	 		
 	 		@Override
 	 		public void onClick(View arg0) {
 	 			// TODO Auto-generated method stub
 	 			Intent intObj = new Intent(getApplicationContext(),MenuLay.class);
 	 			startActivity(intObj);
 	 			overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
 	 			
 	 			//finish();
 	 		}
 	 	});
 	 	
 	 	
		
		tvEnterSity = (AutoCompleteTextView) findViewById(R.id.autoCompleteCity);
		
	/*	tvEnterSity.setOnEditorActionListener(new EditText.OnEditorActionListener() {

             @Override
             public boolean onEditorAction(TextView v, int actionId,
                     KeyEvent event) {
                 
            	 String str2 = tvEnterSity.getText().toString();
     			String str = str2.replace(" ", "");
     			Intent intent = new Intent(WeatherLocationActivity.this,WeatherFirstActivity.class);
     			WeatherMyLog.d(greg, "read city " + str);
     			intent.putExtra("city", str);
     			intent.putExtra("city2", str2);
     			startActivity(intent);
     			//setResult(1, intent);
				return true;
				
             }

			
         });*/
		
		
		
		String[] cities = getResources().getStringArray(R.array.city);

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_dropdown_item_1line, cities);
		tvEnterSity.setAdapter(adapter);

		Button btnSearchByCity = (Button) findViewById(R.id.btnSearchByCity);
		btnSearchByCity.setOnClickListener(this);

		lat = (EditText) findViewById(R.id.editLat);
		lon = (EditText) findViewById(R.id.editLon);

		InputFilter filter = new InputFilter() {
			@Override
			public CharSequence filter(CharSequence source, int start, int end,
					Spanned dest, int dstart, int dend) {
				Toast toast = Toast.makeText(locationActivity,
						"����������� ���������� ���������",
						Toast.LENGTH_SHORT);
				toast.setGravity(Gravity.TOP, 0, 0); 
				for (int i = start; i < end; i++) {
					if (!isEnglish(source.charAt(i))) {
						toast.show();
						return "";
					}
				}
				return null;
			}

			private boolean isEnglish(char charAt) {
				String validationString = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM, ";
				if (validationString.indexOf(charAt) == -1)
					return false;
				else
					return true;
			}
		};
		tvEnterSity.setFilters(new InputFilter[] { filter });

		Button btnSearchByCrd = (Button) findViewById(R.id.btnSearchByCrd);
		btnSearchByCrd.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		//Intent intent = new Intent();
		switch (v.getId()) {
		case R.id.btnSearchByCity:
			String str2 = tvEnterSity.getText().toString();
			String str = str2.replace(" ", "");
			
			WeatherMyLog.d(greg, "read city " + str);
			intent.putExtra("city", str);
			intent.putExtra("city2", str2);
			setResult(1, intent);
			break;
		case R.id.btnSearchByCrd:
			String lat = this.lat.getText().toString();
			String lon = this.lon.getText().toString();
			WeatherMyLog.d(greg, "read lat " + lat + "lon " + lon);
			intent.putExtra("lat", lat);
			intent.putExtra("lon", lon);
			setResult(2, intent);
			break;
		}
		finish();
	}
}
