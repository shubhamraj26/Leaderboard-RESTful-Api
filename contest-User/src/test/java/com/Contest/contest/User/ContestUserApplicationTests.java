package com.Contest.contest.User;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.Contest.contest.User.Entity.User;
import com.Contest.contest.User.Repository.UserRepository;
import com.Contest.contest.User.Service.UserService;

@SpringBootTest
class LeaderboardApplicationTests {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Test
    void contextLoads() {
    }

    @Test
    void testCreateUser() {
        User user = new User();
        user.setUserId("1");
        user.setUsername("TestUser");

        User createdUser = userService.createUser(user);
        assertEquals("TestUser", createdUser.getUsername());
        assertEquals(0, createdUser.getScore());
    }

    @Test
    void testUpdateUserScore() {
        User user = new User();
        user.setUserId("2");
        user.setUsername("TestUser2");
        userService.createUser(user);

        User updatedUser = userService.updateUserScore("2", 50);
        assertEquals(50, updatedUser.getScore());
        assertTrue(updatedUser.getBadges().contains("Code Champ"));
    }

    @Test
    void testDeleteUser() {
        User user = new User();
        user.setUserId("3");
        user.setUsername("TestUser3");
        userService.createUser(user);

        userService.deleteUser("3");
        Optional<User> deletedUser = userRepository.findById("3");
        assertTrue(deletedUser.isEmpty());
    }
}
