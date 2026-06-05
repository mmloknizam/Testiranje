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
@Table(name = "skolskagodina")
public class SkolskaGodina {
    @Id
    @Column(name = "skolskaGodinaID")
    private Long skolskaGodinaID;
    @Column(name = "godina")
    private String godina;

    public SkolskaGodina() {
    }

    public SkolskaGodina(Long skolskaGodinaID, String godina) {
        this.skolskaGodinaID = skolskaGodinaID;
        this.godina = godina;
    }

    public Long getSkolskaGodinaID() {
        return skolskaGodinaID;
    }

    public void setSkolskaGodinaID(Long skolskaGodinaID) {
        this.skolskaGodinaID = skolskaGodinaID;
    }

    public String getGodina() {
        return godina;
    }

    public void setGodina(String godina) {
        this.godina = godina;
    }

    @Override
    public String toString() {
        return godina;
    }
    
    
}
