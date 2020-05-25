package com.jbd.tuts.common;

import java.util.concurrent.TimeUnit;

public class LoopTaskE implements Runnable {

	// this is the task
	private static int count = 0;
	private int instanceNumber;
	private String taskId;

	private volatile boolean shutdown = false;

	@Override
	public void run() {
		String currentThreadName = Thread.currentThread().getName();

		System.out.println("##### [" + currentThreadName + " ]<" + taskId + "> Starting ##### ");
		for (int i = 1;; i++) {
			System.out.println(" [ " + currentThreadName + "] <" + taskId + ">Tick tick : " + i);

			try {
				TimeUnit.MILLISECONDS.sleep((long) (Math.random() * 3000));
			} catch (InterruptedException e) {

				e.printStackTrace();
			}

			synchronized (this) {
				if (shutdown) {
					break;
				}
			}
		}
		System.out.println("***** [" + currentThreadName + "] <" + taskId + "> Done ***** ");
	}

	public void cancel() {
		System.out.println("***** [" + Thread.currentThread().getName() + "] <" + taskId + "> Shutting Down *****");

		synchronized (this) {
			this.shutdown = true;
		}
	}

	public LoopTaskE() {
		this.instanceNumber = ++count;
		this.taskId = "LoopTaskE" + instanceNumber;
	}
}
