package com.jbd.threads.aliveCheck;

import java.util.concurrent.TimeUnit;

import com.jbd.tuts.common.LoopTaskC;

public class ThreadsAliveCheck {

	public static void main(String[] args) throws InterruptedException {

		String currentThreadName = Thread.currentThread().getName();

		System.out.println("[" + currentThreadName + "] Main thread starts here ... ");

		Thread t1 = new Thread(new LoopTaskC(), "Thread - 1 ");
		Thread t2 = new Thread(new LoopTaskC(), "Thread - 2 ");

		boolean t1isALive = t1.isAlive();
		boolean t2isALive = t2.isAlive();

		System.out
				.println("[" + currentThreadName + "] Before starting: Is '" + t1.getName() + "' alive = " + t1isALive);

		System.out
				.println("[" + currentThreadName + "] Before starting: Is '" + t2.getName() + "' alive = " + t2isALive);

		t1.start();
		t2.start();

		while (true) {
			TimeUnit.MILLISECONDS.sleep(600);

			t1isALive = t1.isAlive();
			t2isALive = t2.isAlive();

			System.out.println("[" + currentThreadName + "] Is '" + t1.getName() + " '" + t1isALive);

			System.out.println("[" + currentThreadName + "] Is '" + t2.getName() + " '" + t2isALive);

			if (!t1isALive && !t2isALive) {
				break;
			}
		}
		System.out.println("[" + currentThreadName + "] Main thread starts here ... ");
	}
}
