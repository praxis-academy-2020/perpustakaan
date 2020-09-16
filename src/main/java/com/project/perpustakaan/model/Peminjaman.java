package com.project.perpustakaan.model;

import static javax.persistence.TemporalType.DATE;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "peminjaman")
public class Peminjaman {

    @ManyToOne(optional = false)
    @JoinColumn(name = "idKatalog", referencedColumnName = "id", insertable = false, updatable = false)
    private Katalog katalog;
    @Column(nullable = false)
    private Long idKatalog;

    @ManyToOne(optional = false)
    @JoinColumn(name = "idMember", referencedColumnName = "id", insertable = false, updatable = false)
    private Member member;
    @Column(nullable = false)
    private Long idMember;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    //@Temporal(TemporalType.DATE)
    private Date tglPinjam;

    //tanggl kembalinya buku, maksimal 10 hari   
    //@Temporal(TemporalType.DATE)
    private Date tglKembali;

    @Column()
    private Boolean status = true;

    @Column(columnDefinition = "integer default 0")
    private int tagihan;

    


    //memasang setter dan getter

    public Long getId() {
        return this.id;
    }

    public Date getTglPinjam() {
        return tglPinjam;
    }
    public void setTglPinjam(Date tglPinjam){
        this.tglPinjam = tglPinjam;
       // this.tglPinjam = new Date(); 
    }
    public Long getIdKatalog() {
        return idKatalog;
    }
    public void setIdKatalog(Long idKatalog){
        this.idKatalog = idKatalog;
    }
    public Long getIdMember() {
        return idMember;
    }
    public void setIdMember(Long idMember){
        this.idMember = idMember;
    }
    public Member getMember() {
        return member;
    }
    public void setMember(Member member){
        this.member = member;
    }
    public Katalog getKatalog() {
        return katalog;
    }
    public void setKatalog(Katalog katalog){
        this.katalog = katalog;
    }

    public void setTagihan(int tagihan){
        this.tagihan = tagihan;
    }

    public int getTagihan(){
        return this.tagihan;
    }

    public Date getTglKembali() {
        return this.tglKembali;
    }
    public void setTglKembali(Date tglKembali){
        this.tglKembali = tglKembali;
        this.tglKembali = new Date();
    }

    public Boolean getStatus() {
        return this.status;
    }
    public void setStatus(Boolean status){
        this.status = status;
    }


}
