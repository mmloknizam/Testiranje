/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.SpringRestAppDemo.controller;

import com.example.SpringRestAppDemo.dto.BrisanjeProfilaDto;
import com.example.SpringRestAppDemo.dto.ConfirmEmailRequestDto;
import com.example.SpringRestAppDemo.dto.IzmenaProfilaDto;
import com.example.SpringRestAppDemo.dto.LoginRequestDto;
import com.example.SpringRestAppDemo.dto.LoginResponseDto;
import com.example.SpringRestAppDemo.dto.ProfilKorisnikaDto;
import com.example.SpringRestAppDemo.dto.PromenaLozinkeDto;
import com.example.SpringRestAppDemo.dto.RegisterRequestDto;
import com.example.SpringRestAppDemo.dto.RegisterResponseDto;
import com.example.SpringRestAppDemo.dto.ResendCodeRequestDto;
import com.example.SpringRestAppDemo.dto.UlogaDto;
import com.example.SpringRestAppDemo.dto.ZvanjeDto;
import com.example.SpringRestAppDemo.repository.VerifikacijaRepository;
import com.example.SpringRestAppDemo.service.KorisnickiProfilService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Marija
 */
@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class KorisnickiProfilController {

    private final KorisnickiProfilService korisnickiProfilService;
    private final VerifikacijaRepository verifikacijaRepository;

    public KorisnickiProfilController(KorisnickiProfilService korisnickiProfilService, VerifikacijaRepository verifikacijaRepository) {
        this.korisnickiProfilService = korisnickiProfilService;
        this.verifikacijaRepository = verifikacijaRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto) throws Exception {
        return ResponseEntity.ok(korisnickiProfilService.login(loginRequestDto));
    }
    
    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDto> register(@RequestBody RegisterRequestDto request) throws Exception {
        return ResponseEntity.ok(korisnickiProfilService.register(request));
    }
    
    @PostMapping("/confirm")
    public ResponseEntity<RegisterResponseDto> confirmEmail(@RequestBody ConfirmEmailRequestDto request) throws Exception {
        return ResponseEntity.ok(korisnickiProfilService.confirmEmail(request));
    }

    @PostMapping("/resend-code")
    public ResponseEntity<RegisterResponseDto> resendVerificationCode(@RequestBody ResendCodeRequestDto request) throws Exception {
        return ResponseEntity.ok(korisnickiProfilService.resendVerificationCode(request.getEmail()));
    }
    
    @DeleteMapping("/delete-code/{email}")
    public ResponseEntity<String> deleteVerificationCode(@PathVariable String email) {
        verifikacijaRepository.findByEmail(email).ifPresent(verifikacijaRepository::delete);
        return ResponseEntity.ok("Kod obrisan");
    }

    @GetMapping("/profil/{id}")
    public ResponseEntity<ProfilKorisnikaDto> getProfil(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(korisnickiProfilService.getProfilKorisnika(id));
    }

    @PutMapping("/profil/{id}")
    public ResponseEntity<ProfilKorisnikaDto> izmeniProfil(
            @PathVariable Long id,
            @RequestBody IzmenaProfilaDto izmena) throws Exception {
        return ResponseEntity.ok(korisnickiProfilService.izmeniProfil(id, izmena));
    }

    @PutMapping("/profil/{id}/lozinka")
    public ResponseEntity<String> promeniLozinku(
            @PathVariable Long id,
            @RequestBody PromenaLozinkeDto dto) throws Exception {
        korisnickiProfilService.promeniLozinku(id, dto);
        return ResponseEntity.ok("Lozinka uspešno promenjena");
    }

    @DeleteMapping("/profil/{id}")
    public ResponseEntity<String> obrisiProfil(
            @PathVariable Long id,
            @RequestBody BrisanjeProfilaDto dto) throws Exception {
        korisnickiProfilService.obrisiProfil(id, dto);
        return ResponseEntity.ok("Profil obrisan");
    }

    @GetMapping("/uloge")
    public ResponseEntity<List<UlogaDto>> getSveUloge() {
        return ResponseEntity.ok(korisnickiProfilService.getSveUloge());
    }

    @GetMapping("/zvanja")
    public ResponseEntity<List<ZvanjeDto>> getSvaZvanja() {
        return ResponseEntity.ok(korisnickiProfilService.getSvaZvanja());
    }
    
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<String> handleException(Exception e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
