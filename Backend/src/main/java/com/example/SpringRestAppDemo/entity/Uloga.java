/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.SpringRestAppDemo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 *
 * @author Marija
 */
@Entity
@Table(name = "uloga")
public class Uloga {
    @Id 
    @Column(name = "ulogaID")
    private Long ulogaID;
    @Column(name = "tip")
    private String tip;

    public Uloga() {
    }

    public Uloga(Long ulogaID, String tip) {
        this.ulogaID = ulogaID;
        this.tip = tip;
    }

    public Long getUlogaID() {
        return ulogaID;
    }

    public void setUlogaID(Long ulogaID) {
        this.ulogaID = ulogaID;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    @Override
    public String toString() {
        return tip;
    }
    
    

}
