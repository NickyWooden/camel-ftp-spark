package com.ly.spark.oneline.utils;

public class Test {
    public static void main(String[] args) {
        String s = "hello.txt";
        String[] m = s.split(".");
        for (int i = 0; i < m.length; i++) {
            System.out.println(m[i]);
        }
    }
}
