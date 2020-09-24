package com.project.perpustakaan.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import com.project.perpustakaan.model.Katalog;
import com.project.perpustakaan.model.User;
import com.project.perpustakaan.repository.KatalogRepo;
import com.project.perpustakaan.repository.UserRepository;
import com.project.perpustakaan.security.JwtTokenProvider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/katalog")
public class CKatalog {
    @Autowired
    private KatalogRepo katalogRepo;
    @Autowired
    private JwtTokenProvider tokenProvider;
    @Autowired
    UserRepository userRepository;
    
    //menampilkan semua
    @GetMapping(path = "/")
    public List<Katalog> get_all(){
        return katalogRepo.findAll();
    }

    //get by Id
    @GetMapping(path= "/{id}")
    public Katalog idkatalog(@PathVariable Long id){
        return katalogRepo.findById(id).get();
    }

    //post
    @PostMapping(path="/")
    public Katalog addKatalog(@RequestBody Katalog katalog){
        return katalogRepo.save(katalog);
    }

    
    
    //membuat fungsi untuk mendapatkan user_role
    //jika rolenya berhasil maka akan diambil

    // update
    @PutMapping("/{id}")
    Katalog updatekatalog(@RequestBody Katalog newKatalog, @PathVariable Long id) {
      
      return katalogRepo.findById(id)
      .map(katalog -> {
        katalog.setJudul(newKatalog.getJudul());
        katalog.setAuthor(newKatalog.getAuthor());
        katalog.setTahun(newKatalog.getTahun());
        katalog.setSinopsis(newKatalog.getSinopsis());
        katalog.setJumlah(newKatalog.getJumlah());
        return katalogRepo.save(katalog);

      })
      .orElseGet(() -> {
        return katalogRepo.save(newKatalog);
      });
    }
    
    //delete
    @DeleteMapping(path= "/{id}")
    public void deleteKatalog(@PathVariable Long id){
        katalogRepo.deleteById(id);
    }    
}
