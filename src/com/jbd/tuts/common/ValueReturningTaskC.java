package com.jbd.tuts.common;

import java.util.concurrent.TimeUnit;

public class ValueReturningTaskC implements Runnable {

	private int a;
	private int b;
	private long sleepTime;
	private int sum;

	private static int count = 0;
	private int instanceNumber;
	private String taskId;

	public ValueReturningTaskC(int a, int b, long sleepTime) {
		super();
		this.a = a;
		this.b = b;
		this.sleepTime = sleepTime;

		// for identity to the task
		this.instanceNumber = ++count;
		this.taskId = "ValueReturnTaskC" + instanceNumber;
	}

	@Override
	public void run() {
		String currentThreadName = Thread.currentThread().getName();

		System.out.println("##### [" + currentThreadName + " ] < " + taskId + "> STARTING #####");
		System.out.println("[" + currentThreadName + " ] < " + taskId + "> Sleeping for " + sleepTime + " milis");

		try {
			TimeUnit.MILLISECONDS.sleep(sleepTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		sum = a + b;

		System.out.println("***** [" + currentThreadName + "]<" + taskId + "> Done ***");

	}

	public int getSum() {

		return sum;
	}

}
