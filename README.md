# cellsociety 

names of all people who worked on the project
* names:

Addison Howenstine, Bill Yu, Brian Keohane

* dates:

start: September 15th, end: October 3rd

estimate of hours: 60h

* roles:

Addison: Backend implementation, Cell / grid structure, models
Bill: User interface & frontend, Initialization
Brian: XML Integration and simulation factories

* resources:

https://stackoverflow.com
https://google.com
Rhondu's XML Demo

* files to start:

`/default package/Main.java`

* files to test:

Press `Choose File`, choose any file in `/data/xml/`

* data or resource files required

files in `/data/xml/` and in `/resource/`

* Information:

intersting example data file: `/data/xml/GameOfLife-GliderGun.xml`
all interaction with the simulation window is done through clicking buttons or
dragging sliders; there are no key shortcuts

* known bugs:

Reset for Ant and Sugarscape are not working. The WaTor, Ant, and Sugarscape simulations save
only some of the current simulation information, not all of it. 

* Extra features:

Chinese language support, user pressed buttons to change shapes, sliders to change parameters, live parameters update (no need to restart the simulation to change parameters). Save current simulations
(working perfectly for Spreading Fire, Game of Life, and Segregation) into XML file and replay simulation
from current point at a later time

* Impression:

Maybe provide more clear writeups for cell models/algorithms.