package com.seva60plus.hum.customview;

import com.seva60plus.hum.R;
import com.seva60plus.hum.R.anim;
import com.seva60plus.hum.R.id;
import com.seva60plus.hum.R.layout;
import com.seva60plus.hum.util.ConnectionDetector;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class NoInternetPage extends Activity {
	
	//---by Dibyendu
		 // flag for Internet connection status
	    Boolean isInternetPresent = false;
	     
	    // Connection detector class
	    ConnectionDetector cd;
	
	   @Override
	   protected void onCreate(Bundle savedInstanceState) {
	      super.onCreate(savedInstanceState);
	      setContentView(R.layout.no_internet_page);
	      overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);
	      
	      cd = new ConnectionDetector(getApplicationContext());// by Dibyendu
	      
	      Button checkBtn = (Button)findViewById(R.id.check_internet);
	      checkBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				 isInternetPresent = cd.isConnectingToInternet();
				 
	                // check for Internet status
	                if (isInternetPresent) {
	                    // Internet Connection is Present
	                	finish();
	                	overridePendingTransition(R.anim.slide_in_down, R.anim.slide_out_down);
	                	
	                }
	                else{
	                	//Internet Connection NOT Present
	                	
	                	
	                }
				
			}
		});
	    
	      Button checkBtnCancel = (Button)findViewById(R.id.check_internet_cancel);  
	      checkBtnCancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
				overridePendingTransition(R.anim.slide_in_down, R.anim.slide_out_down);
			}
		});
	      
	   }
}
