package com.ljd.java.string;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class StringTest {

	public static void main(String[] args) {
		
		String s = "";
		String[] arr = s.trim().split(",");
		System.out.println(arr.length);
		System.out.println(s.length());
	}
}
