package com.project.perpustakaan.controller;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import com.project.perpustakaan.model.Katalog;
import com.project.perpustakaan.model.Peminjaman;
import com.project.perpustakaan.repo.KatalogRepo;
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
    @Autowired
    private KatalogRepo katalogRepo;
    
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
    // yang di edti adalah id member, id katalog dan dan tanggal pinjam
    @PostMapping(path="/")
    public Peminjaman addPeminjaman(@RequestBody Peminjaman peminjaman){
        Katalog katalog = katalogRepo.findById(peminjaman.getIdKatalog()).get();
        int jumlah = katalog.getJumlah();
        if(jumlah>=1){
          katalog.setJumlah(--jumlah);
          return peminjamanRepo.save(peminjaman);
        }
        return null;
    }

    //update
    @PutMapping("/{id}")
    Peminjaman updatepeminjaman(@RequestBody Peminjaman newPeminjaman, @PathVariable Long id) {
      
      return peminjamanRepo.findById(id)
      .map(peminjaman -> {
       peminjaman.setTglPinjam(newPeminjaman.getTglPinjam());
       peminjaman.setIdKatalog(newPeminjaman.getIdKatalog());
       peminjaman.setIdMember(newPeminjaman.getIdMember());
       peminjaman.setStatus(newPeminjaman.getStatus());
        return peminjamanRepo.save(peminjaman);

      })
      .orElseGet(() -> {
        return peminjamanRepo.save(newPeminjaman);
      });
    }
    
    //delete. hanya dapat di hapus jika statusnya false
    @DeleteMapping(path= "/{id}")
    public void deletePeminjaman(@PathVariable Long id){
      Peminjaman peminjaman = peminjamanRepo.findById(id).get();
      if (!peminjaman.getStatus()) {
        peminjamanRepo.deleteById(id);  
      }
    } 
    
    //mengubah status peminjaman dan menghitung denda peminjaman
    @PutMapping("/pengembalian/{id}")
    Peminjaman updateTagihan(@RequestBody Peminjaman newPeminjaman, @PathVariable Long id) {
      
      Peminjaman peminjaman = peminjamanRepo.findById(id).get();
        try {
          if(peminjaman.getStatus()){
            Katalog katalog = katalogRepo.findById(peminjaman.getIdKatalog()).get();  
            peminjaman.setTglKembali(newPeminjaman.getTglKembali());
            peminjaman.setTagihan(2000);//perlu untuk menghitung tanggal
            peminjaman.setStatus(false);
            int jumlah = katalog.getJumlah();
            katalog.setJumlah(++jumlah);
          }
          return peminjamanRepo.save(peminjaman);
        } catch (Exception e) {
          System.out.println(e.getMessage());
          return peminjamanRepo.save(newPeminjaman);
        }

      
    }

    
}
