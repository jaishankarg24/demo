package com.example.demo;

import com.example.demo.entities.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {


		//ApplicationContext context = SpringApplication.run(DemoApplication.class, args);

		//3.
		User user = User.builder()
						.name("Jai")
						.email("jai@gmail.com")
						.password("pwd")
						.build();
		//2.
		//User user = new User(1L, "jai", "jai@gmail", "pwd");

		//1.
		//user.setName("jai");
		//user.setEmail("email");
		//user.setPassword("pwd");
	}

}
