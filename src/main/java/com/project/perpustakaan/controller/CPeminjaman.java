package com.project.perpustakaan.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.TimeZone;

import com.project.perpustakaan.model.Katalog;
import com.project.perpustakaan.model.Peminjaman;
import com.project.perpustakaan.repository.KatalogRepo;
import com.project.perpustakaan.repository.PeminjamanRepo;

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
    // yang di edti adalah id User, id katalog dan dan tanggal pinjam
    @PostMapping(path="/")
    public Peminjaman addPeminjaman(@RequestBody Peminjaman peminjaman){
        Katalog katalog = katalogRepo.findById(peminjaman.getIdKatalog()).get();
        peminjaman.setTglPinjam(Calendar.getInstance(TimeZone.getTimeZone("Asia/Jakarta")).getTime());
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
       peminjaman.setIdUser(newPeminjaman.getIdUser());
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
            peminjaman.setStatus(false);
            int jumlah = katalog.getJumlah();
            katalog.setJumlah(++jumlah);
            //memasukkan tanggal kembali
              peminjaman.setTglKembali(Calendar.getInstance().getTime());

            //menghitung durasi peminjaman
            
            long denda = 5000;
            Date d1 = peminjaman.getTglPinjam();;
            Date d2 = Calendar.getInstance(TimeZone.getTimeZone("Asia/Jakarta")).getTime();  
            long diff = d2.getTime()-d1.getTime();
            long diffDays = diff / (24 * 60 * 60 * 1000);
            System.out.println("HARIIII = "+diffDays);
            // if(diffDays>7){
            //   peminjaman.setTagihan((diffDays-7)* denda);
            // }else peminjaman.setTagihan(0);//perlu untuk menghitung tanggal
            
          }
          return peminjamanRepo.save(peminjaman);
        } catch (Exception e) {
          System.out.println(e.getMessage());
          return peminjamanRepo.save(newPeminjaman);
        }


        
    }

    
}
