package com.yang.algorithm;

import java.util.Arrays;

/**
 * Description:
 *
 * @author mark
 * Date 2020/10/29
 */
public class Prim {
    public static void main(String[] args) {
        String[] arr = {"A", "B", "C", "D", "E", "F", "G"};
        Graph graph = new Graph(arr);
        graph.insertEdge("A", "B", 5);
        graph.insertEdge("A", "G", 2);
        graph.insertEdge("A", "C", 7);
        graph.insertEdge("B", "G", 3);
        graph.insertEdge("B", "D", 9);
        graph.insertEdge("D", "F", 4);
        graph.insertEdge("F", "G", 6);
        graph.insertEdge("F", "E", 5);
        graph.insertEdge("E", "G", 4);
        graph.insertEdge("E", "C", 8);
        graph.findWay();
    }
}

class Graph {
    private String[] vertexes;

    private int[][] edges;

    private boolean[] isVisited;

    public Graph(String[] vertexes) {
        this.vertexes = vertexes;
        edges = new int[vertexes.length][vertexes.length];
        isVisited = new boolean[vertexes.length];
        for (int[] edge : edges) {
            Arrays.fill(edge, Integer.MAX_VALUE);
        }
    }

    public void findWay() {
        for (String vertex : this.vertexes) {
            cleanIsVisited();
            System.out.print("start from: " + vertex);
            finWay(indexOf(vertex));
            System.out.println(" end");
        }
    }

    public void finWay(int index) {
        isVisited[index] = true;
        int m = -1;
        int n = -1;
        for (int k = 1; k < this.vertexes.length; k ++){
            int temp = Integer.MAX_VALUE;
            for (int i = 0; i < this.vertexes.length; i++) {
                for (int j = 0; j < this.vertexes.length; j++){
                    if(isVisited[i] && !isVisited[j] && this.edges[i][j] < temp){
                        temp = this.edges[i][j];
                        m = i;
                        n = j;
                    }
                }
            }
            isVisited[n] = true;
            System.out.printf(" find next: %s, <%s, %s>, weight: %d", this.vertexes[n], this.vertexes[m], this.vertexes[n], this.edges[m][n]);
        }
    }

    public void cleanIsVisited() {
        Arrays.fill(isVisited, Boolean.FALSE);
    }

    public void insertEdge(String a, String b, int weight) {
        int i = indexOf(a);
        int j = indexOf(b);
        this.edges[i][j] = weight;
        this.edges[j][i] = weight;
    }

    private int indexOf(String value) {
        for (int i = 0; i < this.vertexes.length; i++) {
            if (this.vertexes[i].equals(value)) {
                return i;
            }
        }
        throw new RuntimeException("could not find: " + value);
    }

    public void print() {
        for (int[] edge : this.edges) {
            System.out.println(Arrays.toString(edge));
        }
    }
}
