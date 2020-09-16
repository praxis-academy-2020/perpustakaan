package com.project.perpustakaan.model;
import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "peminjaman")
public class Peminjaman {

    @ManyToOne(optional=false)
    @JoinColumn(name = "idKatalog", referencedColumnName= "id",insertable = false,updatable = false)
    private Katalog katalog;
    @Column(nullable = false)
    private Long idKatalog;

    @ManyToOne(optional=false)
    @JoinColumn(name = "idMember", referencedColumnName="id",insertable = false,updatable = false)
    private Member member;
    @Column(nullable = false)
    private Long idMember;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private Date tglPinjam;

    //tanggl kembalinya buku, maksimal 20    
    @Column(nullable = false)
    private Date tglKembali;

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
        return tglKembali;
    }
    public void setTglKembali(Date tglPinjam){
        this.tglKembali = tglKembali;
    }


}
