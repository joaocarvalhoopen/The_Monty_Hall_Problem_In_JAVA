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

        import static java.util.Collections.shuffle;

public class SingleThreadSimulation {
    private Random rand = null;

    public SingleThreadSimulation() {

    }

    private int choice(List<Integer> options){
        int randomIndex = rand.nextInt(options.size());
        return options.get(randomIndex);
    }

    private double run(boolean switchDoor, int num_times) {
        int num_successes = 0;
        List<Integer> options = new ArrayList<Integer>();
        rand = new Random();
        for (int i = 0; i < num_times; i++) {
            options.clear();
            options.add(1);
            options.add(2);
            options.add(3);
            shuffle(options);
            int car = choice(options);
            int selected = choice(options);
            for (int j = 2; j >= 0; j--) {
                if ((options.get(j) != car) && (options.get(j) != selected)) {
                    options.remove(options.get(j));
                    break;
                }
            }
            if (switchDoor) {
                options.remove((Integer)selected);
                selected = choice(options);
            }
            if (selected == car) {
                num_successes++;
            }
        }
        return ((double) num_successes / num_times) * 100;
    }

    public void runExperiment() {
        System.out.println("Single thread simulation");
        int num_simulation = 1000000;
        boolean switchDoor = false;
        double result_1 = run(switchDoor, num_simulation);
        System.out.println(
                String.format("\tPercentage of successes if we maintain the same door: %.3f %%", result_1));
        switchDoor = true;
        double result_2 = run(switchDoor, num_simulation);
        System.out.println(
                String.format("\tPercentage of successes if we switch doors : %.3f %%", result_2));
    }

}
