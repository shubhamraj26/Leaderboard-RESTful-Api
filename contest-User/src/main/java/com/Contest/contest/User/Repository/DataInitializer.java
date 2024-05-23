package com.Contest.contest.User.Repository;

import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.Contest.contest.User.Entity.User;

@Configuration
public class DataInitializer {

    @Autowired
    private UserRepository userRepository;

    @Bean
    CommandLineRunner initData() {
        return args -> {
            userRepository.deleteAll(); // Clear existing data

            User user1 = new User();
            user1.setUserId("1");
            user1.setUsername("Alice");
            user1.setScore(25);
            user1.setBadges(new TreeSet<>(Set.of("Code Ninja")));

            User user2 = new User();
            user2.setUserId("2");
            user2.setUsername("Bob");
            user2.setScore(45);
            user2.setBadges(new TreeSet<>(Set.of("Code Champ")));

            User user3 = new User();
            user3.setUserId("3");
            user3.setUsername("Charlie");
            user3.setScore(75);
            user3.setBadges(new TreeSet<>(Set.of("Code Master")));

            userRepository.save(user1);
            userRepository.save(user2);
            userRepository.save(user3);
        };
    }
}