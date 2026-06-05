/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.SpringRestAppDemo.controller;

import com.example.SpringRestAppDemo.entity.Zvanje;
import com.example.SpringRestAppDemo.repository.ZvanjeRepository;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Marija
 */
@RestController
@RequestMapping("/api/zvanje")
public class ZvanjeController {

    private final ZvanjeRepository zvanjeRepository;

    public ZvanjeController(ZvanjeRepository zvanjeRepository) {
        this.zvanjeRepository = zvanjeRepository;
    }
    
    @GetMapping
    public List<Zvanje> getAll() {
        return zvanjeRepository.findAll();
    }
}