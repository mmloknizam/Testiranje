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
public class ResendCodeRequestDto implements Serializable{
    private String email;

    public String getEmail() { 
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

}
