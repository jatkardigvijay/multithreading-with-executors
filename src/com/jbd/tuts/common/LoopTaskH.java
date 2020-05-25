package com.jbd.tuts.common;

import java.util.concurrent.TimeUnit;

public class LoopTaskH implements Runnable {

	// this is the task
	private static int count = 0;
	private int instanceNumber;
	private String taskId;

	private boolean sleepInterrupted = false;

	@Override
	public void run() {
		String currentThreadName = Thread.currentThread().getName();

		System.out.println("##### [" + currentThreadName + " ]<" + taskId + "> Starting ##### ");
		for (int i = 1;; i++) {
			System.out.println(" [ " + currentThreadName + "] <" + taskId + ">Tick tick : " + i);

			try {
				TimeUnit.MILLISECONDS.sleep((long) (Math.random() * 3000));
			} catch (InterruptedException e) {

				System.out.println("***** [" + currentThreadName + "] <" + taskId + "> Sleep interrupted."
						+ "SETTING THE FLAG ...");

				sleepInterrupted = true;
			}

			doSomeMoreWork();
			if (sleepInterrupted || Thread.interrupted()) {
				System.out.println("[" + currentThreadName + "] <" + taskId + "> INTERRUPTED. Cancelling ...");
				break;
			}

		}
		System.out.println("***** [" + currentThreadName + "] <" + taskId + "> Done ***** ");
	}

	private void doSomeMoreWork() {
		System.out.println("[" + Thread.currentThread().getName() + "] <" + taskId + "> DOING SOME MORE WORK");
	}

	public LoopTaskH() {
		this.instanceNumber = ++count;
		this.taskId = "LoopTaskH" + instanceNumber;
	}
}
