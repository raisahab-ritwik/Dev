package com.seva60plus.hum.knowledgebase;

import java.util.ArrayList;

import com.seva60plus.hum.R;
import com.seva60plus.hum.R.id;
import com.seva60plus.hum.R.layout;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Toast;

public class ShakeMainActivity extends Activity implements SensorEventListener,OnSeekBarChangeListener {
    // For shake motion detection.
	private Sensor accelerometer;
    private SensorManager sensorMgr;
    private long lastUpdate = -1;
    private float x, y, z;
    private float last_x, last_y, last_z;
    private static int SHAKE_THRESHOLD = 800;
    long curTime;
    
    SensorEventListener mySensor;
    
    int pg;
	 SeekBar seekBar1;
	 Button on,off;
 //  private int gt;
	 
	 //----map
		LocationManager lm;
		double lat, lng;
		double latitude,longitude;
		String Late,Lang,msgUrl;
		String call1,call2;
		static int value = 0;
		
		
		public static final String MY_PREFS_NAME = "MyPrefsFile";
		
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.shake_activity_main); // other initializations
	
	SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE); 
    call1 = prefs.getString("saathi1Call", null);
    call2 = prefs.getString("saathi2Call", null);
    //prefThrd = (int) prefs.getLong("prgThrd", prefThrd);
    
    //Toast.makeText(getApplicationContext(), call1, Toast.LENGTH_SHORT).show();
    
    //pg = prefThrd;
	//---Map
	lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
	
	LocationListener listener = new LocationListener() {
	    public void onLocationChanged(Location loc) {
	    	//LatLng coordinate = new LatLng(loc.getLatitude(), loc.getLongitude());
	    	lat = loc.getLatitude();
	    	lng = loc.getLongitude();
	    	
	    	//Toast.makeText(getApplicationContext(), "Lat :: "+lat+"\nLong :: "+lng, Toast.LENGTH_LONG).show();
	    	
           /* LocationAddress locationAddress = new LocationAddress();
            locationAddress.getAddressFromLocation(lat, lng,
                    getApplicationContext(), new GeocoderHandler());
	    	locationAddressMessage=locationAddress.toString(); */
	    	
	    }

	    public void onStatusChanged(String provider, int status, Bundle extras) {}
	    public void onProviderEnabled(String provider) {}
	    public void onProviderDisabled(String provider) {}
	};
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
	
	//Toast.makeText(getApplicationContext(), " GPS Your Location is - \nLat: " + lat + "\nLong: " + lng, Toast.LENGTH_LONG).show();
	Late = Double.toString(lat);
	Lang = Double.toString(lng);
	
	 msgUrl = "https://www.google.co.in/maps/place/" + Late + "," + Lang;
	//---end Map 
	 
	 seekBar1=(SeekBar)findViewById(R.id.seekBar1);  
     seekBar1.setOnSeekBarChangeListener(this); 
     seekBar1.setProgress(value);
     
   /*  on = (Button)findViewById(R.id.btnOn);
     off = (Button)findViewById(R.id.btnOff);
     
     on.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
			on.setVisibility(View.INVISIBLE);
			
			if (sensorMgr.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null) {
				
				accelerometer = sensorMgr.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);	
				sensorMgr.registerListener(mySensor, sensorMgr.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL); 
				Toast.makeText(getApplicationContext(), "Acc Detected..", Toast.LENGTH_SHORT).show();
			}
			else{
				Toast.makeText(getApplicationContext(), "Acc Not Detected..", Toast.LENGTH_SHORT).show();	
				}
			 
		}
	});
	//-----------new
     off.setOnClickListener(new OnClickListener() {
 		
 		@SuppressWarnings("deprecation")
		@Override
 		public void onClick(View v) {
 			// TODO Auto-generated method stub
 			int sensorType = Sensor.TYPE_ACCELEROMETER;
 			sensorMgr.unregisterListener((SensorEventListener) mySensor);
 			off.setVisibility(View.INVISIBLE);
 			on.setVisibility(View.VISIBLE);
 		}
 	});*/
	
     
   //----------  
     	sensorMgr=(SensorManager) getSystemService(SENSOR_SERVICE); 
		
		
	if (sensorMgr.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null) {
	
		accelerometer = sensorMgr.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);	
		sensorMgr.registerListener(this, sensorMgr.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL); 
		//Toast.makeText(getApplicationContext(), "Acc Detected..", Toast.LENGTH_SHORT).show();
	}
	else{
		//Toast.makeText(getApplicationContext(), "Acc Not Detected..", Toast.LENGTH_SHORT).show();	
		}
    
    }
 
    protected void onPause() {
    	/* if (sensorMgr != null) { 
    		 sensorMgr.unregisterListener(this); 
    		 sensorMgr = null; }*/
    	 super.onPause();
    }
 
    
 	@Override
	public void onSensorChanged(SensorEvent event) {
		// TODO Auto-generated method stub
		
 		if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
			  curTime = System.currentTimeMillis(); // only allow one update every 100ms.
		//System.out.println("cur: "); 
 		}
		 if ((curTime - lastUpdate) > 100) { 
			// System.out.println("if_cur: ");
			 long diffTime = (curTime - lastUpdate); 
			 lastUpdate = curTime; 
			 x = event.values[0]; 
			 y = event.values[1]; 
			 z = event.values[2]; 
			 float speed = Math.abs(x+y+z - last_x - last_y - last_z) / diffTime * 10000; 
			// System.out.println("--_cur: ");
			 SHAKE_THRESHOLD = 800 + pg;
			// Toast.makeText(getApplicationContext(), SHAKE_THRESHOLD, Toast.LENGTH_SHORT).show();
			if(SHAKE_THRESHOLD < 800){
				//Toast.makeText(getApplicationContext(), "value too low", Toast.LENGTH_SHORT).show();
			}else{
			 if (speed > SHAKE_THRESHOLD) { 
				 // yes, this is a shake action! Do something about it!
				// System.out.println(".........: "); 
				 System.out.println("..sucess: "+SHAKE_THRESHOLD);
				 
				// Toast.makeText(getApplicationContext(), "Shaking.."+SHAKE_THRESHOLD+ "\n"+"Sending SMS...", Toast.LENGTH_SHORT).show();
				// sendLongSMS1();
	//----Open--- sendLongSMS2();
				 } 
			 last_x = x; 
			 last_y = y;  
			 last_z = z;
			 }
		 }
		 
 		// Toast.makeText(getApplicationContext(), "Shaking..", Toast.LENGTH_SHORT).show(); 
		
	}

	@Override
	public void onAccuracyChanged(Sensor s, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {
		
		// TODO Auto-generated method stub
		 //Toast.makeText(getApplicationContext(),"seekbar progress: "+progress, Toast.LENGTH_SHORT).show(); 
			pg = progress;
			
			value = progress;
			
			
			/*SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
			prefs.getLong("prgThrd", pg);*/
			
			// System.out.println("Level:  "+pg);
			//seekBar1.setProgress(prefThrd);
		
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
		
		
	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
		
	}
	/*
	public void sendLongSMS1() {
	 	
	 	//String shop = getString(R.string.shop_number);
		//Soumika-9874238368;
	     String phoneNumber = call1;
	     String message = msgUrl ;

	     SmsManager smsManager = SmsManager.getDefault();
	     ArrayList<String> parts = smsManager.divideMessage(message); 
	     smsManager.sendMultipartTextMessage(phoneNumber, null, parts, null, null);
	     //Toast.makeText(getApplicationContext(), "Customer Name:"+custName+"\nPhone Number:"+phNumber+"\nMedicine Name:"+medicineName+"\nQuantity:"+quntNumber, Toast.LENGTH_SHORT).show();
	     Toast.makeText(getApplicationContext(), "SMS SENT", Toast.LENGTH_SHORT).show();
	
	
	}*/
/*	
public void sendLongSMS2() {
	 	
	 	//String shop = getString(R.string.shop_number);
		//Soumika-9874238368;
	     String phoneNumber = call2;
	     String message = msgUrl ;

	     SmsManager smsManager = SmsManager.getDefault();
	     ArrayList<String> parts = smsManager.divideMessage(message); 
	     smsManager.sendMultipartTextMessage(phoneNumber, null, parts, null, null);
	     //Toast.makeText(getApplicationContext(), "Customer Name:"+custName+"\nPhone Number:"+phNumber+"\nMedicine Name:"+medicineName+"\nQuantity:"+quntNumber, Toast.LENGTH_SHORT).show();
	     Toast.makeText(getApplicationContext(), "SMS SENT", Toast.LENGTH_SHORT).show();
	 }
    */
}