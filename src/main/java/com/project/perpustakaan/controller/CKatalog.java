package com.project.perpustakaan.controller;

import java.util.List;
import java.util.Optional;

import com.project.perpustakaan.model.Katalog;
import com.project.perpustakaan.repo.KatalogRepo;
import org.springframework.beans.factory.annotation.Autowired;
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
    
    //menampilkan semua
    @GetMapping(path = "/get")
    public List<Katalog> get_all(){
        return katalogRepo.findAll();
    }

    //get by Id
    @GetMapping(path= "/get/{id}")
    public Katalog idkatalog(@PathVariable Long id){
        return katalogRepo.findById(id).get();
    }

    //post
    @PostMapping(path="/post")
    public Katalog addKatalog(@RequestBody Katalog katalog){
        return katalogRepo.save(katalog);
    }

    //update
    @PutMapping("/edit/{id}")
    Katalog updatekatalog(@RequestBody Katalog newUser, @PathVariable Long id) {
      
      return katalogRepo.findById(id)
      .map(katalog -> {
        katalog.setJudul(newUser.getJudul());
        katalog.setAuthor(newUser.getAuthor());
        katalog.setTahun(newUser.getTahun());
        katalog.setSinopsis(newUser.getSinopsis());
        return katalogRepo.save(katalog);

      })
      .orElseGet(() -> {
        //  newUser.setId(id);
        return katalogRepo.save(newUser);
      });
    }
    
    //delete
    @DeleteMapping(path= "/delete/{id}")
    public void deleteKatalog(@PathVariable Long id){
        katalogRepo.deleteById(id);
    }    
}
