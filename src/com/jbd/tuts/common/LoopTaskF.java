package com.jbd.tuts.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class LoopTaskF implements Runnable {

	// this is the task
	private static int count = 0;
	private int instanceNumber;
	private String taskId;

	private final int DATA_SIZE = 100000;

	@Override
	public void run() {
		String currentThreadName = Thread.currentThread().getName();

		System.out.println("##### [" + currentThreadName + " ]<" + taskId + "> Starting ##### ");
		for (int i = 1;; i++) {
			System.out.println(" [ " + currentThreadName + "] <" + taskId + ">Tick tick : " + i);

			doSomeWork();

			if (Thread.interrupted()) {
				System.out.println("[" + currentThreadName + "] <" + taskId + "> Interrupted. Cancelling ...");
				break;
			}
		}
		System.out.println("[" + currentThreadName + "] <" + taskId + "> Retriving 'Interrupted' status again : "
				+ Thread.interrupted());
		System.out.println("***** [" + currentThreadName + "] <" + taskId + "> Done ***** ");
	}

	private void doSomeWork() {
		for (int i = 0; i < 2; i++) {
			Collections.sort(generateDataSet());
		}
	}

	private List<Integer> generateDataSet() {
		List<Integer> intList = new ArrayList<Integer>();
		Random randomGenerator = new Random();
		for (int i = 0; i < DATA_SIZE; i++) {
			intList.add(randomGenerator.nextInt(DATA_SIZE));
		}
		return intList;
	}

	public LoopTaskF() {
		this.instanceNumber = ++count;
		this.taskId = "LoopTaskF" + instanceNumber;
	}
}
