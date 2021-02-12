package com.LargestLake;


import java.util.*;

public class Graph {
    class GraphNode {
        private int weight;
        private String name;

        public GraphNode(int weight, String name) {
            this.weight = weight;
            this.name = name;
        }

        public int getWeight() {
            return weight;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return name + " " + weight;
        }
    }

    private Map<String, GraphNode> nodes = new HashMap<>();
    private Map<String, LinkedList<GraphNode>> adjacencyList = new HashMap<>();

    public void addNode(String name, int weight) {
        var node = new GraphNode(weight, name);

        nodes.putIfAbsent(name, node);
        adjacencyList.putIfAbsent(name, new LinkedList<>());
    }

    public void addConnection(String node1, String node2) {
        if (!nodes.containsKey(node1) || !nodes.containsKey(node2)) {
            throw new IllegalArgumentException("Can't add connection for non existing nodes");
        }

        addConnectionIfNotExists(node1, node2);
    }

    private void addConnectionIfNotExists(String nodeFrom, String nodeTo) {
        var destinationNode = nodes.get(nodeTo);

        var connections = adjacencyList.get(nodeFrom);
        for (var connection : connections) {
            if (connection.name.equals(destinationNode.name)) {
                return;
            }
        }

        connections.addLast(destinationNode);
    }

    public void print() {
        var builder = new StringBuilder();

        for (var node : adjacencyList.keySet()) {
            builder.append("Node ").append(node).append(": ");

            for (var connection : adjacencyList.get(node)) {
                builder.append(connection.name).append(" -> ");
            }
            builder.replace(builder.length() - 4, builder.length(), "");
            builder.append("\n");
        }

        System.out.println(builder.toString());
    }

    public void solve() {
        var visited = new HashSet<GraphNode>();

        var largestLake = new ArrayList<GraphNode>();
        for (var node : nodes.keySet()) {
            var gn = nodes.get(node);

            if (!visited.contains(gn)) {
                var lake = new ArrayList<GraphNode>();
                var stack = new Stack<GraphNode>();
                stack.push(gn);

                while (!stack.isEmpty()) {
                    var current = stack.pop();
                    if (visited.contains(current)) {
                        continue;
                    }

                    visited.add(current);
                    if (current.weight == 0) {
                        lake.add(current);
                        for (var neighbor : adjacencyList.get(current.name)) {
                            if (!visited.contains(neighbor)) {
                                stack.push(neighbor);
                            }
                        }
                    }
                }

                if (lake.size() > largestLake.size()) {
                    largestLake = lake;
                }
            }
        }

        if (largestLake.isEmpty()) {
            System.out.println("No lakes were found");
        } else {
            System.out.println("Largest lake:");
            var builder = new StringBuilder();
            for (var node : largestLake) {
                builder.append(node.name).append(" -> ");
            }
            builder.replace(builder.length() - 4, builder.length(), "");
            System.out.println(builder.toString());
        }
    }
}
