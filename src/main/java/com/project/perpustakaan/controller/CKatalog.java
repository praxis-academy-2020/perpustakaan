package com.project.perpustakaan.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import com.project.perpustakaan.model.Katalog;
import com.project.perpustakaan.repository.KatalogRepo;
import com.project.perpustakaan.repository.UserRepository;
import com.project.perpustakaan.service.FilesStorageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(path = "/katalog")
// @PreAuthorize("hasRole(\"ROLE_ADMIN\")")
public class CKatalog {
  @Autowired
  FilesStorageService storageService;

  @Autowired
  private KatalogRepo katalogRepo;

  @Autowired
  UserRepository userRepository;

  DateFormat formatTanggal = new SimpleDateFormat("_HH-mm-ss-SSSS_dd-MM-yyyy");
  Calendar kalender = Calendar.getInstance();

  // menampilkan semua
  @GetMapping(path = "/")
  public List<Katalog> get_all() {
    return katalogRepo.findAll();
  }

  // get by Id
  @GetMapping(path = "/{id}")
  public Katalog idkatalog(@PathVariable Long id) {
    return katalogRepo.findById(id).get();
  }

  // post
  @RequestMapping(path = "/", method = RequestMethod.POST, consumes = { "multipart/form-data" })
  public Katalog addKatalog(@RequestPart("katalog") Katalog katalog, @RequestPart("file") MultipartFile file) {
    // menambahkab gambar pada katalog
    Calendar kalender = Calendar.getInstance();
    String rename = "katalog_".concat(katalog.getJudul()).concat(formatTanggal.format(kalender.getTime()));
    String newNameFoto = storageService.save(file, rename);
    String url = "http://localhost:8081/files/".concat(newNameFoto);

    System.out.println("ini adalah urlnya" + url);
    katalog.setFoto(url);
    System.out.println("inin adlaha id katalgnya" + katalog.getId());
    return katalogRepo.save(katalog);
  }

  // update
  @PutMapping("/{id}")
  @RequestMapping(path = "/{id}", method = RequestMethod.PUT, consumes = { "multipart/form-data" })
  Katalog updatekatalog(@RequestPart("file") MultipartFile file, @RequestPart("katalog") Katalog newKatalog,
      @PathVariable Long id) {

    return katalogRepo.findById(id).map(katalog -> {
      katalog.setJudul(newKatalog.getJudul());
      katalog.setAuthor(newKatalog.getAuthor());
      katalog.setTahun(newKatalog.getTahun());
      katalog.setSinopsis(newKatalog.getSinopsis());
      katalog.setJumlah(newKatalog.getJumlah());
      // manipulasi filenya
      String LinkNameFoto = katalog.getFoto();
      String[] deleteFiles = LinkNameFoto.split("/");
      String deleteFile = deleteFiles[deleteFiles.length - 1];
      Calendar kalender = Calendar.getInstance();
      String rename = "katalog_".concat(newKatalog.getJudul()).concat(formatTanggal.format(kalender.getTime()));
      System.out.println("ini nama dari file baru = "+rename);
      try {
        LinkNameFoto = storageService.save(file, rename);
        String url = "http://localhost:8081/files/".concat(LinkNameFoto);
        katalog.setFoto(url);
        if (deleteFile != "ImageKatalog.jpg") {
          storageService.delete(deleteFile);
        }
      } catch (Exception e) {
        System.out.println(e);
      }

      return katalogRepo.save(katalog);
    }).orElseGet(() -> {
      return katalogRepo.save(newKatalog);
    });

  }

  // delete
  @DeleteMapping(path = "/{id}")
  public void deleteKatalog(@PathVariable Long id) {
    try {
      Katalog katalog = katalogRepo.findById(id).get();
      String deleteLink = katalog.getFoto();
      // memecah string untuk mendapatkan nama foto
      String[] deleteFiles = deleteLink.split("/");
      String deleteFile = deleteFiles[deleteFiles.length - 1];
      if (deleteFile != "ImageKatalog.jpg") {
        storageService.delete(deleteFile);
      }
      // delete row database
      katalogRepo.deleteById(id);
    } catch (Exception e) {
      System.out.println("funsi tidak dapat dijalankan");
      // TODO: handle exception
    }
  }
}
