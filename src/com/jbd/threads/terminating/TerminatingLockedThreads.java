package com.jbd.threads.terminating;

import java.util.concurrent.TimeUnit;

import com.jbd.tuts.common.LoopTaskG;
import com.jbd.tuts.common.LoopTaskH;

public class TerminatingLockedThreads {

	public static void main(String[] args) throws InterruptedException {
		String currentThread = Thread.currentThread().getName();
		System.out.println("Main thread starts here...");

		LoopTaskG task1 = new LoopTaskG();
		LoopTaskG task2 = new LoopTaskG();
		LoopTaskG task3 = new LoopTaskG();

		LoopTaskH task4 = new LoopTaskH();
		LoopTaskH task5 = new LoopTaskH();

		Thread t1 = new Thread(task1, "MyThread - 1");
		t1.start();

		Thread t2 = new Thread(task2, "MyThread - 2");
		t2.start();

		Thread t3 = new Thread(task3, "MyThread - 3");
		t3.start();

		Thread t4 = new Thread(task4, "MyThread - 4");
		t4.start();

		Thread t5 = new Thread(task5, "MyThread - 5");
		t5.start();

		TimeUnit.MILLISECONDS.sleep(3000);

		System.out.println("[" + currentThread + "] Interrupting " + t1.getName() + " ... ");
		t1.interrupt();
		System.out.println("[" + currentThread + "] Interrupting " + t2.getName() + " ... ");
		t2.interrupt();
		System.out.println("[" + currentThread + "] Interrupting " + t3.getName() + " ... ");
		t3.interrupt();
		System.out.println("[" + currentThread + "] Interrupting " + t4.getName() + " ... ");
		t4.interrupt();
		System.out.println("[" + currentThread + "] Interrupting " + t5.getName() + " ... ");
		t4.interrupt();

		System.out.println("Main thread ends here...");
	}
}
