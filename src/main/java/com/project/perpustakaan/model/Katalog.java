package com.project.perpustakaan.model;

import java.util.List;

//import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

//import org.hibernate.mapping.List;

@Entity
@Table(name = "katalog")
public class Katalog {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String judul;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private int tahun;

    @Column
    private String sinopsis;
    
    @OneToMany(mappedBy = "katalog")
    private List<Peminjaman> peminjaman;
    //membuat setter dan getter dan reaalsi table

    //id
    public Long getId(){
        return this.id;
    }
    //judul
    public void setJudul(String judul){
        this.judul = judul;
    }
    public String getJudul(){
        return this.judul;
    }
    //author
    public void setAuthor(String author){
        this.author = author;
    }
    public String getAuthor(){
        return this.author;
    }

    //tahun
    public void setTahun(int tahun){
        this.tahun = tahun;
    }
    public int getTahun(){
        return this.tahun;
    }
    //sinopsis
    public void setSinopsis(String sinopsis){
        this.sinopsis = sinopsis;
    }
    public String getSinopsis(){
        return this.sinopsis;
    }

    
}
