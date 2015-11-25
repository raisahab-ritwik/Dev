package com.seva60plus.hum.wellbeing;




import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.achartengine.ChartFactory;
import org.achartengine.model.CategorySeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;

import com.seva60plus.hum.R;
import com.seva60plus.hum.R.anim;
import com.seva60plus.hum.R.drawable;
import com.seva60plus.hum.R.id;
import com.seva60plus.hum.R.layout;
import com.seva60plus.hum.activity.MenuLay;
import com.seva60plus.hum.activity.Registration;
import com.seva60plus.hum.util.ConnectionDetector;
import com.seva60plus.hum.util.Contact;
import com.seva60plus.hum.util.DatabaseHandler;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnShowListener;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.TableRow.LayoutParams;


 
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
 
public class WelbingStaActivityMood extends Activity {
 
	
	ImageView backBtn,menuIcon;
	RelativeLayout backSetup;
	Button whatsapp,fb,twitter;
	 //---by Dibyendu
	 // flag for Internet connection status
    Boolean isInternetPresent = false;
     
    // Connection detector class
    ConnectionDetector cd;
    
    
    Button tab_task,tab_sta;
    RelativeLayout tab_water,tab_execise,tab_mood;
    
    
    Button tab_water_btn, tab_execise_btn, tab_mood_btn;
   
    TableLayout table_layout;
    
  //---------------DB

    DatabaseHandler db;
 	Calendar c;
 	String setTime = "00:00:01";
 	String dDay,dMonth,dYear;
 	Thread myThread = null;
 	
 	//---------------
 
 	RelativeLayout sync_btn;
 	
 	int mGood = 0, mOkay = 0, mSad = 0, mNA = 0;
 	
 	Dialog progress;
	private AnimationDrawable progressAnimation;

	String eMsg = "";
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welbing_sta_activity_mood);
        
        cd = new ConnectionDetector(getApplicationContext());// by Dibyendu
        
        db = new DatabaseHandler(this);
        c = Calendar.getInstance();
        
        
      //------START------------For Progress Spinner--------------

  		progress = new Dialog(WelbingStaActivityMood.this);
  		progress.getWindow().setBackgroundDrawableResource(R.drawable.spinner_dialog_backround);

  		//Remove the Title
  		progress.getWindow().requestFeature(Window.FEATURE_NO_TITLE);

  		//progress.setTitle("");

  		//Set the View of the Dialog - Custom
  		progress.setContentView(R.layout.custom_progress_dialog);

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

        
        LinearLayout banner=(LinearLayout)findViewById(R.id.footerLay);
        
 		 banner.setOnClickListener(new View.OnClickListener() {
 		        @Override
 		        public void onClick(View view) {
 		            // Take action.
 		       /* 	Intent myIntent = new Intent(getActivity(), AdBanner.class);
 		        	myIntent.putExtra("banner_value", "2");
 		        	startActivity(myIntent);
 		        	*/
 		       /* 	Intent i = new Intent(Intent.ACTION_VIEW, 
 						       Uri.parse("http://www.ethnikyarn.com"));
 						startActivity(i);
 		        	*/
 		        }
 		    });
 	  
 		 
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
 	
 	menuIcon = (ImageView)findViewById(R.id.menu_icon);
 	menuIcon.setOnClickListener(new OnClickListener() {
 		
 		@Override
 		public void onClick(View arg0) {
 			// TODO Auto-generated method stub
 			
 			Intent intObj = new Intent(getApplicationContext(),MenuLay.class);
 			startActivity(intObj);
 			overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
 			
 			//finish();
 		}
 	});
 	
 	
 	
 	
 	tab_task = (Button)findViewById(R.id.task_btn);
 	tab_task.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
			Intent intObj = new Intent(WelbingStaActivityMood.this,WelbingActivityMood.class);
			startActivity(intObj);
			overridePendingTransition(0, 0);
			finish();
			
		}
	});
 	tab_sta = (Button)findViewById(R.id.static_btn);
 	tab_sta.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
			Intent intObj = new Intent(WelbingStaActivityMood.this,WelbingStaActivityMood.class);
			startActivity(intObj);
			overridePendingTransition(0, 0);
			finish();
			
		}
	});
 	
 	tab_water = (RelativeLayout)findViewById(R.id.wel_water_btn);
 	tab_water.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
			Intent intObj = new Intent(WelbingStaActivityMood.this,WelbingStaActivityWater.class);
			startActivity(intObj);
			overridePendingTransition(0, 0);
			finish();
			
			
			
		}
	});
 	tab_water_btn = (Button)findViewById(R.id.btn_water_btn);
 	tab_water_btn.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
			Intent intObj = new Intent(WelbingStaActivityMood.this,WelbingStaActivityWater.class);
			startActivity(intObj);
			overridePendingTransition(0, 0);
			finish();
			
			
			
		}
	});
 	
 	tab_execise = (RelativeLayout)findViewById(R.id.wel_ex_btn);
 	tab_execise.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
			Intent intObj = new Intent(WelbingStaActivityMood.this,WelbingStaActivityExercise.class);
			startActivity(intObj);
			overridePendingTransition(0, 0);
			finish();
			
		}
	});
 	tab_execise_btn = (Button)findViewById(R.id.btn_ex_btn);
 	tab_execise_btn.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
			Intent intObj = new Intent(WelbingStaActivityMood.this,WelbingStaActivityExercise.class);
			startActivity(intObj);
			overridePendingTransition(0, 0);
			finish();
			
		}
	});
 	
 	tab_mood = (RelativeLayout)findViewById(R.id.wel_mood_btn);
 	tab_mood.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
			Intent intObj = new Intent(WelbingStaActivityMood.this,WelbingStaActivityMood.class);
			startActivity(intObj);
			overridePendingTransition(0, 0);
			finish();
			
		}
	});
 	tab_mood_btn = (Button)findViewById(R.id.btn_mood_btn);
 	tab_mood_btn.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
			Intent intObj = new Intent(WelbingStaActivityMood.this,WelbingStaActivityMood.class);
			startActivity(intObj);
			overridePendingTransition(0, 0);
			finish();
			
		}
	});
 	
 	sync_btn = (RelativeLayout)findViewById(R.id.refreshLay);
 	sync_btn.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
		//picahrt
			

			float[] datas = new float[4];
	        datas[0] = mGood;
	        datas[1] = mOkay;
	        datas[2] = mSad;
	        datas[3] = mNA;
	        
	        String[] labels = new String[4];
	        labels[0] = "GOOD";
	        labels[1] = "OKAY";
	        labels[2] = "SAD";
	        labels[3] = "NA";
	        
			
			Intent intObj = new Intent(WelbingStaActivityMood.this,Sample.class);
			intObj.putExtra("numbers", datas);
			intObj.putExtra("lables", labels);
			startActivity(intObj);
			
			//openChart();
			//new GetDataOnPiChart().execute();
			//MainActivity1.getStatusData();
			
		}
	});
 	
 	table_layout = (TableLayout) findViewById(R.id.tableLayout1);
	 BuildTable();
	 
	 
        Runnable myRunnableThread = new CountDownRunner();
 	    myThread= new Thread(myRunnableThread);
 	 //   myThread.start();
        
        List<Contact> contacts = db.getAllContacts();
		
		for (Contact cn : contacts) {
            String log = cn.getDate() + "--" + cn.getTime()+ "--" + cn.getResult()+ "--" + cn.getStaus()+ "--" + cn.getMode()+"\n";
                // Writing Contacts to log
         
        Log.d("Name: ", log);
        
        }
      
		
      
    }
 
   
    
    
   
    
    
    
    
//----------Database----------------------
    
    public void inserData(String result, String status, String mode) {
		
    	
    	 Date dt = new Date();
         int hours = dt.getHours();
         int minutes = dt.getMinutes();
         int seconds = dt.getSeconds();
         
      
         int year = c.get(Calendar.YEAR);
         int month = c.get(Calendar.MONTH)+1;
         int day = c.get(Calendar.DAY_OF_MONTH);
         
        int mlen = String.valueOf(month).length();
        int dlen = String.valueOf(day).length();
        
        if(mlen == 1){
     	   dMonth = "0"+month;
        }else{
     	   dMonth = String.valueOf(month);
        }
        
        if(dlen == 1){
     	   dDay = "0"+day;
        }else{
     	   dDay = String.valueOf(day);
        }
         
        String toDate =dDay+"."+dMonth+"."+year;
        String curTime = hours + ":" + minutes + ":" + seconds;
    	
    	db.addContact(new Contact(toDate, curTime, result, status, mode));
    	Toast.makeText(getApplicationContext(), "Thank you", Toast.LENGTH_SHORT).show();
    	
    	
    	 List<Contact> contacts = db.getAllContacts();
 		
 		for (Contact cn : contacts) {
             String log = cn.getDate() + "--" + cn.getTime()+ "--" + cn.getResult()+ "--" + cn.getStaus()+ "--" + cn.getMode()+"\n";
                 // Writing Contacts to log
          
         Log.d("Name: ", log);
         
         }
       
	}
    
    public void doWork() {
	    runOnUiThread(new Runnable() {
	        public void run() {
	            try{
	              //  TextView txtCurrentTime= (TextView)findViewById(R.id.text_view);
	                    Date dt = new Date();
	                    int hours = dt.getHours();
	                    int minutes = dt.getMinutes();
	                    int seconds = dt.getSeconds();
	                    
	                 
	                    int year = c.get(Calendar.YEAR);
	                    int month = c.get(Calendar.MONTH)+1;
	                    int day = c.get(Calendar.DAY_OF_MONTH);
	                    
	                   int mlen = String.valueOf(month).length();
	                   int dlen = String.valueOf(day).length();
	                   
	                   if(mlen == 1){
	                	   dMonth = "0"+month;
	                   }else{
	                	   dMonth = String.valueOf(month);
	                   }
	                   
	                   if(dlen == 1){
	                	   dDay = "0"+day;
	                   }else{
	                	   dDay = String.valueOf(day);
	                   }
	                    
	                   String toDate =dDay+"."+dMonth+"."+year;
	                   String curTime = hours + ":" + minutes + ":" + seconds;
	                    
	                  //  txtCurrentTime.setText(curTime+"  DATE : "+toDate);
	                    
	                   // String getTime = txtCurrentTime.getText().toString();
	                   
	                    
	                    if(curTime.equals("0:1:0")){
	                    	
	                    	System.out.println("*********** "+curTime);
	                    	
	                    	db.addContact(new Contact(toDate, curTime, "NA", "2", "Ex"));
	                    	Toast.makeText(getApplicationContext(), "Thank you", Toast.LENGTH_SHORT).show();
	                    	
	                    }
	                    else{
	                    	
	                    	System.out.println("*********** "+curTime);
	                    }
	                    
	                    
	            }catch (Exception e) {}
	        }
	    });
	}
	
	
	class CountDownRunner implements Runnable{
	    // @Override
	    public void run() {
	            while(!Thread.currentThread().isInterrupted()){
	                try {
	                doWork();
	                    Thread.sleep(1000); // Pause of 1 Second
	                } catch (InterruptedException e) {
	                        Thread.currentThread().interrupt();
	                }catch(Exception e){
	                }
	            }
	    }
	}
   
	
	private void BuildTable() {

		
		TableRow row1 = new TableRow(this);
		row1.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT));
		for (int j = 0; j < 5; j++) {

			if(j==0){
				
				TextView tv = new TextView(this);
				tv.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
						LayoutParams.WRAP_CONTENT));
				tv.setBackgroundResource(R.drawable.cell_shape_row);
				tv.setGravity(Gravity.CENTER);
				tv.setTextSize(18);
				tv.setTypeface(tv.getTypeface(), Typeface.NORMAL);
				tv.setTextColor(Color.parseColor("#FFFFFF"));
				tv.setPadding(0, 10, 0, 10);

				tv.setText("Date");

				row1.addView(tv);
			}
			else if(j==1){
				
				
				TextView tv = new TextView(this);
				tv.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
						LayoutParams.WRAP_CONTENT));
				tv.setBackgroundResource(R.drawable.cell_shape_row);
				tv.setGravity(Gravity.CENTER);
				tv.setTextSize(18);
				tv.setTypeface(tv.getTypeface(), Typeface.NORMAL);
				tv.setTextColor(Color.parseColor("#FFFFFF"));
				tv.setPadding(0, 10, 0, 10);

				tv.setText("Time");

				row1.addView(tv);
			}
			else if(j==2){
				
				
				TextView tv = new TextView(this);
				tv.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
						LayoutParams.WRAP_CONTENT));
				tv.setBackgroundResource(R.drawable.cell_shape_row);
				tv.setGravity(Gravity.CENTER);
				tv.setTextSize(18);
				tv.setTypeface(tv.getTypeface(), Typeface.NORMAL);
				tv.setTextColor(Color.parseColor("#FFFFFF"));
				tv.setPadding(0, 10, 0, 10);

				tv.setText("Response");

				row1.addView(tv);
			}
			/*
			else if(j==3){
				
				TextView tv = new TextView(this);
				tv.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
						LayoutParams.WRAP_CONTENT));
				tv.setBackgroundResource(R.drawable.cell_shape);
				tv.setGravity(Gravity.CENTER);
				tv.setTextSize(18);
				tv.setPadding(0, 5, 0, 5);

				tv.setText(cn.getStaus());

				row.addView(tv);
			}
			else if(j==4){
				
				TextView tv = new TextView(this);
				tv.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
						LayoutParams.WRAP_CONTENT));
				tv.setBackgroundResource(R.drawable.cell_shape);
				tv.setGravity(Gravity.CENTER);
				tv.setTextSize(18);
				tv.setPadding(0, 5, 0, 5);

				tv.setText(cn.getMode());

				row.addView(tv);
			}
			*/
			else{
				
				}
			
			}
		
		
        table_layout.addView(row1);
        
      
	//---=======	
		
		List<Contact> contacts = db.getAllContactsNeed("mood");
		
		System.out.println("LENGTH "+contacts.size());
		
		for (Contact cn : contacts) {
            String log = cn.getDate() + "--" + cn.getTime()+ "--" + cn.getResult()+ "--" + cn.getStaus()+ "--" + cn.getMode()+"\n";
                // Writing Contacts to log
           // detailsText.append(log);
        Log.d("Name: ", log+":"+contacts);
        
        TableRow row = new TableRow(this);
		row.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT));
     
		for (int j = 0; j < 5; j++) {

			if(j==0){
				
				TextView tv = new TextView(this);
				tv.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
						LayoutParams.WRAP_CONTENT));
				tv.setBackgroundResource(R.drawable.cell_shape);
				tv.setGravity(Gravity.CENTER);
				tv.setTextSize(18);
				tv.setPadding(0, 5, 0, 5);
				tv.setTextColor(Color.parseColor("#295978"));

				tv.setText(cn.getDate());

				row.addView(tv);
			}
			else if(j==1){
				
				TextView tv = new TextView(this);
				tv.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
						LayoutParams.WRAP_CONTENT));
				tv.setBackgroundResource(R.drawable.cell_shape);
				tv.setGravity(Gravity.CENTER);
				tv.setTextSize(18);
				tv.setPadding(0, 5, 0, 5);
				tv.setTextColor(Color.parseColor("#295978"));

				tv.setText(cn.getTime());

				row.addView(tv);
			}
			else if(j==2){
				
				TextView tv = new TextView(this);
				tv.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
						LayoutParams.WRAP_CONTENT));
				tv.setBackgroundResource(R.drawable.cell_shape);
				tv.setGravity(Gravity.CENTER);
				tv.setTextSize(18);
				tv.setPadding(0, 5, 0, 5);
				tv.setTextColor(Color.parseColor("#295978"));

				tv.setText(cn.getResult());
				
				//----For Pichart dibyendu 03.09.15
				if(cn.getResult().contains("good")){
					mGood = mGood + 1;
				}
				else if(cn.getResult().contains("okay")){
					mOkay = mOkay + 1;
				}
				else if(cn.getResult().contains("sad")){
					mSad = mSad + 1;
				}
				else{
					mNA = mNA + 1;
				}
				
				//----------end------
				
				row.addView(tv);
			}
			
			/*else if(j==3){
				
				TextView tv = new TextView(this);
				tv.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
						LayoutParams.WRAP_CONTENT));
				tv.setBackgroundResource(R.drawable.cell_shape);
				tv.setGravity(Gravity.CENTER);
				tv.setTextSize(18);
				tv.setPadding(0, 5, 0, 5);

				tv.setText(cn.getStaus());

				row.addView(tv);
			}
			else if(j==4){
				
				TextView tv = new TextView(this);
				tv.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
						LayoutParams.WRAP_CONTENT));
				tv.setBackgroundResource(R.drawable.cell_shape);
				tv.setGravity(Gravity.CENTER);
				tv.setTextSize(18);
				tv.setPadding(0, 5, 0, 5);

				tv.setText(cn.getMode());

				row.addView(tv);
			}
			*/
			else{
				
				}
			
			}
		
		
        table_layout.addView(row);
        
        }

	}
	
	 private void shareAppLinkViaFacebook() {
	     /*   String urlToShare = "seva60plus.co.in";

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
	        } */
		 
		 
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
	    
	    
	    
	//-----------Pi Chart----------
	    private void openChart(){    	
	    	
	    	System.out.println("Pi Chart: \n"+mGood+"\n"+mOkay+"\n"+mSad);
	    	
	    	// Pie Chart Section Names
	    	String[] code = new String[] {
	    			"GOOD", "OKAY", "SAD", "NA" 
	    	};    	
	    	
	    	// Pie Chart Section Value
	    	double[] distribution = {mGood,mOkay,mSad,mNA} ;
	    	
	    	// Color of each Pie Chart Sections
	    	int[] colors = { Color.GREEN, Color.YELLOW, Color.RED, Color.BLACK
	    					 };
	    	
	    	// Instantiating CategorySeries to plot Pie Chart    	
	    	CategorySeries distributionSeries = new CategorySeries(" Android version distribution as on October 1, 2012");
	    	for(int i=0 ;i < distribution.length;i++){
	    		// Adding a slice with its values and name to the Pie Chart
	    		distributionSeries.add(code[i], distribution[i]);
	    	}   
	    	
	    	// Instantiating a renderer for the Pie Chart
	    	DefaultRenderer defaultRenderer  = new DefaultRenderer();    	
	    	for(int i = 0 ;i<distribution.length;i++){    		
	    		SimpleSeriesRenderer seriesRenderer = new SimpleSeriesRenderer();    	
	    		seriesRenderer.setColor(colors[i]);
	    		seriesRenderer.setDisplayChartValues(true);
	    		// Adding a renderer for a slice
	    		defaultRenderer.addSeriesRenderer(seriesRenderer);
	    	}
	    	
	    	defaultRenderer.setChartTitle("");
	    	defaultRenderer.setChartTitleTextSize(60);
	    	defaultRenderer.setZoomButtonsVisible(true);    	    		
	    		
	    	// Creating an intent to plot bar chart using dataset and multipleRenderer    	
	    	Intent intent = ChartFactory.getPieChartIntent(getBaseContext(), distributionSeries , defaultRenderer, "AChartEnginePieChartDemo");    	
	    	
	    	// Start Activity
	    	startActivity(intent);
	    	
	    }
	    
	    
	    //-----------Show on Pi Chart
		private class GetDataOnPiChart extends AsyncTask<Void, Integer, String> {


			@Override
			protected void onPreExecute() {
				// setting progress bar to zero
				//progressBar.setProgress(0);
				//Caption = txtCaption.getText().toString();
				progress.show();
				super.onPreExecute();
			}

			@Override
			protected String doInBackground(Void... params) {
				// TODO Auto-generated method stub
				return getDataOnPiChart();
			}	
			@SuppressWarnings("deprecation")
			private String getDataOnPiChart() {
				
				try {
					
					openChart();
					
				} catch (Exception e) {
					// TODO: handle exception
				eMsg = e.toString();
				}
				
				return eMsg;
			}
			@Override
			protected void onPostExecute(String result) {
				//Log.e(TAG, "Response from server: " + result);
			
				progress.dismiss();
				
				super.onPostExecute(result);
			}
		}

	    
}