package com.jbd.tuts.common;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class CalculationTaskD implements Callable<Integer> {

	private int a;
	private int b;
	private long sleepTime;

	private static int count = 0;
	private int instanceNumber;
	private String taskId;

	private static SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss.SSS");

	public CalculationTaskD(int a, int b, long sleepTime) {
		super();
		this.a = a;
		this.b = b;
		this.sleepTime = sleepTime;

		// for identity to the task
		this.instanceNumber = ++count;
		this.taskId = "CalcTaskD" + instanceNumber;
	}

	@Override
	public Integer call() throws Exception {
		Date startTime = new Date();
		String currentThreadName = Thread.currentThread().getName();

		System.out.println("##### [" + currentThreadName + " ] < " + taskId + "> Started at : "
				+ dateFormatter.format(startTime) + "#####");

		TimeUnit.MILLISECONDS.sleep(sleepTime);

		System.out.println("***** [" + currentThreadName + "]<" + taskId + "> Finished at : "
				+ dateFormatter.format(new Date()) + "*****\n");

		return a + b;
	}

}
