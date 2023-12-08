// Jeffery Zhang
// TA: Jake Page
// CSE 123
// Due: October 11, 2023
// C0: Abstract Strategy Games

// A class to represent a game of Chomp that implements the 
// AbstractStrategyGame interface.

import java.util.*;

public class Chomp implements AbstractStrategyGame {
    public static final int SIZE = 6;
    public static final int HEIGHT = 3;
    private char[][][] board;
    private boolean isP1Turn;

    // Constructs a new Chomp game.
    public Chomp() {
        board = new char[HEIGHT][SIZE][SIZE];
        initializeGrid();
        isP1Turn = true;
    }

    // Fills the board with 'chocolate', including a
    // poisonous piece at the final top-left corner.
    private void initializeGrid() {
        for (int h = 0; h < HEIGHT; h++) {
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    board[h][i][j] = 'o';
                }
            }
        }
        board[0][0][0] = 'x';
    }

    // Returns whether or not the game is over.
    @ Override
    public boolean isGameOver() {
        return getWinner() != -1;
    }

    // Returns the index of the winner of the game.
    // 1 if player 1, 2 if player 2,
    // and -1 if the game is not over.
    @ Override
    public int getWinner() {
        if (board[0][0][0] == '.') {
            return isP1Turn ? 1 : 2;
        } else {
            return -1;
        }
    }

    // Returns the index of which player's turn it is.
    // 1 if player 1, 2 if player 2, -1 if the game is over
    @ Override
    public int getNextPlayer() {
        if (isGameOver()) {
            return -1;
        }
        return isP1Turn ? 1 : 2;
    }

    // Given a row and col through Scanner input,
    // "chomps" the piece at the row and col.
    // Throws an IllegalArgumentException if the position is
    // invalid, whether that be out of bounds or an fully empty space.
    @ Override
    public void makeMove(Scanner input) {
        System.out.print("Row? ");
        int row = input.nextInt();
        System.out.print("Column? ");
        int col = input.nextInt();
        makeMove(row, col);
        isP1Turn = !isP1Turn;
    }

    // Private helper method for makeMove.
    // Given a row and col through Scanner input,
    // "chomps" the piece at the row and col.
    // Throws an IllegalArgumentException if the position is
    // invalid, whether that be out of bounds or an fully empty space.
    private void makeMove(int row, int col) {
        if (row < 0 || row >= SIZE ||
        col < 0 || col >= SIZE) {
            throw new IllegalArgumentException("Invalid, " + "(" + row + ", " + col + ") is out of bounds");
        }
        if (board[2][row][col] == '.' &&
            board[1][row][col] == '.' &&
            board[0][row][col] == '.') {
            throw new IllegalArgumentException("Invalid, all layers in position "
            + "(" + row + ", " + col + ") are empty");
        }
        int currentLayer = 2;
        if (board[currentLayer][row][col] != '.') {
            doChomp(row, col, currentLayer);
        } else {
            boolean chomped = false;
            currentLayer--;
            while (currentLayer != -1 && !chomped) {
                if (board[currentLayer][row][col] != '.') {
                    doChomp(row, col, currentLayer);
                    chomped = true;
                }
                currentLayer--;
            }
        }
    }

    // A further private helper method for makeMove.
    // Given a row, col, and a specified layer,
    // "chomps" the piece at the row and col.
    private void doChomp(int row, int col, int layer) {
        for (int i = row; i < SIZE; i++) {
            for (int j = col; j < SIZE; j++) {
                board[layer][i][j] = '.';
            }
        }
    }

    // Returns a String containing instructions to play the game.
    @ Override
    public String instructions() {
        String result = "\n";
        result += "Player 1 goes first. Choose where to play by entering a row and\n";
        result += "column number, where (0, 0) is the upper left and ("+ SIZE + ", " + SIZE + ")\n";
        result += "is the lower right. Spaces that show as a 'o' are \"chocolates\".\n";
        result += "'.' are empty spaces. Choosing a chocolate on a player's turn causes all\n";
        result += "chocolates *on the same layer* to the right and below it to become \"chomped\"\n"; 
        result += "Chomping a chocolate reveals the chocolate directly below it, if there is one.\n";
        result += "The player that consumes the top-leftmost chocolate from the final layer\n";
        result += "(denoted by 'x'), loses the game. Have fun!";
        return result;
    }

    // Returns a String representation of the current state of the board.
    @ Override
    public String toString() {
        String result = "\n";
        for (int h = HEIGHT - 1; h >= 0; h--) {
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    result += board[h][i][j] + " ";
                }
                result += "\n";
            }
            result += "\n\n";
        }
        return result;
    }
 }
