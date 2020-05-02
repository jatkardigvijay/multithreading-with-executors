package com.jbd.tuts.common;

import java.util.concurrent.TimeUnit;

public class LoopTaskD implements Runnable {

	// this is the task
	private static int count = 0;
	private int instanceNumber;
	private String taskId;

	private long sleepTime;

	@Override
	public void run() {

		boolean isRunningInDaemonThread = Thread.currentThread().isDaemon();
		String threadType = isRunningInDaemonThread ? "DAEMON" : "USER";

		String currentThreadName = Thread.currentThread().getName();

		System.out.println("##### [" + currentThreadName + ", " + threadType + " ]<" + taskId + "> Starting ##### ");
		for (int i = 10; i > 0; i--) {
			System.out.println(" [ " + currentThreadName + "," + threadType + "] <" + taskId + ">Tick tick : " + i);

			try {
				TimeUnit.MILLISECONDS.sleep(sleepTime);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
		}
		System.out.println("***** [" + currentThreadName + "," + threadType + "] <" + taskId + "> Done ***** ");
	}

	public LoopTaskD(long sleep) {
		this.sleepTime = sleep;
		this.instanceNumber = ++count;
		this.taskId = "LoopTaskD" + instanceNumber;
	}
}
