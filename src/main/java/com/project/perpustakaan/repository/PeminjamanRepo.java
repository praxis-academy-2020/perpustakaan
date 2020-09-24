package com.project.perpustakaan.repository;
import com.project.perpustakaan.model.Peminjaman;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PeminjamanRepo extends JpaRepository<Peminjaman,Long>{
    
}
