package com.seva60plus.hum.sharelocation;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnShowListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.v4.app.FragmentActivity;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.seva60plus.hum.R;
import com.seva60plus.hum.R.anim;
import com.seva60plus.hum.R.drawable;
import com.seva60plus.hum.R.id;
import com.seva60plus.hum.R.layout;
import com.seva60plus.hum.activity.MenuLay;
import com.seva60plus.hum.activity.Registration;

public class MapShareActivity extends FragmentActivity {
	GoogleMap mMap;
	Marker mMarker;
	LocationManager lm;
	double lat, lng;
	double latitude,longitude;
	ImageView backBtn,homeBtn;
	RelativeLayout backSetup;
	TextView headerTitleText;
	Button shareLocation;
	AppLocationService appLocationService;
	TextView tvAddress;
	String locationAddressMessage="";
	String Late = "" ,Lang = "";
	String locationforMarker;
	String call1,call2;
	String name1,name2;
	String LocationMsg = "",AddressMsg = "";
	
	String SMSAddress;
	
	//ProgressDialog progress;
	
	//ProgressDialog progress;
	   
    Dialog progress;
    private AnimationDrawable progressAnimation;
	
	public static final String MY_PREFS_NAME = "MyPrefsFile";
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);
		/*
		progress = new ProgressDialog(MapShareActivity.this,R.style.main_content);
        progress.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
		 progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		 progress.setMessage("Please Wait....");
		*/
		
		//------START------------For Progress Spinner--------------
        
        progress = new Dialog(MapShareActivity.this);
        progress.getWindow().setBackgroundDrawableResource(R.drawable.spinner_dialog_backround);
       
        //Remove the Title
        progress.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
       
        //progress.setTitle("");
       
        //Set the View of the Dialog - Custom
        progress.setContentView(R.layout.custom_progress_dialog2);
       
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
    //-------END-----------For Progress Spinner--------------    ​
        
		
		   SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE); 
	        call1 = prefs.getString("saathi1Call", null);
	        call2 = prefs.getString("saathi2Call", null);
	        name1 = prefs.getString("saathi1CallName", null);
	        name2 = prefs.getString("saathi2CallName", null);
		
		 appLocationService = new AppLocationService(MapShareActivity.this);

		 
		 LinearLayout banner=(LinearLayout)findViewById(R.id.footerLay);
	        
		 banner.setOnClickListener(new View.OnClickListener() {
		        @Override
		        public void onClick(View view) {
		            // Take action.
		        /*	Intent myIntent = new Intent(MapShareActivity.this, AdBanner.class);
		        	myIntent.putExtra("banner_value", "1");
		        	startActivity(myIntent);
		        	*/
		        	Intent i = new Intent(Intent.ACTION_VIEW, 
						       Uri.parse("http://www.ethnikyarn.com"));
						startActivity(i);
		        	
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
		 
			homeBtn=(ImageView)findViewById(R.id.menu_icon);
	        
			 homeBtn.setOnClickListener(new View.OnClickListener() {
			        @Override
			        public void onClick(View view) {
			            // Take action.
			        	Intent myIntent = new Intent(MapShareActivity.this, MenuLay.class);
			        	startActivity(myIntent);
			        	overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
			        	
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
			 
		 shareLocation=(Button)findViewById(R.id.shareLocationBtn);
		        
			 shareLocation.setOnClickListener(new View.OnClickListener() {
			        @Override
			        public void onClick(View view) {
			            // Take action.
                	                
			   /*      Location location = appLocationService
		                        .getLocation(LocationManager.GPS_PROVIDER);

		                //you can hard-code the lat & long if you have issues with getting it
		                //remove the below if-condition and use the following couple of lines
		                //double latitude = 37.422005;
		                //double longitude = -122.084095

		                if (location != null) {
		                     latitude = location.getLatitude();
		                     longitude = location.getLongitude();
		                    LocationAddress locationAddress = new LocationAddress();
		                    locationAddress.getAddressFromLocation(latitude, longitude,
		                    getApplicationContext(), new GeocoderHandler());
		                
		                   
		                   	                    
		                } else {
		                   // showSettingsAlert();
		                	Toast.makeText(getApplicationContext(), "Please enable your GPS Location Service from settings", Toast.LENGTH_LONG).show();
		                }*/
		              
		            //   Toast.makeText(getApplicationContext(), "ON NET Your Location is - \nLat: " + lat + "\nLong: " + lng, Toast.LENGTH_LONG).show();
		
		             //  Toast.makeText(getApplicationContext(), "LocMsg: " + locationAddressMessage, Toast.LENGTH_LONG).show();
		               //Toast.makeText(getApplicationContext(), "LOCATION SHARING", Toast.LENGTH_LONG).show();
			       
			        	Intent i = new Intent(android.content.Intent.ACTION_VIEW);
			            i.putExtra("address", call1+"; "+call2);
			            // here i can send message to emulator 5556,5558,5560
			            // you can change in real device
			            i.putExtra("sms_body", SMSAddress);
			            i.setType("vnd.android-dir/mms-sms");
			            startActivity(i);
			        
			        
			        }
		
			        
			        
			    }); 
		
			 progress.show();
		mMap = ((SupportMapFragment)getSupportFragmentManager()
				.findFragmentById(R.id.map)).getMap();
	}
	
	LocationListener listener = new LocationListener() {
	    public void onLocationChanged(Location loc) {
	    	LatLng coordinate = new LatLng(loc.getLatitude(), loc.getLongitude());
	    	lat = loc.getLatitude();
	    	lng = loc.getLongitude();
	    	
	    	//Toast.makeText(getApplicationContext(), "Lat :: "+lat+"\nLong :: "+lng, Toast.LENGTH_LONG).show();
	    	
            LocationAddress locationAddress = new LocationAddress();
            locationAddress.getAddressFromLocation(lat, lng,
                    getApplicationContext(), new GeocoderHandler());
	    	//locationAddressMessage=locationAddress.toString(); 
	    	
	    	
	    	
	    	
	    	
	    	  
	    	
	    	
	    	
	    }

	    public void onStatusChanged(String provider, int status, Bundle extras) {}
	    public void onProviderEnabled(String provider) {}
	    public void onProviderDisabled(String provider) {}
	};
	
	public void onResume() {
		super.onResume();
		
		lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);

		boolean isNetwork = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
		boolean isGPS = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);

		if(isNetwork) {
			lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000, 10, listener);
			Location loc = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
			if(loc != null) {
				lat = loc.getLatitude();
			    lng = loc.getLongitude();
			    
			  //  Toast.makeText(getApplicationContext(), " NETWrok Your Location is - \nLat: " + lat + "\nLong: " + lng, Toast.LENGTH_LONG).show();
			  /*  Late = Double.toString(lat);
			    Lang = Double.toString(lng);*/
			}
			 
			
		}
		
		if(isGPS) {
			lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 10, listener);
			Location loc = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
			if(loc != null) {
				lat = loc.getLatitude();
			    lng = loc.getLongitude();
			    
			   // Toast.makeText(getApplicationContext(), " GPS Your Location is - \nLat: " + lat + "\nLong: " + lng, Toast.LENGTH_LONG).show();
			  /*  Late = Double.toString(lat);
			    Lang = Double.toString(lng);*/
}
			
		}
	}
	
	public void onPause() {
		super.onPause();
		lm.removeUpdates(listener);
	}

public void showSettingsAlert() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(
                MapShareActivity.this);
        alertDialog.setTitle("SETTINGS");
        alertDialog.setMessage("Enable Location Provider! Go to settings menu?");
        alertDialog.setPositiveButton("Settings",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(
                                Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        MapShareActivity.this.startActivity(intent);
                    }
                });
        //
        alertDialog.setNegativeButton("With Out Internet",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                      //  dialog.cancel();
                    	 Intent intent = new Intent(MapShareActivity.this, SmsLocation.class);
                        // intent.putExtra("msg",locationAddressMessage);
                    	 
                    	 Late = Double.toString(lat);
         			    Lang = Double.toString(lng);
                    	 
                         intent.putExtra("lat", Late);
                         intent.putExtra("lng", Lang);
                         startActivity(intent);
                    	
                    }
                });
        alertDialog.show();
    }
	
	private class GeocoderHandler extends Handler {
        @Override
        public void handleMessage(Message message) {
            String locationAddress;
            switch (message.what) {
                case 1:
                    Bundle bundle = message.getData();
                    locationAddress = bundle.getString("address");
                    break;
                default:
                    locationAddress = null;
            }
          //  tvAddress.setText(locationAddress);
            Late = Double.toString(lat);
			    Lang = Double.toString(lng);
            
           locationAddressMessage = locationAddress.toString();
           locationforMarker = locationAddressMessage;
           
          // Toast.makeText(getApplicationContext(), "LocMsg: " + locationAddressMessage, Toast.LENGTH_LONG).show();
          
           int r1 = locationforMarker.indexOf("A");
           final String markAdd = locationforMarker.replace(locationforMarker.substring(0,r1), " ");
           final String markAdd2 = markAdd.replace("null\n","");
           final String markAdd3 = markAdd2.replace("null","");
           final String markAdd4 = markAdd3.replace("\n","<br>");
           
           String url = "http://maps.google.com/maps?q="+lat+","+lng;
           SMSAddress = markAdd4.replace("<br>","\n")+"\n\n"+"Link: "+url;
           
           System.out.println("LIST ADDRESS : "+markAdd4);
           
           
           
           if(mMarker != null)
	    		mMarker.remove();
	    	
           LatLng cord = new LatLng(lat, lng);
           
	    	mMarker = mMap.addMarker(new MarkerOptions()
	    	.position(new LatLng(lat, lng))
	    	//.title("Lat :: "+lat+"\nLong :: "+lng)
	    	.title("My Location")
	    	.snippet(markAdd4)
	    	.icon(BitmapDescriptorFactory.fromResource(R.drawable.userpin)));
	    	//.snippet(locationAddressMessage));
	    	
	    	//mMarker.showInfoWindow();
	    	mMap.setInfoWindowAdapter(new InfoWindowAdapter() {
				
				@Override
				public View getInfoWindow(Marker arg0) {
					// TODO Auto-generated method stub
					return null;
				}
				
				@Override
				public View getInfoContents(Marker marker) {
					// TODO Auto-generated method stub
					View v = getLayoutInflater().inflate(R.layout.address, null);
					TextView info = (TextView)v.findViewById(R.id.add);
					TextView info2 = (TextView)v.findViewById(R.id.add2);
					info2.setText(Html.fromHtml("<strong><u>"+mMarker.getTitle().toString()+"</u></strong>"));
					info.setText(Html.fromHtml(markAdd4));
					return v;
				}
			}); 
	    	
			mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(cord, 16));
			mMarker.showInfoWindow();
            
			progress.dismiss();
        /*    Intent intent = new Intent(MapShareActivity.this, SmsLocation.class);
            intent.putExtra("msg",locationAddressMessage);
            intent.putExtra("lat", Late);
            intent.putExtra("lng", Lang);
            startActivity(intent);*/
            
          
            
            
            
         /* Intent i = new Intent(android.content.Intent.ACTION_VIEW);
            i.putExtra("address", call1+"; "+call2);
            // here i can send message to emulator 5556,5558,5560
            // you can change in real device
            i.putExtra("sms_body", locationAddressMessage);
            i.setType("vnd.android-dir/mms-sms");
            startActivity(i);*/
           
            
            
        }
    }
	
}
