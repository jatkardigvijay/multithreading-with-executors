package com.jbd.threads.daemonThreads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.jbd.tuts.common.DaemonsThreadsFactory;
import com.jbd.tuts.common.LoopTaskD;

public class DaemonThreadsUsingExecutors {

	public static void main(String[] args) {

		String currentThreadName = Thread.currentThread().getName();

		System.out.println("[" + currentThreadName + "] Main thread starts here ...");

		ExecutorService exeService = Executors.newCachedThreadPool(new DaemonsThreadsFactory());

		exeService.execute(new LoopTaskD(100));
		exeService.execute(new LoopTaskD(200));
		exeService.execute(new LoopTaskD(300));
		exeService.execute(new LoopTaskD(400));

		exeService.shutdown();

		System.out.println("[" + currentThreadName + "] Main thread starts here ...");
	}
}
