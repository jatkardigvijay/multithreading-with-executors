package com.jbd.tuts.api.executors.naming;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.jbd.tuts.common.LoopTaskC;
import com.jbd.tuts.common.NamedThreadsFactory;

public class UsingCachedThreadPoolPart2 {

	public static void main(String[] args) throws InterruptedException {

		String currentThreadName = Thread.currentThread().getName();

		System.out.println(" [ " + currentThreadName + " ] Main thread starts here...");

		ExecutorService exeService = Executors.newCachedThreadPool(new NamedThreadsFactory());

		exeService.execute(new LoopTaskC()); // Task-1
		exeService.execute(new LoopTaskC()); // Task-2
		exeService.execute(new LoopTaskC()); // Task-3

		TimeUnit.MILLISECONDS.sleep(15);
		
		exeService.execute(new LoopTaskC()); // Task-4
		exeService.execute(new LoopTaskC()); // Task-5
		exeService.execute(new LoopTaskC()); // Task-6
		exeService.execute(new LoopTaskC()); // Task-7
		exeService.execute(new LoopTaskC()); // Task-8

		exeService.shutdown();

		System.out.println(" [ " + currentThreadName + " ] Main thread starts here...");

	}

}
