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
    private String Author;

    @Column(nullable = false)
    private int tahun;

    @Column
    private String sinopsis;
    
    @OneToMany(mappedBy = "katalog")
    private List<Peminjaman> peminjaman;
    //membuat setter dan getter dan reaalsi table

    
}
