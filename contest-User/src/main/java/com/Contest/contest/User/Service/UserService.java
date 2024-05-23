package com.Contest.contest.User.Service;

import com.Contest.contest.User.Entity.User;
import com.Contest.contest.User.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.TreeSet;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .sorted(Comparator.comparingInt(User::getScore).reversed())
                .collect(Collectors.toList());
    }

    public Optional<User> getUserById(String userId) {
        return userRepository.findById(userId);
    }

    public User createUser(User user) {
        user.setScore(0);
        user.setBadges(new TreeSet<>());
        return userRepository.save(user);
    }

    public User updateUserScore(String userId, int score) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            user.setScore(score);
            updateBadges(user);
            return userRepository.save(user);
        } else {
            throw new IllegalArgumentException("User not found");
        }
    }

    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }

    private void updateBadges(User user) {
        user.getBadges().clear();
        if (user.getScore() >= 1 && user.getScore() < 30) {
            user.getBadges().add("Code Ninja");
        } else if (user.getScore() >= 30 && user.getScore() < 60) {
            user.getBadges().add("Code Champ");
        } else if (user.getScore() >= 60 && user.getScore() <= 100) {
            user.getBadges().add("Code Master");
        }
    }
}
