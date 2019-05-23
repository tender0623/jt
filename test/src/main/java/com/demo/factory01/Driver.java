package com.demo.factory01;

/**
 * Created by CGB on 2019/4/17.
 */

/**
 * 此类为一个工厂类对象
 */
public class Driver {
    public static Car driver(String s) throws Exception {
        if (s.equalsIgnoreCase("Benz")){
            return new Benz();
        }else if (s.equalsIgnoreCase("Bmw")){
            return new Bmw();
        }else throw new Exception();
    }
    public static void main(String[] args){
      String s1="bcd";

      String s2="bcd";
        System.out.println(s1==s2);
    }
}
