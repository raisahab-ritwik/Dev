package com.seva60plus.hum.specialoffers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.seva60plus.hum.R;
import com.seva60plus.hum.R.anim;
import com.seva60plus.hum.R.id;
import com.seva60plus.hum.R.layout;
import com.seva60plus.hum.activity.MenuLay;
import com.seva60plus.hum.activity.Registration;
import com.seva60plus.hum.util.ConnectionDetector;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.location.LocationManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.ContactsContract.Data;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ListView;

public class OffersListMedicalActivity extends Activity{
	
	RelativeLayout option1,option2,option3,option4;
	
	RelativeLayout backBtn;
	ImageView menuIcon;
	ImageView backBtnSub;
	
	RelativeLayout visitWebBtn;
	//---by Dibyendu
		 // flag for Internet connection status
	    Boolean isInternetPresent = false;
	     
	    // Connection detector class
	    ConnectionDetector cd;
	    LocationManager lm;
	    
	    //------End
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.offers_medical);
		
		
		cd = new ConnectionDetector(getApplicationContext());// by Dibyendu
		lm = (LocationManager) getSystemService(LOCATION_SERVICE);// by Dibyendu
		
		 LinearLayout banner=(LinearLayout)findViewById(R.id.footerLay);
	        
		 banner.setOnClickListener(new View.OnClickListener() {
		        @Override
		        public void onClick(View view) {
		            // Take action.
		        /*	Intent myIntent = new Intent(OffersListMedicalActivity.this, AdBanner.class);
		        	myIntent.putExtra("banner_value", "1");
		        	startActivity(myIntent);
		        	*/
		        	Intent i = new Intent(Intent.ACTION_VIEW, 
						       Uri.parse("http://www.ethnikyarn.com"));
						startActivity(i);
		        	
		        }
		    });
        
		
		 
		backBtnSub = (ImageView)findViewById(R.id.back);
		backBtnSub.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
				overridePendingTransition(0, 0);
			}
		});
		
	
		menuIcon = (ImageView)findViewById(R.id.menu_icon);
		menuIcon.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intObj = new Intent(OffersListMedicalActivity.this,MenuLay.class);
				startActivity(intObj);
				overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
				
				//finish();
			}
		});
		
		backBtn=(RelativeLayout)findViewById(R.id.back_settings);
        
		backBtn.setOnClickListener(new View.OnClickListener() {
	        @Override
	        public void onClick(View view) {
	            // Take action.
	        	//finish();
	        /*	 isInternetPresent = cd.isConnectingToInternet();
				 
	                // check for Internet status
	                if (isInternetPresent) {
	                    // Internet Connection is Present
	                	*/

	    	        	Intent i = new Intent(OffersListMedicalActivity.this,Registration.class);
	    				startActivity(i);
	    				overridePendingTransition(R.anim.slide_in_right, R.anim.slide_leave_right);
	    				
	           /*     }
	                else {
	                    // Internet connection is not present
	                	Intent i = new Intent(OffersListActivity.this,NoInternetPage.class);
	                	startActivity(i);
	    				overridePendingTransition(0, 0);
	                	
	                }*/
	        	
	        }
	    });
		
		visitWebBtn = (RelativeLayout)findViewById(R.id.visit_btn);
		visitWebBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(Intent.ACTION_VIEW, 
					       Uri.parse("http://www.ethnikyarn.com"));
					startActivity(i);
			}
		});
		
	}
	

		
}