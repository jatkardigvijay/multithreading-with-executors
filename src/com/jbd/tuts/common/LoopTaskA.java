package com.jbd.tuts.common;

import java.util.concurrent.TimeUnit;

public class LoopTaskA implements Runnable {

	// this is the task
	private static int count = 0;
	private int id;

	@Override
	public void run() {
		System.out.println("#####<Task-" + id + "> Starting ##### ");
		for (int i = 10; i > 0; i--) {
			System.out.println("<Task-" + id + ">Tick tick : " + i);

			try {
				TimeUnit.MILLISECONDS.sleep((long) (Math.random() * 1000));
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
		}
		System.out.println("*****<Task-" + id + "> Done ***** ");
	}

	public LoopTaskA() {
		this.id = ++count;
	}
}
