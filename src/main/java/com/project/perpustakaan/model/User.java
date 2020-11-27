package com.project.perpustakaan.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.project.perpustakaan.model.audit.DateAudit;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.NaturalId;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "username"
        }),
        @UniqueConstraint(columnNames = {
                "email"
        })
})

public class User extends DateAudit{
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @NotBlank
        @Size(max = 40)
        @Column(nullable = false)
        private String noHp;

        @NotBlank
        @Size(max = 15)
        private String username;

        @NaturalId
        @NotBlank
        @Size(max = 40)
        @Email
        private String email;

        //harus di buat unik
        @Column(name = "foto")
        @ColumnDefault(value = "'ImageUser.png")
        private String foto;

        @NotBlank
        @Size(max = 100)
        private String password;


        //membuat dengan menggunakan table baru user roles
        @ManyToMany(fetch = FetchType.LAZY)
        @JoinTable(name = "user_roles",
                joinColumns = @JoinColumn(name = "user_id"),
                inverseJoinColumns = @JoinColumn(name = "role_id"))
        private Set<Role> roles = new HashSet<>();

        public User() {

        }
        public User(String noHp, String username, String email, String password, String foto) {
                this.noHp = noHp;
                this.username = username;
                this.email = email;
                this.password = password;
                this.foto = foto;
        }

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public String getUsername() {
                return username;
        }

        public void setUsername(String username) {
                this.username = username;
        }

        public String getNoHp() {
                return noHp;
        }

        public void setNoHp(String noHp) {
                this.noHp = noHp;
        }

        public String getEmail() {
                return email;
        }

        public void setEmail(String email) {
                this.email = email;
        }

        public String getFoto() {
                return foto;
        }

        public void setFoto(String foto) {
                this.foto = foto;
        }

        public String getPassword() {
                return password;
        }

        public void setPassword(String password) {
                this.password = password;
        }

        public Set<Role> getRoles() {
                return roles;
        }

        public void setRoles(Set<Role> roles) {
                this.roles = roles;
        }
}


