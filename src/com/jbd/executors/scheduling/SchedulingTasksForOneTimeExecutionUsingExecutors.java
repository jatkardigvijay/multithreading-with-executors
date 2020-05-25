package com.jbd.executors.scheduling;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import com.jbd.tuts.common.CalculationTaskD;
import com.jbd.tuts.common.NamedThreadsFactory;
import com.jbd.tuts.common.ScheduledTaskB;

public class SchedulingTasksForOneTimeExecutionUsingExecutors {

	private static SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss.SSS");

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		String currentThreadName = Thread.currentThread().getName();
		System.out.println("[" + currentThreadName + "] Main thread starts here ...");

		ScheduledExecutorService exeService = Executors.newScheduledThreadPool(3,new NamedThreadsFactory());

		System.out.println("[" + currentThreadName + "] Current Time : " + dateFormatter.format(new Date()));

		ScheduledFuture<?> scheduledFuture1 = exeService.schedule(new ScheduledTaskB(3000), 4, TimeUnit.SECONDS);
		ScheduledFuture<Integer> scheduledFuture2 = exeService.schedule(new CalculationTaskD(2, 3, 3000), 6,
				TimeUnit.SECONDS);
		exeService.schedule(new ScheduledTaskB(0), 8, TimeUnit.SECONDS);
		ScheduledFuture<?> scheduledFuture4 = exeService.schedule(new CalculationTaskD(2, 3, 0), 10, TimeUnit.SECONDS);
		
		exeService.shutdown();
		
		scheduledFuture1.cancel(true);
		scheduledFuture2.cancel(true);

		System.out.println("[" + currentThreadName + "] Retrieving results now ... \n");

	//	System.out.println("[" + currentThreadName + "] Task-1 result = " + scheduledFuture1.get() + "\n");
	//	System.out.println("[" + currentThreadName + "] Task-2 result = " + scheduledFuture2.get() + "\n");
		System.out.println("[" + currentThreadName + "] Task-4 result = " + scheduledFuture4.get() + "\n");

		System.out.println("[" + currentThreadName + "] Main thread ends here ...");
	}
}
