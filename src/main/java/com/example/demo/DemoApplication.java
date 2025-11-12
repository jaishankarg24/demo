package com.example.demo;

import com.example.demo.entities.Address;
import com.example.demo.entities.Profile;
import com.example.demo.entities.User;
import com.example.demo.repositories.AddressRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.AddressService;
import com.example.demo.services.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

import java.math.BigDecimal;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {


		ApplicationContext context = SpringApplication.run(DemoApplication.class, args);
		//AddressService addressService = context.getBean(AddressService.class);
		//addressService.showAddressById(1L);

		var service =  context.getBean(UserService.class);
		//service.showEntityStates();
		//service.showRelatedEntities();
		//service.fetchAddress();
		//service.persistRelated();
		//service.deleteRelated();
		//service.manageProducts();
		//service.updateProductPrices();
		//service.fetchProducts();
		//service.fetchUser();
		//service.fetchUsers();
		//service.fetchProductsProcedure();
		//service.printLoyalProfiles();
		//service.fetchProducts();
		//service.fetchProductsByCriteria();
		//service.fetchProductsBySpecifications("prod", BigDecimal.valueOf(1), null);
		//service.fetchSortedProducts();
		service.fetchPaginatedProducts(0, 10);

		//UserRepository repository = context.getBean(UserRepository.class);

		/*User user = User.builder()
						.name("Jai")
						.email("jai@gmail.com")
						.password("pwd")
						.build();

		repository.save(user);*/

		//User user1 = repository.findById(1L).orElseThrow();
		//System.out.println(user1.getName());

		//repository.findAll().forEach(user -> System.out.println(user.getEmail()));

		//repository.deleteById(1L);
		/*
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
