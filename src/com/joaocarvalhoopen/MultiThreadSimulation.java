/******************************************************************************
 *                                                                            *
 *  The Monty Hall Problem in Java                                            *
 *                                                                            *
 ******************************************************************************
 * Author: Joao Nuno Carvalho                                                 *
 * Date: 2019.12.22                                                           *
 * License: MIT Open Source License                                           *
 * Description: A multi-threaded Java implementation using the Monte Carlo    *
 *              method of a solution to the Monty Hall problem.               *
 *                                                                            *
 * See the project page for an extended description, explanation and          *
 * references at: https://github.com/joaocarvalhoopen                         *
 ******************************************************************************
 */

package com.joaocarvalhoopen;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

import static java.util.Collections.shuffle;

public class MultiThreadSimulation {
    private static final int MYTHREADS = 16;

    public static void runExperiment() {
        System.out.println("Multi thread simulation");
        int numSimulationsForEachThread = 1_000_000;
        AtomicLong numSuccessDontChangeDoor = new AtomicLong(0);
        AtomicLong numSuccessChangeDoor = new AtomicLong(0);
        long numTotalExperiments = 0;

        ExecutorService executor = Executors.newFixedThreadPool(MYTHREADS);

        for (int i = 0; i < MYTHREADS; i++) {
            numTotalExperiments += (long) (numSimulationsForEachThread / 2);
            boolean switchDoor = false;
            if (i < (MYTHREADS / 2))
                switchDoor = true;
            Runnable worker = new MyRunnable(switchDoor,
                                             numSimulationsForEachThread,
                                             numSuccessDontChangeDoor,
                                             numSuccessChangeDoor);
            executor.execute(worker);
        }
        executor.shutdown();
        // Wait until all threads are finish
        while (!executor.isTerminated()) {

        }

        System.out.println("\tFinished all threads");
        // System.out.println( "numSuccessDontChangeDoor.get() : " + numSuccessDontChangeDoor.get() );
        // System.out.println( "numSuccessChangeDoor.get() : " + numSuccessChangeDoor.get() );
        // System.out.println( "numTotalExperiments : " + numTotalExperiments );
        double result_1 = calcProbabilities(numSuccessDontChangeDoor.get(), numTotalExperiments);
        System.out.println(
                String.format("\tPercentage of successes if we maintain the same door: %.3f %%", result_1));
        double result_2 = calcProbabilities(numSuccessChangeDoor.get(), numTotalExperiments);
        System.out.println(
                String.format("\tPercentage of successes if we switch doors : %.3f %%", result_2));
    }

    private static double calcProbabilities(long numSuccesses, long numTimes){
        return ((double) numSuccesses / numTimes) * 100;
    }

    public static class MyRunnable implements Runnable {
        private final boolean switchDoor;
        private final int numTimes;
        private AtomicLong numSuccessDontChangeDoor;
        private AtomicLong numSuccessChangeDoor;

        MyRunnable(boolean switchDoor,
                   int numSimulationsForEachThread,
                   AtomicLong numSuccessDontChangeDoor,
                   AtomicLong numSuccessChangeDoor ) {
            this.switchDoor = switchDoor;
            this.numTimes = numSimulationsForEachThread;
            this.numSuccessDontChangeDoor = numSuccessDontChangeDoor;
            this.numSuccessChangeDoor = numSuccessChangeDoor;
        }

        @Override
        public void run() {
            int numSuccesses = 0;
            List<Integer> options = new ArrayList<Integer>();
            Random rand = new Random();
            for (int i = 0; i < numTimes; i++) {
                options.clear();
                options.add(1);
                options.add(2);
                options.add(3);
                shuffle(options);
                int car = choice(rand, options);
                int selected = choice(rand, options);
                for (int j = 2; j >= 0; j--) {
                    //System.out.println("j: " + j );
                    if ((options.get(j) != car) && (options.get(j) != selected)) {
                        options.remove(options.get(j));
                        break;
                    }
                }
                if (switchDoor) {
                    options.remove((Integer)selected);
                    selected = choice(rand, options);
                }
                if (selected == car) {
                    if (switchDoor)
                        numSuccessChangeDoor.incrementAndGet();
                    else
                        numSuccessDontChangeDoor.incrementAndGet();
                }
            }
        }

        private int choice(Random rand, List<Integer> options){
            int randomIndex = rand.nextInt(options.size());
            return options.get(randomIndex);
        }

    }
}

