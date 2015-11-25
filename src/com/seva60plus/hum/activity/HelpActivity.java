package com.seva60plus.hum.activity;


import android.app.Activity;
import android.os.Bundle;

import com.seva60plus.hum.R;

public class HelpActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.help);
		setTitle(R.string.help_title);
	}
	
}
