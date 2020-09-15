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


    //memasagn setter dan getter
    void setKatalog(Katalog katalog){
        this.katalog = katalog;
    }

    void setMember(Member member){
        this.member = member;
    }

    void setTanggal(Date tanggal){
        this.tgl_pinjam = tanggal;
    }

    public Katalog getkatalog(){
        return this.katalog;
    }

    public Member getMember(){
        return this.member;
    }

    public Date getTanggal(){
        return this.tgl_pinjam;
    }
    
}
