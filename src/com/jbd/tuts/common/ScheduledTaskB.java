package com.jbd.tuts.common;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class ScheduledTaskB implements Runnable {

	private long sleepTime;

	private static int count = 0;
	private int instanceNumber;
	private String taskId;

	// as variable read and returned to by different threads, we make it volatile

	private SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss.SSS");

	public ScheduledTaskB(long sleepTime) {
		super();
		this.sleepTime = sleepTime;

		// for identity to the task
		this.instanceNumber = ++count;
		this.taskId = "ScheduledTaskB" + instanceNumber;
	}

	@Override
	public void run() {
		Date startTime = new Date();
		String currentThreadName = Thread.currentThread().getName();

		System.out.println("##### [" + currentThreadName + " ] < " + taskId + "> Started at : "
				+ dateFormatter.format(startTime) + "#####");

		System.out.println("[" + currentThreadName + " ] < " + taskId + "> Sleeping for " + sleepTime + " milis");

		try {
			TimeUnit.MILLISECONDS.sleep(sleepTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("***** [" + currentThreadName + "]<" + taskId + "> Finished at : "
				+ dateFormatter.format(new Date()) + "*****\n");

	}

}
