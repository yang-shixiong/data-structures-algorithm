package com.yang.tree.huffmantree;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description:
 *
 * @author mark
 * Date 2020/10/27
 */
public class HuffmanCompressString {

    public static Map<Byte, String> huffmanCode = new HashMap<>();

    public static void main(String[] args) throws IOException {
        String str = "i like like like java do you like a java";
        compressString(str);
        compressFile("E:\\ss.png", "E:\\s2.png");
    }

    public static void compressString(String str) {
        byte[] codes = str.getBytes();
        byte[] bytes = huffmanZip(codes);
        byte[] result = decode(bytes);
        System.out.println(new String(result));
    }

    public static void compressFile(String src, String dts) {
        FileInputStream inputStream = null;
        FileOutputStream outputStream = null;
        FileOutputStream outputStream2 = null;
        try {
            inputStream = new FileInputStream(src);
            outputStream = new FileOutputStream(dts + ".zip");
            outputStream2 = new FileOutputStream(dts);
            FileChannel channel = inputStream.getChannel();
            ByteBuffer allocate = ByteBuffer.allocate((int) channel.size());
            channel.read(allocate);
            byte[] bytes = huffmanZip(allocate.array());
            FileChannel channel1 = outputStream.getChannel();
            channel1.write(ByteBuffer.wrap(bytes));
            FileChannel channel2 = outputStream2.getChannel();
            byte[] decode = decode(bytes);
            channel2.write(ByteBuffer.wrap(decode));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
                outputStream.close();
                outputStream2.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    public static byte[] huffmanZip(byte[] bytes) {
        Map<Byte, Integer> map = new HashMap<>();
        for (byte aByte : bytes) {
            Integer count = map.computeIfAbsent(aByte, k -> 0);
            map.put(aByte, count + 1);
        }
        List<HuffNode> list = new ArrayList<>();
        map.forEach((value, weight) -> list.add(new HuffNode(value, weight)));

        while (list.size() > 1) {
            sort(list);
            HuffNode second = list.remove(1);
            HuffNode first = list.remove(0);
            HuffNode huffNode = new HuffNode(second.weight + first.weight);
            huffNode.right = first;
            huffNode.left = second;
            list.add(huffNode);
        }
        HuffNode root = list.get(0);
        getCodes(root, null, null);
        StringBuilder huffmanStr = new StringBuilder();
        for (byte aByte : bytes) {
            huffmanStr.append(huffmanCode.get(aByte));
        }
        byte[] arr = new byte[huffmanStr.length() % 8 == 0 ? huffmanStr.length() / 8 : huffmanStr.length() / 8 + 1];
        int index = 0;
        for (int i = 0; i < huffmanStr.length(); i += 8) {
            if (i + 8 < huffmanStr.length()) {
                arr[index] = (byte) Integer.parseInt(huffmanStr.substring(i, i + 8), 2);
            } else {
                arr[index] = (byte) Integer.parseInt(huffmanStr.substring(i), 2);
            }
            index++;
        }
        return arr;
    }

    public static void getCodes(HuffNode node, String road, StringBuilder stringBuilder) {
        StringBuilder builder = stringBuilder == null ? new StringBuilder() : new StringBuilder(stringBuilder);
        if (road != null)
            builder.append(road);
        if (node.value != null) {
            huffmanCode.put(node.value, builder.toString());
        } else {
            if (node.left != null) {
                getCodes(node.left, "0", builder);
            }
            if (node.right != null) {
                getCodes(node.right, "1", builder);
            }
        }
    }

    public static byte[] decode(byte[] huffmanBytes) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < huffmanBytes.length; i++) {
            stringBuilder.append(byteToBitString(!(i == huffmanBytes.length - 1), huffmanBytes[i]));
        }
        Map<String, Byte> map = new HashMap<>();
        huffmanCode.forEach((value, str) -> map.put(str, value));

        String value = "";
        List<Byte> list = new ArrayList<>();
        int index = 0;
        while (true) {
            value += stringBuilder.charAt(index);
            if (index == stringBuilder.length() - 1) {
                Byte aByte = map.get(value);
                list.add(aByte);
                break;
            }
            if (map.get(value) != null) {
                list.add(map.get(value));
                value = "";
            }
            index++;
        }
        byte[] result = new byte[list.size()];

        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        return result;
    }

    private static String byteToBitString(boolean flag, byte b) {
        int temp = b;
        if (flag) {
            temp |= 256;
        }
        String s = Integer.toBinaryString(temp);
        if (flag) {
            return s.substring(s.length() - 8);
        }
        return s;
    }

    public static void sort(List<HuffNode> list) {
        for (int i = list.size() / 2 - 1; i > 0; i--) {
            heapSort(list, i, list.size());
        }
        for (int j = list.size() - 1; j > 0; j--) {
            HuffNode end = list.get(j);
            HuffNode first = list.get(0);
            list.set(0, end);
            list.set(j, first);
            heapSort(list, 0, j);
        }
    }

    public static void heapSort(List<HuffNode> list, int n, int length) {
        HuffNode temp = list.get(n);
        for (int k = 2 * n + 1; k < length; k = 2 * k + 1) {
            if (k + 1 < length && list.get(k).weight < list.get(k + 1).weight) {
                k++;
            }
            if (temp.weight < list.get(k).weight) {
                list.set(n, list.get(k));
                n = k;
            } else {
                break;
            }
        }
        list.set(n, temp);
    }

}

class HuffNode implements Comparable<HuffNode> {
    Byte value;
    int weight;
    HuffNode left;
    HuffNode right;

    public HuffNode(int weight) {
        this.weight = weight;
    }

    public HuffNode(Byte value, int weight) {
        this.value = value;
        this.weight = weight;
    }

    public void preOrder() {

        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    @Override
    public String toString() {
        return "HuffNode{" +
                "value=" + value +
                ", weight=" + weight +
                '}';
    }

    @Override
    public int compareTo(HuffNode o) {
        return this.weight - o.weight;
    }
}