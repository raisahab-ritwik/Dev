package com.seva60plus.hum.wellbeing;

import com.seva60plus.hum.R;
import com.seva60plus.widget.charttest.PieChart;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Sample extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart_sample);

        Bundle extras = getIntent().getExtras();
        float[] arrayDatas = extras.getFloatArray("numbers");
        String[] arrayLabels = extras.getStringArray("lables");
        
        
        
        PieChart pieChart = (PieChart) findViewById(R.id.pieChart);
        float[] datas = new float[4];
        datas[0] = 34;
        datas[1] = 10;
        datas[2] = 32;
        datas[3] = 50;
       
        System.out.println("BUNDLE: "+datas);
        
        pieChart.setData(arrayDatas);

        String[] labels = new String[4];
        labels[0] = "JOHN";
        labels[1] = "GEORGE";
        labels[2] = "JACK";
        labels[3] = "BOBBY";
        pieChart.setLabels(arrayLabels);
        
        Button closeButton = (Button)findViewById(R.id.button1);
        closeButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
        
    }


}
