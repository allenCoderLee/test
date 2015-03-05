package com.ljd.guava.model;

import java.util.Comparator;

public class Foo {

	public int a;
	public int b;
	public int c;
	public Foo(int a, int b, int c) {
		super();
		this.a = a;
		this.b = b;
		this.c = c;
	}
	@Override
	public String toString() {
		return "[a:" + a + ", b:" + b + ", c:" + c + "]";
	}
	
	public static class CompareA implements Comparator<Integer> {

		@Override
		public int compare(Integer o1, Integer o2) {
			return o2 - o1;
		}
	}
}
