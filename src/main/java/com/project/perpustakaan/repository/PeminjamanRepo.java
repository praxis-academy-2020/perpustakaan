package com.project.perpustakaan.repository;

import java.util.List;
import java.util.Optional;

import com.project.perpustakaan.model.Peminjaman;
import com.project.perpustakaan.model.User;

import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PeminjamanRepo extends JpaRepository<Peminjaman, Long> {

    // @Query("SELECT a FROM peminjaman WHERE peminjaman.idUesr = ?1")
     List<Peminjaman> findByIduser(Long iduser);

}
