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
public class IzmenaProfilaDto implements Serializable{
    private Long zvanjeID;
    private Long ulogaID;

    public IzmenaProfilaDto() {
    }

    public IzmenaProfilaDto(Long zvanjeID, Long ulogaID) {
        this.zvanjeID = zvanjeID;
        this.ulogaID = ulogaID;
    }

    public Long getZvanjeID() {
        return zvanjeID;
    }

    public void setZvanjeID(Long zvanjeID) {
        this.zvanjeID = zvanjeID;
    }

    public Long getUlogaID() {
        return ulogaID;
    }

    public void setUlogaID(Long ulogaID) {
        this.ulogaID = ulogaID;
    }
    
}
