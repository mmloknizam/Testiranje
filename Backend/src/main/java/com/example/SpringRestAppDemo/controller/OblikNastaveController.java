/*
* Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
* Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
*/
package com.example.SpringRestAppDemo.controller;
 
import com.example.SpringRestAppDemo.entity.OblikNastave;
import com.example.SpringRestAppDemo.repository.OblikNastaveRepository;
import java.util.List;
import org.springframework.web.bind.annotation.*;
/**
*
* @author Marija
*/
@RestController
@RequestMapping("/api/obliknastave")
@CrossOrigin(origins = "http://localhost:3000")
public class OblikNastaveController {
 
    private final OblikNastaveRepository oblikNastaveRepository;
 
    public OblikNastaveController(OblikNastaveRepository oblikNastaveRepository) {
        this.oblikNastaveRepository = oblikNastaveRepository;
    }
 
    @GetMapping
    public List<OblikNastave> findAll() {
        return oblikNastaveRepository.findAll();
    }
}