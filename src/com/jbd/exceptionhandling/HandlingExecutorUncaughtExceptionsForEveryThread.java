package com.jbd.exceptionhandling;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.jbd.tuts.common.ExceptionLeakingTask;
import com.jbd.tuts.common.ThreadExceptionHandler;

public class HandlingExecutorUncaughtExceptionsForEveryThread {

	public static void main(String[] args) {
		String currentThreadName = Thread.currentThread().getName();
		System.out.println("[" + currentThreadName + "] Main thread starts here ...");

		Thread.setDefaultUncaughtExceptionHandler(new ThreadExceptionHandler("Default Handler"));

		ExecutorService exeService1 = Executors.newCachedThreadPool();
		exeService1.execute(new ExceptionLeakingTask());
		exeService1.execute(new ExceptionLeakingTask());
		exeService1.execute(new ExceptionLeakingTask());

		ExecutorService exeService2 = Executors.newCachedThreadPool();
		exeService2.execute(new ExceptionLeakingTask());
		exeService2.execute(new ExceptionLeakingTask());
		exeService2.execute(new ExceptionLeakingTask());

		exeService1.shutdown();
		exeService2.shutdown();

		System.out.println("[" + currentThreadName + "] Main thread ends here ...");
	}

}
