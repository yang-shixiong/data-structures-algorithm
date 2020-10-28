package com.yang.algorithm;

/**
 * Description:
 *
 * @author mark
 * Date 2020/10/28
 */
public class MatchString {

    public static void main(String[] args) {
        String str1 = "BBC ABCDAB ABCDABCDABDE";
        String target = "ABCDABD";
        useSubString(str1, target);
        useNothing(str1, target);
        useWhile(str1, target);
    }

    public static void useSubString(String str1, String target) {
        for (int i = 0; i < str1.length() - target.length() + 1; i++) {
            if (target.equals(str1.substring(i, i + target.length()))) {
                System.out.println(i);
                break;
            }
        }
    }

    public static void useNothing(String str1, String target) {
        char[] str1Arr = str1.toCharArray();
        char[] targetArr = target.toCharArray();
        for (int i = 0; i < str1Arr.length - targetArr.length + 1; i++) {
            for (int j = 0; j < targetArr.length; j++) {
                if (str1Arr[i + j] == targetArr[j]) {
                    if (j == targetArr.length - 1) {
                        System.out.println("find it, index: " + i);
                    }
                } else {
                    break;
                }
            }
        }
    }

    public static void useWhile(String str1, String target) {
        char[] str1Arr = str1.toCharArray();
        char[] targetArr = target.toCharArray();
        int i = 0;
        int j = 0;
        while (i < str1Arr.length && j < targetArr.length) {
            if (str1Arr[i] == targetArr[j]) {
                i++;
                j++;
            } else {
                i = i - j + 1;
                j = 0;
            }
        }
        if (j == targetArr.length) {
            System.out.println("find it: " + (i - j));
        }
    }
}
