/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.SpringRestAppDemo.dto;


import java.io.Serializable;

/**
 *
 * @author Marija
 */
public class ConfirmEmailRequestDto implements Serializable{
    private String email;
    private String kod;
    private String lozinka;
    private Long ulogaID;
    private Long nastavnikID;
    
    public String getEmail() { 
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getKod() { 
        return kod; 
    }
    public void setKod(String kod) { 
        this.kod = kod; 
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

    public Long getNastavnikID() {
        return nastavnikID;
    }

    public void setNastavnikID(Long nastavnikID) {
        this.nastavnikID = nastavnikID;
    }
    
}
