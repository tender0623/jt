package com.demo.factory01;

/**
 * Created by CGB on 2019/4/17.
 */
public class Test01 {
    public static void main(String[] args) throws Exception {
        Car car = Driver.driver("Benz");
        System.out.println(car);
    }
}
