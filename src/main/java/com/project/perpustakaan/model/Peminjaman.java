package com.project.perpustakaan.model;

//import java.text.SimpleDateFormat;
//import java.sql.Date;
import java.util.Calendar;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "peminjaman")
public class Peminjaman {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private Long idKatalog;

    @ManyToOne(optional = false)
    @JoinColumn(name = "idKatalog", referencedColumnName = "id", insertable = false, updatable = false)
    private Katalog katalog;
    
    @Column(nullable = false)
    private Long iduser;

    @ManyToOne(optional = false)
    @JoinColumn(name = "iduser", referencedColumnName = "id", insertable = false, updatable = false)
    private User user;

    @Column(nullable = false)
    @JsonFormat(pattern="dd-MMMM-yyyy")
    private Date tglPinjam = Calendar.getInstance().getTime();

    @JsonFormat(pattern="dd-MMMM-yyyy")
    private Date tglKembali;

    private Boolean status = true;

    private long tagihan;

    //memasang setter dan getter

    public Long getId() {
        return this.id;
    }

    public Date getTglPinjam() {
        return tglPinjam;
    }
    public void setTglPinjam(Date tglPinjam){
       this.tglPinjam = tglPinjam;
    }
    public Long getIdKatalog() {
        return idKatalog;
    }
    public void setIdKatalog(Long idKatalog){
        this.idKatalog = idKatalog;
    }
    public Long getIdUser() {
        return iduser;
    }
    public void setIdUser(Long iduser){
        this.iduser = iduser;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user){
        this.user = user;
    }
    public Katalog getKatalog() {
        return katalog;
    }
    public void setKatalog(Katalog katalog){
        this.katalog = katalog;
    }

    public Date getTglKembali() {
        return this.tglKembali;
    }
    public void setTglKembali(Date tglKembali){
        this.tglKembali = tglKembali;
       // this.tglKembali = new Date();
    }

    public Boolean getStatus() {
        return this.status;
    }
    public void setStatus(Boolean status){
        this.status = status;
    }

    public long getTagihan() {
        return this.tagihan;
    }
    public void setTagihan(long tagihan){
        this.tagihan = tagihan;
    }


}
