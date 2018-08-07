// ConnectFour player module
// By: Pedro Pereira
// IME - USP

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class ConnectPlayer {
    int[][] board;
    String color;
    ConnectStrategy strategy;

    public static final String RESET_COLOR = "\u001B[0m";
    public static final String RED_COLOR = "\u001B[31m";
    public static final String BLUE_COLOR = "\u001B[34m";

    public ConnectPlayer(int size, String color) {
        board = new int[size][size];
        this.color = color;
        ConnectStrategy = new ConnectStrategy(size, opsColor(color));
    }

    public void printBoard() {
        for(int i = 0; i < board.length; i++) {
            StdOut.print((i % 10) + "  ");
        }
        StdOut.println();
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[i].length; j++) {
                if(board[i][j] == 0) {
                    // Neutral piece
                    StdOut.print("-  ");
                }
                else if(board[i][j] == 1) {
                    // Red piece
                    StdOut.print(RED_COLOR + "X  " + RESET_COLOR);
                }
                else {
                    // Blue piece
                    StdOut.print(BLUE_COLOR + "X  " + RESET_COLOR);
                }
            }
            StdOut.print("\n\n");
        }
    }

    public boolean gameOver() {
        return gameOver("r") || gameOver("b");
    }

    public void readMove() {
        Integer move = StdIn.readInt();
        while(move < 0 || move >= board.length) {
            StdOut.println("Not a valid move.");
            move = StdIn.readInt();
        }
        if(!applyMove(move, color)) {
            StdOut.println("The column specified is full.");
            readMove();
        }
        strategy.playerMove(move);
    }

    public void AImove() {
        int tries = 5;
        int move = strategy.getMove();
        while(!applyMove(move, opsColor(color)) && tries > 0) {
            StdOut.println("AI tried to move at " + move + ". " + tries + " AI tries remaining.");
            move = strategy.getMove();
            tries--;
        }
    }

    public boolean redWon() {
        return gameOver("r");
    }

    private boolean applyMove(int move, String color) {
        int applyColor = (color.equals("r") ? 1 : -1);
        for(int i = board.length - 1; i >= 0; i--) {
            if(board[i][move] == 0) {
                board[i][move] = applyColor;
                return true;
            }
        }
        // column of move was full
        return false;
    }

    private boolean gameOver(String color) {
        int lookFor = (color.equals("r") ? 1 : -1);
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[i].length; j++) {
                if(board[i][j] == lookFor && check(i, j)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean check(int i, int j) {
        // The nasty stuff
        int color = board[i][j];
        // Horizontal
        if(j >= 3) {
            // Horizontal - left
            if(color == board[i][j - 1] &&
               color == board[i][j - 2] &&
               color == board[i][j - 3]) {
                    return true;
            }
        }
        // Vertical
        if(i <= board.length - 4) {
            // Vertical - down
            if(color == board[i + 1][j] &&
               color == board[i + 2][j] &&
               color == board[i + 3][j]) {
                    return true;
               }
        }
        // Diagonal left - upwards
        if(i >= 3 && j >= 3) {
            if(color == board[i - 1][j - 1] &&
               color == board[i - 2][j - 2] &&
               color == board[i - 3][j - 3]) {
                    return true;
            }
        }
        // Diagonal right - upwards
        if(i >= 3 && j <= board.length - 4) {
            if(color == board[i - 1][j + 1] &&
               color == board[i - 2][j + 2] &&
               color == board[i - 3][j + 3]) {
                    return true;
            }
        }
        return false;
    }

    private String opsColor(String color) {
        return (color.equals("r") ? "b" : "r");
    }
}
