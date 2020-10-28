package com.yang.graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Description:
 *
 * @author mark
 * Date 2020/10/28
 */
public class GraphDemo {

    public static void main(String[] args) {
        String[] vertexes = {"1", "2", "3", "4", "5", "6", "7", "8"};
        Graph graph = new Graph(vertexes);
        graph.insertEdges("1", "2", 1);
        graph.insertEdges("1", "3", 1);
        graph.insertEdges("2", "4", 1);
        graph.insertEdges("2", "5", 1);
        graph.insertEdges("4", "8", 1);
        graph.insertEdges("5", "8", 1);
        graph.insertEdges("3", "6", 1);
        graph.insertEdges("3", "1", 1);
        graph.insertEdges("7", "6", 1);
        graph.print();
        graph.deepFirstSearch("1");
        graph.boardFirstSearch("1");
    }
}

class Graph {

    private final String[] vertexes;

    private final int[][] edges;

    private final boolean[] isVisited;

    public Graph(String[] vertexes) {
        this.vertexes = vertexes;
        edges = new int[vertexes.length][vertexes.length];
        isVisited = new boolean[vertexes.length];
    }

    public void insertEdges(String a, String b, int weight) {
        int i = indexOf(a);
        int j = indexOf(b);
        edges[i][j] = weight;
        edges[j][i] = weight;
    }

    private int indexOf(String vertex) {
        for (int i = 0; i < vertexes.length; i++) {
            if (vertexes[i].equals(vertex)) {
                return i;
            }
        }
        throw new RuntimeException("could not find the vertex");
    }

    public void boardFirstSearch(String vertex) {
        int index = indexOf(vertex);
        resetIsVisited();
        Queue<Integer> queue = new LinkedList<>();
        queue.add(index);
        isVisited[index] = true;
        boardFirstSearch(queue);
        System.out.println();
    }

    private void boardFirstSearch(Queue<Integer> queue) {
        while (!queue.isEmpty()) {
            Integer index = queue.poll();
            System.out.print(vertexes[index] + "->");
            for (int i = 0; i < vertexes.length; i++) {
                if (i == index) continue;
                if (edges[index][i] != 0 && !isVisited[i]) {
                    isVisited[i] = true;
                    queue.add(i);
                }
            }
        }

    }

    public void deepFirstSearch(String vertex) {
        int i = indexOf(vertex);
        resetIsVisited();
        isVisited[i] = true;
        deepFirstSearch(i);
        System.out.println();
    }

    private void deepFirstSearch(int index) {
        System.out.print(vertexes[index] + "->");
        for (int i = 0; i < vertexes.length; i++) {
            if (i == index) continue;
            if (edges[index][i] != 0 && !isVisited[i]) {
                isVisited[i] = true;
                deepFirstSearch(i);
            }
        }
    }

    public void resetIsVisited() {
        Arrays.fill(isVisited, false);
    }

    public void print() {
        for (int i = 0; i < vertexes.length; i++) {
            System.out.println();
            for (int j = 0; j < vertexes.length; j++) {
                System.out.printf("(%s,%s)-%d\t", vertexes[i], vertexes[j], edges[i][j]);
            }
        }
        System.out.println();
    }
}
