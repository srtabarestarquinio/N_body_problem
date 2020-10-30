# N_body_problem 
## Assingment 1 - Data Structures and Algorithms
### Rose Tabares
This assignment tests your knowledge of Lists by requiring that you generate a model for the movement of celestial bodies in 2-dimensions.

Background:
  The n -body problem is the problem of taking the initial positions and velocities of “ n ” celestial bodies
  and predicting their subsequent positions and motions using Newtonian laws of motion and gravity.
  Where “ n ” is greater than 2, no closed-form solution exists to this problem, so a simulation (or
  something similar) is required to predict the precise positions of the celestial bodies in question.
  
Requirements:
  Your implementation must read the data file containing the details of several celestial bodies and
  simulate the motion and effects of gravity on the bodies. Your implementation must use two different
  implementations of the List data structure. Individual requirements for your implementation are detailed
  below.
  -Requirement 1: Read the data file
  -Requirement 2: Create, instantiate and maintain a List of celestial bodies
  -Requirement 3: Render each celestial body
  -Requirement 4: Change the position of the bodies
  
I have included a sample file including 3 Celestial Bodies called nbody_input.txt. Main program is in file called N_body.java which calls Lists.java which includes LinkedLists and ArrayLists and will use either one depending on the information provided in the sample file. 
In command line I have compiled Lists.java file, then compiled N_body.java file, and finally run file including sample file in arguments: "java N_body nbody_input.txt". 

  
  
