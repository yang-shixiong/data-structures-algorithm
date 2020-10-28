package com.yang.algorithm;

/**
 * Description:
 *
 * @author mark
 * Date 2020/10/28
 */
public class HanoiTower {

    public static void main(String[] args) {
        long l = System.currentTimeMillis();
        hanoiTower(64, 'a', 'b', 'c');
        System.out.println(System.currentTimeMillis() - l);
    }

    public static void hanoiTower(int num, char a, char b, char c) {
        if (num == 1) {
            //System.out.println("the 1 from " + a + " to " + c);
        } else {
            hanoiTower(num - 1, a, c, b);
            //System.out.println("the " + num + " from " + a + " to " + c);
            hanoiTower(num - 1, b, a, c);
        }
    }
}
