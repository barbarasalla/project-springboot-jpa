package com.projectjavaweb.course.config;

import com.projectjavaweb.course.entities.Order;
import com.projectjavaweb.course.entities.User;
import com.projectjavaweb.course.entities.enums.OrderStatus;
import com.projectjavaweb.course.repository.OrderRepository;
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


    @Override
    public void run(String... args) throws Exception {

        User u1 = new User(null, "Maria", "maria@email.com", "988888888", "12345");
        User u2 = new User(null, "Joao", "joao@email.com", "988888888", "12345");

        Order o1 = new Order(null, Instant.parse("2023-10-18T21:55:07Z"), u1, OrderStatus.WAITING_PAYMENT);
        Order o2 = new Order(null, Instant.parse("2023-10-18T20:55:07Z"), u2, OrderStatus.WAITING_PAYMENT);

        userRepository.saveAll(Arrays.asList(u1, u2));
        orderRepository.saveAll(Arrays.asList(o1, o2));

    }
}
