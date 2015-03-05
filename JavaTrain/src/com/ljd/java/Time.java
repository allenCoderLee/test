package com.ljd.java;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Time {

	public static void main(String[] args) {
		long now = System.currentTimeMillis();
		System.out.println(now);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmssSSS");
		String d1 = sdf.format(new Date(now));
		System.out.println(d1);
		
		try {
			Date d2 = sdf.parse(d1);
			System.out.println(d2.getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
