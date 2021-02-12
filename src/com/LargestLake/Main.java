package com.LargestLake;

public class Main {

    // Given a matrix n x n size where every cell is represented by value 0 or 1 (water or ground).
    // Water cells can connect to each other (top,down,left,right) and create a larger water pools.
    // Find a largest lake
    public static void main(String[] args) {
        var data = new int[][]{
                {0, 0, 1, 1},
                {1, 0, 0, 0},
                {1, 0, 0, 0},
                {0, 1, 1, 1}
        };
        var graph = matrixToGraph(data);
        graph.solve();
    }

    private static Graph matrixToGraph(int[][] matrix) {
        var graph = new Graph();

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                graph.addNode(String.format("%d%d", i, j), matrix[i][j]);
            }
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (j - 1 > 0) {
                    graph.addConnection(String.format("%d%d", i, j), String.format("%d%d", i, j - 1));
                }

                if (j + 1 < matrix[i].length) {
                    graph.addConnection(String.format("%d%d", i, j), String.format("%d%d", i, j + 1));
                }

                if (i - 1 > 0) {
                    graph.addConnection(String.format("%d%d", i, j), String.format("%d%d", i - 1, j));
                }

                if (i + 1 < matrix[i].length) {
                    graph.addConnection(String.format("%d%d", i, j), String.format("%d%d", i + 1, j));
                }
            }
        }

        return graph;
    }
}
