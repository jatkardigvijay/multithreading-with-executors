package com.fbd.executors.running;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.fbd.tuts.common.LoopTaskA;

public class UsingCachedThreadPool {

	public static void main(String[] args) {
		
		System.out.println("Main thread starts here");
		
		//the only difference between newFixedThreadPool and newCachedThreadPool is the method used on the class
		
		ExecutorService exeService = Executors.newCachedThreadPool();
		
		exeService.execute(new LoopTaskA());
		exeService.execute(new LoopTaskA());
		exeService.execute(new LoopTaskA());
		/*
		 * exeService.execute(new LoopTaskA()); exeService.execute(new LoopTaskA());
		 * exeService.execute(new LoopTaskA());
		 */
		
		exeService.shutdown();
		
		//exeService.execute(new LoopTaskA());
		
		System.out.println("Main thread starts here");
	}
}
