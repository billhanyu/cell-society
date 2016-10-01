## Refactoring Changes for Brian Keohane

### First Change - Initializer.java
Our Initializer.java class was a mess heading into recitation on Thursday; we had 4 different 
switch statements that could easily be replaced by one. I (partially) refactored the Initializer.java
class to make control of the simulation determined by one switch statement in Commit 58d7449d51683688aaf24f2f5789a5d26b7cc7fb ("Refactored Initializer Class to have only 1 'if' statement").
However, there is still much to improve with this class; specifically, we need to be able to initialize
the correct type of builder outside of the switch statement or figure out a way to bury/hide some of
this logic in lower classes to make the program more readable/design friendly. This will make adding our
next 3 simulations much easier.

### Second Change - XML Factory Files
In the XML package, the way in which I was creating simulations was horribly inefficient and included
a good amount of repeated code. I pulled a lot of the code up into the higher, abstract 
SimulationFactory.java class and used these methods to better create the Parameter classes needed in
each specific simulation factory. These changes are visible in two commits:
Commit 7f40fc6c274c3aaa4aee435f0f558b83bf3769da ("Refactor XML Simulation Factory") and
Commit 08d0f9e4c0cad07ca4a55234ed0e6330cda4b33e ("Continued to refactor XML Simulation Factory Classes")
I feel that this is better because it 1) gets rid of repeated code and 2) allows for the easy creation
of the correct parameter class depending on whether or not to initialize the cells based on ratios or
based on specific states. 