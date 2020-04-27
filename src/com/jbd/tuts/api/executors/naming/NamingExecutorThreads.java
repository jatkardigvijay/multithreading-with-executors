package com.jbd.tuts.api.executors.naming;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.jbd.tuts.common.LoopTaskC;
import com.jbd.tuts.common.NamedThreadFactory;

public class NamingExecutorThreads {

	public static void main(String[] args) {

		String currentThreadName = Thread.currentThread().getName();

		System.out.println(" [ " + currentThreadName + " ] Main thread starts here...");

		ExecutorService exeService = Executors.newCachedThreadPool(new NamedThreadFactory());

		exeService.execute(new LoopTaskC());
		exeService.execute(new LoopTaskC());
		exeService.execute(new LoopTaskC());

		System.out.println("[" + currentThreadName + "]Main thread ends here...");
	}

}
