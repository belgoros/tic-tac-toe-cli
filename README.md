# Tic Tac Toe Game

This a basic example of CLI implementation for a [Tic Tac Toe](https://en.wikipedia.org/wiki/Tic-tac-toe) game in Java.

## Used libraries

- Java 11
- Maven
- JUnit5

## Set up

Run `mvn clean install` to install dependencies

## Run

The game board is considered to be a 3 x 3 grid.
Rows and columns are numbered as 1 to 3.

Every player must place three of their marks in a horizontal, vertical or diagonal row.

The values should be entered as pair of 2 numbers separated with spaces, i.e. `1 1` to mark the cell in the first row and the first column.
`3 3` for the 3d row in the last (third) column.

To start the game, just run the main class `TicTacToe` from your favorite IDE.
In the opened console, follow the instructions to enter the values.

## Tests

[JUnit5](https://junit.org/junit5/) is used for testing.
Run `mvn test`` all the tests should pass.
