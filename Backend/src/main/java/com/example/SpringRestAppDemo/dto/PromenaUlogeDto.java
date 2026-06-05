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
public class PromenaUlogeDto implements Serializable{
    private Long ulogaID;

    public Long getUlogaID() {
        return ulogaID;
    }

    public void setUlogaID(Long ulogaID) {
        this.ulogaID = ulogaID;
    }
}
