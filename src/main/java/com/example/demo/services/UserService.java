package com.example.demo.services;

import com.example.demo.entities.Address;
import com.example.demo.entities.Category;
import com.example.demo.entities.Product;
import com.example.demo.entities.User;
import com.example.demo.repositories.*;
import com.example.demo.repositories.specifications.ProductSpec;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Set;

@AllArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final EntityManager entityManager;
    private final ProfileRepository profileRepository;
    private final AddressRepository addressRepository;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    // AllArgsConstructor - to avoid explicit constructor dependency injection

    @Transactional
    public void showEntityStates() {
        var user = User.builder()
                .name("John Doe")
                .email("john.doe@example.com")
                .password("password")
                .build();

        if (entityManager.contains(user))
            System.out.println("Persistent");
        else
            System.out.println("Transient / Detached");

        userRepository.save(user);

        if (entityManager.contains(user))
            System.out.println("Persistent");
        else
            System.out.println("Transient / Detached");

    }

    @Transactional
    public void showRelatedEntities() {
        var profile = profileRepository.findById(2L).orElseThrow();
        System.out.println(profile.getUser().getEmail());
    }

    public void fetchAddress() {
        var address = addressRepository.findById(1L).orElseThrow();
    }

    public void persistRelated() {
        var user = User.builder()
                .name("John Doe")
                .email("john.doe@example.com")
                .password("password")
                .build();

        var address = Address.builder()
                .street("street")
                .city("city")
                .state("state")
                .zip("zip")
                .build();

        user.addAddress(address);

        userRepository.save(user);
        //addressRepository.save(address);
    }

    @Transactional
    public void deleteRelated() {
        //Deleting Children data userRepository.deleteById(1L);

        //Deleting Parent from child entity
        var user = userRepository.findById(6L).orElseThrow();
        var address = user.getAddresses().getFirst();
        user.removeAddress(address);//address is deleted
        userRepository.save(user);
    }

    @Transactional
    public void manageProducts() {

        /* Step 1: Create a Product with a new Category
        Category category = new Category("Category 1");

        Product product = Product.builder().name("Product 1").description("Description 1").price(BigDecimal.valueOf(10.99)).category(category).build();

        productRepository.save(product);*/

        // Step 2: Create a Product for an Existing Category
        /*
        Category category = categoryRepository.findById((byte) 1).orElseThrow();

        Product product = Product.builder().name("Product 2").description("Description 2").price(BigDecimal.valueOf(10.99)).category(category).build();

        productRepository.save(product);*/

        // Step 3: Add Products to a User’s Wishlist
        /*User user = userRepository.findById(2L).orElseThrow();

        Iterable<Product> products = productRepository.findAll();

        products.forEach(user::addFavoriteProduct);
        userRepository.save(user);*/

        //Step 4: Deleting a Product
        productRepository.deleteById(2L);

    }

    @Transactional
    public void updateProductPrices() {
        productRepository.updatePriceByCategory(BigDecimal.valueOf(10), (byte)1);
    }

    /*
    public void fetchProducts() {
        var products = productRepository.findByCategory(new Category((byte)1));
        products.forEach(System.out::println);
    }*/


    @Transactional
    public void fetchProducts() {
        var product = new Product();
        product.setName("product");

        var matcher = ExampleMatcher.matching()
                .withIncludeNullValues()
                .withIgnorePaths("id", "description")
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        var example = Example.of(product, matcher);

        var products = productRepository.findAll(example);
        products.forEach(System.out::println);
    }

    public void fetchProductsByCriteria() {
        var products = productRepository.findProductsByCriteria("prod", BigDecimal.valueOf(1), null);
        products.forEach(System.out::println);
    }

    public void fetchProductsBySpecifications(String name, BigDecimal minPrice, BigDecimal maxPrice) {
        Specification<Product> spec = Specification.where(null);

        if (name != null) {
            spec = spec.and(ProductSpec.hasName(name));
        }
        if (minPrice != null) {
            spec = spec.and(ProductSpec.hasPriceGreaterThanOrEqualTo(minPrice));
        }
        if (maxPrice != null) {
            spec = spec.and(ProductSpec.hasPriceLessThanOrEqualTo(maxPrice));
        }

        productRepository.findAll(spec).forEach(System.out::println);
    }

    @Transactional
    public void fetchUser() {
        var user = userRepository.findByEmail("jai@gmail.com").orElseThrow();
        System.out.println(user);
    }

    @Transactional
    public void fetchUsers() {
        var users = userRepository.findAllWithTags();
        users.forEach(u -> {
            System.out.println(u);
            u.getAddresses().forEach(System.out::println);
        });
    }

    @Transactional
    public void fetchProductsProcedure() {
        var products = productRepository.findProducts(BigDecimal.valueOf(1), BigDecimal.valueOf(15));
        products.forEach(System.out::println);
    }

    @Transactional
    public void printLoyalProfiles() {
        //var profiles = profileRepository.findByLoyaltyPointsGreaterThan(2);
        //var profiles = profileRepository.findByLoyaltyPointsGreaterThanOrderByUserEmail(2);
        //profiles.forEach(p -> System.out.println(p.getId()+ ": " + p.getUser().getEmail()));

        var profiles = profileRepository.findLoyaltyProfiles(2);
        profiles.forEach(p -> System.out.println(p.getId()+ ": " + p.getEmail()));

        var users = userRepository.findLoyalUsers(2);
        users.forEach(p -> System.out.println(p.getId() + ": " + p.getEmail()));
    }
}
