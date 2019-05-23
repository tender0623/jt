package com.jt.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jt.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jt.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper userMapper;

	/**
	 *
	 * @param param 代表用户输入的内容
	 * @param type  1.username 2.Phone 3.email
	 * @return sql:select count(*) from tb_user where username="zhangsan";
	 * 首先应该将type转化为具体数据库的字段
	 */
	@Override
	public Boolean findCheckUser(String param, Integer type) {
		String colum=null;
		switch (type){
			case 1:
				colum="username";
				break;
			case 2:
				colum="phone";
				break;
			case 3:
				colum="email";
				break;
		}
		QueryWrapper<User> wrapper = new QueryWrapper<
				>();
		wrapper.eq(colum,param);
		int count = userMapper.selectCount(wrapper);
		return count==0?false:true;
	}
}
