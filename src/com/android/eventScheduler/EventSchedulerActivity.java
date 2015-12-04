package com.android.eventScheduler;

import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TabHost;
import android.widget.Toast;
import android.widget.TabHost.TabSpec;

public class EventSchedulerActivity extends TabActivity{
	private int ADD_NEW_TAB = Menu.FIRST;
	TabHost tabs;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tabhost);

		tabs = getTabHost();
		tabs.setup();


		TabSpec tspec1 = tabs.newTabSpec("First Tab");
		tspec1.setIndicator("MONTH");
		Intent monthIntent = new Intent(this,com.exina.android.calendar.CalendarActivity.class);
		monthIntent.setAction(Intent.ACTION_PICK);
		tspec1.setContent(monthIntent);
		tabs.addTab(tspec1);


		TabSpec tspec2 = tabs.newTabSpec("Second Tab");
		tspec2.setIndicator("DAY");
		Intent dayIntent = new Intent(this,SettingEventTab1.class);		
		tspec2.setContent(dayIntent);
		tabs.addTab(tspec2);




		Bundle extras = getIntent().getExtras(); 
		if (extras != null) {
			String printTest = extras.getString("tabSwitchData");

			dayIntent.putExtra("intentData", printTest);
			switchTab(1);
		}


	}

	public void switchTab(int tab){
		tabs.setCurrentTab(tab);
	}


}