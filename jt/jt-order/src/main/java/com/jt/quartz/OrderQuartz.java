package com.jt.quartz;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.jt.mapper.OrderMapper;
import com.jt.pojo.Order;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;


//准备订单定时任务
@Component
public class OrderQuartz extends QuartzJobBean{

	@Autowired
	private OrderMapper orderMapper;

	//定時任務執行時,執行job具體操作
	@Override
	protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
		//得到日曆對象
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MINUTE,-15);//進行時間的運算
		Date time = calendar.getTime();

		Order order = new Order();
		order.setStatus(6).setUpdated(new Date());
		UpdateWrapper<Order> wrapper = new UpdateWrapper<>();
		wrapper.eq("status",1).lt("created",time);
		orderMapper.update(order,wrapper);


	}


	/**@Autowired
	private OrderService orderService;
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {

		orderService.updateOrderStatus();
		System.out.println("定时任务执行");
	}*/
}
