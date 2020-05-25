package com.jbd.threads.terminating;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import com.jbd.tuts.common.CalculationTaskB;
import com.jbd.tuts.common.FactorialTaskA;
import com.jbd.tuts.common.LoopTaskA;
import com.jbd.tuts.common.LoopTaskF;
import com.jbd.tuts.common.NamedThreadsFactory;
import com.jbd.tuts.common.TaskResult;

public class TerminatingAllExecutorTasks {

	public static void main(String[] args) throws Exception {
		String currentThreadName = Thread.currentThread().getName();
		System.out.println("[" + currentThreadName + "] Main thread starts here ...");

		ExecutorService exeService = Executors.newCachedThreadPool(new NamedThreadsFactory());

		// We have
		// blocked runnable
		// non blocked runnable
		// blocked callable
		// non blocked callable
		// are our tasks here

		LoopTaskA task1 = new LoopTaskA();
		LoopTaskF task2 = new LoopTaskF();
		FactorialTaskA task3 = new FactorialTaskA(30, 500);
		// CalculationTaskC task4 = new CalculationTaskC();
		CalculationTaskB task5 = new CalculationTaskB(2, 3, 9000);

		exeService.execute(task1);
		exeService.execute(task2);
		Future<Long> t3Future = exeService.submit(task3);
		// Future<Long> t4Future=exeService.submit(tas4);
		Future<TaskResult<String, Integer>> t5Future = exeService.submit(task5);

		TimeUnit.MILLISECONDS.sleep(1000);

		exeService.shutdownNow();

		System.out.println("[" + currentThreadName + "] All Threads Terminated"
		// if use awaitTermination(5000) instead of 50, loop task A also terminates and
		// we get output AllThreads Terminated = True
				+ exeService.awaitTermination(5000, TimeUnit.MILLISECONDS));

		System.out.println("[" + currentThreadName + "] 'FactorialTaskB-1' Result = " + t3Future.get());

		// System.out.println("[" + currentThreadName + "] 'CalcTaskC-1' Result = " +
		// t4Future.get());

		try {
			System.out.println("[" + currentThreadName + "] 'CalcTaskB-1' Result = " + t5Future.get());

		} catch (ExecutionException e1) {
			System.out.println(
					"[" + currentThreadName + "] 'CalcTaskB-1' Reesult = Got Execution Exception. The cause is : \n");
			e1.getCause().printStackTrace();
		}

		System.out.println("[" + currentThreadName + "] Main thread ends here ...");
	}
}
