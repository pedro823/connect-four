// ConnectFour strategy module
// By: Pedro Pereira
// IME - USP

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;


public class ConnectStrategy {
    String color;
    ValueTable table;

    private class ValueTable {
        int[][] matrix;
        float[] row_value;

        public ValueTable(int size) {
            matrix = new int[size][size];
            row_value = new float[size];
        }

        public int findBest(String color) {

        }



        private int findColor(String color) {
            return color.equals("r") ? 1 : -1;
        }
    }

    // Starts a strategy module. initiates value table
    public ConnectStrategy(int size, String color) {
        this.color = color;
        table = new ValueTable(size);
    }

    public int getMove() {

    }

    public void playerMove() {

    }

}
