package com.android.eventScheduler;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.w3c.dom.Text;

import android.R.string;
import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class SettingEventTab1 extends ListActivity{

	final  String[] _dayHours = new String[7];
	String dataToUse;
	String date;
	String month;
	String year;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.daylayout_tabone);
		
		ListView dayList = (ListView)findViewById(android.R.id.list);
		TextView dateDisplay = (TextView)findViewById(R.id.TVListdateDisplay);
		

		Bundle intentData = getIntent().getExtras();
		if(intentData!=null){
			dataToUse = intentData.getString("intentData");
		}
		month = getMonthName(Integer.parseInt(dataToUse.substring(2,4)));	
		
		
		setListAdapter(new MyListAdapter(SettingEventTab1.this));
		dateDisplay.setText(month);
	}

	public class MyListAdapter extends ArrayAdapter {
		private Activity contextACT;


		public MyListAdapter(Activity context) {
			super(context, R.layout.row_for_day,_dayHours);
			this.contextACT = context;
		}

		public View getView(final int position, View convertView,ViewGroup parent) 
		{

			LayoutInflater inflator = contextACT.getLayoutInflater();
			View row = inflator.inflate(R.layout.row_for_day, null);
			TextView date = (TextView)row.findViewById(R.id.TVRowDate);
			
					return (row);

		}

	}

	public String getMonthName(int month) {
		return new DateFormatSymbols().getMonths()[month];
	}
	
//	getNextPrevday()
//	{
//		Calendar cal = Calendar.getInstance();
//		if()
//	}

}





