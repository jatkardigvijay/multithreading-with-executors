package com.jbd.threads.aliveCheck;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

import com.jbd.tuts.common.CalculationTaskA;
import com.jbd.tuts.common.LoopTaskC;
import com.jbd.tuts.common.NamedThreadsFactory;

public class ExecutorThreadsAliveCheck {

	public static void main(String[] args) throws InterruptedException, ExecutionException {

		// here we submit two tasks. one is runnable and other is callable
		// LoopTaskC as runnable and CalculationTaskA as callable

		String currentThreadName = Thread.currentThread().getName();

		System.out.println("[" + currentThreadName + "] Main thread starts here ... ");

		ExecutorService exeService = Executors.newCachedThreadPool(new NamedThreadsFactory());

		// runnable does have any return type... so ? used
		Future<?> f1 = exeService.submit(new LoopTaskC());

		Future<Integer> f2 = exeService.submit(new CalculationTaskA(3, 4, 700));

		FutureTask<?> ft3 = new FutureTask<Void>(new LoopTaskC(), null);
		FutureTask<Integer> ft4 = new FutureTask<Integer>(new LoopTaskC(), 999);
		// bellow is a callable future task
		FutureTask<Integer> ft5 = new FutureTask<Integer>(new CalculationTaskA(4, 5, 500));

		exeService.execute(ft3);
		exeService.execute(ft4);
		exeService.execute(ft5);

		exeService.shutdown();

		for (int i = 0; i <= 5; i++) {
			TimeUnit.MILLISECONDS.sleep(600);

			System.out.println("[" + currentThreadName + "] ITR-" + i + "-> is 'LoopTaskC -1' done =" + f1.isDone());

			System.out.println("[" + currentThreadName + "] ITR-" + i + "-> is 'CalcTaskA -1' done =" + f2.isDone());

			System.out.println("[" + currentThreadName + "] ITR-" + i + "-> is 'LoopTaskC -2' done =" + ft3.isDone());

			System.out.println("[" + currentThreadName + "] ITR-" + i + "-> is 'LoopTaskC -3' done =" + ft4.isDone());

			System.out.println("[" + currentThreadName + "] ITR-" + i + "-> is 'CalcTaskA -2' done =" + ft5.isDone());
		}

		System.out.println("\n$$$$$ [" + currentThreadName + "] Retriving results now ... $$$$$");

		System.out.println("[" + currentThreadName + "] 'LoopTaskC -1' result = " + f1.get());

		System.out.println("[" + currentThreadName + "] 'CalcTaskA -1' result = " + f2.get());

		System.out.println("[" + currentThreadName + "] 'LoopTaskC -2' result = " + ft3.get());

		System.out.println("[" + currentThreadName + "] 'LoopTaskC -3' result = " + ft4.get());

		System.out.println("[" + currentThreadName + "] 'CalcTaskA -2' result = " + ft5.get());

		System.out.println("[" + currentThreadName + "] Main thread ends here ... ");
	}
}
