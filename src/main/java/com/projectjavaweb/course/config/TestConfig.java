package com.projectjavaweb.course.config;

import com.projectjavaweb.course.entities.Category;
import com.projectjavaweb.course.entities.Order;
import com.projectjavaweb.course.entities.Product;
import com.projectjavaweb.course.entities.User;
import com.projectjavaweb.course.entities.enums.OrderStatus;
import com.projectjavaweb.course.repository.CategoryRepository;
import com.projectjavaweb.course.repository.OrderRepository;
import com.projectjavaweb.course.repository.ProductRepository;
import com.projectjavaweb.course.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.Instant;
import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void run(String... args) throws Exception {

        Category cat1 =new Category(null, "Electronics");
        Category cat2 =new Category(null, "Books");

        Product p1 = new Product(null, "The Lord of the Rings", "Lorem ipsum dolor sit Lorem ipsum", 200.00, "foto.jpg");
        Product p2 = new Product(null, "Smart TV", "Lorem ipsum dolor sit Lorem ipsum", 2000.00, "foto2.jpg");

        categoryRepository.saveAll(Arrays.asList(cat1, cat2));
        productRepository.saveAll(Arrays.asList(p1, p2));

        p1.getCategories().add(cat2);
        p2.getCategories().add(cat1);

        productRepository.saveAll(Arrays.asList(p1, p2));

        User u1 = new User(null, "Maria", "maria@email.com", "988888888", "12345");
        User u2 = new User(null, "Joao", "joao@email.com", "988888888", "12345");

        Order o1 = new Order(null, Instant.parse("2023-10-18T21:55:07Z"), u1, OrderStatus.WAITING_PAYMENT);
        Order o2 = new Order(null, Instant.parse("2023-10-18T20:55:07Z"), u2, OrderStatus.WAITING_PAYMENT);

        userRepository.saveAll(Arrays.asList(u1, u2));
        orderRepository.saveAll(Arrays.asList(o1, o2));

    }
}
