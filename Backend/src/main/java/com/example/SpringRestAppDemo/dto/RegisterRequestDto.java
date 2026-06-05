/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.SpringRestAppDemo.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

/**
 *
 * @author Milena
 */
public class RegisterRequestDto {
    @NotNull
    @Pattern(
        regexp = "^[A-Za-z0-9._%+-]+@fon\\.bg\\.ac\\.rs$",
        message = "Email mora biti u formatu @fon.bg.ac.rs"
    )
    private String email;
    private String lozinka;
    private Long ulogaID;
    private String adminSifra;

    public String getEmail() { 
        return email; 
    }
    public void setEmail(String email) { 
        this.email = email;
    }

    public String getLozinka() { 
        return lozinka; 
    }
    public void setLozinka(String lozinka) { 
        this.lozinka = lozinka; 
    }

    public Long getUlogaID() { 
        return ulogaID; 
    }
    public void setUlogaID(Long ulogaID) { 
        this.ulogaID = ulogaID; 
    }

    public String getAdminSifra() {
        return adminSifra;
    }

    public void setAdminSifra(String adminSifra) {
        this.adminSifra = adminSifra;
    }
}
