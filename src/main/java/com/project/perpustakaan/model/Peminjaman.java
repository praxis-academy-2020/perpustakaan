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
    @JoinColumn(name = "katalog_id", referencedColumnName= "id",insertable = false,updatable = false)
    private Katalog katalog;

    @Column
    private Long id_katalog;

    @ManyToOne
    @JoinColumn(name = "member_id", referencedColumnName="id",insertable = false,updatable = false)
    private Member member;

    @Column
    private Long id_member;

    //memasagn setter dan getter

    void setTgl_pinjam(Date tanggal){
        this.tgl_pinjam = tanggal;
    }

    void setId_katalog(Long id){
        this.id_katalog= id;
    }

    void setId_member(Long id){
        this.id_member= id;
    }

    public Long getId_katalog(){
        return id_katalog;
    }

    public Date getTanggal(){
        return this.tgl_pinjam;
    }
    
    
}
