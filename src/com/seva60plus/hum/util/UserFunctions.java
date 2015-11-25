package com.seva60plus.hum.util;

import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class UserFunctions {

	private JsonParser jsonParser;
	String res;
	//url declaration...				//"http://seva60plus.co.in/seva60PlusAndroidAPI/register" 
//	private static String registerURL ="http://wrctec.com/androidApi/sevaGenie/register/index/";
	private static String registerURL ="http://seva60plus.co.in/seva60PlusAndroidAPI/register";
//	private static String log_inURL ="http://192.168.2.48/Controllers/Login/user_loginAnd";
//	private static String forgetPasswordURL ="http://dev.wrctechnologies.com/stunited/forgot.php";
	// constructor
	
	private static String sendStatisticsData = "http://seva60plus.co.in/seva60PlusAndroidAPI/insert_hum_stat";
	
	private static String send_lat_lang_url = "http://seva60plus.co.in/seva60PlusAndroidAPI/latlong/insert";
	
	public UserFunctions(){
		jsonParser = new JsonParser();
	}

	//<............. different method declarations ..............>

	public JSONObject registerUser(String submit, String subId,  String imei, String possibleEmail, String userName, String userCountry, String userPhone, String dateOfBirth, String sex, String address1, String cityName, String state, String pinNum, String saathiName1, String emailID1, String saathiName2, String emailID2, String saathiCountry1, String con1, String saathiCountry2, String con2, String pherma, String geoLocation, String userStatus){

		// Building Parameters

		List<NameValuePair> params = new ArrayList<NameValuePair>();

		params.add(new BasicNameValuePair("submit", submit));
		params.add(new BasicNameValuePair("sub_id", subId));
		//params.add(new BasicNameValuePair("gcm_id", regId));
		params.add(new BasicNameValuePair("imei", imei));
		params.add(new BasicNameValuePair("email", possibleEmail));
		params.add(new BasicNameValuePair("name", userName));
		params.add(new BasicNameValuePair("country_code", userCountry));
		params.add(new BasicNameValuePair("phone", userPhone));
		params.add(new BasicNameValuePair("dob", dateOfBirth));
		params.add(new BasicNameValuePair("gender", sex));
		params.add(new BasicNameValuePair("address1", address1));
		//params.add(new BasicNameValuePair("address2", address2));		
		params.add(new BasicNameValuePair("city", cityName));
		params.add(new BasicNameValuePair("state", state));
		params.add(new BasicNameValuePair("pincode", pinNum));
		params.add(new BasicNameValuePair("sathi_name1", saathiName1));
		params.add(new BasicNameValuePair("sathi_email1", emailID1));
		params.add(new BasicNameValuePair("sathi_name2", saathiName2));
		params.add(new BasicNameValuePair("sathi_email2", emailID2));
		params.add(new BasicNameValuePair("sathi1_country_code", saathiCountry1));
		params.add(new BasicNameValuePair("sathi_contact_no1", con1));
		params.add(new BasicNameValuePair("sathi2_country_code", saathiCountry2));
		params.add(new BasicNameValuePair("sathi_contact_no2", con2));
		params.add(new BasicNameValuePair("pharmacy_no", pherma));
		params.add(new BasicNameValuePair("status", userStatus));
		/*params.add(new BasicNameValuePair("avatar", encodeUser));
		params.add(new BasicNameValuePair("sathi_image1", encodeSaathi1));
		params.add(new BasicNameValuePair("sathi_image2", encodeSaathi2)); */
		params.add(new BasicNameValuePair("is_geo_enabled", geoLocation));
		
		/*params.add(new BasicNameValuePair("status", "MQ=="));
		params.add(new BasicNameValuePair("code", "MQ=="));
		params.add(new BasicNameValuePair("msg", "MQ=="));
		params.add(new BasicNameValuePair("added_date", "MQ=="));*/
		

		// return json Object
		System.out.println(" Responce: ");
		JSONObject json = jsonParser.getJSONFromUrl(registerURL,params);
		
		/*try {
			 res = json.getString("code");
			 System.out.println("JSON Responce: "+res);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		//System.out.println("Name ValuePair Responce: "+params);
		System.out.println("JSON Responce: "+json);
		return json;
	}

//json for Login...
	
public JSONObject sendStatisticsData(String mood, String result, String phoneNo, String iDate, String iTime){
		
		// Building Parameters

		List<NameValuePair> params = new ArrayList<NameValuePair>();
		
		params.add(new BasicNameValuePair("notification_type", mood));
		params.add(new BasicNameValuePair("phone_no", phoneNo));
		params.add(new BasicNameValuePair("ans", result));
		params.add(new BasicNameValuePair("added_date", iDate));
		params.add(new BasicNameValuePair("added_time", iTime));
		

		// return json Object ,,,,


		JSONObject json = jsonParser.getJSONFromUrl(sendStatisticsData,params);
		System.out.println("JSON Responce: "+json);
		return json;
	}
/*
//json for Forget Password...

public JSONObject forgetPassword(String email,String submit){
	
	// Building Parameters

	List<NameValuePair> params = new ArrayList<NameValuePair>();

	params.add(new BasicNameValuePair("email",email));		
	params.add(new BasicNameValuePair("submit",submit));
	// return json
	JSONObject json = jsonParser.getJSONFromUrl(forgetPasswordURL,params);
	return json;
}*/


//--send lat long

public JSONObject sendLatlang(String Phone, String MyLat, String MyLang, String myTime){
	
	// Building Parameters

	List<NameValuePair> params = new ArrayList<NameValuePair>();
	
	params.add(new BasicNameValuePair("phone_no", Phone));
	params.add(new BasicNameValuePair("latitude", MyLat));
	params.add(new BasicNameValuePair("longitude", MyLang));
	params.add(new BasicNameValuePair("latlongtime", myTime));

	
	
	// return json Object ,,,,


	JSONObject json = jsonParser.getJSONFromUrl(send_lat_lang_url,params);
	System.out.println("JSON Responce: "+json);
	return json;
}

//---



}

