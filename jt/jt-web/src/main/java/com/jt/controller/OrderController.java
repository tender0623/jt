package com.jt.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jt.pojo.Cart;
import com.jt.pojo.Order;
import com.jt.service.CartService;
import com.jt.service.OrderService;
import com.jt.util.UserThreadLocal;
import com.jt.vo.SysResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by CGB on 2019/4/23.
 */

@Controller
@RequestMapping("/order")
public class OrderController {

    @Reference(timeout = 3000,check =false )
    private CartService cartService;
    @Reference(timeout = 3000,check = false)
    private OrderService orderService;


    @RequestMapping("/create")
    public String show(Model model){

        Long userId = UserThreadLocal.get().getId();
        List<Cart> carts = cartService.findCartListByUserId(userId);
        model.addAttribute("carts",carts);
        return "order-cart";
    }

    //实现订单提交
    @RequestMapping("/submit")
    @ResponseBody
    public SysResult saveOrder(Order order){
        try {
            Long userId = UserThreadLocal.get().getId();
            order.setUserId(userId);
            String orderId=orderService.saveOrder(order);
            if (!StringUtils.isEmpty(orderId)){
                return SysResult.ok(orderId);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return SysResult.fail();
    }

    //實現根據orderId查詢item信息
    @RequestMapping("/success")
    public String findOrderById(String id,Model model){
        Order order=orderService.findOrderById(id);
        model.addAttribute("order",order);
        return "success";
    }
}
