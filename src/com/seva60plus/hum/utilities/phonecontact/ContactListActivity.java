package com.seva60plus.hum.utilities.phonecontact;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.seva60plus.hum.R;
import com.seva60plus.hum.activity.MenuLay;
import com.seva60plus.hum.activity.Registration;
import com.seva60plus.hum.util.ConnectionDetector;
import com.seva60plus.hum.utilities.Utilities;

import android.R.string;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ContactListActivity extends Activity implements
		OnItemClickListener {

	private ListView listView;
	private List<ContactBean> list = new ArrayList<ContactBean>();
	
	ImageView backBtn,menuIcon;
	RelativeLayout backSetup;
	//---by Dibyendu
 	 // flag for Internet connection status
     Boolean isInternetPresent = false;
      
     // Connection detector class
     ConnectionDetector cd;
     
     

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.contact_list_main);

		cd = new ConnectionDetector(getApplicationContext());// by Dibyendu
		
		
		LinearLayout banner=(LinearLayout)findViewById(R.id.footerLay);
        
		 banner.setOnClickListener(new View.OnClickListener() {
		        @Override
		        public void onClick(View view) {
		            // Take action.
		       /* 	Intent myIntent = new Intent(Utilities.this, AdBanner.class);
		        	myIntent.putExtra("banner_value", "2");
		        	startActivity(myIntent);
		        	*/
		        /*	Intent i = new Intent(Intent.ACTION_VIEW, 
						       Uri.parse("http://www.homers.in"));
						startActivity(i);
		        	*/
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
       	Intent i = new Intent(ContactListActivity.this,Registration.class);
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
			Intent intObj = new Intent(ContactListActivity.this,MenuLay.class);
			startActivity(intObj);
			overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
			
			//finish();
		}
	});
		
		listView = (ListView) findViewById(R.id.list);
		listView.setOnItemClickListener(this);

		Cursor phones = getContentResolver().query(
				ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null,
				null, null);
		while (phones.moveToNext()) {

			String name = phones
					.getString(phones
							.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));

			String phoneNumber = phones
					.getString(phones
							.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
			
			String photoUri = phones
					.getString(phones
							.getColumnIndex(ContactsContract.CommonDataKinds.Phone.PHOTO_URI));

			ContactBean objContact = new ContactBean();
			objContact.setName(name);
			objContact.setPhoneNo(phoneNumber);
			objContact.setPhotoUri(photoUri);
			list.add(objContact);

		}
		phones.close();

		ContanctAdapter objAdapter = new ContanctAdapter(
				ContactListActivity.this, R.layout.contact_list_row, list);
		listView.setAdapter(objAdapter);

		if (null != list && list.size() != 0) {
			Collections.sort(list, new Comparator<ContactBean>() {

				@Override
				public int compare(ContactBean lhs, ContactBean rhs) {
					return lhs.getName().compareTo(rhs.getName());
				}
			});
			/*AlertDialog alert = new AlertDialog.Builder(
					ContactListActivity.this).create();
			alert.setTitle("");

			alert.setMessage(list.size() + " Contact Found!!!");

			alert.setButton("OK", new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
				}
			});
			alert.show();*/
	
		} else {
			showToast("No Contact Found!!!");
		}
		
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				//change by Sumangal
				ContactBean bean = (ContactBean) parent.getItemAtPosition(position);
				
				//showContactInfo(bean.getName(), bean.getPhoneNo(), bean.getPhotoUri());
				
				Toast.makeText(getApplicationContext(), "Hii"+bean.getPhoneNo(), Toast.LENGTH_SHORT).show();
			}
		});
		
	}

	private void showToast(String msg) {
		Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onItemClick(AdapterView<?> listview, View v, int position,
			long id) {
		ContactBean bean = (ContactBean) listview.getItemAtPosition(position);
		
		//showCallDialog(bean.getName(), bean.getPhoneNo());
	}

	private void showCallDialog(String name, final String phoneNo) {
		AlertDialog alert = new AlertDialog.Builder(ContactListActivity.this)
				.create();
		alert.setTitle("Call?");

		alert.setMessage("Are you sure want to call " + name + " ?");

		alert.setButton("No", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
		alert.setButton2("Yes", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				String phoneNumber = "tel:" + phoneNo;
				Intent intent = new Intent(Intent.ACTION_CALL, Uri
						.parse(phoneNumber));
				startActivity(intent);
			}
		});
		alert.show();
	}
	
	
	private void showContactInfo(String name, String phone, String img) {
		
		// custom dialog
        final Dialog dialog = new Dialog(ContactListActivity.this);
        dialog.setContentView(R.layout.contact_details_dialog);
        dialog.setTitle("Contact Details");

        // set the custom dialog components - text, image and button
        TextView text = (TextView) dialog.findViewById(R.id.cNameTextDetails);
        text.setText("Name : "+name);
        
        TextView text2 = (TextView) dialog.findViewById(R.id.cNoTextDetails);
        text2.setText("Contact No. : "+phone);
        
        ImageView image = (ImageView) dialog.findViewById(R.id.cUserImageDetails);
        
        try {
		    Bitmap  bitmap = MediaStore.Images.Media
		        .getBitmap(getApplicationContext().getContentResolver(),
		          Uri.parse(img));
		     
		    image.setImageBitmap(bitmap);

		     } catch (FileNotFoundException e) {
		      // TODO Auto-generated catch block
		      e.printStackTrace();
		     } catch (IOException e) {
		      // TODO Auto-generated catch block
		      e.printStackTrace();
		     }

        dialog.show();
		
	}
}
