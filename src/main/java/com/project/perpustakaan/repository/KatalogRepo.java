package com.project.perpustakaan.repository;
import com.project.perpustakaan.model.Katalog;
import org.springframework.data.jpa.repository.JpaRepository;


public interface KatalogRepo extends JpaRepository<Katalog,Long>{
    
}
