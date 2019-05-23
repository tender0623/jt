package com.jt.service;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.jt.mapper.ItemDescMapper;
import com.jt.pojo.Item;
import com.jt.pojo.ItemDesc;
import com.jt.vo.EasyUIList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jt.mapper.ItemMapper;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	private ItemMapper itemMapper;
	@Autowired
	private ItemDescMapper itemDescMapper;


	@Override
	public EasyUIList findItemByPage(Integer page, Integer rows) {
		int total=itemMapper.selectCount(null);
		/**获取分页后的数据
		 * sql: 查询第一页      limit(起始位置,显示条数)
		 * select * from to_item limit((n-1)rows,rows)
		*/
		int start = (page-1)*rows;
		List<Item> itemList =itemMapper.findItemListByPage(start,rows);
		return new EasyUIList(total,itemList);
	}

	/**
	 * 知识回顾:
	 * propagation:表示事务传播属性
	 * 		REQUIRED:必须添加事务,默认值
	 * 		SPPORTS:事务支持
	 * 		REQUIRES_NEW:会开启一个全新的事务
	 *如果是运行时异常,则spring负责事务回滚
	 * 如果是编译时异常(检查异常),spring不负责事务回滚
	 * rollbackfor: 指定的异常回滚
	 * norollbackfor:指定异常不回滚
	 * @param item
	 * @return
	 */
	@Override
	@Transactional
	public void saveItem(Item item,ItemDesc itemDesc) {
		item.setStatus(1);
		item.setCreated(new Date());
		item.setUpdated(new Date());
	     itemMapper.insert(item);

	     itemDesc.setItemId(item.getId()).setCreated(item.getCreated()).setUpdated(item.getUpdated());
	     itemDescMapper.insert(itemDesc);
	}

	/**
	 * 该操作是一个批量跟新操作
	 * sql:
	 * 	update tb_item_cat set status=#{status} updated=#{updated} where id in(......)
	 *
	 * @param ids
	 * @param status
	 */
	@Override
	public void updateState(Long[] ids, Integer status) {
		Item item = new Item();
		item.setStatus(status).setUpdated(new Date());
		//System.out.println(item);
		UpdateWrapper<Item> wrapper = new UpdateWrapper<>();
		List<Long> idList= Arrays.asList(ids);
		wrapper.in("id",idList);
		itemMapper.update(item,wrapper);

	}

	@Override
	@Transactional
	public void updateItem(Item item,ItemDesc itemDesc) {
		item.setUpdated(new Date());
		itemMapper.updateById(item);
		itemDesc.setItemId(item.getId()).setUpdated(item.getUpdated());
		itemDescMapper.updateById(itemDesc);
	}

	@Override
	@Transactional
	public void deleteItems(Long[] ids) {
		List<Long> idList=Arrays.asList(ids);
		itemMapper.deleteBatchIds(idList);
	}

	@Override
	public ItemDesc findItemDescById(Long itemId) {
		ItemDesc itemDesc = itemDescMapper.selectById(itemId);
		return itemDesc;
	}

	@Override
	public Item findItemById(long itemId) {
		Item item = itemMapper.selectById(itemId);
		return item;
	}
}
