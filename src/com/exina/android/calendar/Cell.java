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

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.Log;

public class Cell {
	private static final String TAG = "Cell";
	protected Rect mBound = null;
	protected int mDayOfMonth = 1;	// from 1 to 31
	protected int mMonth = 0;	// from 0 to 11
	protected Paint mPaint = new Paint(Paint.SUBPIXEL_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
	
	int dx, dy;
	public Cell(int dayOfMon,int month, Rect rect, float textSize, boolean bold) {
		mDayOfMonth = dayOfMon;
		mMonth=month;
		mBound = rect;
		mPaint.setTextSize(textSize/*26f*/);
		mPaint.setColor(Color.BLACK);
		
		if(bold) mPaint.setFakeBoldText(true);
		
		dx = (int) mPaint.measureText(String.valueOf(mDayOfMonth)) / 2;
		dy = (int) (-mPaint.ascent() + mPaint.descent()) / 2;
	}
	
	public Cell(int dayOfMon,int month, Rect rect, float textSize,Context context) {
		this(dayOfMon,month, rect, textSize, false);
		
	}
	
	protected void draw(Canvas canvas,Context context) {
		canvas.drawText(String.valueOf(mDayOfMonth), mBound.centerX() - dx, mBound.centerY() + dy, mPaint);
	}
	
	public int getDayOfMonth() {
		return mDayOfMonth;
	}
	
	public int getMonth() {
		return mMonth;
	}
	
	public boolean hitTest(int x, int y) {
		return mBound.contains(x, y); 
	}
	
	public Rect getBound() {
		return mBound;
	}
	
	public String toString() {
		return String.valueOf(mDayOfMonth)+"("+mBound.toString()+")";
	}
	
}

