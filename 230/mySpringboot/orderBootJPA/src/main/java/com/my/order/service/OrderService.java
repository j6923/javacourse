package com.my.order.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.customer.vo.Customer;
import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.order.repository.OrderInfoRepository;
import com.my.order.vo.OrderInfo;
@Service("oService")
public class OrderService {
	@Autowired
//	private OrderDAOInterface dao;
	private OrderInfoRepository repository;
	public void add(OrderInfo info) throws AddException{
		//dao.add(info);
		repository.save(info);
	}
	public List<OrderInfo> findById(String orderId) throws FindException{
//		System.out.println("in ordeservice dao=" + dao);
//		return dao.findById(orderId);
		Customer c = new Customer();
		c.setId(orderId);
		return repository.findByOrderCustomer(c);
	}
}
