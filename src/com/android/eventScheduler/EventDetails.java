package com.android.eventScheduler;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EventDetails extends Activity{
	
	Button _kick;
	boolean flag  = false;
	
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.eventdetails);
		
		_kick = (Button)findViewById(R.id.BTFlag);
		
		_kick.setOnClickListener(new View.OnClickListener() {
			
			
			public void onClick(View v) {

				flag = true;
				
			}
		});
		
	}

}
