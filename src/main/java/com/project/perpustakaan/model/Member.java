package com.project.perpustakaan.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "member")
public class Member {


@Id 
@GeneratedValue(strategy=GenerationType.AUTO)
 private Long id;
 
 @Column(nullable = false)
 private String nama;

 @Column(nullable = false)
 private String email;

 @Column(nullable = false)
 private String noHp;

 @OneToMany(mappedBy = "member")
 private List<Peminjaman> peminjaman;

 //menambahkan seter dan getter

 public void setNama(String nama){
     this.nama = nama;
 }

 public String getNama(){
     return this.nama;
 }

 public void setEmail(String email){
    this.email = email;
}

public String getEmail(){
    return this.email;
}

public void setNoHp(String noHp){
    this.noHp = noHp;
}

public String getNoHp(){
    return this.noHp;
}

public Long getId(){
    return this.id;
}
public void setId(Long id){
    this.id = id;
}
    
}
