package com.seva60plus.hum.sathi;




import java.io.IOException;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.seva60plus.hum.R;
import com.seva60plus.hum.R.anim;
import com.seva60plus.hum.R.id;
import com.seva60plus.hum.R.layout;
import com.seva60plus.hum.R.string;
import com.seva60plus.hum.R.style;
import com.seva60plus.hum.activity.MenuLay;
import com.seva60plus.hum.activity.Registration;
import com.seva60plus.hum.customview.NoInternetPage;
import com.seva60plus.hum.util.ConnectionDetector;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.telephony.TelephonyManager;
import android.text.Html;
import android.util.Base64;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


public class Sathicall extends Activity {
	RelativeLayout sathiCall1,sathiCall2,sathiSms1,sathiSms2;
	TextView headerTitleText,sathi1Name,sathi2Name,sathi1Email,sathi2Email,sathi1Num,sathi2Num;
	ImageView imVCature_pic1,imVCature_pic2;
	ImageView backBtn,homeBtn;
	RelativeLayout settings;
	
	
	Button whatsapp,fb,twitter;
	
	String call1="",call2="";
	String image1="",image2="";
	String name1="",name2="";
	String email1="",email2="";
	String con1="";
	String con2="";
	String sathi1email;
	String sathi2email;
	String decodeSaathi1 = "",decodeSaathi2 = "";
	String saathiName1,saathiName2;
	String result;
	TelephonyManager tm;
	String subId;
	String name1Saathi = "",name2Saathi = "",call1Saathi = "",call2Saathi = "",email1Saathi = "",email2Saathi = "",sathi1image,sathi2image;//===for storing the value
	Bitmap saathi1Image,saathi2Image;
	public static final String MY_PREFS_NAME = "MyPrefsFile";
	
	TextView emailSend;
	
	
	String messege,eMsg;
	
	String userName ="",userPhone = "",dateOfBirth = "",address1 = "" ,address2 = "",cityName = "",state = "",pinNum = "",emailID1 = "",emailID2 = "";
	String pherma="";
	String sex;
	String decodeUser = "";
	//---by Dibyendu
		 // flag for Internet connection status
	    Boolean isInternetPresent = false;
	     
	    // Connection detector class
	    ConnectionDetector cd;
 
	    ProgressDialog progress;
	    
	    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sathicall);
     
        
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        
        
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
        
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE); 
        call1 = prefs.getString("saathi1Call", "");
        call2 = prefs.getString("saathi2Call", "");
        name1 = prefs.getString("saathi1CallName", "");
        name2 = prefs.getString("saathi2CallName", "");
        email1 = prefs.getString("saathi1emailID", "");
        email2 = prefs.getString("saathi2emailID", "");
        image1 = prefs.getString("sathi_Image1", "");
        image2 = prefs.getString("sathi_Image2", "");
        
        cd = new ConnectionDetector(getApplicationContext());// by Dibyendu
        
        tm=(TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
        subId=tm.getSubscriberId().toString();
        System.out.println(subId);
        
        
        progress = new ProgressDialog(Sathicall.this,R.style.main_content);
        progress.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
		 progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		 progress.setMessage("Please Wait....");
        
        LinearLayout banner=(LinearLayout)findViewById(R.id.footerLay);
        
		 banner.setOnClickListener(new View.OnClickListener() {
		        @Override
		        public void onClick(View view) {
		            // Take action.
		     /*   	Intent myIntent = new Intent(Sathicall.this, AdBanner.class);
		        	myIntent.putExtra("banner_value", "2");
		        	startActivity(myIntent);
		        */
		        	
		        /*	Intent i = new Intent(Intent.ACTION_VIEW, 
						       Uri.parse("http://www.homers..in"));
						startActivity(i);*/
		        }
		    });
        
        
        
        
        if (call1.equals("") || call2.equals("") || name1.equals("") || name2.equals("") || email1.equals("") || email2.equals(""))  {
			
        	/*Intent intObj = new Intent(Sathicall.this,Registration.class);
        	startActivity(intObj);
        	finish();
        	*/
        	
        	isInternetPresent = cd.isConnectingToInternet();
			 
            // check for Internet status
            if (isInternetPresent) {
            	
            	//new GetUserDetails().execute();
            	
            	
            	
            	 System.out.println("OK");
        	
        	 JSONObject json1 = null;
             String str = "";
             HttpResponse response;
             HttpClient myClient = new DefaultHttpClient();
             HttpPost myConnection = new HttpPost("http://seva60plus.co.in/seva60PlusAndroidAPI/register/checkUserExists/"+subId);
             
             try {
                 response = myClient.execute(myConnection);
                 str = EntityUtils.toString(response.getEntity(), "UTF-8");
                 
                 System.out.println("Responce "+str);
                 
             } catch (ClientProtocolException e) {
                 e.printStackTrace();
             } catch (IOException e) {
                 e.printStackTrace();
             } 
             
             try{
            	 progress.show();
            	 
            	 JSONObject jObject = new JSONObject(str);
            	 result = jObject.getString("code");
            	 
            	 System.out.println("OResult Code "+result);
            	 
            	  //404310115970712
                 if(result.equals("100")){
                	 
                	 new GetUserDetails().execute();
               /* 	 
                 JSONArray jArray = jObject.getJSONArray("details");
                 json1 = jArray.getJSONObject(0);
                 
                
               
                	 
                	 saathiName1=json1.getString("sathi1_name");
                     saathiName2=json1.getString("sathi2_name");

                     con1=json1.getString("sathi1_contact_no");
                     con2=json1.getString("sathi2_contact_no");
                     
                     sathi1email = json1.getString("sathi1_email");
                     sathi2email = json1.getString("sathi2_email");
                     
                     decodeSaathi1=json1.getString("sathi1_image");
                     decodeSaathi2=json1.getString("sathi2_image");
                     
                     //
                     
                     name1Saathi = saathiName1;
                     name2Saathi = saathiName2;
                     call1Saathi = con1;
                     call2Saathi = con2;
                     email1Saathi = sathi1email;
                     email2Saathi = sathi2email;
                     
                     
                     saathi1Image = decodeBase64(decodeSaathi1);
                     saathi2Image = decodeBase64(decodeSaathi2);
                     
                     sathi1Name = (TextView)findViewById(R.id.userTxt);
                     sathi1Name.setText(name1Saathi);  
                     
                     sathi2Name = (TextView)findViewById(R.id.userTxt2);
                     sathi2Name.setText(name2Saathi); 
                     
                     sathi1Email = (TextView)findViewById(R.id.userEmail);
                     sathi1Email.setText(Html.fromHtml("<u>"+email1Saathi+"</u>")); 
                     
                     sathi2Email = (TextView)findViewById(R.id.userEmail2);
                     sathi2Email.setText(Html.fromHtml("<u>"+email2Saathi+"</u>"));
                    // text.setText(Html.fromHtml("<u>Terms of Service</u><br/>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum."));
                     
                     sathi1Num = (TextView)findViewById(R.id.userCall);
                     sathi1Num.setText(call1Saathi); 
                     
                     sathi2Num = (TextView)findViewById(R.id.userCall2);
                     sathi2Num.setText(call2Saathi); 
                     
                     
                     imVCature_pic1 = (ImageView)findViewById(R.id.sathiImage1);
                  //  imVCature_pic1.setImageBitmap(saathi1Image);
                     imVCature_pic2 = (ImageView)findViewById(R.id.imageView2);
                 //	imVCature_pic2.setImageBitmap(saathi2Image);
                     
                     progress.dismiss();
                	 */
                 }
                 
                 else {
                	 progress.dismiss();
                	 System.out.println("Oops");
                	 Toast.makeText(getApplicationContext(), "You are not registered user", Toast.LENGTH_SHORT).show();
                	 finish();
                 }
                 
                 
                 
                 
               //  Toast.makeText(getApplicationContext(), sex, Toast.LENGTH_LONG).show();
                  
             } catch ( JSONException e) {
            	 
            	 progress.dismiss();
                 e.printStackTrace();                
             }
             
           //====Storeing The Variable====
            
             
            
            }
            else {
            	
            	System.out.println("No internet");
            	//Toast.makeText(getApplicationContext(), "You are not registered user", Toast.LENGTH_SHORT).show();
            	//finish();
                // Internet connection is not present
            	Intent i = new Intent(Sathicall.this,NoInternetPage.class);
            	startActivity(i);
				overridePendingTransition(0, 0);
				finish();
            	
            }
            
             //===Storeing complete====
		} else {
			System.out.println("AVAIBLE");
			
			name1Saathi = name1;
            name2Saathi = name2;
            call1Saathi = call1;
            call2Saathi = call2;
            email1Saathi = email1;
            email2Saathi = email2;
            saathi1Image = decodeBase64(image1);
            saathi2Image = decodeBase64(image2);
            
            sathi1Name = (TextView)findViewById(R.id.userTxt);
            sathi1Name.setText(name1Saathi);  
            
            sathi2Name = (TextView)findViewById(R.id.userTxt2);
            sathi2Name.setText(name2Saathi); 
            
            sathi1Email = (TextView)findViewById(R.id.userEmail);
            sathi1Email.setText(Html.fromHtml("<u>"+email1Saathi+"</u>")); 
            
            sathi2Email = (TextView)findViewById(R.id.userEmail2);
            sathi2Email.setText(Html.fromHtml("<u>"+email2Saathi+"</u>"));
           // text.setText(Html.fromHtml("<u>Terms of Service</u><br/>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum."));
            
            sathi1Num = (TextView)findViewById(R.id.userCall);
            sathi1Num.setText(call1Saathi); 
            
            sathi2Num = (TextView)findViewById(R.id.userCall2);
            sathi2Num.setText(call2Saathi); 
            
            
            imVCature_pic1 = (ImageView)findViewById(R.id.sathiImage1);
          //  imVCature_pic1.setImageBitmap(saathi1Image);
            imVCature_pic2 = (ImageView)findViewById(R.id.imageView2);
        //	imVCature_pic2.setImageBitmap(saathi2Image);
            
            
            
		} //===Storeing complete====
       
        
        
        System.out.println("OUTTT");
        
        headerTitleText=(TextView)findViewById(R.id.header_title);
        headerTitleText.setText("Seva60Plus");        
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
		
		settings=(RelativeLayout)findViewById(R.id.back_settings);
        
		settings.setOnClickListener(new View.OnClickListener() {
	        @Override
	        public void onClick(View view) {
	        	
	        /*	isInternetPresent = cd.isConnectingToInternet();
				 
                // check for Internet status
                if (isInternetPresent) {
                 */   // Internet Connection is Present
	            // Take action.
	        	//finish();
	        	Intent i = new Intent(Sathicall.this,Registration.class);
	        	startActivity(i);
	        	overridePendingTransition(0, 0);
	        	finish();
            /*    }
                else {
                    // Internet connection is not present
                	Intent i = new Intent(Sathicall.this,NoInternetPage.class);
                	startActivity(i);
    				overridePendingTransition(0, 0);
                	
                }*/
	        }
	    });
		
		homeBtn=(ImageView)findViewById(R.id.menu_icon);
        
		 homeBtn.setOnClickListener(new View.OnClickListener() {
		        @Override
		        public void onClick(View view) {
		            // Take action.
		        	Intent myIntent = new Intent(Sathicall.this, MenuLay.class);
		        	startActivity(myIntent);
		        	overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
		        	finish();
		        	
		        }
		    });
		 
		 
		 
		 //sathi1Email = (TextView)findViewById(R.id.userEmail);
		 if(email1Saathi.equals("") || email1Saathi == null){
			 
			 System.out.println("cancel"+email1Saathi); 
		
		 }
		 else{
		 sathi1Email.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				 final Intent intent = new Intent(android.content.Intent.ACTION_SEND);
			        intent.setType("text/plain");
			        intent.putExtra(Intent.EXTRA_EMAIL  , new String[]{email1Saathi});
			        final PackageManager pm = getPackageManager();
			        final List<ResolveInfo> matches = pm.queryIntentActivities(intent, 0);
			        ResolveInfo best = null;
			        for (final ResolveInfo info : matches)
			           if (info.activityInfo.packageName.endsWith(".gm") ||
			        info.activityInfo.name.toLowerCase().contains("gmail")) best = info;
			        if (best != null)
			           intent.setClassName(best.activityInfo.packageName, best.activityInfo.name);
			        startActivity(intent);
			}
		});
		
		 //sathi2Email = (TextView)findViewById(R.id.userEmail);
		 sathi2Email.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				 final Intent intent = new Intent(android.content.Intent.ACTION_SEND);
			        intent.setType("text/plain");
			        intent.putExtra(Intent.EXTRA_EMAIL  , new String[]{email2Saathi});
			        final PackageManager pm = getPackageManager();
			        final List<ResolveInfo> matches = pm.queryIntentActivities(intent, 0);
			        ResolveInfo best = null;
			        for (final ResolveInfo info : matches)
			           if (info.activityInfo.packageName.endsWith(".gm") ||
			        info.activityInfo.name.toLowerCase().contains("gmail")) best = info;
			        if (best != null)
			           intent.setClassName(best.activityInfo.packageName, best.activityInfo.name);
			        startActivity(intent);
			}
		});
        
	
		 System.out.println("call"); 
        sathiCall1=(RelativeLayout)findViewById(R.id.sathi_callBtn);
       // sathiCall1.setText(name1Saathi+"\n"+call1Saathi);
        sathiCall1.setOnClickListener(new View.OnClickListener() {
	        @Override
	        public void onClick(View view) {
	            // Take action.
	        	String phnum = getString(R.string.sathi1); 
	          /*Intent callIntent = new Intent(Intent.ACTION_CALL);
	            callIntent.setData(Uri.parse("tel:" + call1Saathi));
	            startActivity(callIntent);
	            finish();*/
	            
	            try {
				    Intent my_callIntent = new Intent(Intent.ACTION_CALL);
				    my_callIntent.setData(Uri.parse("tel:"+call1Saathi));
				    //here the word 'tel' is important for making a call...
				    startActivity(my_callIntent);
				} catch (ActivityNotFoundException e) {
				    Toast.makeText(getApplicationContext(), "Error in your phone call"+e.getMessage(), Toast.LENGTH_LONG).show();
				}
	            
	            
	        	
	        }
	    });
        
        sathiCall2=(RelativeLayout)findViewById(R.id.sathi_callBtn2);
       // sathiCall2.setText(name2Saathi+"\n"+call2Saathi);
        sathiCall2.setOnClickListener(new View.OnClickListener() {
	        @Override
	        public void onClick(View view) {
	            // Take action.
	        	String phnum = getString(R.string.sathi2); 
	            /*Intent callIntent = new Intent(Intent.ACTION_CALL);
	            callIntent.setData(Uri.parse("tel:" + call2Saathi));
	            startActivity(callIntent);
	            finish();*/
	        	
	        	try {
				    Intent my_callIntent = new Intent(Intent.ACTION_CALL);
				    my_callIntent.setData(Uri.parse("tel:"+call2Saathi));
				    //here the word 'tel' is important for making a call...
				    startActivity(my_callIntent);
				} catch (ActivityNotFoundException e) {
				    Toast.makeText(getApplicationContext(), "Error in your phone call"+e.getMessage(), Toast.LENGTH_LONG).show();
				}
	        	
	        }
	    });
        
        
        
        sathiSms1=(RelativeLayout)findViewById(R.id.sathi_sms1Lay);
        // sathiCall2.setText(name2Saathi+"\n"+call2Saathi);
        sathiSms1.setOnClickListener(new View.OnClickListener() {
 	        @Override
 	        public void onClick(View view) {
 	            // Take action.
 	        	 Intent i = new Intent(android.content.Intent.ACTION_VIEW);
 	            i.putExtra("address", call1Saathi);
 	            // here i can send message to emulator 5556,5558,5560
 	            // you can change in real device
 	            i.putExtra("sms_body", "");
 	            i.setType("vnd.android-dir/mms-sms");
 	            startActivity(i);
 	        	
 	        }
 	    });
        
        sathiSms2=(RelativeLayout)findViewById(R.id.sathi_sms2Lay);
        // sathiCall2.setText(name2Saathi+"\n"+call2Saathi);
        sathiSms2.setOnClickListener(new View.OnClickListener() {
 	        @Override
 	        public void onClick(View view) {
 	            // Take action.
 	        	 Intent i = new Intent(android.content.Intent.ACTION_VIEW);
 	            i.putExtra("address", call2Saathi);
 	            // here i can send message to emulator 5556,5558,5560
 	            // you can change in real device
 	            i.putExtra("sms_body", "");
 	            i.setType("vnd.android-dir/mms-sms");
 	            startActivity(i);
 	        	
 	        }
 	    });
        
	}      
       
        
        

}
    
    public static Bitmap decodeBase64(String imageCodeJson) 
	{
		 byte[] decodedString = Base64.decode(imageCodeJson, Base64.DEFAULT);
		 Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length); 
		 return (decodedByte) ;
	}
    
    
    //--------------GET USER DETAILS
    
    private class GetUserDetails extends AsyncTask<Void, Integer, String> {

    	
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
			return getUser();
		}
		
		@SuppressWarnings("deprecation")
		private String getUser() {
			JSONObject json1 = null;
	        String str = "";
	        HttpResponse response;
	        HttpClient myClient = new DefaultHttpClient(); //http://seva60plus.co.in/seva60PlusAndroidAPI/register
	       HttpPost myConnection = new HttpPost("http://seva60plus.co.in/seva60PlusAndroidAPI/register/checkUserExists/"+subId);
	      //  HttpPost myConnection = new HttpPost("http://seva60plus.co.in/seva60PlusAndroidAPI/register/checkUserExists/"+subId);
	        
	        try {
	            response = myClient.execute(myConnection);
	            str = EntityUtils.toString(response.getEntity(), "UTF-8");
	            
	        } catch (ClientProtocolException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } 
	        System.out.println("Json Sarted.... ");
	        try{ System.out.println("Try.. ");
	            //JSONArray jArray = new JSONArray(str);
	         JSONObject json = new JSONObject(str);
	           messege = json.getString("code");
	         
	            //messege = "User does not exists with this sim no.";
	            
	            if(messege.equals("100")){
	            	
	            	JSONArray jArray = json.getJSONArray("details");
	            	json1 = jArray.getJSONObject(0);
	            	
	            userName=json1.getString("name");
	            userPhone=json1.getString("phone");
	            dateOfBirth=json1.getString("dob");
	            sex=json1.getString("gender");
	            address1=json1.getString("address1");
	            cityName=json1.getString("city");
	            state=json1.getString("state");
	            pinNum=json1.getString("pincode");
	            saathiName1=json1.getString("sathi1_name");
	            saathiName2=json1.getString("sathi2_name");
	            emailID1=json1.getString("sathi1_email");
	            emailID2=json1.getString("sathi2_email");
	            con1=json1.getString("sathi1_contact_no");
	            con2=json1.getString("sathi2_contact_no");
	            
	            System.out.println("Message :: "+messege);
	            	System.out.println("Data Getting.. ");
	            	
	            	
	            	 SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
		        	 editor.putString("UserName", userName);
		             editor.putString("UserPhoneNumber", userPhone);
		             editor.putString("dob", dateOfBirth);
		             editor.putString("sex", sex);
		             editor.putString("add1", address1);
		             editor.putString("city", cityName);
		             editor.putString("state", state);
		             editor.putString("pin", pinNum);
		             editor.putString("saathi1CallName", saathiName1);
		             editor.putString("saathi2CallName", saathiName2);
		             editor.putString("saathi1emailID", emailID1);
		             editor.putString("saathi2emailID", emailID2);
		             editor.putString("saathi1Call", con1);
		             editor.putString("saathi2Call", con2);
		             
		             
		             editor.commit(); 
	            	
	            } 
	            
	            else{
	            	progress.dismiss();
               	 System.out.println("Oops");
               	 Toast.makeText(getApplicationContext(), "You are not registered user", Toast.LENGTH_SHORT).show();
               	 finish();
	            }
	          //  Toast.makeText(getApplicationContext(), sex, Toast.LENGTH_LONG).show();
	             
	            } catch ( JSONException e) {
	            	progress.dismiss();
	            	
	            e.printStackTrace(); 
	            eMsg = e.toString();
	            System.out.println("Data Getting Faild.. ");
	            }
	        return eMsg;
			
		}
		
		@Override
		protected void onPostExecute(String result) {
			
			name1Saathi = saathiName1;
            name2Saathi = saathiName2;
            call1Saathi = con1;
            call2Saathi = con2;
            email1Saathi = emailID1;
            email2Saathi = emailID2;
            
         
            sathi1Name = (TextView)findViewById(R.id.userTxt);
            sathi1Name.setText(name1Saathi);  
            
            sathi2Name = (TextView)findViewById(R.id.userTxt2);
            sathi2Name.setText(name2Saathi); 
            
            sathi1Email = (TextView)findViewById(R.id.userEmail);
            sathi1Email.setText(Html.fromHtml("<u>"+email1Saathi+"</u>")); 
            
            sathi2Email = (TextView)findViewById(R.id.userEmail2);
            sathi2Email.setText(Html.fromHtml("<u>"+email2Saathi+"</u>"));
           // text.setText(Html.fromHtml("<u>Terms of Service</u><br/>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum."));
            
            sathi1Num = (TextView)findViewById(R.id.userCall);
            sathi1Num.setText(call1Saathi); 
            
            sathi2Num = (TextView)findViewById(R.id.userCall2);
            sathi2Num.setText(call2Saathi); 
            
            
            imVCature_pic1 = (ImageView)findViewById(R.id.sathiImage1);
         //  imVCature_pic1.setImageBitmap(saathi1Image);
            imVCature_pic2 = (ImageView)findViewById(R.id.imageView2);
        //	imVCature_pic2.setImageBitmap(saathi2Image);
            
            progress.dismiss();
			
			super.onPostExecute(result);
		}
    	
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
