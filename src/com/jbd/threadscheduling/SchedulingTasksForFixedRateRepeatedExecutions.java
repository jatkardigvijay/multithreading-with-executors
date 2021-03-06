package com.jbd.threadscheduling;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

import com.jbd.tuts.common.ScheduledTaskA;
import com.jbd.tuts.utils.TimeUtils;

public class SchedulingTasksForFixedRateRepeatedExecutions {

	private static SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss.SSS");

	public static void main(String[] args) throws InterruptedException {
		String currentThreadName = Thread.currentThread().getName();
		System.out.println("[" + currentThreadName + "] Main thread starts here ...");

		Timer timer = new Timer("Timer - Thread", true);
		Date currentTime = new Date();

		System.out.println("[" + currentThreadName + "] Current Time : " + currentTime);

		Date scheduledTime = TimeUtils.getFutureTime(currentTime, 3000);
		long intervalMillis = 2000;

		timer.scheduleAtFixedRate(new ScheduledTaskA(1000), scheduledTime, intervalMillis);

		System.out.println(
				"[" + currentThreadName + "] Task-1 first run schedueled for " + dateFormatter.format(scheduledTime)
						+ " and then repeatedly at the interval of every " + intervalMillis / 1000 + " seconds !");
		long delayMillis = 4000;
		long intervalMillis2 = 2000;
		timer.scheduleAtFixedRate(new ScheduledTaskA(1000), delayMillis, intervalMillis2);
		System.out.println("[" + currentThreadName + "] Task-2 first run scheduled " + delayMillis / 1000
				+ " seconds after " + dateFormatter.format(currentTime) + "and then repeatedly at an interval of "
				+ intervalMillis2 / 1000 + " seconds ! ");

		TimeUnit.MILLISECONDS.sleep(16000);
		System.out.println("[" + currentThreadName + "] Cancelling the timer now ...");
		timer.cancel();

		System.out.println("[" + currentThreadName + "] Main thread ends here ...");

	}
}
