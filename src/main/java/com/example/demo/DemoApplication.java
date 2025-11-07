package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {

		//SpringApplication.run(DemoApplication.class, args);
//		var orderService = new OrderService(new StripePaymentService());
		var orderService = new OrderService();
		orderService.setPaymentService(new StripePaymentService());
		orderService.placeOrder();
	}

}
