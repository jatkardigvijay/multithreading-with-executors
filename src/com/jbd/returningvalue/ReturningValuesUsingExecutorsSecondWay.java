package com.jbd.returningvalue;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.jbd.tuts.common.CalculationTaskB;
import com.jbd.tuts.common.LoopTaskA;
import com.jbd.tuts.common.NamedThreadsFactory;
import com.jbd.tuts.common.TaskResult;

public class ReturningValuesUsingExecutorsSecondWay {

	public static void main(String[] args) {

		String currentThread = Thread.currentThread().getName();
		System.out.println("[" + currentThread + "] Main thread starts here...");

		ExecutorService exeservice = Executors.newCachedThreadPool(new NamedThreadsFactory());

		// creating the instnce of completion service and providing it to tasks for
		// execution
		CompletionService<TaskResult<String, Integer>> tasks = new ExecutorCompletionService<TaskResult<String, Integer>>(
				exeservice);

		tasks.submit(new CalculationTaskB(2, 3, 2000));
		tasks.submit(new CalculationTaskB(3, 4, 1000));
		tasks.submit(new CalculationTaskB(4, 5, 500));

		// Future<?> result4 = exeservice.submit(new LoopTaskA());
		tasks.submit(new LoopTaskA(), new TaskResult<String, Integer>("LoopTaskA-1", 999));

		exeservice.shutdown();

		for (int i = 0; i < 4; i++) {
			try {
				System.out.println(tasks.take().get());
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}

		System.out.println("[" + currentThread + "] Main thread ends here...");
	}
}
