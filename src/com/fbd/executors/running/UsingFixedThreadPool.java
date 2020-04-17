package com.fbd.executors.running;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.fbd.tuts.common.LoopTaskA;

public class UsingFixedThreadPool {

	public static void main(String[] args) {
		System.out.println("Main thread starts");

		// initialize phase
		ExecutorService exeService = Executors.newFixedThreadPool(6);

		// service phase
		exeService.execute(new LoopTaskA());
		exeService.execute(new LoopTaskA());
		exeService.execute(new LoopTaskA());

		// above in newFixedThreadPool(3)--> we gave 3 tasks,
		// if we put new 3 tasks, then first 3 tasks should be completed for 4,5,6 to
		// start
		exeService.execute(new LoopTaskA());
		exeService.execute(new LoopTaskA());
		exeService.execute(new LoopTaskA());

		// we see the red button as the program was running
		// so we have to do the SHUTDOWN task
		exeService.shutdown();

		/* exeService.execute(new LoopTaskA()); */

		System.out.println("Main thread ends here");
	}
}
