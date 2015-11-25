package com.seva60plus.hum.utilities;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.seva60plus.hum.R;
import com.seva60plus.hum.activity.MenuLay;
import com.seva60plus.hum.activity.Registration;
import com.seva60plus.hum.customview.NoInternetPage;
import com.seva60plus.hum.util.ConnectionDetector;
import com.seva60plus.hum.utilities.phonecontact.ContactListActivity;
import com.seva60plus.hum.utilities.weather.WeatherNewMain;
 
public class Utilities extends Activity {

	public static final String MY_PREFS_NAME = "MyPrefsFile";
	
	RelativeLayout heartrate,calendar,calculator,cameraBtn,contact,musicplayer,videoplayer,weather;
	ImageView backBtn,menuIcon;
	RelativeLayout backSetup;
	ComponentName cn;
	private Camera camera = null;
    private boolean isFlashOn;
    private boolean hasFlash;
    Parameters params;
    RelativeLayout btnSwitch;
    
  //---by Dibyendu
  	 // flag for Internet connection status
      Boolean isInternetPresent = false;
       
      // Connection detector class
      ConnectionDetector cd;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.utilities);
		
		cd = new ConnectionDetector(getApplicationContext());// by Dibyendu
		
		 btnSwitch = (RelativeLayout) findViewById(R.id.tourch_btn);
		  hasFlash = getApplicationContext().getPackageManager()
	                .hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
		
		
		  LinearLayout banner=(LinearLayout)findViewById(R.id.footerLay);
	        
			 banner.setOnClickListener(new View.OnClickListener() {
			        @Override
			        public void onClick(View view) {
			            // Take action.
			       /* 	Intent myIntent = new Intent(Utilities.this, AdBanner.class);
			        	myIntent.putExtra("banner_value", "2");
			        	startActivity(myIntent);
			        	*/
			        /*	Intent i = new Intent(Intent.ACTION_VIEW, 
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
	        	Intent i = new Intent(Utilities.this,Registration.class);
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
				Intent intObj = new Intent(Utilities.this,MenuLay.class);
				startActivity(intObj);
				overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
				
				//finish();
			}
		});
		
		

		heartrate = (RelativeLayout)findViewById(R.id.heartbeat_btn);
		heartrate.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				getCamera();
				
				if(camera!=null){
		            camera.stopPreview();
		            camera.setPreviewCallback(null);

		            camera.release();
		            camera = null;
		     
				Intent i = new Intent(Utilities.this,HeartRateInstraction.class);
				startActivity(i);
				
				//finish();
				}	
			}
		});
		
		
		 // get the camera
       // getCamera();
         
        // displaying button image
       // toggleButtonImage();
         
         
        // Switch button click event to toggle flash on/off
        btnSwitch.setOnClickListener(new View.OnClickListener() {
 
            @Override
            public void onClick(View v) {
            	
            	System.out.println("----Toarch Clik--");
            	
            	 // get the camera
                getCamera();
                 
                // displaying button image
                toggleButtonImage();
            	
            	if(hasFlash){
            		
            		if (isFlashOn) {
                    // turn off flash
                    turnOffFlash();
            		} else {
                    // turn on flash
                    turnOnFlash();
            		}
            		
            }else{
        		System.out.println("!!---Flash : "+hasFlash);
        		Toast.makeText(getApplicationContext(), "Sorry Flash is not available", Toast.LENGTH_SHORT).show();
        	}
                
            }
        });
		
        
        weather = (RelativeLayout)findViewById(R.id.weather_btn);
        weather.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				 	 isInternetPresent = cd.isConnectingToInternet();
				 
                // check for Internet status
            if (isInternetPresent) {
                    // Internet Connection is Present
            // Take action.
            
        	Intent i = new Intent(Utilities.this,WeatherNewMain.class);
        	startActivity(i);
        	overridePendingTransition(0, 0);
        	
        	//finish();
            }
            else {
                // Internet connection is not present
            	Intent i = new Intent(Utilities.this,NoInternetPage.class);
            	startActivity(i);
				overridePendingTransition(0, 0);
            	
            }
				//Toast.makeText(getApplicationContext(), "Coming Soon!", Toast.LENGTH_SHORT).show();
			}
		});
		
		calendar = (RelativeLayout)findViewById(R.id.calender_btn);
		calendar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i = new Intent();

				//Froyo or greater (mind you I just tested this on CM7 and the less than froyo one worked so it depends on the phone...)
				cn = new ComponentName("com.google.android.calendar", "com.android.calendar.LaunchActivity");

				//less than Froyo
			//	cn = new ComponentName("com.android.calendar", "com.android.calendar.LaunchActivity");

				i.setComponent(cn);
				startActivity(i);
			}
		});
		
		calculator = (RelativeLayout)findViewById(R.id.calculator_btn);
		calculator.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				   Intent i = new Intent();
				    i.setClassName("com.android.calculator2", "com.android.calculator2.Calculator");
				    startActivity(i);
			}
		});
		
		
		cameraBtn = (RelativeLayout)findViewById(R.id.camera_btn);
		cameraBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				//Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
				//startActivity(intent);
				Toast.makeText(getApplicationContext(), "Coming Soon!", Toast.LENGTH_SHORT).show();
			}
		});
		
		
		contact = (RelativeLayout)findViewById(R.id.contact_btn);
		contact.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				/* Intent i = new Intent(Intent.ACTION_INSERT_OR_EDIT);
		         i.setType(ContactsContract.Contacts.CONTENT_ITEM_TYPE);   

		         startActivity(i);*/
				Intent i = new Intent(Utilities.this,ContactListActivity.class);
		         i.setType(ContactsContract.Contacts.CONTENT_ITEM_TYPE);   

		         startActivity(i);
				
			}
		});
		
		
		musicplayer = (RelativeLayout)findViewById(R.id.musicplayer_btn);
		musicplayer.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent("android.intent.action.MUSIC_PLAYER");
				startActivity(intent);
			}
		});
		
		videoplayer = (RelativeLayout)findViewById(R.id.videoplayer_btn);
		videoplayer.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Toast.makeText(getApplicationContext(), "Coming soon", Toast.LENGTH_SHORT).show();
			}
		});
		
		
		
		
		
		

		
	}
	
	
	// Get the camera
    private void getCamera() {
        if (camera == null) {
            try {
                camera = Camera.open();
                params = camera.getParameters();
            } catch (RuntimeException e) {
                Log.e("Camera Error. Failed to Open. Error: ", e.getMessage());
            }
        }
    }
    
 
	
	 private void toggleButtonImage(){
	        if(isFlashOn){
	            btnSwitch.setBackgroundResource(R.drawable.tourch_on);
	        }else{
	            btnSwitch.setBackgroundResource(R.drawable.tourch);
	        }
	    }
	
	 // Turning On flash
    private void turnOnFlash() {
        if (!isFlashOn) {
            if (camera == null || params == null) {
                return;
            }
            // play sound
         //   playSound();
             
            params = camera.getParameters();
            params.setFlashMode(Parameters.FLASH_MODE_TORCH);
            camera.setParameters(params);
            camera.startPreview();
            isFlashOn = true;
             
            // changing button/switch image
            toggleButtonImage();
        }
 
    }
 
 
    // Turning Off flash
    private void turnOffFlash() {
        if (isFlashOn) {
            if (camera == null || params == null) {
                return;
            }
            // play sound
           // playSound();
             
            params = camera.getParameters();
            params.setFlashMode(Parameters.FLASH_MODE_OFF);
            camera.setParameters(params);
            camera.stopPreview();
            isFlashOn = false;
             
            // changing button/switch image
            toggleButtonImage();
        }
    }
	
}
