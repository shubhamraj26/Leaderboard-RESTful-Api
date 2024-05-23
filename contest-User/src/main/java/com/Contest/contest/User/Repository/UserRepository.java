package com.Contest.contest.User.Repository;

import com.Contest.contest.User.Entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}
