package com.yang.algorithm;

import java.util.Arrays;

/**
 * Description:
 *
 * @author mark
 * Date 2020/10/29
 */
public class Floyd {

    public static void main(String[] args) {
        String[] arr = {"A", "B", "C", "D", "E", "F", "G"};
        FloydGraph graph = new FloydGraph(arr);
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
        graph.findWay();
        graph.print();
        graph.printPreVertexes();
    }
}

class FloydGraph {
    private int[][] edges;

    private String[][] preVertexes;

    private String[] vertexes;

    public FloydGraph(String[] vertexes) {
        this.vertexes = vertexes;
        this.edges = new int[vertexes.length][vertexes.length];
        for (int i = 0; i < this.edges.length; i ++) {
            Arrays.fill(this.edges[i], Integer.MAX_VALUE);
            this.edges[i][i] = 0;

        }
        this.preVertexes = new String[vertexes.length][vertexes.length];
        for (int i = 0; i < this.preVertexes.length; i++) {
            Arrays.fill(this.preVertexes[i], this.vertexes[i]);
        }
    }

    public void findWay() {
        for (int i = 0; i < this.vertexes.length; i++) {
            findWay(i);
        }
    }

    private void findWay(int index) {
        for(int i = 0; i < this.edges.length; i++){
            if(i == index) continue;
            for (int j = 0; j < this.edges.length; j++){
                if(j == index) continue;
                if(this.edges[i][j] - this.edges[index][i] > this.edges[index][j]){
                    this.edges[i][j] = this.edges[index][i] + this.edges[index][j];
                    this.preVertexes[i][j] = this.preVertexes[index][j];
                }
            }
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
        for (int i = 0; i < this.edges.length; i++) {
            for (int j = 0; j < this.edges[i].length; j ++) {
                System.out.printf("from %s to %s = %4d\t",this.vertexes[i], this.vertexes[j], this.edges[i][j]);
            }
            System.out.println();
        }
    }

    public void printPreVertexes() {
        for (String[] edge : this.preVertexes) {
            System.out.println(Arrays.toString(edge));
        }
    }
}
