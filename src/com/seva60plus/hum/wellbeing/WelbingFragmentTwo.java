package com.seva60plus.hum.wellbeing;



import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.seva60plus.hum.R;
import com.seva60plus.hum.R.anim;
import com.seva60plus.hum.R.id;
import com.seva60plus.hum.R.layout;
import com.seva60plus.hum.activity.MenuLay;
import com.seva60plus.hum.activity.Registration;
import com.seva60plus.hum.reminder.service.ScheduleClient;
import com.seva60plus.hum.util.ConnectionDetector;
import com.seva60plus.hum.util.Contact;
import com.seva60plus.hum.util.DatabaseHandler;

 
public class WelbingFragmentTwo extends Fragment {
	
	ImageView backBtn,menuIcon;
	RelativeLayout backSetup;
	
	 //---by Dibyendu
	 // flag for Internet connection status
    Boolean isInternetPresent = false;
     
    // Connection detector class
    ConnectionDetector cd;
    
    Button button1;
    EditText edit_text;
    
    
  //---------------DB

    DatabaseHandler db;
 	Calendar c;
 	String setTime = "00:00:01";
 	String dDay,dMonth,dYear;
 	Thread myThread = null;
 	
    
    /* Initiating Menu XML file (menu.xml) */
    @Override
    public void onCreateOptionsMenu(Menu menu,  MenuInflater inflater)
    {
    	super.onCreateOptionsMenu(menu, inflater);
     
       // inflater.inflate(R.layout.menu, menu);
        
   
    }
   
    
    /**
    * Event Handling for Individual menu item selected
    * Identify single menu item by it's id
    * */
   @Override
   public boolean onOptionsItemSelected(MenuItem item)
   {
     
	   return true;
   }
 
  
   @Override
   public void onActivityCreated(Bundle savedInstanceState) {
	   
       super.onActivityCreated(savedInstanceState);

   }

   @Override
   public View onCreateView(LayoutInflater inflater, ViewGroup container,
           Bundle savedInstanceState) {
	  
 
	   Log.i("type page","oncreateview");
       View view = inflater.inflate(R.layout.welbing_fragment_two, container, false);
      
       // cd = new ConnectionDetector(getActivity().getApplicationContext());// by Dibyendu
    
 db = new DatabaseHandler(getActivity());
       
       c = Calendar.getInstance();
       
	/*   
       Runnable myRunnableThread = new CountDownRunner();
	    myThread= new Thread(myRunnableThread);
	//    myThread.start();
       
	 */  
       
       LinearLayout banner=(LinearLayout)view.findViewById(R.id.footerLay);
       
		 banner.setOnClickListener(new View.OnClickListener() {
		        @Override
		        public void onClick(View view) {
		            // Take action.
		       /* 	Intent myIntent = new Intent(getActivity(), AdBanner.class);
		        	myIntent.putExtra("banner_value", "2");
		        	startActivity(myIntent);
		        	*/
		        	Intent i = new Intent(Intent.ACTION_VIEW, 
						       Uri.parse("http://www.homers.in"));
						startActivity(i);
		        	
		        }
		    });
	  
	backBtn=(ImageView)view.findViewById(R.id.back);
  
	backBtn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
          // Take action.
      	getActivity().finish();
      	
      }
  });
	
	backSetup=(RelativeLayout)view.findViewById(R.id.back_settings);
  
	backSetup.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
      /*	 isInternetPresent = cd.isConnectingToInternet();
			 
              // check for Internet status
          if (isInternetPresent) {
           */       // Internet Connection is Present
          // Take action.
      	Intent i = new Intent(getActivity(),Registration.class);
      	startActivity(i);
      	getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_leave_right);
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
	
	menuIcon = (ImageView)view.findViewById(R.id.menu_icon);
	menuIcon.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent intObj = new Intent(getActivity(),MenuLay.class);
			startActivity(intObj);
			getActivity().overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
			
			//finish();
		}
	});
   
	edit_text = (EditText)view.findViewById(R.id.edit_glass);
	button1=(Button)view.findViewById(R.id.wel_btn);
	  
	button1.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
          // Take action.
    	 
    	  String glass = edit_text.getText().toString();
    	  
    	  inserData(glass, "2", "WATER");
    	  
      	Toast.makeText(getActivity(), glass, Toast.LENGTH_SHORT).show();
      	
      }
  });
	
	
       return view;
   }

  
    @Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("type page","oncreate");
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
    	Toast.makeText(getActivity(), "inserted..OK", Toast.LENGTH_SHORT).show();
    	
    	 List<Contact> contacts = db.getAllContacts();
 		
 		for (Contact cn : contacts) {
             String log = cn.getDate() + "--" + cn.getTime()+ "--" + cn.getResult()+ "--" + cn.getStaus()+ "--" + cn.getMode()+"\n";
                 // Writing Contacts to log
          
         Log.d("Name: ", log);
         
         }
    	
       
	}
   /* 
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
	                   
	                    
	                    if(curTime.equals(setTime)){
	                    	
	                    	System.out.println("*********** "+setTime);
	                    	
	                    	db.addContact(new Contact(toDate, curTime, "NA", "2", "Ex"));
	                    	Toast.makeText(getActivity(), "inserted..OK", Toast.LENGTH_SHORT).show();
	                    	
	                    }
	                    else{
	                    	
	                    	 System.out.println("Time IS : "+getTime);
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
   */
    
    
}