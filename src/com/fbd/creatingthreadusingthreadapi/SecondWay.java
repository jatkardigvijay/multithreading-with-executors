package com.fbd.creatingthreadusingthreadapi;

import java.util.concurrent.TimeUnit;

public class SecondWay {

	public static void main(String[] args) {
		System.out.println("main thread starts here ...");
		new SecondTask().start();
		Thread t = new SecondTask();
		t.start();
		System.out.println("main thread starts here ...");
	}

	// we need a task and a thread

}

class SecondTask extends Thread {

	// this is the task
	private static int count = 0;
	private int id;

	@Override
	public void run() {
		for (int i = 10; i > 0; i--) {
			System.out.println("<" + id + ">Tick tick : " + i);

			try {
				TimeUnit.MILLISECONDS.sleep(200);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
		}
	}

	public SecondTask() {
		this.id = ++count;
	}

}