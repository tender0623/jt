package com.jt.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jt.pojo.Cart;
import com.jt.service.CartService;
import com.jt.util.UserThreadLocal;
import com.jt.vo.SysResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by CGB on 2019/4/22.
 */
@Controller
@RequestMapping("/cart")
public class CartController {

    @Reference(timeout = 3000,check = false)
    private CartService cartService;


    //实现购物车列表数据展现
    @RequestMapping("/show")
    public String show(Model model){
        //獲取當前用戶信息
        Long userId = UserThreadLocal.get().getId();
        //1.先獲取購物車信息
        List<Cart> cartList=cartService.findCartListByUserId(userId);
        model.addAttribute("cartList",cartList);
        return "cart";
    }

    /**
     * 实现购物车商品数据的修改
     * 如果使用restFu风格有多个参数，并且和pojo属性一致，可以用对象来接受
     * @param cart
     * @return
     */
    @RequestMapping("/update/num/{itemId}/{num}")
    @ResponseBody
    public SysResult updateNum(Cart cart){
        try {
            //Long userId=7L;
            Long userId = UserThreadLocal.get().getId();
            cart.setUserId(userId);
            cartService.updateCartNum(cart);
          return SysResult.ok();
        }catch (Exception e){
            e.printStackTrace();
            return SysResult.fail();
        }
    }

    @RequestMapping("/add/{itemId}")
    public String addCart(Cart cart){
        Long userId = UserThreadLocal.get().getId();
        cart.setUserId(userId);
        cartService.addCart(cart);
        return "redirect:/cart/show.html";
    }

    //http://www.jt.com/cart/delete/562379.html
    @RequestMapping("/delete/{itemId}")
    public SysResult deleteCart(@PathVariable Long itemId){
        cartService.deleteCart(itemId);
        return SysResult.ok();
    }
}