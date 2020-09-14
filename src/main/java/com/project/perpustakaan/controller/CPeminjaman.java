package com.project.perpustakaan.controller;

import java.util.List;
import java.util.Optional;

import com.project.perpustakaan.model.Peminjaman;
import com.project.perpustakaan.repo.PeminjamanRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/peminjaman")
public class CPeminjaman {

    @Autowired
    private PeminjamanRepo peminjamanRepo;
    
    @GetMapping(path = "/")//menampilkan semua
    public List<Peminjaman> get_all(){
        return peminjamanRepo.findAll();
    }

    @GetMapping(path= "/{id}")
    public Optional<Peminjaman> idPeminjaman(@PathVariable Long id){
        return peminjamanRepo.findById(id);
    }

    


    @PostMapping(path="/post")
    public Peminjaman addPeminjaman(@RequestBody Peminjaman peminjaman){
        return peminjamanRepo.save(peminjaman);
    }

    


    
}