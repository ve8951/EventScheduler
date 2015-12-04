/*
 * Copyright (C) 2011 Chris Gao <chris@exina.net>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.exina.android.calendar;

import com.android.eventScheduler.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;
import java.text.DateFormatSymbols;

public class CalendarActivity extends Activity  implements CalendarView.OnCellTouchListener, android.view.View.OnClickListener{
	public static final String MIME_TYPE = "vnd.android.cursor.dir/vnd.exina.android.calendar.date";
	CalendarView mView = null;
	TextView mHit;
	Handler mHandler = new Handler();
	TextView monthdisp;

	Button _UIBTPrev,_UIBTNext;
	public static int daySelected,year,month;


	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.maincalendar);
		mView = (CalendarView)findViewById(R.id.calendar);
		mView.setOnCellTouchListener(this);
		mView.setLayoutParams(new LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT, android.view.ViewGroup.LayoutParams.MATCH_PARENT));

		_UIBTPrev = (Button)findViewById(R.id.BTGoToPrevMonth);

		_UIBTPrev.setOnClickListener(this);
		_UIBTNext = (Button)findViewById(R.id.BTGoToNextMonth);
		_UIBTNext.setOnClickListener(this); 

		monthdisp = (TextView)findViewById(R.id.TVMonthDisplay);
		monthdisp.setText(""+getMonthName(mView.getMonth())+","+mView.getYear());

	}


	public String getMonthName(int month) {
		return new DateFormatSymbols().getMonths()[month];
	}
	public void onTouch(Cell cell) {
		Intent intent = getIntent();
		String action = intent.getAction();
		if(action.equals(Intent.ACTION_PICK) || action.equals(Intent.ACTION_GET_CONTENT)) {
			year  = mView.getYear();
			month = mView.getMonth();
			daySelected	   = cell.getDayOfMonth();
			

			// FIX issue 6: make some correction on month and year
			if(cell instanceof CalendarView.GrayCell) {
				// oops, not pick current month...				
				if (daySelected < 15) {
					// pick one beginning day? then a next month day
					if(month==11)
					{
						month = 0;
						year++;
					} else {
						month++;
					}

				} else {
					// otherwise, previous month
					if(month==0) {
						month = 11;
						year--;
					} else {
						month--;
					}
				}
			}

			Intent ret = new Intent();
			ret.putExtra("year", year);
			ret.putExtra("month", month);
			ret.putExtra("day", daySelected);
			this.setResult(RESULT_OK, ret);
			return;
		}
		mHandler.post(new Runnable() {
			public void run() {
//				Toast.makeText(CalendarActivity.this, DateUtils.getMonthString(mView.getMonth(), DateUtils.LENGTH_LONG) + " "+mView.getYear(), Toast.LENGTH_SHORT).show();
			}
		});
	}

	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.BTGoToNextMonth:
			mView.nextMonth();
			monthdisp.setText(""+getMonthName(mView.getMonth())+","+mView.getYear());
			break;

		case R.id.BTGoToPrevMonth:
			mView.previousMonth();
			monthdisp.setText(""+getMonthName(mView.getMonth())+","+mView.getYear());
			break;

		default:
			break;
		}

	}
	
	
	public void switchTabSpecial(long l){
		CalendarActivity ParentActivity = (CalendarActivity) this.getParent() ;
		 ParentActivity.switchTabSpecial(l);
	}

}




