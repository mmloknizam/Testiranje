/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.SpringRestAppDemo.dto;


import java.io.Serializable;

/**
 *
 * @author Milena
 */
public class KorisnickiProfilDto implements Serializable{
    private Long korisnickiProfilID;
    private String email;
    private String lozinka;
    private UlogaDto uloga;

    public Long getKorisnickiProfilID() {
        return korisnickiProfilID;
    }

    public void setKorisnickiProfilID(Long korisnickiProfilID) {
        this.korisnickiProfilID = korisnickiProfilID;
    }

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

    public UlogaDto getUloga() {
        return uloga;
    }

    public void setUloga(UlogaDto uloga) {
        this.uloga = uloga;
    }

}
