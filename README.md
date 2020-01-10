# The Monty Hall Problem In JAVA
A multi-threaded Java implementation of a solution to the Monty Hall problem, using the Monte Carlo method.

## Description of the problem
The **Monty Hall problem** is a **probabilistic problem** based on an American TV Show **Let's Make a Deal** from 1975 where the presenter host Monty says to the contestant that he has 3 doors, in which one has a car, other has a goat and the other has something without value. The contestant has to choose one door, then Monty opens one of the other doors that don’t have the car and he questions the contestant if he wants to stay with the some door or switch doors to the other that remains. The question of the problem is: can we analyse mathematically the problem and help the contestant choose the right door that maximizes his chances of winning the car? **IS there any advantage in switching doors?**
<br>

![Goat from wikipedia](/480px-Hausziege_04.jpg) <br>
Goat from Wikipedia

## How to attack this problem?
1. We can attack this problem with conditional probability theory and that is explained in the references bellow.
2. There is a second way to attack this problem, if we know how to program. We can write a program that is a computer simulation, make a high number of experiments using the Monte Carlo method and find the right answer to the problem. In this way we don't need to know conditional probability. We only need to know how the sequence of the steps of the problem, make the computer run all the different paths several times and count the outcomes. Then see the proportions of each outcome in regard to the overall number of experiments and find the correct probability of each outcome.    

## Explanation of the solution
We made two sets of experiments (simulations), in the first one we didn't switch doors and in the second, we switch doors and counted all the times that we found the car behind the door that we chose. <br>
<br>
The function run simulation has two arguments, the first is to specify if it will switch the doors in the middle, and the second specify the number of experiments (simulations) that will be made. <br>
<br>
After that we create a list of 3 doors, we randomly shuffle the list, choose randomly the door where the car is and the door that we will be going to choose. Then we take out one of the doors that don't have the car, and the show host Monty will open the door to show us. We switch doors if that option is active and finally we count the successes if we have chosen the door that contains the car. <br> 
<br>
The parallel multi-threaded version creates a thread pool of 16 threads and that runs each task in parallel on the pool. The results are communicated back to the main thread by two AtomicCounter objects, where they are written to the terminal.

## Results 
After executing the program we obtain the following results. 
* Percentage of successes if we maintain the same door **33.220%**
* Percentage of successes if we switch doors **66.691%**

## About the Monte Carlo method from Wikipedia
This method is very powerful because of its simplicity and the history of this method is interesting. The description of Ulam is fantastic and I will cite it here because it will help you not forgetting about this method. <br>

<br>
“In 1946, physicists at Los Alamos Scientific Laboratory were investigating radiation shielding and the distance that neutrons would likely travel through various materials. Despite having most of the necessary data, such as the average distance a neutron would travel in a substance before it collided with an atomic nucleus, and how much energy the neutron was likely to give off following a collision, the Los Alamos physicists were unable to solve the problem using conventional, deterministic mathematical methods. Stanislaw Ulam had the idea of using random experiments. He recounts his inspiration as follows: <br>

<br>
The first thoughts and attempts I made to practice [the Monte Carlo Method] were suggested by a question which occurred to me in 1946 as I was convalescing from an illness and playing solitaires. The question was what are the chances that a Canfield solitaire laid out with 52 cards will come out successfully? After spending a lot of time trying to estimate them by pure combinatorial calculations, I wondered whether a more practical method than "abstract thinking" might not be to lay it out say one hundred times and simply observe and count the number of successful plays. This was already possible to envisage with the beginning of the new era of fast computers, and I immediately thought of problems of neutron diffusion and other questions of mathematical physics, and more generally how to change processes described by certain differential equations into an equivalent form interpretable as a succession of random operations. Later [in 1946], I described the idea to John von Neumann, and we began to plan actual calculations. <br>
–Stanislaw Ulam <br>
<br>

Being secret, the work of von Neumann and Ulam required a code name. Von Neumann chose the name Monte Carlo. The name refers to the Monte Carlo Casino in Monaco where Ulam's uncle would borrow money to gamble. Using lists of "truly random" random numbers was extremely slow, but von Neumann developed a way to calculate pseudorandom numbers, using the middle-square method. Though this method has been criticized as crude, von Neumann was aware of this: he justified it as being faster than any other method at his disposal, and also noted that when it went awry it did so obviously, unlike methods that could be subtly incorrect. <br>
<br>

Monte Carlo methods were central to the simulations required for the Manhattan Project, though severely limited by the computational tools at the time. In the 1950s they were used at Los Alamos for early work relating to the development of the hydrogen bomb, and became popularized in the fields of physics, physical chemistry, and operations research. <br>
“ From Wikipedia of the Monte Carlo Method (see references)

## References
* [Monty Hall Problem - Numberphile](https://www.youtube.com/watch?v=4Lb-6rxZxx0)
* [Monty Hall Problem (Solutions using conditional probability) - Wikipedia ](http://en.wikipedia.org/wiki/Monty_Hall_problem)
* [Monte Carlo method - Wikipedia](http://en.wikipedia.org/wiki/Monte_Carlo_method)
* [Goat - Wikipedia](https://en.wikipedia.org/wiki/Goat)


## License:
MIT open source

## Have fun!
Best regards, <br>
Joao Nuno Carvalho <br>
