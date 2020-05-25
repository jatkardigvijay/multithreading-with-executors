package com.jbd.joiningthreads;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.jbd.tuts.common.LoopTaskI;
import com.jbd.tuts.common.NamedThreadsFactory;

public class JoiningExecutorThreads {

	public static void main(String[] args) {
		String currentThreadName = Thread.currentThread().getName();
		System.out.println("[" + currentThreadName + "] Main thread starts here ...");

		ExecutorService exeService = Executors.newCachedThreadPool(new NamedThreadsFactory());

		CountDownLatch doneSignal = new CountDownLatch(4);

		exeService.execute(new LoopTaskI(doneSignal));
		exeService.execute(new LoopTaskI(doneSignal));
		exeService.execute(new LoopTaskI(doneSignal));
		exeService.execute(new LoopTaskI(doneSignal));

		exeService.shutdown();

		try {
			doneSignal.await();
			System.out.println("[" + currentThreadName + "] " + currentThreadName + " Got the signal to continue ... ");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("[" + currentThreadName + "] Main thread starts here ...");
	}
}
