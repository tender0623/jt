package com.demo.test;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by CGB on 2019/4/30.
 */
public class Test {
    public static void main(String[] args) {
        int[] a = suiji();
        System.out.println(Arrays.toString(a));
        sort(a);
        System.out.println(Arrays.toString(a));
    }

    private static int[] suiji() {
        int n = new Random().nextInt(5) + 6;
        int[] a = new int[n];
        for (int i = 0; i < a.length; i++) {
            a[i] = new Random().nextInt(1000);
        }
        return a;
    }


    public static void sort(int[] a) {

        for (int i = 0; i < a.length; i++) {
            int m = i;
            for (int j = i + 1; j < a.length; j++) {
                if (a[i] > a[j]) {
                    m = j;
                }
                int t = a[i];
                a[i] = a[m];
                a[m] = t;
            }
        }

    }
}