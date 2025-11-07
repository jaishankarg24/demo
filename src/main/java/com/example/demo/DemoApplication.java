package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(DemoApplication.class, args);
		var orderService = context.getBean(OrderService.class);
		orderService.placeOrder();

		var notificationManager = context.getBean(NotificationManager.class);
		notificationManager.sendNotification("Hello, this is a test message!");

		HeavyResource heavyResource = context.getBean(HeavyResource.class);

		var orderService2 = context.getBean(OrderService.class);

	}

}
