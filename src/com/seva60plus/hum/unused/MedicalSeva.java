package com.seva60plus.hum.unused;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.seva60plus.hum.R;
import com.seva60plus.hum.R.anim;
import com.seva60plus.hum.R.id;
import com.seva60plus.hum.R.layout;
import com.seva60plus.hum.activity.MenuLay;
import com.seva60plus.hum.knowledgebase.ShakeMainActivity;
import com.seva60plus.hum.pillreminder.TakeThePill;
import com.seva60plus.hum.utilities.HeartRateInstraction;


public class MedicalSeva extends Activity {
	
	Button orderMed,heartRate,pillReminder,fallSafe;
	TextView headerTitleText;
	ImageView backBtn,homeBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.medical_seva);
		
		
		
		headerTitleText=(TextView)findViewById(R.id.header_title);
        headerTitleText.setText("MEDICAL SEVA");        
        Typeface font = Typeface.createFromAsset(getAssets(), "openSansBold.ttf");
        headerTitleText.setTypeface(font);
        
        backBtn=(ImageView)findViewById(R.id.back);
        
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
		        	Intent myIntent = new Intent(MedicalSeva.this, MenuLay.class);
		        	startActivity(myIntent);
		        	overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
		        	
		        }
		    });
		
		orderMed=(Button)findViewById(R.id.orderMedBtn);
		orderMed.setOnClickListener(new View.OnClickListener() {
	        @Override
	        public void onClick(View view) {
	            // Take action.
	        	//Intent myIntent = new Intent(MedicalSeva.this, Registration.class);
	        	//startActivity(myIntent);
	        	//Toast.makeText(getApplicationContext(), "Coming Soon", Toast.LENGTH_SHORT).show();
	        	Intent myIntent = new Intent(MedicalSeva.this, OrderMed.class);
	        	startActivity(myIntent);
	        }
	    });
		
		pillReminder=(Button)findViewById(R.id.pillReminderBtn);
		pillReminder.setOnClickListener(new View.OnClickListener() {
	        @Override
	        public void onClick(View view) {
	            // Take action.
	        	Intent myIntent = new Intent(MedicalSeva.this, TakeThePill.class);
	        	startActivity(myIntent);
	        	
	        	//Toast.makeText(getApplicationContext(), "Coming Soon", Toast.LENGTH_SHORT).show();
	        	
	        }
	    });
		heartRate=(Button)findViewById(R.id.monitorHeartBtn);
		heartRate.setOnClickListener(new View.OnClickListener() {
	        @Override
	        public void onClick(View view) {
	            // Take action.
	        	Intent myIntent = new Intent(MedicalSeva.this, HeartRateInstraction.class);
	        	startActivity(myIntent);
	        	
	        	
	        	
	        }
	    });
		
		fallSafe=(Button)findViewById(R.id.safeBtn);
		fallSafe.setOnClickListener(new View.OnClickListener() {
	        @Override
	        public void onClick(View view) {
	            // Take action.
	        	Intent myIntent = new Intent(MedicalSeva.this, ShakeMainActivity.class);
	        	startActivity(myIntent);
	        	
	        	
	        	
	        }
	    });
		
		
		
		
	}

	
}
