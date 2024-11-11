package com.movie.Movie_BE.Service;

import com.movie.Movie_BE.Model.User;
import com.movie.Movie_BE.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user) {
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            throw new DuplicateKeyException("Email đã tồn tại: " + user.getEmail());
        }

        return userRepository.save(user);
    }
}
