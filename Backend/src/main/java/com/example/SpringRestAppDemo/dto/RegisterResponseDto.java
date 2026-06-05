/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.SpringRestAppDemo.dto;

/**
 *
 * @author Milena
 */
public class RegisterResponseDto {
     private String message;

    public RegisterResponseDto(String message) {
        this.message = message;
    }

    public String getMessage() { 
        return message; 
    }
}
