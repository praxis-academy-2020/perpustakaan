package com.project.perpustakaan.repo;
import com.project.perpustakaan.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepo extends JpaRepository<Member,Long>{
    
}
