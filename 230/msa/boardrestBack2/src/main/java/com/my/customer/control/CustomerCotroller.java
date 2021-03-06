package com.my.customer.control;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.my.customer.vo.Customer;

@RestController
public class CustomerCotroller {
	@RequestMapping("/login")
	public void login(String id, String pwd, HttpSession session) {
		//비지니스로직호출 성공된 가정
		Customer c = new Customer();
		c.setId(id);
		c.setPwd(pwd);
		session.setAttribute("loginInfo", c);
	}
	@RequestMapping("/logout")
	public void logout(HttpSession session) {
		session.removeAttribute("loginInfo");
		session.invalidate();
	}
	@GetMapping("/checkLogin")
	public Customer checkLogin(HttpSession session) {
		Customer c = (Customer)session.getAttribute("loginInfo");
		if(c != null) {
			return c;
		}else {
			return null;
		}
	}
}
