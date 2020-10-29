package com.yang.algorithm;

import java.util.*;

/**
 * Description:
 *
 * @author mark
 * Date 2020/10/29
 */
public class Greedy {

    public static void main(String[] args) {
        Set<String> set1 = Set.of("北京", "上海", "天津");
        Set<String> set2 = Set.of("广州", "北京", "深圳");
        Set<String> set3 = Set.of("成都", "上海", "杭州");
        Set<String> set4 = Set.of("上海", "天津");
        Set<String> set5 = Set.of("杭州", "大连");

        Map<String, Set<String>> map = new HashMap<>();
        map.put("K1", set1);
        map.put("K2", set2);
        map.put("K3", set3);
        map.put("K4", set4);
        map.put("K5", set5);
        Set<String> regions = new HashSet<>();

        map.forEach((key, value) ->  regions.addAll(value));

        while (!regions.isEmpty()){
            int temp = 0;
            String region = null;
            for (String s : map.keySet()) {
                int count = 0;
                for (String value : map.get(s)) {
                    if(regions.contains(value)){
                        count++;
                    }
                }
                if(count > temp){
                    temp = count;
                    region = s;
                }
            }
            System.out.println("choose: " + region);
            regions.removeAll(map.get(region));
        }
    }
}
