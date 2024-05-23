package com.Contest.contest.User.Entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;
import java.util.TreeSet;

@Data
@Document(collection = "users")
public class User {
    @Id
    private String userId;
    private String username;
    private int score = 0;
    private Set<String> badges = new TreeSet<>();
}
