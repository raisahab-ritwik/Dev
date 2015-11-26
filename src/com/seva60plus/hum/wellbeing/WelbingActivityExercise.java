package com.seva60plus.hum.wellbeing;




import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.seva60plus.hum.R;
import com.seva60plus.hum.R.anim;
import com.seva60plus.hum.R.id;
import com.seva60plus.hum.R.layout;
import com.seva60plus.hum.activity.MenuLay;
import com.seva60plus.hum.activity.Registration;
import com.seva60plus.hum.util.ConnectionDetector;
import com.seva60plus.hum.util.Contact;
import com.seva60plus.hum.util.DatabaseHandler;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;


 
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
 
public class WelbingActivityExercise extends Activity {
	private static Context ctx;
	public static final String PREFS_NAME = "PrefsFile";
	public static final String ALARMS_KEY = "alarms_enabled";
	
	
	ImageView backBtn,menuIcon;
	RelativeLayout backSetup;
	
	 //---by Dibyendu
	 // flag for Internet connection status
    Boolean isInternetPresent = false;
     
    // Connection detector class
    ConnectionDetector cd;
    
    
    Button tab_task,tab_sta;
    
    RelativeLayout tab_water,tab_execise,tab_mood;
    RelativeLayout yes_btn, no_btn;
    
    Button tab_water_btn, tab_execise_btn, tab_mood_btn;
    Button yes_btn_text, no_btn_text;
    Button whatsapp,fb,twitter;
  //---------------DB

    DatabaseHandler db;
 	Calendar c;
 	String setTime = "00:00:01";
 	String dDay,dMonth,dYear;
 	Thread myThread = null;
 	
 	//---------------
 
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welbing_activity_exercise);
        ctx=this;
        cd = new ConnectionDetector(getApplicationContext());// by Dibyendu
        
        db = new DatabaseHandler(this);
        c = Calendar.getInstance();
        
        
        LinearLayout banner=(LinearLayout)findViewById(R.id.footerLay);
        
 		 banner.setOnClickListener(new View.OnClickListener() {
 		        @Override
 		        public void onClick(View view) {
 		            // Take action.
 		       /* 	Intent myIntent = new Intent(getActivity(), AdBanner.class);
 		        	myIntent.putExtra("banner_value", "2");
 		        	startActivity(myIntent);
 		        	*/
 		        /*	Intent i = new Intent(Intent.ACTION_VIEW, 
 						       Uri.parse("http://www.homers.in"));
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
 	
 	yes_btn = (RelativeLayout)findViewById(R.id.wel_btn);
 	yes_btn.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
			inserData("no", "2", "exercise");
			
		}
	});
 	yes_btn_text = (Button)findViewById(R.id.btn_wel_btn);
 	yes_btn_text.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
			inserData("no", "2", "exercise");
			
		}
	});
 	
 	no_btn = (RelativeLayout)findViewById(R.id.wel_btn2);
 	no_btn.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
			inserData("yes", "2", "exercise");
			
		}
	});
 	no_btn_text = (Button)findViewById(R.id.btn_wel_btn2);
 	no_btn_text.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
			inserData("yes", "2", "exercise");
			
		}
	});
 	
 	tab_task = (Button)findViewById(R.id.task_btn);
 	tab_task.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
			Intent intObj = new Intent(WelbingActivityExercise.this,WelbingActivityExercise.class);
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
			
			Intent intObj = new Intent(WelbingActivityExercise.this,WelbingStaActivityExercise.class);
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
			
			Intent intObj = new Intent(WelbingActivityExercise.this,WelbingActivityWater.class);
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
			
			Intent intObj = new Intent(WelbingActivityExercise.this,WelbingActivityWater.class);
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
			
			Intent intObj = new Intent(WelbingActivityExercise.this,WelbingActivityExercise.class);
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
			
			Intent intObj = new Intent(WelbingActivityExercise.this,WelbingActivityExercise.class);
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
			
			Intent intObj = new Intent(WelbingActivityExercise.this,WelbingActivityMood.class);
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
			
			Intent intObj = new Intent(WelbingActivityExercise.this,WelbingActivityMood.class);
			startActivity(intObj);
			overridePendingTransition(0, 0);
			finish();
			
		}
	});
 	
     
        Runnable myRunnableThread = new CountDownRunner();
 	    myThread= new Thread(myRunnableThread);
 	  //  myThread.start();
        
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
         
        String toDate1 =dDay+"."+dMonth+"."+year;
        
        String toDate =year+"-"+dMonth+"-"+dDay;
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
	
	public static boolean getAlarmsEnabled(){
		SharedPreferences settings = ctx.getSharedPreferences(PREFS_NAME, 0);
		return settings.getBoolean(ALARMS_KEY, true);
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
    
    
 //-----------System Back Press
    @Override
    public void onBackPressed(){
    	
    	inserData("n/a", "2", "exercise");
    	finish();
    }
    
    
	
	
}