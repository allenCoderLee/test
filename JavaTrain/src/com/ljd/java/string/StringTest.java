package com.ljd.java.string;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringTest {

	
	public static void main(String[] args) {
		
//		String s = "abci032830";
//		String[] arr = s.trim().split(",");
//		System.out.println(arr.length);
//		System.out.println(s.length());
//		System.out.println(s.substring("abc".length() - 1));
//		
//		
//		String url = "servicesREST//";
//		System.out.println(url.replaceAll("/+", "/"));
		
		Pattern p = Pattern.compile("^\\[(([^,]+),?)+\\]$");
		String line = "[rc_route,m,o]";
		Matcher m = p.matcher(line);
		if(m.matches()) {
			for(int i = 0; i <= m.groupCount(); i++) 
				System.out.println(i + " : " + m.group(i));
			
		}
		
	}
}
