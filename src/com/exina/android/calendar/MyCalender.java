package com.exina.android.calendar;

 public class MyCalender 
{
	public int day;
	public int month;
	public boolean thisMonth;
	public MyCalender(int d,int m, boolean b) 
	{
		day = d;
		month=m;
		thisMonth = b;
	}
	public MyCalender(int d,int m) 
	{
		this(d, m,false);
	}
}