package com.my.order.repository;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.my.customer.vo.Customer;
import com.my.order.vo.OrderInfo;
import com.my.order.vo.OrderLine;
import com.my.product.vo.Product;

@SpringBootTest
class OrderInfoRepositoryTest {
	@Autowired
	private OrderInfoRepository repository;
	private Logger logger  = LoggerFactory.getLogger(getClass());
	@Test
	void testInsert() {
		OrderInfo info = new OrderInfo();
		Customer c = new Customer();
		c.setId("id999"); //주문자ID
		info.setOrderCustomer(c);
		List<OrderLine> lines = new ArrayList<>();
		OrderLine line = new OrderLine();
		line.setOrderInfo(info);
		Product p = new Product();
		p.setProdNo("C0001");
		line.setOrderProduct(p);
		line.setOrderQuantity(1);
		lines.add(line);
		
		OrderLine line2 = new OrderLine();
		line2.setOrderInfo(info);
		Product p2 = new Product();
		p2.setProdNo("C0002");
		line2.setOrderProduct(p2);
		line2.setOrderQuantity(2);
		lines.add(line2);
		
		info.setLines(lines);
		repository.save(info);
	}
	@Transactional
	@Test
	void testFindByOrderCustomer() {
		String id = "id999";
		Customer c = new Customer();
		c.setId(id);
		List<OrderInfo> list = repository.findByOrderCustomer(c);
		list.forEach(info-> {
			logger.error("orderNo=" + info.getOrderNo() + ":orderDt=" + info.getOrderDt()+":lines.size="+info.getLines().size());

			
		});
	}
}