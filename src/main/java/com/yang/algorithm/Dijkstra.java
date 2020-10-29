package com.yang.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Description:
 *
 * @author mark
 * Date 2020/10/29
 */
public class Dijkstra {

    public static void main(String[] args) {
        String[] arr = {"A", "B", "C", "D", "E", "F", "G"};
        DijkstraGraph graph = new DijkstraGraph(arr);
        graph.insertEdge("A", "B", 5);
        graph.insertEdge("A", "C", 7);
        graph.insertEdge("A", "G", 2);
        graph.insertEdge("B", "G", 3);
        graph.insertEdge("B", "D", 9);
        graph.insertEdge("D", "F", 4);
        graph.insertEdge("F", "G", 6);
        graph.insertEdge("F", "E", 5);
        graph.insertEdge("E", "G", 4);
        graph.insertEdge("E", "C", 8);
        graph.finWay("G");
    }
}

class DijkstraGraph {
    private int[][] edges;

    private String[] vertexes;

    private int[] preVertexes;

    private List<String> list;

    public DijkstraGraph(String[] vertexes) {
        this.vertexes = vertexes;
        this.list = new ArrayList<>(Arrays.asList(vertexes));
        this.edges = new int[vertexes.length][vertexes.length];
        for (int i = 0; i < vertexes.length; i++) {
            for (int j = 0; j < vertexes.length; j++) {
                this.edges[i][j] = Integer.MAX_VALUE;
            }
        }
        preVertexes = new int[vertexes.length];
        Arrays.fill(preVertexes, -1);
    }

    public void finWay(String a) {
        int index = indexOf(a);
        boolean remove = list.remove(a);
        findWay(index);
    }

    public void findWay(int index) {
        while (!this.list.isEmpty()) {
            int temp = Integer.MAX_VALUE;
            int j = 0;
            for (int i = 0; i < edges[index].length; i++) {
                if (edges[index][i] < temp && this.list.contains(this.vertexes[i])) {
                    temp = edges[index][i];
                    j = i;
                }
            }
            System.out.println("find ->: " + this.vertexes[j]);
            this.list.remove(this.vertexes[j]);
            for (int i = 0; i < this.edges[j].length; i++) {
                if (index == i) continue;
                if (this.edges[index][i] - this.edges[j][i] > this.edges[index][j]) {
                    this.edges[index][i] = this.edges[j][i] + this.edges[index][j];
                    this.preVertexes[i] = j;
                }
            }
        }
        for (int i = 0; i < this.preVertexes.length; i++) {
            System.out.printf("from %s to %s min way is %s %s -> %s\n", this.vertexes[index], this.vertexes[i], this.vertexes[index], getIndex(i), this.vertexes[i]);
        }
    }

    private String getIndex(int i) {
        if (this.preVertexes[i] == -1) {
            return "";
        } else {
            return " -> " + this.vertexes[this.preVertexes[i]] + getIndex(this.preVertexes[i]);
        }
    }

    private int indexOf(String value) {
        for (int i = 0; i < this.vertexes.length; i++) {
            if (this.vertexes[i].equals(value)) {
                return i;
            }
        }
        throw new RuntimeException("could not find: " + value);
    }

    public void insertEdge(String a, String b, int weight) {
        int i = indexOf(a);
        int j = indexOf(b);
        this.edges[i][j] = weight;
        this.edges[j][i] = weight;
    }

    public void print() {
        for (int[] edge : this.edges) {
            for (int i : edge) {
                System.out.printf("%12d", i);
            }
            System.out.println();
        }
    }
}
