package com.project.perpustakaan.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import com.project.perpustakaan.controller.service.SPeminjaman;
import com.project.perpustakaan.controller.service.SUser;
import com.project.perpustakaan.controller.service.Service;
import com.project.perpustakaan.model.Peminjaman;
import com.project.perpustakaan.model.User;
import com.project.perpustakaan.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/user")
@PreAuthorize("hasRole(\"ROLE_USER\")")
public class CUser {

    @Autowired
    private SUser sUser;
    @Autowired
    private Service service;
    @Autowired
    private SPeminjaman sPeminjaman;
    @Autowired
    UserRepository userRepository;

    // update USERNYA
    @PutMapping("/")
    User updateUser(@RequestBody User newUser, HttpServletRequest request) {
      try {
        
        long id = service.getUserIdByToken(request);
        return sUser.updateUser(newUser,id);
      } catch (Exception e) {
        System.out.println("fungsi gagal dijalankan");
        e.printStackTrace();
        return null;
        //TODO: handle exception
      }
    }

    //post peminjamanNYA
    @PostMapping(path="/")
    public Peminjaman addPeminjaman(@RequestBody Peminjaman peminjaman, HttpServletRequest request){
      try {
        
        long id = service.getUserIdByToken(request);
        return sPeminjaman.addPeminjaman(peminjaman.getIdKatalog(),id);
      } catch (Exception e) {
        System.out.println("fungsi tidak dapat dijalankan");
        e.printStackTrace();
        return null;
        //TODO: handle exception
      }
    }

    //get data user
    @GetMapping(path= "/")
    public Optional<User> idUser(HttpServletRequest request){
      try {
        long id = service.getUserIdByToken(request);
        return userRepository.findById(id);
        
      } catch (Exception e) {
        System.out.println("fungsi gagal dijalankan");
        e.printStackTrace();
        return null;
        //TODO: handle exception
      }
    }

    //get Besar tagihan by id User
      
}
