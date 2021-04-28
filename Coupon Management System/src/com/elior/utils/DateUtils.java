package com.elior.utils;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
	public static java.sql.Date convert(Date value) {
		return new java.sql.Date(value.getTime());
	}

	public static Date addDays(Date date, int days) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, days);
		return cal.getTime();
	}
}
