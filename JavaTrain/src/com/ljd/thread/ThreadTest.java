package com.ljd.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadTest {

	public ThreadTest() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		//固定线程但队列无界线程池。当某线程跑完后再取新任务继续跑，而不会无限增加线程；但队列可以无限增加新的等待任务
		ExecutorService service = Executors.newFixedThreadPool(10);
		try {
			for(int i = 0; i < 100; i++) {
				service.execute(new Runnable1(i));
			}
		} catch (Throwable e) {
			System.out.println(" ==============================================");
		}
		
	}
	
	public static class Runnable1 implements Runnable {
		int i;
		
		public Runnable1(int i) {
			super();
			this.i = i;
		}

		public void run() {
			System.out.println(i);
			if(i%33 == 0) throw new NullPointerException("test " + i);
		}
	}
}
