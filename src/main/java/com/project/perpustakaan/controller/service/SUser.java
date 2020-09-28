package com.project.perpustakaan.controller.service;

import com.project.perpustakaan.model.User;
import com.project.perpustakaan.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class SUser {
    @Autowired
    UserRepository userRepository;


    //melakukan update user
    public User updateUser(User newUser,Long id) {
      
        return userRepository.findById(id)
        .map(user -> {
          user.setUsername(newUser.getUsername());
          user.setPassword(newUser.getPassword());
          user.setEmail(newUser.getEmail());
          user.setNoHp(newUser.getNoHp());
          return userRepository.save(user);  
        })
        .orElseGet(() -> {
          return userRepository.save(newUser);
        });
      }
}
