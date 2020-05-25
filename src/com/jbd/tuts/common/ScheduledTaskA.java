package com.jbd.tuts.common;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class ScheduledTaskA extends TimerTask {

	private long sleepTime;

	private static int count = 0;
	private int instanceNumber;
	private String taskId;

	// as variable read and returned to by different threads, we make it volatile

	private SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss.SSS");

	public ScheduledTaskA(long sleepTime) {
		super();
		this.sleepTime = sleepTime;

		// for identity to the task
		this.instanceNumber = ++count;
		this.taskId = "ScheduledTaskA" + instanceNumber;
	}

	@Override
	public void run() {
		Date startTime = new Date();
		Date scheduledForRunningTime = new Date(super.scheduledExecutionTime());
		String currentThreadName = Thread.currentThread().getName();

		System.out.println("##### [" + currentThreadName + " ] < " + taskId + "> Scheduled to run at : "
				+ dateFormatter.format(scheduledForRunningTime) + ", Actually started at : "
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
