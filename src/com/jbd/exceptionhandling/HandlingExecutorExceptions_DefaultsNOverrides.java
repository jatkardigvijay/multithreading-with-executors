package com.jbd.exceptionhandling;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.jbd.tuts.common.ExceptionLeakingTask;
import com.jbd.tuts.common.ThreadExceptionHandler;
import com.jbd.tuts.common.ThreadFactoryWithExceptionHandlerAlternator;

public class HandlingExecutorExceptions_DefaultsNOverrides {

	// specific thread handler overrides default thread handler
	public static void main(String[] args) {
		String currentThreadName = Thread.currentThread().getName();
		System.out.println("[" + currentThreadName + "] Main thread starts here ... ");

		Thread.setDefaultUncaughtExceptionHandler(new ThreadExceptionHandler());

		ExecutorService exeService = Executors.newCachedThreadPool(new ThreadFactoryWithExceptionHandlerAlternator());

		exeService.execute(new ExceptionLeakingTask());
		exeService.execute(new ExceptionLeakingTask());
		exeService.execute(new ExceptionLeakingTask());
		exeService.execute(new ExceptionLeakingTask());

		exeService.shutdown();

		System.out.println("[" + currentThreadName + "] Main thread ends here ... ");
	}
}
