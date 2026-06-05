/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.SpringRestAppDemo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

/**
 *
 * @author Marija
 */
@Entity
@Table(name = "verifikacija")
public class Verifikacija {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "verifikacijaID")
    private Long verifikacijaID;
    @Column(name = "email")
    private String email;
    @Column(name = "kod")
    private String kod;
    @Column(name = "vreme")
    private LocalDateTime vreme;

    public Verifikacija() {
    }
    
    public Verifikacija(String email, String kod, LocalDateTime vreme) {
        this.email = email;
        this.kod = kod;
        this.vreme = vreme;
    }
    public Verifikacija(Long verifikacijaID, String email, String kod, LocalDateTime vreme) {
        this.verifikacijaID = verifikacijaID;
        this.email = email;
        this.kod = kod;
        this.vreme = vreme;
    }

    public Long getVerifikacijaID() {
        return verifikacijaID;
    }

    public void setVerifikacijaID(Long verifikacijaID) {
        this.verifikacijaID = verifikacijaID;
    }

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

    public LocalDateTime getVreme() {
        return vreme;
    }

    public void setVreme(LocalDateTime vreme) {
        this.vreme = vreme;
    }
}
