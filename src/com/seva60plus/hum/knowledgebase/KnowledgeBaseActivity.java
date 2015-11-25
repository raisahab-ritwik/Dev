package com.seva60plus.hum.knowledgebase;



import com.seva60plus.hum.R;
import com.seva60plus.hum.R.anim;
import com.seva60plus.hum.R.id;
import com.seva60plus.hum.R.layout;
import com.seva60plus.hum.activity.MenuLay;
import com.seva60plus.hum.activity.Registration;
import com.seva60plus.hum.util.ConnectionDetector;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;



 
public class KnowledgeBaseActivity extends Activity {

	
	ImageView backBtn,menuIcon;
	RelativeLayout backSetup;
	RelativeLayout option1,option2,option3,option4;
   
	 // flag for Internet connection status
    Boolean isInternetPresent = false;
     
    // Connection detector class
    ConnectionDetector cd;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.knowledge_base_layout);
		
		
		cd = new ConnectionDetector(getApplicationContext());// by Dibyendu
		
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
	        	
	       /* 	 isInternetPresent = cd.isConnectingToInternet();
				 
	                // check for Internet status
	            if (isInternetPresent) {
	            */        // Internet Connection is Present	
	            // Take action.
	        	Intent i = new Intent(KnowledgeBaseActivity.this,Registration.class);
	        	startActivity(i);
	        	overridePendingTransition(0, 0);
	        	finish();
	        	
          /*  }
            else {
                // Internet connection is not present
            	Intent i = new Intent(KnowledgeBase.this,NoInternetPage.class);
            	startActivity(i);
				overridePendingTransition(0, 0);
            	
            }*/
	        	
	        }
	    });
		
		menuIcon=(ImageView)findViewById(R.id.menu_icon);
        
		menuIcon.setOnClickListener(new View.OnClickListener() {
	        @Override
	        public void onClick(View view) {
	            // Take action.
	        	Intent i = new Intent(KnowledgeBaseActivity.this,MenuLay.class);
	        	startActivity(i);
	        	overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
	        	finish();
	        	
	        }
	    });
		
		 LinearLayout banner=(LinearLayout)findViewById(R.id.footerLay);
	        
		 banner.setOnClickListener(new View.OnClickListener() {
		        @Override
		        public void onClick(View view) {
		            // Take action.
		        /*	Intent myIntent = new Intent(KnowledgeBase.this, AdBanner.class);
		        	myIntent.putExtra("banner_value", "1");
		        	startActivity(myIntent);
		        	*/
		        /*	Intent i = new Intent(Intent.ACTION_VIEW, 
						       Uri.parse("http://www.ethnikyarn.com"));
						startActivity(i);
		        	*/
		        }
		    });
		
		 option1 = (RelativeLayout)findViewById(R.id.option1);
			option1.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent i = new Intent(KnowledgeBaseActivity.this,KnowledgeBaseVideo.class);
					startActivity(i);
					//Toast.makeText(getApplicationContext(), "Coming Soon!", Toast.LENGTH_SHORT).show();
				}
			});
			
			option2 = (RelativeLayout)findViewById(R.id.option2);
			option2.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					
					
					
					Toast.makeText(getApplicationContext(), "Coming Soon!", Toast.LENGTH_SHORT).show();
				}
			});
			option3 = (RelativeLayout)findViewById(R.id.option3);
			option3.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Toast.makeText(getApplicationContext(), "Coming Soon!", Toast.LENGTH_SHORT).show();
				}
			});
		 

	}
	
	
}
