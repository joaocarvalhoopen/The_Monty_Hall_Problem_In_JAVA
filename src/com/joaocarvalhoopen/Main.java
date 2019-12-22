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

public class Main {

    public static void main(String[] args) {
        System.out.println("####################################");
        System.out.println("#  The Monty Hall problem in JAVA  #");
        System.out.println("####################################");

        // Single threaded solution.
	    SingleThreadSimulation oneSim = new SingleThreadSimulation();
	    oneSim.runExperiment();

        // Multi-threaded solution.
	    MultiThreadSimulation.runExperiment();
    }
}
