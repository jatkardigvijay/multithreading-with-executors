package com.jbd.tuts.common;

import java.util.concurrent.TimeUnit;

public class LoopTaskC implements Runnable {

	// this is the task
	private static int count = 0;
	private int instanceNumber;
	private String taskId;

	@Override
	public void run() {

		String currentThreadName = Thread.currentThread().getName();

	//	System.out.println("##### [" + currentThreadName + " ]<" + taskId + "> Starting ##### ");
		System.out.println("##### [" + Thread.currentThread().getName() + " ]<" + taskId + "> Starting ##### ");
		for (int i = 10; i > 0; i--) {
	//		System.out.println(" [ " + currentThreadName + "] <" + taskId + ">Tick tick : " + i);
			System.out.println(" [ " + Thread.currentThread().getName() + "] <" + taskId + ">Tick tick : " + i);
			try {
				TimeUnit.MILLISECONDS.sleep((long) (Math.random() * 1000));
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
		}
	//	System.out.println("***** [" + currentThreadName + "] <" + taskId + "> Done ***** ");
		System.out.println("***** [" + Thread.currentThread().getName() + "] <" + taskId + "> Done ***** ");
	}

	public LoopTaskC() {
		this.instanceNumber = ++count;
		this.taskId = "LoopTaskC" + instanceNumber;
	}
}
