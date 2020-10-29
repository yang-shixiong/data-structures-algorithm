package com.yang.algorithm;

import java.util.Arrays;

/**
 * Description:
 *
 * @author mark
 * Date 2020/10/29
 */
public class Kruskal {

    public static void main(String[] args) {
        String[] arr = {"A", "B", "C", "D", "E", "F", "G"};
        KruskalGraph graph = new KruskalGraph(arr);
        graph.insertEdge("A", "B", 12);
        graph.insertEdge("A", "F", 16);
        graph.insertEdge("A", "G", 14);
        graph.insertEdge("B", "C", 10);
        graph.insertEdge("B", "F", 7);
        graph.insertEdge("C", "F", 6);
        graph.insertEdge("C", "E", 5);
        graph.insertEdge("C", "D", 3);
        graph.insertEdge("D", "E", 4);
        graph.insertEdge("E", "F", 2);
        graph.insertEdge("E", "G", 8);
        graph.insertEdge("G", "F", 9);
        graph.print();
        graph.findMiniEdge();
    }
}


class KruskalGraph {
    private String[] vertexes;

    private int edgeNum;

    private int[][] edges;

    private boolean[] isVisited;

    private EdgeInfo[] edgeInfos;

    public KruskalGraph(String[] vertexes) {
        this.vertexes = vertexes;
        edges = new int[vertexes.length][vertexes.length];
        isVisited = new boolean[vertexes.length];
        for (int[] edge : edges) {
            Arrays.fill(edge, Integer.MAX_VALUE);
        }
    }

    public void cleanIsVisited() {
        Arrays.fill(isVisited, Boolean.FALSE);
    }

    public boolean isEnd() {
        for (boolean visited : this.isVisited) {
            if (!visited) {
                return false;
            }
        }
        return true;
    }

    private void initEdgeInfos() {
        edgeInfos = new EdgeInfo[this.edgeNum];
        int i = 0;
        for (int m = 0; m < this.vertexes.length; m++) {
            for (int n = m + 1; n < this.vertexes.length; n++) {
                if (this.edges[m][n] != Integer.MAX_VALUE) {
                    edgeInfos[i++] = new EdgeInfo(this.vertexes[m], this.vertexes[n], this.edges[m][n]);
                }
            }
        }
        this.sortEdgeInfo();
    }

    private void sortEdgeInfo() {
        for (int i = 0; i < edgeNum; i++) {
            for (int j = i + 1; j < edgeNum; j++) {
                if (edgeInfos[i].weight > edgeInfos[j].weight) {
                    EdgeInfo temp = edgeInfos[i];
                    edgeInfos[i] = edgeInfos[j];
                    edgeInfos[j] = temp;
                }
            }
        }
    }

    public void findMiniEdge() {
        this.initEdgeInfos();
        int[] ends = new int[this.edgeNum];
        for (EdgeInfo edgeInfo : this.edgeInfos) {
            int start = indexOf(edgeInfo.start);
            int end = indexOf(edgeInfo.end);

            int startEnd = getEnds(ends, start);
            int endEnd = getEnds(ends, end);
            if (startEnd != endEnd) {
                ends[startEnd] = endEnd;
                System.out.println(edgeInfo.toString());
            }
        }
    }

    public int getEnds(int[] ends, int i){
        while (ends[i] != 0){
            i = ends[i];
        }
        return i;
    }

    public void insertEdge(String a, String b, int weight) {
        int i = indexOf(a);
        int j = indexOf(b);
        this.edges[i][j] = weight;
        this.edges[j][i] = weight;
        edgeNum++;
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
            for (int i : edge) {
                System.out.printf("%12d", i);
            }
            System.out.println();
        }
    }
}

class EdgeInfo {
    String start;
    String end;
    int weight;

    public EdgeInfo(String start, String end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "edge{" +
                "start=" + start +
                ", end=" + end +
                ", weight=" + weight +
                '}';
    }
}