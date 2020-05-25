package com.jbd.tuts.common;

import java.util.concurrent.TimeUnit;

public class LoopTaskG implements Runnable {

	// this is the task
	private static int count = 0;
	private int instanceNumber;
	private String taskId;

	@Override
	public void run() {
		String currentThreadName = Thread.currentThread().getName();

		System.out.println("##### [" + currentThreadName + " ]<" + taskId + "> Starting ##### ");
		for (int i = 1;; i++) {
			System.out.println(" [ " + currentThreadName + "] <" + taskId + ">Tick tick : " + i);

			try {
				TimeUnit.MILLISECONDS.sleep((long) (Math.random() * 3000));
			} catch (InterruptedException e) {

				System.out.println(
						"***** [" + currentThreadName + "] <" + taskId + "> Sleep interrupted. Cancelling ... ");
				break;
			}

		}
		System.out.println("***** [" + currentThreadName + "] <" + taskId + "> Done ***** ");
	}

	public LoopTaskG() {
		this.instanceNumber = ++count;
		this.taskId = "LoopTaskG" + instanceNumber;
	}
}
