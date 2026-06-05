/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.SpringRestAppDemo.service;

import com.example.SpringRestAppDemo.dto.BrisanjeProfilaDto;
import com.example.SpringRestAppDemo.dto.ConfirmEmailRequestDto;
import com.example.SpringRestAppDemo.dto.IzmenaProfilaDto;
import com.example.SpringRestAppDemo.dto.LoginRequestDto;
import com.example.SpringRestAppDemo.dto.LoginResponseDto;
import com.example.SpringRestAppDemo.dto.ProfilKorisnikaDto;
import com.example.SpringRestAppDemo.dto.PromenaLozinkeDto;
import com.example.SpringRestAppDemo.dto.RegisterRequestDto;
import com.example.SpringRestAppDemo.dto.RegisterResponseDto;
import com.example.SpringRestAppDemo.dto.UlogaDto;
import com.example.SpringRestAppDemo.dto.ZvanjeDto;
import java.util.List;

/**
 *
 * @author Milena
 */
public interface KorisnickiProfilService {
    LoginResponseDto login(LoginRequestDto request) throws Exception;
    RegisterResponseDto register(RegisterRequestDto request) throws Exception;
    RegisterResponseDto confirmEmail(ConfirmEmailRequestDto request) throws Exception;
    RegisterResponseDto resendVerificationCode(String email) throws Exception;
    
    ProfilKorisnikaDto getProfilKorisnika(Long korisnickiProfilID) throws Exception;
    ProfilKorisnikaDto izmeniProfil(Long korisnickiProfilID, IzmenaProfilaDto izmena) throws Exception;
    void promeniLozinku(Long korisnickiProfilID, PromenaLozinkeDto dto) throws Exception;
    void obrisiProfil(Long korisnickiProfilID, BrisanjeProfilaDto dto) throws Exception;
    List<UlogaDto> getSveUloge();
    List<ZvanjeDto> getSvaZvanja();
}
