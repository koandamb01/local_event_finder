package com.local.events.app.services;

import com.local.events.app.models.User;
import com.local.events.app.repositories.UserRepository;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepo;

    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    // Register a user
    public User registerUser(User user){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashed = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashed);
        return this.userRepo.save(user);
    }

    // Authenticate a user when login
    public boolean authenticateUser(String email, String password){
        User user = this.userRepo.findByEmail(email);
        if(user == null){
            return false;
        }
        else{
            if(BCrypt.checkpw( password, user.getPassword())){
                return true;
            }
            else{
                return false;
            }
        }
    }

    // find all users
    public List<User> findAllUsers(){ return this.userRepo.findAll(); }

    // find user by email
    public User findUserByEmail(String email){ return this.userRepo.findByEmail(email); }

    // find user by id
    public User findUserById(Long id){
        Optional<User> res = this.userRepo.findById(id);
        if(res.isPresent()){
            return res.get();
        }
        else{
            return null;
        }
    }
}
