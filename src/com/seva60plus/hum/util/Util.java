package com.seva60plus.hum.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * Utility Class
 * 
 * @author raisahab.ritwik
 */
public class Util {
	/**
	 * Check if Internet Connection is available or not
	 * 
	 * @param context
	 *            - {@link Context}
	 **/
	public static boolean isInternetAvailable(Context context) {

		ConnectivityManager conManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo i = conManager.getActiveNetworkInfo();
		if ((i == null) || (!i.isConnected()) || (!i.isAvailable())) {

			return false;
		}
		return true;
	}
	/** Hide Soft Keyboard **/
	public static void hideSoftKeyboard(Context context, View view) {
		InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0);
	}
}
