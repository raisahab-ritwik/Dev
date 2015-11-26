package com.seva60plus.hum.specialoffers;

import com.seva60plus.hum.R;
import com.seva60plus.hum.R.anim;
import com.seva60plus.hum.R.id;
import com.seva60plus.hum.R.layout;
import com.seva60plus.hum.activity.MenuLay;
import com.seva60plus.hum.activity.Registration;
import com.seva60plus.hum.util.ConnectionDetector;

import android.location.LocationManager;
import android.os.Bundle;
import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class OfferFuneralActivity extends FragmentActivity {
	ViewPager Tab;
	OfferFuneralTabPagerAdapter TabAdapter;
	//ActionBar actionBar;

	
	RelativeLayout backBtn;
	ImageView menuIcon;
	ImageView backBtnSub;
	
	//---by Dibyendu
		 // flag for Internet connection status
	    Boolean isInternetPresent = false;
	     
	    // Connection detector class
	    ConnectionDetector cd;
	    LocationManager lm;
	    
	    //------End
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.offer_funeral_activity);


		cd = new ConnectionDetector(getApplicationContext());// by Dibyendu
		lm = (LocationManager) getSystemService(LOCATION_SERVICE);// by Dibyendu
		
		backBtnSub = (ImageView)findViewById(R.id.iv_back);
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
				Intent intObj = new Intent(OfferFuneralActivity.this,MenuLay.class);
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

	    	        	Intent i = new Intent(OfferFuneralActivity.this,Registration.class);
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

		final Button nextBtn = (Button)findViewById(R.id.button1);
		nextBtn.setVisibility(View.INVISIBLE);
		final Button preBtn = (Button)findViewById(R.id.button2);
		preBtn.setVisibility(View.INVISIBLE);
		
		TabAdapter = new OfferFuneralTabPagerAdapter(getSupportFragmentManager());

		Tab = (ViewPager)findViewById(R.id.pager);
		Tab.setAdapter(TabAdapter);
		 Tab.setOnPageChangeListener(
                new ViewPager.SimpleOnPageChangeListener() {
                    @Override
                    public void onPageSelected(int position) {
                    	
                    	System.out.println("MAIN: "+Tab.getCurrentItem()); 
                    	if(Tab.getCurrentItem() == 0){
        					nextBtn.setBackgroundColor(Color.parseColor("#404041"));
        					preBtn.setBackgroundColor(Color.parseColor("#A0A0A0"));
        				}
        				else if(Tab.getCurrentItem() == 1){
        					nextBtn.setBackgroundColor(Color.parseColor("#404041"));
        					preBtn.setBackgroundColor(Color.parseColor("#404041"));
        				}
        				else if(Tab.getCurrentItem() == 2){
        					nextBtn.setBackgroundColor(Color.parseColor("#A0A0A0"));
        					preBtn.setBackgroundColor(Color.parseColor("#404041"));
        				}
        				else{
        					nextBtn.setBackgroundColor(Color.parseColor("#A0A0A0"));
        					preBtn.setBackgroundColor(Color.parseColor("#A0A0A0"));
        				}
                    	}
                });
		
		
		/*  
        actionBar = getActionBar();
        //Enable Tabs on Action Bar
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        ActionBar.TabListener tabListener = new ActionBar.TabListener(){

			@Override
			public void onTabReselected(android.app.ActionBar.Tab tab,
					FragmentTransaction ft) {
				// TODO Auto-generated method stub

			}

			@Override
			 public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {

	            Tab.setCurrentItem(tab.getPosition());
	        }

			@Override
			public void onTabUnselected(android.app.ActionBar.Tab tab,
					FragmentTransaction ft) {
				// TODO Auto-generated method stub

			}};
			//Add New Tab
			actionBar.addTab(actionBar.newTab().setText("Android").setTabListener(tabListener));
			actionBar.addTab(actionBar.newTab().setText("iOS").setTabListener(tabListener));
			actionBar.addTab(actionBar.newTab().setText("Windows").setTabListener(tabListener));
		 */


		nextBtn.setBackgroundColor(Color.parseColor("#404041"));
		preBtn.setBackgroundColor(Color.parseColor("#A0A0A0"));
		/*   
        if(Tab.getCurrentItem() == 2){
        g	nextBtn.setBackgroundColor(Color.GRAY);
        	//preBtn.setBackgroundColor(Color.GREEN);
        }
        else{
        	nextBtn.setBackgroundColor(Color.GREEN);
        	//preBtn.setBackgroundColor(Color.GRAY);
        }

	      if(Tab.getCurrentItem() == 0){
	    	  preBtn.setBackgroundColor(Color.GRAY);
	        }
	        else{
	        	preBtn.setBackgroundColor(Color.GREEN);
	        }
		 */
		nextBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Tab.setCurrentItem(getItem(+1), true);
				System.out.println("NEXT : "+Tab.getCurrentItem());

				if(Tab.getCurrentItem() == 0){
					nextBtn.setBackgroundColor(Color.parseColor("#404041"));
					preBtn.setBackgroundColor(Color.parseColor("#A0A0A0"));
				}
				else if(Tab.getCurrentItem() == 1){
					nextBtn.setBackgroundColor(Color.parseColor("#404041"));
					preBtn.setBackgroundColor(Color.parseColor("#404041"));
				}
				else if(Tab.getCurrentItem() == 2){
					nextBtn.setBackgroundColor(Color.parseColor("#A0A0A0"));
					preBtn.setBackgroundColor(Color.parseColor("#404041"));
				}
				else{
					nextBtn.setBackgroundColor(Color.parseColor("#A0A0A0"));
					preBtn.setBackgroundColor(Color.parseColor("#A0A0A0"));
				}
			}
		});



		preBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Tab.setCurrentItem(getItem(-1), true);
				System.out.println("PRE : "+Tab.getCurrentItem());

				if(Tab.getCurrentItem() == 2){
					nextBtn.setBackgroundColor(Color.parseColor("#A0A0A0"));
					preBtn.setBackgroundColor(Color.parseColor("#404041"));
				}
				else if(Tab.getCurrentItem() == 1){
					nextBtn.setBackgroundColor(Color.parseColor("#404041"));
					preBtn.setBackgroundColor(Color.parseColor("#404041"));
				}
				else if(Tab.getCurrentItem() == 0){
					nextBtn.setBackgroundColor(Color.parseColor("#404041"));
					preBtn.setBackgroundColor(Color.parseColor("#A0A0A0"));
				}
				else{
					nextBtn.setBackgroundColor(Color.parseColor("#A0A0A0"));
					preBtn.setBackgroundColor(Color.parseColor("#A0A0A0"));
				}
			}
		});
	}


	private int getItem(int i) {
		return Tab.getCurrentItem() + i;
	}

}
