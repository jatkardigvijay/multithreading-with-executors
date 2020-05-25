package com.jbd.exceptionhandling;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.jbd.tuts.common.ExceptionLeakingTask;
import com.jbd.tuts.common.ThreadFactoryWithExceptionHandler;

public class HandlingExecutorUncaughtExceptionsDifferentlyPerThread {

	public static void main(String[] args) {
		String currentThreadName = Thread.currentThread().getName();
		System.out.println("[" + currentThreadName + "] Main thread starts here ...");

		ExecutorService exeService = Executors.newCachedThreadPool(new ThreadFactoryWithExceptionHandler());

		exeService.execute(new ExceptionLeakingTask());
		exeService.execute(new ExceptionLeakingTask());
		exeService.execute(new ExceptionLeakingTask());
		exeService.execute(new ExceptionLeakingTask());

		exeService.shutdown();

		System.out.println("[" + currentThreadName + "] Main thread starts here ...");
	}
}
