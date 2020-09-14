package com.project.perpustakaan.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "member")
public class Member {


@Id @GeneratedValue
 private long id;
 
 @Column(nullable = false)
 private String nama;

 @Column(nullable = false)
 private String email;

 @Column(nullable = false)
 private String no_hp;

 @OneToMany(mappedBy = "member")
 private List<Peminjaman> peminjaman;

 //menambahkan seter dan getter
    
}
