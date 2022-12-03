# scrabblescrabble
SYSC 3110 Group Project (Fall 2022)

- Authors:
	- Ahmad Alkawasmeh - 101108706
	- James Grieder - 101177911
	- Daniel Kuchanski - 101182041
	- Ibtasam Rasool - 101186050


# Introduction

scrabblescrabble is a mock "Scrabble" game played on a 15 x 15 grid.  In its current iteration, each of the 2 players has a tray with 7 letters drawn from a bag of pre-set letters.  Points are awarded based on the words placed, determined by the score of each letter used.  This first iteration of scrabblescrabble accepts user input from the keyboard.


# Game Instructions

Upon opening the game, the game is already initialized for 2 players and begins with the first player's turn.  The players alternate taking turns.  On each turn the player chooses from the following options:

- Place a word on the game board by typing in the following format:
	- WORD COORDINATE
	- For example: "TREE 10H" to place horizontally, or "TREE H10" to place vertically
	- The direction is determined by the first coordinate
	- Coordinates range from 1 to 15 and A to O

- Pass their turn with no points

- Return one or more letters to the letterbag and draw new letters by typing the following format:
	- SWAP LETTERS
	- For example: "SWAP ABC" will swap the letters ABC if they are present in the tray

If a player attempts to place a word that is an invalid input, their turn is passed to the next player.  Invalid inputs include:
- A word that goes out of position on the board
- A word not present in the dictionary

This iteration of the game does not currently implement the game-ending conditions.  Future iterations will end the game upon the letterbag and the current player running out of letters, or upon the occurrence of 6 consecutive turns where no player earns a score.


# Included Files

- src folder
	- This folder contains all of the source code for the scrabblescrabble game.
- legalWordsList.txt
	-  This file contains the list of all legal words that can be played in the scrabblescrabble game.  Each line of this file contains one legal word.
- UML Diagram M1 Submission FINAL.png
	- This UML diagram outlines the current class structure of the program.
- ChangeLog.PDF
	- This document outlines the rationale of design decisions for the current implementation of the program.


# New Updates

N/A.  This iteration represents milestone 1 of 4.  All of the current program functionalities have been introduced in this milestone.


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

The project is currently in milestone 1 of 4.  Future milestones are scheduled to include the following features:

- Milestone 2:
	- A GUI-based version of scrabblescrabble
	- Will accept game input via the user's mouse
	- Unit tests for the underlying game logic of the scrabblescrabble model
	
- Milestone 3: 
	- Blank tiles
	- Premium squares (including double letter, triple letter, double word and triple word squares)
	- The option to use 1 or more AI players
	
- Milestone 4: 
	- Undo/Redo buttons that work over multiple turns
	- The ability to save the game and reload it
	- Custom scrabblescrabble boards with different premium square layouts
