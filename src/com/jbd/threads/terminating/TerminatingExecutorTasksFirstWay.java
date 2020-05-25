package com.jbd.threads.terminating;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.jbd.tuts.common.FactorialTaskA;
import com.jbd.tuts.common.LoopTaskE;
import com.jbd.tuts.common.NamedThreadsFactory;

public class TerminatingExecutorTasksFirstWay {

	public static void main(String[] args) throws Exception {
		String currentThread = Thread.currentThread().getName();
		System.out.println(" [" + currentThread + "] Main thread starts here ...");

		ExecutorService exeService = Executors.newCachedThreadPool(new NamedThreadsFactory());

		LoopTaskE task1 = new LoopTaskE();
		FactorialTaskA task2 = new FactorialTaskA(30, 1000);

		exeService.execute(task1);
		exeService.submit(task2);

		exeService.shutdown();

		TimeUnit.MILLISECONDS.sleep(1000);
		task1.cancel();
		task2.cancel();

		System.out.println(" [" + currentThread + "] Main thread ends here ...");
	}
}
