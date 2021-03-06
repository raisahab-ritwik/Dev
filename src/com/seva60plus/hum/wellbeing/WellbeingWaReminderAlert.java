
package com.seva60plus.hum.wellbeing;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import com.seva60plus.hum.R;
import com.seva60plus.hum.R.id;
import com.seva60plus.hum.R.layout;
import com.seva60plus.hum.R.raw;
import com.seva60plus.hum.pillreminder.PillsDbAdapter;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class WellbeingWaReminderAlert extends Activity{
    
	Long rowId;
	private PillsDbAdapter mDbHelper;
	public static final String DATE_TIME_FORMAT = "yyyy-MM-dd kk:mm:ss";
	MediaPlayer mMood;
	TextView mTitleText;
	 String mainTime="";
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wellbeing_wa_reminder_alart);
        
        
        mTitleText = (TextView)findViewById(R.id.textView2);
        mTitleText.setText("HOW MANY HOUR DID YOU SLEEP?");
       
        mMood = MediaPlayer.create(this, R.raw.good_morning);
        
        mMood.setLooping(true);
        mMood.start();
        
        /* 
        Intent intent = getIntent();
        rowId = intent.getExtras().getLong(PillsDbAdapter.RKEY_ROWID);
        
        mDbHelper = new PillsDbAdapter(this);
		mDbHelper.open(); 
       
		try {
			
		
			Cursor reminder = mDbHelper.fetchReminder(rowId);
			startManagingCursor(reminder);
			mTitleText.setText("Task : "+reminder.getString(
					reminder.getColumnIndexOrThrow(PillsDbAdapter.RKEY_TITLE))+"\n");
			
			// Get the date from the database and format it for our use. 
			SimpleDateFormat dateTimeFormat = new SimpleDateFormat(DATE_TIME_FORMAT);
			Date date = null;
			try {
				String dateString = reminder.getString(reminder.getColumnIndexOrThrow(PillsDbAdapter.RKEY_DATE_TIME)); 
				date = dateTimeFormat.parse(dateString);
				//mTitleText.append("Date : "+date.toString()); 
				
				// Thu Jan 01 17:34:25 IST 1970//
				//----------------------------------------    
				//----------------------------------------    
                String getDate = date.toString();
                System.out.println("----MAinDate :"+getDate);
                
                String cutDate = getDate.substring(0, 10);
                System.out.println("----CUT DATE :"+cutDate);
                
                String cutTime = getDate.substring(11, getDate.indexOf("I"));
                System.out.println("----CUT TIME :"+cutTime);
                
                String cutYear = getDate.substring(getDate.indexOf("T")+1,getDate.length());
                System.out.println("----CUT Year :"+cutYear);
                
                String mainDate = cutDate+","+cutYear;
                System.out.println("----CUT Full Date :"+mainDate);
                
                //-------------------------------------
                //-------------------------------------
                
               
                try {
                     SimpleDateFormat sdf = new SimpleDateFormat("H:mm");
                     Date dateObj = sdf.parse(cutTime);
                    System.out.println(dateObj);
                    System.out.println("$$$ AM-PM: "+new SimpleDateFormat("K:mm aa").format(dateObj));
                    mainTime = new SimpleDateFormat("K:mm aa").format(dateObj);
                    
                   
                } catch (final ParseException e) {
                    e.printStackTrace();
                }
                
               
                mTitleText.append("Date : "+mainDate);
                mTitleText.append("\nTime : "+mainTime);
                
				
			} catch (ParseException e) {
				Log.e("ReminderEditActivity", e.getMessage(), e); 
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
        */
        Button okBtn = (Button)findViewById(R.id.ok_btn);
        okBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mMood.stop();
				Intent objIntent = new Intent(getApplicationContext(), WelbingActivityWater.class); 
				startActivity(objIntent);
				finish();
			}
		});
        
    }
    

}
