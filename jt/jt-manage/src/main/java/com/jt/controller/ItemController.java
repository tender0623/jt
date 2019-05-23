package com.jt.controller;

import com.jt.pojo.Item;
import com.jt.pojo.ItemDesc;
import com.jt.vo.EasyUIList;
import com.jt.vo.SysResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.jt.service.ItemService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.temporal.Temporal;

@RequestMapping("/item/")
@RestController
public class ItemController {
	
	@Autowired
	private ItemService itemService;

	/**
	 * 关于框架编码的说明:
	 *  使用旧的ssm框架时3-4版本,如果返回数据为string 则将数据通过@ResponseBody返回时,采用ISO-8859-1编码格式,所有返回为乱码
	 *  如果返回数据为对象时(pojo/vo),采用UTF-8格式
	 *
	 *  当使用springboot时,返回数据都是UTF-8
	 *
	 *  Post乱码问题: response.setcontent("text/html;charset-utf-8")
	 * @param page
	 * @param rows
	 * @return
	 */
	//实现商品的分页查询
	@RequestMapping("query")
	public EasyUIList finfItemByPage(Integer page,Integer rows){
		return  itemService.findItemByPage(page,rows);

	}

	@RequestMapping("save")
	public SysResult saveItem(Item item,ItemDesc itemDesc){
		try {
			itemService.saveItem(item,itemDesc);
			return SysResult.ok();
		}catch (Exception e){
			e.printStackTrace();
			return SysResult.fail();
		}
	}
	//实现商品下架
	@RequestMapping("instock")
	public SysResult  instock(Long[] ids){
		try {
			int status=2;
			itemService.updateState(ids,status);
			return SysResult.ok();
		}catch (Exception e){
			e.printStackTrace();
			return SysResult.fail();
		}

	}
	//实现商品上架
	@RequestMapping("reshelf")
	public SysResult  reshelf(Long[] ids){
		try {
			int status=1;
			itemService.updateState(ids,status);
			return SysResult.ok();
		}catch (Exception e){
			e.printStackTrace();
			return SysResult.fail();
		}

	}
	//实现商品修改操作
	@RequestMapping("update")
	public SysResult updateItem(Item item,ItemDesc itemDesc){
		try {
			itemService.updateItem(item,itemDesc);
			return SysResult.ok();
		}catch (Exception e){
			e.printStackTrace();
			return SysResult.fail();
		}
	}
	//事项商品删除
	@RequestMapping("delete")
	public SysResult deleteItems(Long[] ids){
		try {
			itemService.deleteItems(ids);

			return SysResult.ok();
		}catch (Exception e){
			e.printStackTrace();
			return SysResult.fail();
		}
	}

	//根据itemId查询商品详情信息
	@RequestMapping("/query/item/desc/{itemId}")
	public SysResult findItemDescById(@PathVariable Long itemId){
		try {
			ItemDesc itemDesc=itemService.findItemDescById(itemId);
			return SysResult.ok(itemDesc);
		}catch (Exception e){
			e.printStackTrace();
			return SysResult.fail();
		}
	}
}
