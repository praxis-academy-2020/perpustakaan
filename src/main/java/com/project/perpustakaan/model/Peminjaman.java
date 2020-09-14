package com.project.perpustakaan.model;
import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "peminjaman")
public class Peminjaman {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private Date tgl_pinjam;

    @ManyToOne
    @JoinColumn(name = "katalog_id")
    private Katalog katalog;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
    
}
