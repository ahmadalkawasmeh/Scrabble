# ScrabbleScrabble
SYSC 3110 Group Project (Fall 2022)

- Authors:
	- Ahmad Alkawasmeh - 101108706
	- James Grieder - 101177911
	- Daniel Kuchanski - 101182041
	- Ibtasam Rasool - 101186050

Individual contributions are listed below.  See the "New Updates" section.


# Introduction

ScrabbleScrabble is a mock "Scrabble" game played on a 15 x 15 grid.  The game supports 2 - 4 players including AI players.  This fourth iteration accepts user input via the mouse, using a graphical user interface.

Each of the players has a tray with 7 letters drawn from a bag of pre-set letters.  Points are awarded based on the words placed, determined by the score of each letter used, and any "special squares" that are point multipliers based on the word or the letter placed on those squares.  The game also features blank tiles which award 0 points, but can be used as any letter of the alphabet.

# Game Instructions

Upon opening the game, you are first prompted to choose the total number of players in the first pop up window and press "OK" to confirm selection, in the second pop up window you choose the number of AI players followed by pressing "OK" to confirm selection, then the game will be opened. 
- User Interface
	- Menu
		- "File" contains the following:
			- "New Game" creates a new game and will take you back to the initial prompt pop ups where you choose the total number of players and the number of AI players.
			- "Save Game" saves the current game progress to a text file to be loaded later and continued
			- "Load Game" loads a previously saved game by promting you to enter a file name the previous game was saved under
			- "Quit Game" closes and terminates the current game
			
		- "Game History Options" contains the following:
			- "Undo" retracts your last move
			- "Redo" reinstates your undon move, basically a Undo for your Undo
			
		- "Help" contains the following:
			- "View Game Instructions" lets you view the game instructions
			- "View ReadMe" lets you see the ReadMe file 
		
	- Game Board
		- In the middle of the screen you will see all the squares for the game board where you will place your letters to form words.
		
	- Scoreboard
		- On the left side of the board you will see the scoreboard which keeps track of points earned per player
		
	- Letter Tray
		- On the bottom of the screen you will see your letter tray
		
	- Buttons
		- On the right side of the board you will see the player moves' buttons
		
		- "Place Word"
			- This button confirms your letters placement on the board and lets the next player take their turn to play
			
		- "Swap" and "GO"
			- This button allows you to swap your letters with another set of letters, in order to swap letters you first press "Swap" then you select 1-7 letters you'd like to 			     swap then press "GO" button in order to confirm the swap
			
		- "Pass"
			- This allows you to pass your turn to the next player if you're unable to make any moves
			
		- "Reset"
			- This button allows you to reset your letter placements for that round by retracting your placed letters from the board back to the tray
		
		- "Play AI Move"
			- This button prompts the AI to make a move
			
This iteration of the game does not currently implement the game-ending conditions.  Future iterations will end the game upon the letterbag and the current player running out of letters, or upon the occurrence of 6 consecutive turns where no player earns a score.


# Included Files

- src folder
	- This folder contains all of the source code for the scrabblescrabble game.
- legalWordsList.txt
	-  This file contains the list of all legal words that may be played in the scrabblescrabble game. Each line of this file contains one legal word.
- UMLDiagrams folder
	- UML Diagram 1: M4 UML with dependencies.png
		- This UML diagram outlines the current class structure of the program with dependencies
	- UML Diagram 1: M4 UML without dependencies.png
		- This UML diagram outlines the current class structure of the program without dependencies
	- Sequence Diagram 1: gameInitializationSequence.png
		- Demonstrates how the ScrabbleScrabble Game class is initialized
	- Sequence Diagram 2: placeWord.png
		- Demonstrates how a Word object is placed on the board
	- Sequence Diagram 3: swapLetter.png
		- Demonstrates how a Player is able to swap a letter from their tray for a new letter from the shared LetterBag
	- Sequence Diagram 4: AIDecisionMaking.png
		- Demonstrates how the AI chooses to either place a word, swap, or pass to progress the game forward
	- Sequence Diagram 5: XMLSequence.png
		- Demonstrates how board reads and parses an XML file and applies the special square configuration to itself
	- ChangeLog.PDF
		- This document outlines the rationale of design decisions for the current implementation of the program.
        - board.xml
                - This is the original standard Scrabble board setup
        - board2.xml
        	- This is the second board configuration consisting of only Triple Word squares
        - board3.xml
        	- This is the third custom board configuration


# New Updates

This iteration represents milestone 4 of 4.  Newly introduced features/changes, listed by contributor, for this milestone include:

- Ahmad Alkawasmeh - 101108706
  - Updated readme
  - UML Diagrams x2
  - Created an extra custom board (board3.xml)
  - 

- James Grieder - 101177911
  - Implemented save/load functionality for all game features included in milestone 3 (Java Serialization)
  - Created AllTests test suite class
  - Debugging of PlayerTest, TrayTest & LetterBag Test multithreading issues
  - AI now has the ability to place the first word
  - Refactoring of AI to be its own individual static class, instead of just a field within the Player class
  - Implemented functionality for the "cancel" buttons when first opening the game and selecting the number of players and AI
  - Added additional constructor for Tray class for testing purposes
  

- Daniel Kuchanski - 101182041
  - Implemented custom special square positioning
  - Debugging of PlayerTest, TrayTest & LetterBag Test multithreading issues
  - Added ability to export and import custom boards using XML
  - Added testing for XML input/output
  - Created an extra custom board (board2.xml)
  - New sequence diagram: XMLSequence.png
  - 

- Ibtasam Rasool - 101186050
  - Implemented multi-level undo/redo functionality
  - Added test methods for undo/redo
  - Refactored GameController and MenuController to use hard-coded command values
  - Debugging of word logic related to diagonal coordinates (i.e. usedSquares[7][7])
  - Added setters for LetterBag and Player classes for testing


# Known Issues

- AI Players can currently overwrite other letters on the board
- Word scoring is not fully implemented for double/triple word scores & letter scores
- Scoring does not include earning 50 points for using the player's entire tray
- Issue where a blank tile cannot be removed from a Player's tray, so the player logically has an 8th blank tile (that cannot be used on the GUI)


# Future Milestones

The project is currently in milestone 4 of 4.  There are currently no future updates planned for ScrabbleScrabble.


