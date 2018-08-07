// ConnectFour main module
// By: Pedro Pereira
// IME - USP

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class ConnectFour {


    public static void printUsage() {
        StdOut.println("Usage: ");
        StdOut.println("    java ConnectFour <color> <matrix size>");
        StdOut.println("    Color being r/b,");
        StdOut.println("    matrix size being a number.");
        StdOut.println("    Red always starts.");
    }

    public static String opsColor(String color) {
        return color.equals("r") ? "b" : "r";
    }

    public static void main(String[] args) {
        if(args.length != 2) {
            printUsage();
            System.exit(0);
        }
        if(!args[0].equals("r") && !args[0].equals("b")) {
            printUsage();
            System.exit(0);
        }
        String color = args[1];
        boolean turn = color.equals("r");
        int size = Integer.parseInt(args[1]);
        ConnectPlayer player = new ConnectPlayer(size, opsColor(color));
        while(true) {
            player.printBoard();
            if(player.gameOver()) {
                break;
            }
            if(turn) {
                player.readMove();
            }
            else {
                player.AImove();
            }
            turn = !turn;
        }
        if(player.redWon()) {
            StdOut.println("Red Won!");
        }
        else {
            StdOut.println("Blue Won!");
        }
        StdOut.println("Thanks for playing.");
    }

}
