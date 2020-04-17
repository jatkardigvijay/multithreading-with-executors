package com.fbd.threadrenaming;

import java.util.concurrent.TimeUnit;

import com.fbd.tuts.common.LoopTaskC;

public class NamingThreadsSecondWay {

	public static void main(String[] args) {

		String currentThreadName = Thread.currentThread().getName();

		System.out.println(" [ " + currentThreadName + " ] Main thread starts here...");
		// using the 2 arg constructor of thread
		new Thread(new LoopTaskC(), "MyThread-1").start();

		Thread t2 = new Thread(new LoopTaskC());
		// t2.setName("MyThread-2");
		t2.start();

		try {
			TimeUnit.MILLISECONDS.sleep(600);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		t2.setName("MyThread-2");

		System.out.println("[" + currentThreadName + "]Main thread ends here...");
	}
}
