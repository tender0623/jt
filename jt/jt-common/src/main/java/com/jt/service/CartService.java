package com.jt.service;

import com.jt.pojo.Cart;

import java.util.List;

/**
 * Created by CGB on 2019/4/22.
 */
public interface CartService {
    List<Cart>findCartListByUserId(Long id);

    void updateCartNum(Cart cart);

    void addCart(Cart cart);

    void deleteCart(Long itemId);
}
