
# ShingShang
![](https://github.com/k-samir/ShingShang/blob/main/image/board.png?raw=true)

----------------
# Presentation
Welcome to the ShingShang project repository!

The objective of this project is to implement a board game called SHING SHANG. It is a two-player game. Each player has an army of 12 Bushis (Pieces). This army is divided into 3 groups: 2 dragons, 4 lions, and 6 monkeys.. 

[Project documentation](https://docs.google.com/document/d/1ZtqhFwuuNymzyjvvDptcIIbRV_NNXb4CPYg6uDdT0ng/edit#)

[Game download link](https://github.com/k-samir/ShingShang/blob/main/jar/ShingShang_SK.jar?raw=true)

[UML Diagram of the Project](https://github.com/k-samir/ShingShang/blob/main/src/UML3.jpg)

----------------
# Educational Objectives
Understand the project and learn how to identify its objectives

The focus is placed more on software design aspects (such as documentation, reusability, etc.) and project tracking rather than on the technical difficulty of the subject

----------------
# Game Basics

Players take turns performing one of the following two actions:

A player can move one of their pieces on the board to another square.
A player can jump over another piece if the piece being jumped over is smaller or the same size as the jumping piece.

----------------

# Moves

To move a piece on the board, the destination square must be free

A piece can move horizontally, vertically, or diagonally, both forward and backward

To jump, the jumping piece must be on a square adjacent to a square occupied by one of its own pieces or an opponent's piece

The jump can be made vertically, horizontally, or diagonally, provided the next square is empty.

----------------

# Shing Shang Sequence

Multiple jumps can be chained during the same turn. This sequence of jumps is called a SHING SHANG.

If, during a SHING SHANG, a player jumps over an opponent's piece, the player must stop, and the opponent's piece is removed from the board. However, the player gains an additional turn with another piece.

----------------


# Specific Rule

Monkeys can move one or two squares in any direction—horizontally, vertically, or diagonally—but cannot change direction during the turn.
Lions can move one square in any direction—horizontally, vertically, or diagonally.
Dragons can only move by jumping.

----------------

# End of the Game

A player wins the game when they manage to move one of their dragons to one of their opponent's portals (special squares) or when they capture both of their opponent's dragons.

----------------

# Starting the Game

To start the game, you need to download the JAR file from GitHub.
[Download Link of the .JAR](https://github.com/k-samir/ShingShang/blob/main/jar/ShingShang_SK.jar?raw=true)

----------------

## Running the Game

On Windows/Linux or Mac: Open a command prompt/bash, navigate to the location of the .jar file, and type: java -jar ShingShangSK.jar 

----------------

# Technologies Used

The game was fully developed using [Eclipse](https://www.eclipse.org/) with `Java` JDK 14.0.2.
Avec also [UMLet](https://www.umlet.com/) for the creation of UML diagrams.


### Author 
* [Samir KAMAR](https://github.com/k-samir)

