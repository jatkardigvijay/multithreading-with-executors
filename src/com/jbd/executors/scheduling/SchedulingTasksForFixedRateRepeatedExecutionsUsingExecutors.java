package com.jbd.executors.scheduling;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import com.jbd.tuts.common.NamedThreadsFactory;
import com.jbd.tuts.common.ScheduledTaskB;

public class SchedulingTasksForFixedRateRepeatedExecutionsUsingExecutors {

	private static SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss.SSS");

	public static void main(String[] args) throws InterruptedException {

		String currentThreadName = Thread.currentThread().getName();
		System.out.println("[" + currentThreadName + "] Main thread starts here ...");

		ScheduledExecutorService exeService = Executors.newScheduledThreadPool(3, new NamedThreadsFactory());

		System.out.println("[" + currentThreadName + "] Current time : " + dateFormatter.format(new Date()));

		ScheduledFuture<?> scheduledFuture1 = exeService.scheduleAtFixedRate(new ScheduledTaskB(1000), 4, 2,
				TimeUnit.SECONDS);

		ScheduledFuture<?> scheduledFuture2 = exeService.scheduleAtFixedRate(new ScheduledTaskB(3000), 4, 2,
				TimeUnit.SECONDS);

	//	scheduledFuture2.cancel(true);
		/*
		 * for (int i = 0; i < 10; i++) { System.out.print("[" + currentThreadName +
		 * "] Next run of task-1 scheduled at approx."); Date scheduledTime =
		 * TimeUtils.getFutureTime(new Date(),
		 * scheduledFuture1.getDelay(TimeUnit.MILLISECONDS));
		 * System.out.println(dateFormatter.format(scheduledTime));
		 * 
		 * TimeUnit.MILLISECONDS.sleep(3000); }
		 */

		TimeUnit.MILLISECONDS.sleep(15000);

		exeService.shutdown();

		System.out.println("[" + currentThreadName + "] Main thread ends here ...");
	}
}
