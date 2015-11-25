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

public class OffersListActivity extends Activity{
	
	RelativeLayout option1,option2,option3,option4,option5,option6;
	
	RelativeLayout backBtn;
	ImageView menuIcon;
	ImageView backBtnSub;
	Button whatsapp,fb,twitter;
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
		setContentView(R.layout.offers_list);
		
		
		cd = new ConnectionDetector(getApplicationContext());// by Dibyendu
		lm = (LocationManager) getSystemService(LOCATION_SERVICE);// by Dibyendu
		
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
		
		
		
		backBtnSub = (ImageView)findViewById(R.id.back);
		backBtnSub.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
				overridePendingTransition(0, 0);
			}
		});
		
		 LinearLayout banner=(LinearLayout)findViewById(R.id.footerLay);
	        
		 banner.setOnClickListener(new View.OnClickListener() {
		        @Override
		        public void onClick(View view) {
		            // Take action.
		        /*	Intent myIntent = new Intent(OffersListActivity.this, AdBanner.class);
		        	myIntent.putExtra("banner_value", "2");
		        	startActivity(myIntent);
		        	*/
		       /* 	Intent i = new Intent(Intent.ACTION_VIEW, 
						       Uri.parse("http://www.homers.in"));
						startActivity(i);
		        	*/
		        }
		    });
        
		
		option1 = (RelativeLayout)findViewById(R.id.option1);
		option1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				/*Intent i = new Intent(OffersListActivity.this,OffersListElderCareActivity.class);
				startActivity(i);*/
				//Toast.makeText(getApplicationContext(), "Coming Soon!", Toast.LENGTH_SHORT).show();
				
				Intent i = new Intent(OffersListActivity.this,OfferElderActivity.class);
				startActivity(i);
			
			}
		});
		
		option2 = (RelativeLayout)findViewById(R.id.option2);
		option2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Intent i = new Intent(OffersListActivity.this,OfferMedicalActivity.class);
				startActivity(i);
			
				
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
		option4 = (RelativeLayout)findViewById(R.id.option4);
		option4.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(OffersListActivity.this,OfferFuneralActivity.class);
				startActivity(i);
				//Toast.makeText(getApplicationContext(), "Coming Soon!", Toast.LENGTH_SHORT).show();
			}
		});
		
		option5 = (RelativeLayout)findViewById(R.id.option5);
		option5.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "Coming Soon!", Toast.LENGTH_SHORT).show();
			}
		});
		
		option6 = (RelativeLayout)findViewById(R.id.option6);
		option6.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Intent i = new Intent(OffersListActivity.this,OffersListFinancialActivity.class);
				startActivity(i);
				//Toast.makeText(getApplicationContext(), "Coming Soon!", Toast.LENGTH_SHORT).show();
			}
		});
		
		menuIcon = (ImageView)findViewById(R.id.menu_icon);
		menuIcon.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intObj = new Intent(OffersListActivity.this,MenuLay.class);
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

	    	        	Intent i = new Intent(OffersListActivity.this,Registration.class);
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