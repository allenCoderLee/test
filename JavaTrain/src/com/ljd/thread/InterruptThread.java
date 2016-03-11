package com.ljd.thread;

public class InterruptThread extends Thread {

	@Override
	public void run() {
		try {
			sleep(10000);
			System.out.println("ten years later");
		} catch (InterruptedException e) {
			System.out.println("error");
		}
	}


	public static void main(String[] args) throws InterruptedException {
		System.out.println("start");
		InterruptThread t = new InterruptThread();
		t.start();
		sleep(3000);
		t.interrupt();
		System.out.println("end");
	}
}
