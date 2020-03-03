# Minesweeper
=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=
CIS 120 Game Project README
PennKey: maggieyu
=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=

===================
=: Core Concepts :=
===================

- List the four core concepts, the features they implement, and why each feature
  is an appropriate use of the concept. Incorporate the feedback you got after
  submitting your proposal.

  1. 2D Arrays
  	 2D Arrays of buttons creates a classic 9x9 minesweeper board. Each square in the grid
  	 corresponds to an individual grid. I use 2D arrays for flooding blank squares,
  	 determining if the blank squares are all uncovered, and easily modifying desired squares.
  	 Resetting each of the 81 buttons was also easy through a nested for loop.

  2. Collections
  	 I used a TreeSet when determining which squares in the 9x9 should be mines. I made a
  	 TreeSet of a new Coordinate class, where x-coordinate correlated to the button width (0-8)
  	 and the y-coordinate correlated to the button height (0-8). TreeSet did not allow duplicates,
  	 which guaranteed there would be 10 distinct mine locations on the grid (I continued to 
  	 create new mine coordinates until TreeSet size was 10).
  	 
  	 Feedback: Changed from using collections to identify if a square was mapped to ensuring that
  	 there were exactly 10 distinct mine locations

  3. Testing
  	 JUnit testing of the reset button, win condition, lose condition, flood recursion, timer,
  	 clicking, status panel.

  4. Recursion
  	 Recursive algorithm flood used to uncover blank spaces. Starts at non-mine square, then tries
  	 up/down/left/right to see if other spaces can be uncovered. If yes, checks to see if the
  	 new spaces have their neighbors that can be uncovered, and so on.


=========================
=: Your Implementation :=
=========================

- Provide an overview of each of the classes in your code, and what their
  function is in the overall game.
  
  Game.java: creates timer, status label, and grid layout
  GameCourt.java: handles the changing parts of the game such as the timer,
  minesweeper grid, flag checkbox, and reset button. Also handles ticks which 
  updates boolean playing and the timer.

  Coordinates.java: helper class for initializing 10 distinct mines on the grid.
  Indicates coordinates of mine. Used instead of the built-in Dimensions class 
  since Collections TreeSet was implemented.

  MButton.java: Button class for each invidiual button. Contains all properties
  of button, and sets Icon. Mouseclicks for each minesweeper square is handled here.
  Recursive flooding is also handled here since properties of each button must be
  handled at the individual level, not the grid level (thus flooding method is not
  in GameCourt.java).


- Were there any significant stumbling blocks while you were implementing your
  game (related to your design, or otherwise)?

  Couldn't figure out how to use TreeSet Collection and the Comparator class.
  Recursive flooding took several hours since StackOverFlowErrors would occur since
  two squares would infinitely check each other if they were both not mines.
  Difficulty determining whether a method should be in MButtons.java or 
  GameCourt.java.

- Evaluate your design. Is there a good separation of functionality? How well is
  private state encapsulated? What would you refactor, if given the chance?
  Separated into three sections: overall game, gameCourt, and individual buttons.
  MButton.java was well-encapsulated since all variables were private and required
  getter and setter methods, which makes it difficult to directly access variables,
  such as if the square is uncovered, or whether the square is a mine. Reconsider
  putting the recursion flooding in the GameCourt, improving the interaction between
  flags and individual buttons. 

========================
=: External Resources :=
========================

- Cite any external resources (libraries, images, tutorials, etc.) that you may
  have used while implementing your game.
  - "Java Swing #2 "Grid Layout & Action Listener" Tutorial"
	https://www.youtube.com/watch?v=Db3cC5iPrOM&t=407s
  - Catbug icon: 
  	https://www.deviantart.com/ejaythellama/art/100x100-Catbug-icon-359876311
  - lots of CIS120 Piazza posts

===============================
=: To run on Linux computers :=
===============================

javac -cp junit.jar *.java && java Game
