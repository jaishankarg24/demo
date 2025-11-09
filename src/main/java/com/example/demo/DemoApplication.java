package com.example.demo;

import com.example.demo.entities.Address;
import com.example.demo.entities.Profile;
import com.example.demo.entities.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {


		ApplicationContext context = SpringApplication.run(DemoApplication.class, args);

		/*
		User user = User.builder()
						.name("Jai")
						.email("jai@gmail.com")
						.password("pwd")
						.build();

		var profile = Profile.builder()
				.bio("bio")
				.build();

		user.setProfile(profile);
		profile.setUser(user);*/

		/*var address = Address.builder()
				.street("street")
				.city("city")
				.state("state")
				.zip("zip")
				.build();

		user.addAddress(address);*/

		//user.addTag("tag1");
		//System.out.println(user);

	}

}
