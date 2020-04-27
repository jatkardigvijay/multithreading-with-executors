package com.jbd.tuts.common;

import java.util.concurrent.TimeUnit;

public class ValueReturningTaskA implements Runnable {

	private int a;
	private int b;
	private long sleepTime;
	private int sum;

	private static int count = 0;
	private int instanceNumber;
	private String taskId;

	// as variable read and returned to by different threads, we make it volatile
	private volatile boolean done = false;

	public ValueReturningTaskA(int a, int b, long sleepTime) {
		super();
		this.a = a;
		this.b = b;
		this.sleepTime = sleepTime;

		// for identity to the task
		this.instanceNumber = ++count;
		this.taskId = "ValueReturnTaskA" + instanceNumber;
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

		done = true;

		synchronized (this) {
			System.out.println("[" + currentThreadName + "]<" + taskId + ">Notifying ...");
			this.notifyAll();
		}
	}

	public int getSum() {
		if (!done) {
			synchronized (this) {
				try {
					System.out.println("[" + Thread.currentThread().getName() + "]==== Waiting for result from" + taskId
							+ "...===");
					this.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			System.out.println("[" + Thread.currentThread().getName() + "]=== WOKEN-UP FOR " + taskId + "...===");
		}
		return sum;
	}

}
