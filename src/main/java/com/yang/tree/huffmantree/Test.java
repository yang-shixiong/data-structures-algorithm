package com.yang.tree.huffmantree;

import java.util.Arrays;

/**
 * Description:
 *
 * @author mark
 * Date 2020/10/27
 */
public class Test {
    public static void main(String[] args) {
        String s = "00000011";
        byte i = (byte)Integer.parseInt(s, 2);
        System.out.println(i);
        int j = i;
        System.out.println(Integer.toBinaryString(j));
        j |= 256;
        String s3 = Integer.toBinaryString(j);
        System.out.println(s3);
        System.out.println(s3.substring(s3.length() - 8));
    }
}
