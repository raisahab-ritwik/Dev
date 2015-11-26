package com.seva60plus.hum.utilities.weather;




import com.seva60plus.hum.R;
import com.seva60plus.hum.activity.MenuLay;
import com.seva60plus.hum.activity.Registration;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class WeatherNewChangeLocation extends Activity{

	public static final String MY_PREFS_NAME = "MyPrefsFile";
	
	EditText editText;
	

	ImageView backBtn,menuIcon;
	RelativeLayout backSetup;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.weather_new_activity_location);

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
		 
		  
		backBtn=(ImageView)findViewById(R.id.iv_back);
        
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
	 	
	 	
		
		 editText = (EditText)findViewById(R.id.autoCompleteCity);
		Button searchBtn = (Button)findViewById(R.id.btnSearchByCity);
		
		searchBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				String city = editText.getText().toString();
				
				SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
				editor.putString("shareWCity", city.replace(" ", ""));
				editor.commit();
				
				Intent intObj = new Intent(WeatherNewChangeLocation.this,WeatherNewMain.class);
				startActivity(intObj);
				finish();
				
			}
		});
		
	}


	
}
