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
@Table(name = "obliknastave")
public class OblikNastave {
    @Id
    @Column(name = "oblikNastaveID")
    private Long oblikNastaveID;
    @Column(name = "tip")
    private String tip;

    public OblikNastave() {
    }

    public OblikNastave(Long oblikNastaveID, String tip) {
        this.oblikNastaveID = oblikNastaveID;
        this.tip = tip;
    }

    public Long getOblikNastaveID() {
        return oblikNastaveID;
    }

    public void setOblikNastaveID(Long oblikNastaveID) {
        this.oblikNastaveID = oblikNastaveID;
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
