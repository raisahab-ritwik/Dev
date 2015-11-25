package com.seva60plus.hum.unused;

import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.seva60plus.hum.R;
import com.seva60plus.hum.R.id;
import com.seva60plus.hum.R.layout;
import com.seva60plus.hum.activity.Registration;
import com.seva60plus.hum.sathi.Sathicall;
import com.seva60plus.hum.util.ConnectionDetector;


public class MainActivity extends Activity {
	
	Button setup,sathiCall,medicalSeva,shareLocation,emergency;
	TextView headerTitleText;
	RelativeLayout backBtn;

	//---by Dibyendu
		 // flag for Internet connection status
	    Boolean isInternetPresent = false;
	     
	    // Connection detector class
	    ConnectionDetector cd;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		cd = new ConnectionDetector(getApplicationContext());// by Dibyendu
		
		headerTitleText=(TextView)findViewById(R.id.header_title);
        headerTitleText.setText("HOME");        
        Typeface font = Typeface.createFromAsset(getAssets(), "openSansBold.ttf");
        headerTitleText.setTypeface(font);
        
        backBtn=(RelativeLayout)findViewById(R.id.back_settings);
        backBtn.setVisibility(View.INVISIBLE);
		
		setup=(Button)findViewById(R.id.setUpBtn);
		setup.setOnClickListener(new View.OnClickListener() {
	        @Override
	        public void onClick(View view) {
	        	
	      /*  	 isInternetPresent = cd.isConnectingToInternet();
				 
	                // check for Internet status
	             if (isInternetPresent) {
	                    // Internet Connection is Present
	          */  // Take action.
	        	Intent myIntent = new Intent(MainActivity.this, Registration.class);
	        	startActivity(myIntent);
	       /*      }
	        	else {
                    // Internet connection is not present
                	Intent i = new Intent(MainActivity.this,NoInternetPage.class);
                	startActivity(i);
    				overridePendingTransition(0, 0);
                	
                }*/
	        	
	        }
	    });
		
		sathiCall=(Button)findViewById(R.id.satheCallBtn);
		sathiCall.setOnClickListener(new View.OnClickListener() {
	        @Override
	        public void onClick(View view) {
	            // Take action.
	        	Intent myIntent = new Intent(MainActivity.this, Sathicall.class);
	        	startActivity(myIntent);
	        	
	        	//Toast.makeText(getApplicationContext(), "Coming Soon", Toast.LENGTH_SHORT).show();
	        	
	        }
	    });
		medicalSeva=(Button)findViewById(R.id.medicalSevaBtn);
		medicalSeva.setOnClickListener(new View.OnClickListener() {
	        @Override
	        public void onClick(View view) {
	            // Take action.
	        	Intent myIntent = new Intent(MainActivity.this, MedicalSeva.class);
	        	startActivity(myIntent);
	        	
	        	
	        	
	        }
	    });
		
		shareLocation=(Button)findViewById(R.id.shareBtn);
		shareLocation.setOnClickListener(new View.OnClickListener() {
	        @Override
	        public void onClick(View view) {
	            // Take action.
	        	//Intent myIntent = new Intent(MainActivity.this, HeartRateMonitor.class);
	        	//startActivity(myIntent);
	        	Intent myIntent = new Intent(MainActivity.this, Map.class);
	        	startActivity(myIntent);
	        	
	        	
	        }
	    });
		
		
		emergency=(Button)findViewById(R.id.emergencyBtn);
		emergency.setOnClickListener(new View.OnClickListener() {
	        @Override
	        public void onClick(View view) {
	            // Take action.
	        	//Intent myIntent = new Intent(MainActivity.this, HeartRateMonitor.class);
	        	//startActivity(myIntent);
	        	Intent myIntent = new Intent(MainActivity.this, Emergency.class);
	        	startActivity(myIntent);
	        	
	        	
	        }
	    });
		
		
		
	}

	
}
