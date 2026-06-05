/*
* Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
* Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
*/
package com.example.SpringRestAppDemo.controller;
 
import com.example.SpringRestAppDemo.entity.SkolskaGodina;
import com.example.SpringRestAppDemo.repository.SkolskaGodinaRepository;
import java.util.List;
import org.springframework.web.bind.annotation.*;
 
/**
*
* @author Marija
*/
@RestController
@RequestMapping("/api/skolskagodina")
@CrossOrigin(origins = "http://localhost:3000")
public class SkolskaGodinaController {
 
    private final SkolskaGodinaRepository skolskaGodinaRepository;
 
    public SkolskaGodinaController(SkolskaGodinaRepository skolskaGodinaRepository) {
        this.skolskaGodinaRepository = skolskaGodinaRepository;
    }
 
    @GetMapping
    public List<SkolskaGodina> findAll() {
        return skolskaGodinaRepository.findAll();
    }
}