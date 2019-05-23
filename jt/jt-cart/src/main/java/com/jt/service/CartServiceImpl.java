package com.jt.service;

import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jt.mapper.CartMapper;
import com.jt.pojo.Cart;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CartServiceImpl implements CartService {
	
	@Autowired
	private CartMapper cartMapper;

	@Override
	public List<Cart> findCartListByUserId(Long userId) {
		QueryWrapper<Cart> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("user_id", userId);
		return cartMapper.selectList(queryWrapper);
	}

	/**
	 * entity:修改的值
	 * updateWrpaper where条件
	 * @param cart
	 */
	@Override
	@Transactional
	public void updateCartNum(Cart cart) {
		Cart cartDB = new Cart();
		cartDB.setNum(cart.getNum()).setUpdated(new Date());
		UpdateWrapper<Cart> updateWrapper = new UpdateWrapper<>();
		updateWrapper.eq("user_id",cart.getUserId()).eq("item_id",cart.getItemId());
		cartMapper.update(cartDB,updateWrapper);
	}

	/**
	 * 能否直接入库：不能，同样的购物行为只有一条购物记录
	 * user_id,item_id;
	 * 1.如果当前商品的数据数据库中已经存在，那么则数据update
	 * 2.如果当前商品数据库中没有，则insert
	 *
	 * @param cart
	 */
	@Override
	@Transactional
	public void addCart(Cart cart) {
		QueryWrapper<Cart> wrapper = new QueryWrapper<>();
		wrapper.eq("item_id",cart.getItemId()).eq("user_id",cart.getUserId());
		Cart cartDB = cartMapper.selectOne(wrapper);
		if (cartDB==null){
			//表示第一次购买
			cart.setCreated(new Date()).setUpdated(cart.getCreated());
			cartMapper.insert(cart);
		}else {
			//表示用户已经购买过了
			int num = cart.getNum() + cartDB.getNum();
			cartDB.setNum(num).setUpdated(new Date());
			cartMapper.updateById(cartDB);
		}
	}

	@Override
	public void deleteCart(Long itemId) {
		QueryWrapper<Cart> wrapper = new QueryWrapper<>();
		wrapper.eq("item_id",itemId);
		cartMapper.delete(wrapper);
	}
}
