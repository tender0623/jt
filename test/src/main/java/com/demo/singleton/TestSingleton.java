package com.demo.singleton;

/**
 * Created by CGB on 2019/4/21.
 */

class Singleton01{
    private Singleton01() {//构造方法私有化,不允许外部new对象
    }
    private static Singleton01 singleton;
    //方法上加static,不用依赖任何对象就可以进行访问,所有不用再外面new对象
    public static Singleton01 getInstance(){
        if (singleton==null){
            synchronized (Singleton01.class){
                if (singleton==null){
                    singleton=new Singleton01();
                }
            }
        }
        return singleton;
    }
}


public class TestSingleton {
    public static void main(String[] args){

        Singleton01 singleton1 = Singleton01.getInstance();
        System.out.println(singleton1);
        Singleton01 singleton2 = Singleton01.getInstance();
        System.out.println(singleton2);
    }

}
