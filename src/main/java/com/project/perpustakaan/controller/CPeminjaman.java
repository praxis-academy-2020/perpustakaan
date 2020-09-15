package com.project.perpustakaan.controller;

import java.util.List;
import java.util.Optional;

import com.project.perpustakaan.model.Peminjaman;
import com.project.perpustakaan.repo.PeminjamanRepo;
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
@RequestMapping(path = "/peminjaman")
public class CPeminjaman {

    @Autowired
    private PeminjamanRepo peminjamanRepo;
    
    //menampilkan semua
    @GetMapping(path = "/")
    public List<Peminjaman> get_all(){
        return peminjamanRepo.findAll();
    }

    //get by Id
    @GetMapping(path= "/{id}")
    public Optional<Peminjaman> idPeminjaman(@PathVariable Long id){
        return peminjamanRepo.findById(id);
    }

    //post
    @PostMapping(path="/")
    public Peminjaman addPeminjaman(@RequestBody Peminjaman peminjaman){
        return peminjamanRepo.save(peminjaman);
    }

    //update
    @PutMapping("/{id}")
    Peminjaman updatepeminjaman(@RequestBody Peminjaman newPeminjaman, @PathVariable Long id) {
      
      return peminjamanRepo.findById(id)
      .map(peminjaman -> {
       peminjaman.setTglPinjam(newPeminjaman.getTglPinjam());
       peminjaman.setIdKatalog(newPeminjaman.getIdKatalog());
       peminjaman.setIdMember(newPeminjaman.getIdMember());
        return peminjamanRepo.save(peminjaman);

      })
      .orElseGet(() -> {
        return peminjamanRepo.save(newPeminjaman);
      });
    }
    
    //delete
    @DeleteMapping(path= "/{id}")
    public void deletePeminjaman(@PathVariable Long id){
        peminjamanRepo.deleteById(id);
    }    
}
