/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.SpringRestAppDemo.controller;

import com.example.SpringRestAppDemo.entity.Uloga;
import com.example.SpringRestAppDemo.repository.UlogaRepository;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Milena
 */
@RestController
@RequestMapping("/api/uloga")
public class UlogaController {

    private final UlogaRepository ulogaRepository;

    public UlogaController(UlogaRepository ulogaRepository) {
        this.ulogaRepository = ulogaRepository;
    }

    @GetMapping
    public List<Uloga> getAll() {
        return ulogaRepository.findAll();
    }
}