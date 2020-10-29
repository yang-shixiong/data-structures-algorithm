package com.yang.algorithm;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Description:
 *
 * @author mark
 * Date 2020/10/29
 */
public class HorseChess {

    public static void main(String[] args) {
        Chess chess = new Chess(8, 8);
        chess.greedyStart(0, 0, 1);
        chess.print();
    }
}

class Chess {
    private int[][] chess;

    private int end;

    public Chess(int row, int column) {
        this.chess = new int[row][column];
        end = row * column;
    }

    public boolean start(int row, int column, int count) {
        chess[row][column] = count;
        if (count == end) {
            System.out.println("finish");
            return true;
        } else {
            if (row - 2 >= 0 && column + 1 < this.chess[0].length && chess[row - 2][column + 1] == 0 && start(row - 2, column + 1, count + 1)) {
                return true;
            } else if (row - 1 >= 0 && column + 2 < this.chess[0].length && chess[row - 1][column + 2] == 0 && start(row - 1, column + 2, count + 1)) {
                return true;
            } else if (row + 1 < this.chess.length && column + 2 < this.chess[0].length && chess[row + 1][column + 2] == 0 && start(row + 1, column + 2, count + 1)) {
                return true;
            } else if (row + 2 < this.chess.length && column + 1 < this.chess[0].length && chess[row + 2][column + 1] == 0 && start(row + 2, column + 1, count + 1)) {
                return true;
            } else if (row + 2 < this.chess.length && column - 1 >= 0 && chess[row + 2][column - 1] == 0 && start(row + 2, column - 1, count + 1)) {
                return true;
            } else if (row + 1 < this.chess.length && column - 2 >= 0 && chess[row + 1][column - 2] == 0 && start(row + 1, column - 2, count + 1)) {
                return true;
            } else if (row - 1 >= 0 && column - 2 >= 0 && chess[row - 1][column - 2] == 0 && start(row - 1, column - 2, count + 1)) {
                return true;
            } else if (row - 2 >= 0 && column - 1 >= 0 && chess[row - 2][column - 1] == 0 && start(row - 2, column - 1, count + 1)) {
                return true;
            } else {
                chess[row][column] = 0;
                return false;
            }
        }
    }

    public void greedyStart(int row, int column, int count) {
        this.chess[row][column] = count;
        ArrayList<Point> next = next(new Point(row, column));
        sort(next);
        while (!next.isEmpty()) {
            Point remove = next.remove(0);
            greedyStart(remove.x, remove.y, count + 1);
        }
        if (count == end - 1) {
            System.out.println("finish");
        } else {
            this.chess[row][column] = 0;
        }
    }

    public void sort(ArrayList<Point> next) {
        next.sort((Point p1, Point p2) -> {
            int count1 = next(p1).size();
            int count2 = next(p2).size();
            if (count1 > count2) {
                return 1;
            } else if (count1 == count2) {
                return 0;
            } else {
                return -1;
            }
        });
    }

    private ArrayList<Point> next(Point current) {
        ArrayList<Point> next = new ArrayList<>();
        Point point = new Point();
        if ((point.x = current.x - 2) >= 0 && (point.y = current.y + 1) < this.chess[0].length && this.chess[point.x][point.y] == 0) {
            next.add(new Point(point));
        }
        if ((point.x = current.x - 1) >= 0 && (point.y = current.y + 2) < this.chess[0].length && this.chess[point.x][point.y] == 0) {
            next.add(new Point(point));
        }
        if ((point.x = current.x + 1) < this.chess.length && (point.y = current.y + 2) < this.chess[0].length && this.chess[point.x][point.y] == 0) {
            next.add(new Point(point));
        }
        if ((point.x = current.x + 2) < this.chess.length && (point.y = current.y + 1) < this.chess[0].length && this.chess[point.x][point.y] == 0) {
            next.add(new Point(point));
        }
        if ((point.x = current.x + 2) < this.chess.length && (point.y = current.y - 1) >= 0 && this.chess[point.x][point.y] == 0) {
            next.add(new Point(point));
        }
        if ((point.x = current.x + 1) < this.chess.length && (point.y = current.y - 2) >= 0 && this.chess[point.x][point.y] == 0) {
            next.add(new Point(point));
        }
        if ((point.x = current.x - 1) >= 0 && (point.y = current.y - 2) >= 0 && this.chess[point.x][point.y] == 0) {
            next.add(new Point(point));
        }
        if ((point.x = current.x - 2) >= 0 && (point.y = current.y - 1) >= 0 && this.chess[point.x][point.y] == 0) {
            next.add(new Point(point));
        }
        return next;
    }

    public void print() {
        for (int[] ints : this.chess) {
            System.out.println(Arrays.toString(ints));
        }
    }
}
