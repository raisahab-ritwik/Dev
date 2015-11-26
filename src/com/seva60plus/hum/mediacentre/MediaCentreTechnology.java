package com.seva60plus.hum.mediacentre;



import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.seva60plus.hum.R;
import com.seva60plus.hum.activity.MenuLay;
import com.seva60plus.hum.activity.Registration;
import com.seva60plus.hum.util.ConnectionDetector;



 
public class MediaCentreTechnology extends Activity {

	
	ImageView backBtn,menuIcon;
	RelativeLayout backSetup;
	Button whatsapp,fb,twitter;
	RelativeLayout option1,option2,option3,option4;
   
    

	//---by Dibyendu
	 // flag for Internet connection status
    Boolean isInternetPresent = false;
     
    // Connection detector class
    ConnectionDetector cd;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.media_centre_internet);
		
		
		cd = new ConnectionDetector(getApplicationContext());// by Dibyendu
		
		whatsapp = (Button)findViewById(R.id.whatsapp_btn);
        fb = (Button)findViewById(R.id.facebook_btn);
        twitter = (Button)findViewById(R.id.twitter_btn);
        
        
        
        whatsapp.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				shareAppLinkViaWhatsApp();
			}
		});
        
        fb.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				shareAppLinkViaFacebook();
			}
		});
        
        twitter.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				shareAppLinkViaTwitter();
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
	        	
	            // Take action.
	        	Intent i = new Intent(MediaCentreTechnology.this,Registration.class);
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
	        	Intent i = new Intent(MediaCentreTechnology.this,MenuLay.class);
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
		
		 option1 = (RelativeLayout)findViewById(R.id.rl_hum_training);
			option1.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent i = new Intent(MediaCentreTechnology.this,YouTubeMainActivity.class);
					i.putExtra("videocode", "oIMTM168BK8");
					startActivity(i);
					//Toast.makeText(getApplicationContext(), "Coming Soon!", Toast.LENGTH_SHORT).show();
				}
			});
			
			option2 = (RelativeLayout)findViewById(R.id.rl_doctor_speaks);
			option2.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					
					Toast.makeText(getApplicationContext(), "Coming Soon!", Toast.LENGTH_SHORT).show();
				}
			});
			option3 = (RelativeLayout)findViewById(R.id.rl_financial_advisory);
			option3.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Toast.makeText(getApplicationContext(), "Coming Soon!", Toast.LENGTH_SHORT).show();
				}
			});
		 

	}
	
	 private void shareAppLinkViaFacebook() {
	       /* String urlToShare = "seva60plus.co.in";

	        try {
	            Intent intent1 = new Intent();
	            intent1.setClassName("com.facebook.katana", "com.facebook.katana.activity.composer.ImplicitShareIntentHandler");
	            intent1.setAction("android.intent.action.SEND");
	            intent1.setType("text/plain");
	            intent1.putExtra("android.intent.extra.TEXT", urlToShare);
	            startActivity(intent1);
	        } catch (Exception e) {
	            // If we failed (not native FB app installed), try share through SEND
	            Intent intent = new Intent(Intent.ACTION_SEND);
	            intent.setType("text/plain");
	            String sharerUrl = "https://www.facebook.com/sharer/sharer.php?u=" + urlToShare;
	            //String sharerUrl = "https://www.facebook.com/sharer/sharer.php?u=http://wrctechnologies.com/";
	            intent = new Intent(Intent.ACTION_VIEW, Uri.parse(sharerUrl));
	            startActivity(intent);
	        }*/
		 
		 Intent i = new Intent(Intent.ACTION_VIEW, 
			       Uri.parse("http://www.facebook.com/Seva60Plus"));
			startActivity(i);
		 
	    }
	    
	    
	    private void shareAppLinkViaTwitter() {
	        String urlToShare = "Please spread the word : Seva60Plus HUM Download Link : https://play.google.com/store/apps/details?id=com.seva60plus.hum";

	        try {
	            Intent intent1 = new Intent();
	            intent1.setClassName("com.twitter.android", "com.twitter.android.PostActivity");
	            intent1.setAction("android.intent.action.SEND");
	            intent1.setType("text/plain");
	            intent1.putExtra("android.intent.extra.TEXT", urlToShare);
	            startActivity(intent1);
	        } catch (Exception e) {
	            // If we failed (not native FB app installed), try share through SEND
	            Intent intent = new Intent(Intent.ACTION_SEND);
	            intent.setType("text/plain");
	            String sharerUrl = "https://twitter.com/intent/tweet?text=Please spread the word : Seva60Plus HUM";
	            //String sharerUrl = "https://www.facebook.com/sharer/sharer.php?u=http://wrctechnologies.com/";
	            intent = new Intent(Intent.ACTION_VIEW, Uri.parse(sharerUrl));
	            startActivity(intent);
	        }

	}
	    
	    private void shareAppLinkViaWhatsApp() {
	    	Intent whatsappIntent = new Intent(Intent.ACTION_SEND);
	        whatsappIntent.setType("text/plain");
	        whatsappIntent.setPackage("com.whatsapp");
	        whatsappIntent.putExtra(Intent.EXTRA_TEXT, "Please spread the word : Seva60Plus HUM Download Link : https://play.google.com/store/apps/details?id=com.seva60plus.hum");
	        try {
	            startActivity(whatsappIntent);
	        } catch (android.content.ActivityNotFoundException ex) {
	            Toast.makeText(getApplicationContext(), "Whatsapp have not been installed.", Toast.LENGTH_SHORT).show();
	        	//ToastHelper.MakeShortText("Whatsapp have not been installed.");
	        }
	        }
	    
	    
}
