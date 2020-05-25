package com.jbd.threads.terminating;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import com.jbd.tuts.common.FactorialTaskB;
import com.jbd.tuts.common.LoopTaskA;
import com.jbd.tuts.common.LoopTaskG;
import com.jbd.tuts.common.NamedThreadsFactory;

public class TerminatingBlockedExecutorTasks {

	public static void main(String[] args) throws InterruptedException {
		String currentThreadName = Thread.currentThread().getName();
		System.out.println("[" + currentThreadName + "] Main thread starts here ...");

		ExecutorService exeService = Executors.newCachedThreadPool(new NamedThreadsFactory());

		LoopTaskA task1 = new LoopTaskA();
		LoopTaskG task2 = new LoopTaskG();
		FactorialTaskB task3 = new FactorialTaskB(30, 500);

		Future<?> f1 = exeService.submit(task1);
		Future<?> f2 = exeService.submit(task2);
		Future<?> f3 = exeService.submit(task3);

		exeService.shutdown();

		TimeUnit.MILLISECONDS.sleep(2000);

		System.out.println("[" + currentThreadName + "] Invoking cancel() on all tasks ... ");
		f1.cancel(true);
		f2.cancel(true);
		f3.cancel(true);

		System.out.println("[" + currentThreadName + "] Main thread ends here ...");
	}
}
