## Introduction
Cells can be used in cellular automata to model a variety of natural phenomena including the spread of fire and self segregation of races in a neighborhood. In the model, the state of a cell may update based on the current state of a cell in relation to the current state of its neighboring cells. We aim to design a framework such that a new type of cell can be added with situation-specific logic to model a new algorithm and view the way states of neighbors affect the spread of cells over time.

A graphical user interface allows the user to select which situation to model and how large to make the grid of cells. States of cells will be initialized and the user will be allowed to start an animation at a selected step speed to demonstrate the behavior and interaction of cells on the screen. 

A cell will know the state of all the cells around it and be able to update its own state during each step based on logic given to its situation-specific logic. This makes the cell logic very flexible because the number of  neighbors will not matter, for example, if it is in the corner, it will only have 3 neighbors when in the middle it may have 8. It will be simple to add methods that check that all logic for that situation of cells is satisfied. 

The majority of the design will be closed to the user other than selecting the type of model to run and the specifications for running that model. Another programmer would be able to add a new type of cell situation to model but would not be able to change the upper framework that runs the simulation for the cell situation.

## Overview
### Classes
The fundamental unit in the simulation is `Cell`. In the `Cell` class, the variables are its neighbors (kept in an `ArrayList`), its current state and future state (because of the need to update for the next round) and its UI component object of type `CellGraphic`. Its methods, besides getters and setters, include a `checkChangeState` method that returns whether the current cell needs to change its state. The class `Cell` is abstract, so for each algorithm, there would be another cell class that extends `Cell` in that algorithm package. The extended subclass would override the `checkChangeState` method because of the algorithmic difference.

`Runner` is the class that controls the behavior of all `Cell`s in the simulation. It keeps a collection of all `Cell`s, which is given by `Builder` when the simulation is initialized. It also has a `checkChangeAllCells` method that loops through all cells and call each cell’s `checkChangeState` method, if the method returns `true`, it then calls `setState` method to update the states of cells. `checkChangeAllCells` should be called whenever the next step is requested.

`Builder` is the class used to initialize the grid of the simulation. It should accept parameters for the grid such as size, and return a `Runner` object with a collection of cells that it built. This class is likely to be abstract, because the parameters for different algorithm grids are probably different, with common ones including the size.

`ProgScene` is an abstract class for scenes in the program. It will only provide one method called `initScene` which returns a scene to be displayed. Any scene in the program should extend this class.

`CellGraphic` is the UI component class for `Cell`. It will have attributes like the shape of the cell and the color and position. Methods are mostly setters and getters.

`Main` is the main class of the program. It keeps reference to all other helpers such as `Runner`, `Builder` and `Decoder`. It has methods like `presentScene`, `decodeXML`, `buildGrid`, `oneStep` that delegate to its helpers. It also has a method `step` that executes each step of the simulation.

`Decoder` is the tool class for decoding XML files. It will have a String attribute `fileName` and when `decodeXML` method is called, it reads from the XML file and returns the parameters read from the file in the form of an object.


## User Interface
There will be two main scenes in the program: the welcome scene and the simulation scene. The welcome scene lets the user choose a simulation from a dropdown menu. A dropdown menu is chosen because later we might add more kinds of simulation. Then we won’t need to worry about the layout. After the user clicks to select one of the simulation algorithms. The scene switches to the simulation scene, where there is a grid, control buttons (start, step, stop, reset), a label prompt, and other controls unique to each algorithm. There will also be a button for the user to switch to another kind of simulation.

An error popup scene will also be implemented for all kinds of error notifications. This will likely be a separate stage where it will disappear after the user clicks ‘OK’.

## Design Details
When the Main.java file is run, a stage will be created that displays a window prompting the user to select a situation model to simulate from a drop down menu. In this new window, the user will be able to choose the dimensions of the grid or number of cells and their size. There will be buttons allowing the user to step through the simulation or run the simulation at a specified step speed.

The simulation will create a grid of cells. The abstract superclass Cell will be a framework for all subclasses which will represent the situation-specific cell models. The abstract superclass Cell will include an ArrayList<Cell> named neighbors. It will also have variables to hold the current state and its future state. There will be a method updateState() that can set the currentState to the currently stored futureState at the end of a step and there will be an abstract method checkChangeState() that will be called on each step but will be defined by the subclass. In this subclass, logic will be implemented based on the situation, for example a SchillingCell would have methods that check satisfaction and look for a place to move. This would be called on each step and used to update the futureState variable.

On each step, the Runner class which contains an array of all the Cells being reasoned with will call checkChangeState() for each Cell and update their states at the end of the step with updateAllCells().

Because Cell is created as an abstract class and situation-specific logic will come from a subclass a large degree of freedom is granted by this abstraction. A new user can easily create a new simulation beyond the three specified. Simulations will be organized in a separate package. For example, a Schilling simulation would be contained in a package with a Cell subclass containing necessary methods for reasoning about a satisfied Cell and where it may move and a Builder class that will specify which user controls are granted, which would include %satisfied, number of empty cells, etc.

The visual representation of the grid will be kept separate from the Cells themselves. A Cell will have a location that does not change. We think of Cells as a physical location, like a jail cell. The occupant of the jail cell may change, but the cell itself does not move locations. The occupant would act as the state while the cell stays in a permanent location. The visual representation of the grid will have generic rectangle objects that have a method to take the color associated with the state of the cell with a matching location field. This allows the front end visual representation of the grid to be fully separated from the backend Cell representation. If the state of a Cell changes, the visual representation that has the same location associated will update its color.

The abstraction we have designed prevents errors made possible in Use Cases. A middle cell and an edge or corner cell would not need to reason any differently. Each Cell has an ArrayList of neighbors, and for a middle cell, that ArrayList would have a size of 8 while an edge would have a size of 5 and a corner a size of 3. This causes no problem, however, because nowhere is it specified how long this ArrayList should be. Methods will loop through all Cells in the ArrayList and reason based on that. the updateState() method for all Cells will solve the Use Cases problem of updating the state at the end of a step. The Runner class will see this change and the rectangle objects pointing to the cell will update their colors. The program will be able to interpret commands both from the XML file when initializing and from the GUI controls while running. There will be a drop down menu to switch which simulation is currently being run.

## Design Considerations
There are a few intriguing considerations that we will have to give further thought to as we implement a full solution for the project. These considerations include the formatting of the XML file, the way in which we animate and place the occupants of the ‘Cells’ on the grid, and the way in which we handle switches in simulation type or changes to a current simulation’s parameters.

After looking at Rhondu’s XMLDemo code that was posted on Gitlab, we discussed various different ways to set up and read from the XML simulation files. Clearly, every simulation XML file will begin with the name of the simulation and the settings for the global parameters specific to the simulation. We talked about whether or not we needed to write out the full information for every cell to start out the game or if there was an easier way to do that. Our possible solution right now is to pick one of the Cell states as the default (ex: In Spreading Fire, when the tree is not on fire, that can be considered default). We will then create a list of cells that are in each of the other, different states (ex: on fire in Spreading Fire). This list will likely contain just numbers (i.e “2, 3, 9, 14, …”) that we will then correlate to cells in the grid. This may involve assigning numbers to each Cell, possibly adding another instance variable to the Cell class. 

The pros of the above method is that we do not have to type redundant information about every cell in the entire program if there are thousands of them. However, that is still an incredibly long list of numbers if we have thousands of cells. There may be a way to specify a filling algorithm in the XML file that the code can read and decide which cells to fill (ex: fill every 5th cell, fill every square (1, 4, 9, 16….) cell, etc.). We are still working on designing a more flexible way to accommodate the setup of the initial state of the simulation. We will handle the XML file in a way similar to Rhondu’s code: create a general DocumentBuilder object that can parse the file, then have a specific XML ‘Factory’ set up the list of cells for whichever simulation is currently taking place. 

A long debate occurred over how we wanted to animate all of the Cells in the grid. One of the first suggestions was cells may need the ability to move around the grid. This would prove helpful for certain simulations (like Segregation) because it would better model the way in which the states change. However, given the possibility that the pattern of cells may change in further simulations, we decided to generate every Cell with its corresponding specific location at the beginning of the simulation. This location will not change, and the contents of that specific cell will always be animated in that particular location. We decided that it is much easier to pass or switch a ‘state’ around/between cells than it is to change a cell’s location. The fixed locations will be generated after we receive the size of the overall grid and all other simulation parameters (cell shape, cell size, overlap between cells, etc.). 

Handling changes to a simulation parameter or simulation file also proved to be a topic of discussion. We debated whether or not we needed to re-initialize the simulation from scratch using the new parameter, or if it was possible to update the simulation in the middle of the simulation. Currently, we feel that it is much more feasible to re-generate a new simulation upon the changing of most parameters (cell-size, etc.). To make our simulations more advanced, we will likely try to set up a way so that changing algorithmic parameters (i.e. the number of neighboring cells needed to do x, y, or z) does not require a complete restart of the simulation and can be accounted for as the simulation is running. 

We are (unfortunately) anticipating that other small issues and challenges will arise throughout the project. The above considerations only represent the problems that we have currently thought about and proposed solutions for. The goal is that making our design flexible will allow for both easy troubleshooting of issues and the ability to add a variety of more advanced simulations and features.

## Team Responsibilities
We have decided to break up the team responsibilities in the following way:

### Addison:
#### Primary 
Back-end design of the abstract Cell class and its subclasses, as well as the algorithms needed to update and change cell state for specific simulations. Work with Brian on Builder class to represent Cells in a way that can easily be displayed in the GUI
#### Secondary  
Contributions to CellGraphic Class; work closely with Brian to standardize the way in which we generate and represent lists of cells and determine neighbors.

### Brian: 
#### Primary
Configuring the layout of the XML Files and designing the Parser and Factories so that simulations can be initialized and a list of Cells is created with all pertinent information (neighbors, initial state, etc.). Work with Addition on Builder class to represent Cells 
#### Secondary
Contributions to the front-end design/GUI (help Bill Animate the simulation based on the Builder Class and transition between simulations based on new files) and setup of the simulation using the Builder class in a way that can easily be displayed in the GUI

### Bill Yu: 
#### Primary
Majority of the front-end design/GUI, including initial menu setup, buttons and the pull-down menu containing each simulation. Animate simulation based on Builder Class and states of the list of Cells. Will also determine how GUI and simulation scene change depending on inputs of new files, new parameters, or button clicks (likely in ProgScene class)
#### Secondary
Contributions to simulation changeState() algorithms if necessary. 

### General
These responsibilities are not completely locked in; if someone completes their portion quickly and efficiently, they will likely be given more to do to help out the team. Likewise, if someone’s responsibilities prove harder than first imagined, others will step in to help them out. We are in constant communication via text and email, and we have meetings planned for both during the week and on the weekends to check-in and discuss important design decisions. 

