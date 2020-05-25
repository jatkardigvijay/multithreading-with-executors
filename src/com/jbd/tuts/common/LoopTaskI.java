package com.jbd.tuts.common;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class LoopTaskI implements Runnable {

	// this is the task
	private static int count = 0;
	private int instanceNumber;
	private String taskId;

	private CountDownLatch doneDownLatch;

	@Override
	public void run() {

		boolean isRunningInDaemonThread = Thread.currentThread().isDaemon();
		String threadType = isRunningInDaemonThread ? "DAEMON" : "USER";

		String currentThreadName = Thread.currentThread().getName();

		System.out.println("##### [" + currentThreadName + ", " + threadType + " ]<" + taskId + "> Starting ##### ");
		for (int i = 10; i > 0; i--) {
			System.out.println(" [ " + currentThreadName + "," + threadType + "] <" + taskId + ">Tick tick : " + i);

			try {
				TimeUnit.MILLISECONDS.sleep((long) Math.random() * 1000);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
		}
		System.out.println("***** [" + currentThreadName + "," + threadType + "] <" + taskId + "> Done ***** ");

		if (doneDownLatch != null) {
			doneDownLatch.countDown();
			System.out.println("***** [" + currentThreadName + "," + threadType + "] <" + taskId + "> Latch Count = "
					+ doneDownLatch.getCount());
		}
	}

	public LoopTaskI(CountDownLatch countDownLatch) {
		this.doneDownLatch = countDownLatch;
		this.instanceNumber = ++count;
		this.taskId = "LoopTaskI" + instanceNumber;
	}
}
