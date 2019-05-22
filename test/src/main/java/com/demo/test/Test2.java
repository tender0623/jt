package com.demo.test;

/**
 * Created by CGB on 2019/5/6.
 */
public class Test2 {
    public static void main(String[] args){
        for(int n=2;n<=1000;n++){
            int s=0;
            int n1=n;
            while (n1>0){
                int t=n1 %10;
                System.out.println(t);
                s+=t*t*t;
                n1/=10;
            }
            if (s==n){
                System.out.println(n);
                System.out.println("woshidashuaige");
            }
        }
    }
}
