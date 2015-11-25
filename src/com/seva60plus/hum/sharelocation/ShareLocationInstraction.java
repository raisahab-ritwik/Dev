package com.seva60plus.hum.sharelocation;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.seva60plus.hum.R;
import com.seva60plus.hum.R.anim;
import com.seva60plus.hum.R.id;
import com.seva60plus.hum.R.layout;
import com.seva60plus.hum.activity.MenuLay;
import com.seva60plus.hum.activity.Registration;
import com.seva60plus.hum.util.ConnectionDetector;





import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


public class ShareLocationInstraction extends Activity {
	
	Button okBtn;
	TextView headerTitleText;
	ImageView homeBtn;
	RelativeLayout backBtn;
	
	GoogleMap mMap;
	Marker mMarker;
	LocationManager lm;
	double lat, lng;

	//---by Dibyendu
		 // flag for Internet connection status
	    Boolean isInternetPresent = false;
	     
	    // Connection detector class
	    ConnectionDetector cd;
	    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.share_location_instruction);
		
		cd = new ConnectionDetector(getApplicationContext());// by Dibyendu
		
		
	
		
		
		headerTitleText=(TextView)findViewById(R.id.header_title);
        headerTitleText.setText("SHARE LOCATION");        
        Typeface font = Typeface.createFromAsset(getAssets(), "openSansBold.ttf");
        headerTitleText.setTypeface(font);
        
        backBtn=(RelativeLayout)findViewById(R.id.back_settings);
        
		backBtn.setOnClickListener(new View.OnClickListener() {
	        @Override
	        public void onClick(View view) {
	        	
	       /* 	 isInternetPresent = cd.isConnectingToInternet();
				 
	                // check for Internet status
	            if (isInternetPresent) {
	                    // Internet Connection is Present
	             */   	
	            // Take action.
	        	Intent i = new Intent(ShareLocationInstraction.this,Registration.class);
	        	startActivity(i);
	        	overridePendingTransition(0, 0);
	        	finish();
	     /*   }
            else {
                // Internet connection is not present
            	Intent i = new Intent(ShareLocationInstraction.this,NoInternetPage.class);
            	startActivity(i);
				overridePendingTransition(0, 0);
            	
            }*/
	        	
	        }
	    });
		
		homeBtn=(ImageView)findViewById(R.id.menu_icon);
        
		 homeBtn.setOnClickListener(new View.OnClickListener() {
		        @Override
		        public void onClick(View view) {
		            // Take action.
		        	Intent myIntent = new Intent(ShareLocationInstraction.this, MenuLay.class);
		        	startActivity(myIntent);
		        	overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
		        	
		        }
		    });
		
		okBtn=(Button)findViewById(R.id.okPressBtn);
		okBtn.setOnClickListener(new View.OnClickListener() {
	        @Override
	        public void onClick(View view) {
	            // Take action.
	        	
	        	Intent i = new Intent(ShareLocationInstraction.this,SmsLocation.class);
	        	startActivity(i);
	        	overridePendingTransition(0, 0);
	        	
	        	//finish();
	        }
	    });
		
		
		
		
		
		
	}

	
	
	
} 
