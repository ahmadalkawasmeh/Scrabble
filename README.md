# scrabblescrabble
SYSC 3110 Group Project (Fall 2022)

- Authors:
	- Ahmad Alkawasmeh - 101108706
	- James Grieder - 101177911
	- Daniel Kuchanski - 101182041
	- Ibtasam Rasool - 101186050


# Introduction

scrabblescrabble is a mock "Scrabble" game played on a 15 x 15 grid.  In its current iteration, the game supports 2 - 4 players.  The game also supports basic AI functionalities.  This third iteration accepts user input via the mouse, using a graphical user interface.

Each of the players has a tray with 7 letters drawn from a bag of pre-set letters.  Points are awarded based on the words placed, determined by the score of each letter used, and any "special squares" that are point multipliers based on the word or the letter placed.  The game also features blank tiles which award 0 points, but can be used as any letter of the alphabet.


# Game Instructions

Upon opening the game, you are first prompted to choose the total number of players in the first pop up window and press "OK" to confirm selection, in the second pop up window you choose the number of AI players followed by pressing "OK" to confirm selection, then the game will be opened. 
- User Interface
	- Menu
		- "File" contains the following:
			- "New Game" creates a new game and will take you back to the initial prompt pop ups where you choose the total number of players and the 			     number of AI players.
			- "Save Game" saves the current game progress to be loaded later and continued
			- "Load Game" loads a previously saved game
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
		- On the left side of the board you will see the scoreboard which keeps track of points earned per player. 
		
	- Letter Tray
		- On the bottom of the screen you will see your letter tray 
		
	- Buttons
		- On the right side of the board you will see the player moves' buttons
		
		- "Place Word"
			- This button confirms your letters placement on the board and lets the next player tkae their turn to play.
			
		- "Swap" and "GO"
			- This button allows you to swap your letters with another set of letters, in order to swap letters you first press "Swap" then you select 			     1-7 letters you'd like to swap then press "GO" button in order to confirm the swap.
			
		- "Pass"
			- This allows you to pass your turn to the next player if you're unable to place a word with your letters or can't make any moves.
			
		- "Reset"
			- This button allows you to reset your letter placements for that round by retracting your letters from the board back to the tray
		
		- "Play AI Move"
			- This button prompts the AI to make a move, wether to place a word, swap or pass their turn.
			
			
# Included Files

- src folder
	- This folder contains all of the source code for the scrabblescrabble game.
	
- legalWordsList.txt
	-  This file contains the list of all legal words that may be played in the scrabblescrabble game.  Each line of this file contains one legal word.
	
- UMLDiagrams folder
	- UML Diagram M3 Submission FINAL.png
		- This UML diagram outlines the current class structure of the program
	- Sequence Diagram 1: gameInitializationSequence.png
		- Demonstrates how the scrabblescrabble Game class is initialized
	- Sequence Diagram 2: placeWord.png
		- Demonstrates how a Word object is placed on the board
	- Sequence Diagram 3: swapLetter.png
		- Demonstrates how a Player is able to swap a letter from their tray for a new letter from the shared LetterBag
	- Sequence Diagram 4: scoreCalculation.png
		- Demonstrates how the score is calculated for a Word placed on the board
	- Sequence Diagram 5: AIDecisionMaking.png
		- Demonstrates how the AI chooses to either place a word, swap, or pass to progress the game forward

- ChangeLog.PDF
	- This document outlines the rationale of design decisions for the current implementation of the program.


# New Updates

This iteration represents milestone 3 of 4.  Newly introduced features for this version include:

- "Special squares" that allow for boosting a player's score depending on which squares they place their letters/words on
	- Includes: Double Letter, Double Word, Triple Letter, and Triple Word score multipliers
- Blank tiles, earning the player zero points but allowing the player to select any letter from the alphabet to place on the board 
- AI functionality that allows an AI player to place a word, swap letters, or pass their turn
- The menu bar above the game currently includes functionality to start a new game, or to quit the current game


# Known Issues

- Letters on the board can currently be overwritten by other players

- Placing a word with shared letters is not functional (players can only currently use letters in their tray)

- Adjacent words do not currently need to be legal
	- Preliminary code and data structures are present, but not fully implemented

- If a player places a word unsuccessfully (i.e. using a word not valid in the dictionary) the turn is skipped to the next player

- Illegal coordinates can be used by the user
	- out of bound letter coordinates raise an exception
	- out of bound numerical coordinates cause the player to lose the letters used (points awarded, but the word is placed off of the board)


# Future Milestones

The project is currently in milestone 3 of 4.  Future milestones are scheduled to include the following features:
	
- Milestone 4: 
	- Undo/Redo buttons that work over multiple turns
	- The ability to save the game and reload it
	- Custom scrabblescrabble boards with different premium square layouts
