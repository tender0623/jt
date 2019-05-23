package com.demo.factory01;

import javax.lang.model.element.NestingKind;

/**
 * Created by CGB on 2019/4/17.
 */
public class Bmw  implements Car {
    @Override
    public void drive() {
        System.out.println("开宝马");
    }


}
