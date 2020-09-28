package com.project.perpustakaan.controller;

import java.util.List;
import java.util.Optional;

import com.project.perpustakaan.controller.service.SUser;
import com.project.perpustakaan.model.User;
import com.project.perpustakaan.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/admin")
@PreAuthorize("hasRole(\"ROLE_ADMIN\")")
public class CAdmin {
    @Autowired
    UserRepository userRepository;
    @Autowired
    SUser sUser;
    //pengolahan semua  user
    
    // menampilkan semua user
    // akses admin
    @GetMapping(path = "/u/")
    public List<User> get_all(){
        return userRepository.findAll();
    }
    
    // get by Id user
    // akses admin
    @GetMapping(path= "/u/{id}")
    public Optional<User> idUser(@PathVariable Long id){
        return userRepository.findById(id);
    }

    // update
    // dilakukan admin dari sebuah id
    @PutMapping("/u/{id}")
    public User updateUserAndRole(@RequestBody User newUser, long id) {
        sUser.updateUser(newUser,id);
        return userRepository.findById(id)
        .map(user->{
        user.setRoles(newUser.getRoles());
        return userRepository.save(user);
        })
      .orElseGet(()->{
        return userRepository.save(newUser);
      });
        
    }

    // delete user By Id
    @DeleteMapping(path= "/u/{id}")
    public void deleteUser(@PathVariable Long id){
        userRepository.deleteById(id);
    }

    // add user dan admin tergantung role yang dipilih
    // akses admin
    @PostMapping(path="/u/")
    public User addUser(@RequestBody User user){
        return userRepository.save(user);
    }

    //akhir line pengolahan semua user

    //awal line pengolahan katalog
    
    //akhir line pengolahan katalog

    //awal line pegolahan peminjaman
    //akhit line pengolahan peminjaman

}
