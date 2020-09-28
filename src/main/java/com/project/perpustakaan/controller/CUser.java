package com.project.perpustakaan.controller;

import javax.servlet.http.HttpServletRequest;

import com.project.perpustakaan.controller.service.SPeminjaman;
import com.project.perpustakaan.controller.service.SUser;
import com.project.perpustakaan.controller.service.Service;
import com.project.perpustakaan.model.Peminjaman;
import com.project.perpustakaan.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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

    // update USERNYA
    @PutMapping("/")
    User updateUser(@RequestBody User newUser, HttpServletRequest request) {
      long id = service.getUserIdByToken(request);
      return sUser.updateUser(newUser,id);
    }

    //post peminjamanNYA
    @PostMapping(path="/")
    public Peminjaman addPeminjaman(@RequestBody Peminjaman peminjaman, HttpServletRequest request){
      long id = service.getUserIdByToken(request);
      return sPeminjaman.addPeminjaman(peminjaman.getIdKatalog(),id);
    }

    //get Besar tagihan by id User
      
}
