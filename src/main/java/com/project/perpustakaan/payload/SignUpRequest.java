package com.project.perpustakaan.payload;

import javax.validation.constraints.*;

public class SignUpRequest {
    @NotBlank
    @Size(min = 4, max = 40)
    private String noHp;

    @NotBlank
    @Size(min = 3, max = 15)
    private String username;

    @NotBlank
    @Size(max = 40)
    @Email
    private String email;

    private String foto;

    @NotBlank
    @Size(min = 6, max = 20)
    private String password;

    public String getNoHp() {
        return this.noHp;
    }

    public void setNoHp(String noHp) {
        this.noHp = noHp;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

	public String getFoto() {
		return foto;
    }
    
    public void setFoto() {
		this.foto = foto;
	}
}
