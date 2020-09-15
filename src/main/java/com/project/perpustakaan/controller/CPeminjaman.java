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
    // @PutMapping("/{id}")
    // Peminjaman updatepeminjaman(@RequestBody Peminjaman newUser, @PathVariable Long id) {
      
    //   return peminjamanRepository.findById(id)
    //   .map(katalog -> {
    //    peminjaman.setTgl_pinjam(newUser.getTgl_pinjam());
    //    peminjaman.setId_katalog(newUser.getId_katalog());
    //    peminjaman.setId_member(newUser.getId_member());
    //     return peminjamanRepository.save(peminjaman);

    //   })
    //   .orElseGet(() -> {
    //      newUser.setId(id);
    //     return peminjamanRepository.save(newUser);
    //   });
    // }
    
    //delete
    @DeleteMapping(path= "/{id}")
    public void deletePeminjaman(@PathVariable Long id){
        peminjamanRepo.deleteById(id);
    }    
}
