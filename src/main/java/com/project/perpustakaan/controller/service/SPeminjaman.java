package com.project.perpustakaan.controller.service;

import java.util.Calendar;
import java.util.TimeZone;

import com.project.perpustakaan.model.Katalog;
import com.project.perpustakaan.model.Peminjaman;
import com.project.perpustakaan.repository.KatalogRepo;
import com.project.perpustakaan.repository.PeminjamanRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class SPeminjaman {
    @Autowired
    private PeminjamanRepo peminjamanRepo;
   
    @Autowired
    private KatalogRepo katalogRepo;


    public Peminjaman addPeminjaman(long id_katalog,long id_user){
        Katalog katalog = katalogRepo.findById(id_katalog).get();  
        Peminjaman peminjaman = new Peminjaman();
        int jumlah = katalog.getJumlah();
        if(jumlah>=1){
          katalog.setJumlah(--jumlah);
          peminjaman.setTglPinjam(Calendar.getInstance(TimeZone.getTimeZone("Asia/Jakarta")).getTime());
          peminjaman.setIdKatalog(id_katalog);
          peminjaman.setIdUser(id_user);
          //status default true di modelnya
          return peminjamanRepo.save(peminjaman);
        }
        return null;
    } 
}
