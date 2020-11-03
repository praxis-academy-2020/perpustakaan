package com.project.perpustakaan.controller;

import java.util.List;
import java.util.Optional;

import com.project.perpustakaan.controller.service.SUser;
import com.project.perpustakaan.model.User;
import com.project.perpustakaan.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(path = "/admin")
// @PreAuthorize("hasRole(\"ROLE_ADMIN\")")
public class CAdmin {
    @Autowired
    UserRepository userRepository;
    @Autowired
    SUser sUser;
    //pengolahan semua  user
    
    // menampilkan semua user
    // akses admin
    @GetMapping(path = "/")
    public List<User> get_all(){
        return userRepository.findAll();
    }
    
    // get by Id user
    // akses admin
    @GetMapping(path= "/{id}")
    public Optional<User> idUser(@PathVariable Long id){
        return userRepository.findById(id);
    }

    // update
    // dilakukan admin dari sebuah id
    @PutMapping("/{id}")
    public User updateUserAndRole(@RequestBody User newUser, long id) {
       try {
           
           sUser.updateUser(newUser,id);
           return userRepository.findById(id)
           .map(user->{
           user.setRoles(newUser.getRoles());
           return userRepository.save(user);
           })
       .orElseGet(()->{
           return userRepository.save(newUser);
       });
       } catch (Exception e) {
        System.out.println("fungsi tidak dapt dijalankan");
        e.printStackTrace();
        return null; 
       }
        
    }

    // delete user By Id
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping(path= "/{id}")
    public void deleteUser(@PathVariable Long id){
        try {
            
            userRepository.deleteById(id);
        } catch (Exception e) {
            //TODO: handle exception
            e.printStackTrace();
            System.out.println("program tidak bisa dieksekusi");
            
        }
    }

    // add user dan admin tergantung role yang dipilih
    // akses admin
    @PostMapping(path="/")
    public User addUser(@RequestBody User user){
        try {
            
            return userRepository.save(user);
        } catch (Exception e) {
            System.out.println("fungsi gagal dijalankan");
            e.printStackTrace();
            return null;
            //TODO: handle exception
        }
    }

    //akhir line pengolahan semua user


}
