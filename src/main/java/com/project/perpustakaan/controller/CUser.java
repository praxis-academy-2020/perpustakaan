package com.project.perpustakaan.controller;

import java.util.List;
import java.util.Optional;

//import com.project.perpustakaan.model.Member;
import com.project.perpustakaan.model.User;
//import com.project.perpustakaan.repository.MemberRepo;
import com.project.perpustakaan.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/member")
public class CUser {
    @Autowired
    private UserRepository userRepository;
    
    //menampilkan semua
    @GetMapping(path = "/")
    public List<User> get_all(){
        return userRepository.findAll();
    }

    //get by Id
    @GetMapping(path= "/{id}")
    public Optional<User> idUser(@PathVariable Long id){
        return userRepository.findById(id);
    }

    //post
    @PostMapping(path="/")
    public User addUser(@RequestBody User user){
        return userRepository.save(user);
    }

    //update
    @PutMapping("/{id}")
    User updatUser(@RequestBody User newUser, @PathVariable Long id) {
      
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
    
    //delete
    @DeleteMapping(path= "/{id}")
    public void deleteMember(@PathVariable Long id){
        userRepository.deleteById(id);
    }     
}
