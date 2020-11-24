package com.project.perpustakaan.controller;


import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import com.project.perpustakaan.controller.service.SPeminjaman;
import com.project.perpustakaan.controller.service.Service;
import com.project.perpustakaan.model.Katalog;
import com.project.perpustakaan.model.Peminjaman;
import com.project.perpustakaan.repository.KatalogRepo;
import com.project.perpustakaan.repository.PeminjamanRepo;

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

import javassist.bytecode.stackmap.BasicBlock.Catch;

@RestController
@RequestMapping(path = "/peminjaman")
// @PreAuthorize("hasRole(\"ROLE_ADMIN\")")
public class CPeminjaman {

    @Autowired
    private PeminjamanRepo peminjamanRepo;
    @Autowired
    private Service service;
    @Autowired
    private KatalogRepo katalogRepo;
    @Autowired
    private SPeminjaman sPeminjaman;
    
    @GetMapping(path = "/")
    public List<Peminjaman> get_all(){
        return peminjamanRepo.findAll();
    }

    //get by Id
    @GetMapping(path= "/{id}")
    public Optional<Peminjaman> idPeminjaman(@PathVariable Long id){
        return peminjamanRepo.findById(id);
    }

    //post peminjaman
    @PostMapping(path="/")
    public Peminjaman addPeminjaman(@RequestBody Peminjaman peminjaman){
      try {
        return sPeminjaman.addPeminjaman(peminjaman.getIdKatalog(),peminjaman.getIdUser());
      } catch (Exception e) {
        System.out.println("fungsi tidak dapat dijalankan");
        return null;
        //TODO: handle exception
      }
    }

    //update
    @PutMapping("/{id}")
    Peminjaman updatepeminjaman(@RequestBody Peminjaman newPeminjaman, @PathVariable Long id) {
      try {
        return peminjamanRepo.findById(id)
        .map(peminjaman -> {
         peminjaman.setTglPinjam(newPeminjaman.getTglPinjam());
         peminjaman.setIdKatalog(newPeminjaman.getIdKatalog());
         peminjaman.setIdUser(newPeminjaman.getIdUser());
         peminjaman.setStatus(newPeminjaman.getStatus());
          return peminjamanRepo.save(peminjaman);
        })
        .orElseGet(() -> {
          return peminjamanRepo.save(newPeminjaman);
        });
        
      } catch (Exception e) {
        System.out.println("fungsi tidak dapat dijalankan");
        e.getStackTrace();
        return null;
        //TODO: handle exception
      }
    }
    
    //delete. hanya dapat di hapus jika statusnya false
    @DeleteMapping(path= "/{id}")
    public void deletePeminjaman(@PathVariable Long id){
      try {
        
        Peminjaman peminjaman = peminjamanRepo.findById(id).get();
        if (!peminjaman.getStatus()) {
          peminjamanRepo.deleteById(id);  
        }
      } catch (Exception e) {
        System.out.println("fungsi tidak dapat dijalankakn");
        e.printStackTrace();;
        //TODO: handle exception
      }
    } 
    
    //mengubah status peminjaman dan menghitung denda peminjaman
    @PutMapping("/pengembalian/{id}")
    Peminjaman updateTagihan(@RequestBody Peminjaman newPeminjaman, @PathVariable Long id) {
      
      Peminjaman peminjaman = peminjamanRepo.findById(id).get();
      
        try {
          if(peminjaman.getStatus()){
            Katalog katalog = katalogRepo.findById(peminjaman.getIdKatalog()).get();
            peminjaman.setStatus(false);
            int jumlah = katalog.getJumlah();
            katalog.setJumlah(++jumlah);
            //memasukkan tanggal kembali
            Date tgl = peminjaman.getTglPinjam();
            peminjaman.setTagihan(service.hitungTagihanByTgl(tgl));//perlu untuk menghitung tanggal
            peminjaman.setTglKembali(Calendar.getInstance().getTime());
          }
          return peminjamanRepo.save(peminjaman);
        } catch (Exception e) {
          System.out.println(e.getMessage());
          peminjamanRepo.save(newPeminjaman);
          return null; 
        }
   
    }

    @GetMapping(path = "/tagihan/{id}")
    public float Tagihan(@PathVariable Long id){
      Peminjaman peminjaman = peminjamanRepo.findById(id).get();
          Date tgl = peminjaman.getTglPinjam();
          return service.hitungTagihanByTgl(tgl);
    }

    
}
