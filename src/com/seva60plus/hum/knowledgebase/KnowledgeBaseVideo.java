package com.seva60plus.hum.knowledgebase;



import java.io.IOException;

import com.seva60plus.hum.R;
import com.seva60plus.hum.R.anim;
import com.seva60plus.hum.R.id;
import com.seva60plus.hum.R.layout;
import com.seva60plus.hum.activity.MenuLay;
import com.seva60plus.hum.activity.Registration;
import com.seva60plus.hum.util.ConnectionDetector;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;



 
public class KnowledgeBaseVideo extends Activity {

	Button video2,video3,video4;
	ImageView backBtn,menuIcon;
	RelativeLayout backSetup;
	RelativeLayout video1lay,video2lay,video3lay,video4lay,video5lay,video6lay;
    Button btnSwitch;
    

	//---by Dibyendu
	 // flag for Internet connection status
    Boolean isInternetPresent = false;
     
    // Connection detector class
    ConnectionDetector cd;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.knowledge_base_video);
		
		
		cd = new ConnectionDetector(getApplicationContext());// by Dibyendu
		
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
	        	
	       /* 	 isInternetPresent = cd.isConnectingToInternet();
				 
	                // check for Internet status
	            if (isInternetPresent) {
	            */        // Internet Connection is Present	
	            // Take action.
	        	Intent i = new Intent(KnowledgeBaseVideo.this,Registration.class);
	        	startActivity(i);
	        	overridePendingTransition(0, 0);
	        	finish();
	        	
          /*  }
            else {
                // Internet connection is not present
            	Intent i = new Intent(KnowledgeBase.this,NoInternetPage.class);
            	startActivity(i);
				overridePendingTransition(0, 0);
            	
            }*/
	        	
	        }
	    });
		
		menuIcon=(ImageView)findViewById(R.id.menu_icon);
        
		menuIcon.setOnClickListener(new View.OnClickListener() {
	        @Override
	        public void onClick(View view) {
	            // Take action.
	        	Intent i = new Intent(KnowledgeBaseVideo.this,MenuLay.class);
	        	startActivity(i);
	        	overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
	        	finish();
	        	
	        }
	    });
		
		 LinearLayout banner=(LinearLayout)findViewById(R.id.footerLay);
	        
		 banner.setOnClickListener(new View.OnClickListener() {
		        @Override
		        public void onClick(View view) {
		            // Take action.
		        /*	Intent myIntent = new Intent(KnowledgeBase.this, AdBanner.class);
		        	myIntent.putExtra("banner_value", "1");
		        	startActivity(myIntent);
		        	*/
		        	Intent i = new Intent(Intent.ACTION_VIEW, 
						       Uri.parse("http://www.ethnikyarn.com"));
						startActivity(i);
		        	
		        }
		    });
		
		
		 video1lay = (RelativeLayout)findViewById(R.id.video1Btn);
		 video1lay.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub				
				
			/*	Uri uri = Uri.parse("file://android.resource://"+getPackageName()+"/"+"raw/"+video1);
				//String path = "file://android.resource://"+getPackageName()+"/"+"raw/"+video1;
				Intent intent = new Intent(Intent.ACTION_VIEW);

				intent.setDataAndType(uri, "video/*"); 
				startActivity(intent); */
	//			String vid = "android.resource//"+getPackageName()+"/raw/video.png"; 
		/*		try {
					Play(vid);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
				
		/*		try {
				    Intent mVideoWatch = new Intent(Intent.ACTION_VIEW);
				    mVideoWatch.setDataAndType(Uri.parse(vid), "video/*");
				    startActivity(mVideoWatch);
				}
				catch(Exception e) {
				  //  Log.e(TAG,e.getMessage());
				}
				
				*/
				Intent i = new Intent(KnowledgeBaseVideo.this,VideoPlayer.class);
				startActivity(i);
				
				
			}
		});
		
		 video2lay = (RelativeLayout)findViewById(R.id.video2Btn);
		 video2lay.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {	
				
				Intent i = new Intent(KnowledgeBaseVideo.this,VideoPlayer.class);
				startActivity(i);
				
				
			}
		});
		 
		 video3lay = (RelativeLayout)findViewById(R.id.video21Btn);
		 video3lay.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {	
				
				Intent i = new Intent(KnowledgeBaseVideo.this,VideoPlayer.class);
				startActivity(i);
				
				
			}
		});
		 video4lay = (RelativeLayout)findViewById(R.id.video22Btn);
		 video4lay.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {	
				
				Intent i = new Intent(KnowledgeBaseVideo.this,VideoPlayer.class);
				startActivity(i);
				
				
			}
		});
		 
		 video5lay = (RelativeLayout)findViewById(R.id.video31Btn);
		 video5lay.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {	
				
				Intent i = new Intent(KnowledgeBaseVideo.this,VideoPlayer.class);
				startActivity(i);
				
				
			}
		});
		 
		 video6lay = (RelativeLayout)findViewById(R.id.video32Btn);
		 video6lay.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {	
				
				Intent i = new Intent(KnowledgeBaseVideo.this,VideoPlayer.class);
				startActivity(i);
				
				
			}
		});
		 

	}
	
	public void Play(String fileName) throws IOException
	{
	    
		AssetFileDescriptor descriptor = getAssets().openFd(fileName);
	    long start = descriptor.getStartOffset();
	    long end = descriptor.getLength();
	    MediaPlayer mediaPlayer=new MediaPlayer();
	    mediaPlayer.setDataSource(descriptor.getFileDescriptor(), start, end);
	    mediaPlayer.prepare();
	    mediaPlayer.start();     
	}
	
}
