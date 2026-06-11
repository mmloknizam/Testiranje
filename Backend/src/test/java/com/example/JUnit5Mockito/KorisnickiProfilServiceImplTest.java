package com.example.JUnit5Mockito;

import com.example.SpringRestAppDemo.dto.*;
import com.example.SpringRestAppDemo.entity.*;
import com.example.SpringRestAppDemo.repository.*;
import com.example.SpringRestAppDemo.service.impl.KorisnickiProfilServiceImpl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class KorisnickiProfilServiceImplTest {

    @Mock private KorisnickiProfilRepository korisnickiProfilRepository;
    @Mock private UlogaRepository ulogaRepository;
    @Mock private VerifikacijaRepository verifikacijaRepository;
    @Mock private NastavnikRepository nastavnikRepository;
    @Mock private ZvanjeRepository zvanjeRepository;

    @InjectMocks
    private KorisnickiProfilServiceImpl service;

    @Test
    void login_success() throws Exception {

        LoginRequestDto dto = new LoginRequestDto();
        dto.setEmail("test@fon.bg.ac.rs");
        dto.setLozinka("Test123!");

        KorisnickiProfil k = new KorisnickiProfil();
        k.setEmail(dto.getEmail());
        k.setLozinka(dto.getLozinka());
        k.setEnabled(true);
        k.setKorisnickiProfilID(1L);

        Uloga u = new Uloga();
        k.setUloga(u);

        when(korisnickiProfilRepository.findByEmail(dto.getEmail()))
                .thenReturn(Optional.of(k));

        LoginResponseDto res = service.login(dto);

        assertNotNull(res);
        assertEquals("test@fon.bg.ac.rs", res.getEmail());
    }

    @Test
    void register_success() throws Exception {

        RegisterRequestDto dto = new RegisterRequestDto();
        dto.setEmail("test@fon.bg.ac.rs");
        dto.setLozinka("Test123!");
        dto.setUlogaID(1L);

        Uloga u = new Uloga();
        u.setUlogaID(1L);
        u.setTip("Student");

        when(korisnickiProfilRepository.findByEmail(dto.getEmail()))
                .thenReturn(Optional.empty());

        when(ulogaRepository.findById(1L))
                .thenReturn(Optional.of(u));

        when(verifikacijaRepository.save(any()))
                .thenAnswer(i -> i.getArgument(0));

        RegisterResponseDto res = service.register(dto);

        assertNotNull(res);
        assertTrue(res.getMessage().contains("Registracija uspešna"));
    }

    @Test
    void confirm_email_success() throws Exception {

        ConfirmEmailRequestDto dto = new ConfirmEmailRequestDto();
        dto.setEmail("test@fon.bg.ac.rs");
        dto.setKod("123");
        dto.setLozinka("Test123!");
        dto.setUlogaID(1L);

        Verifikacija v = new Verifikacija();
        v.setEmail(dto.getEmail());
        v.setKod(dto.getKod());
        v.setVreme(LocalDateTime.now());

        Uloga u = new Uloga();
        u.setUlogaID(1L);

        when(verifikacijaRepository.findByEmailAndKod(dto.getEmail(), dto.getKod()))
                .thenReturn(Optional.of(v));

        when(ulogaRepository.findById(1L))
                .thenReturn(Optional.of(u));

        when(korisnickiProfilRepository.save(any()))
                .thenAnswer(i -> i.getArgument(0));

        RegisterResponseDto res = service.confirmEmail(dto);

        assertNotNull(res);
        assertTrue(res.getMessage().contains("Korisnik uspešno aktiviran"));
    }

    @Test
    void get_profil_success() throws Exception {

        KorisnickiProfil k = new KorisnickiProfil();
        k.setKorisnickiProfilID(1L);
        k.setEmail("test@fon.bg.ac.rs");

        when(korisnickiProfilRepository.findById(1L))
                .thenReturn(Optional.of(k));

        ProfilKorisnikaDto dto = service.getProfilKorisnika(1L);

        assertEquals("test@fon.bg.ac.rs", dto.getEmail());
    }

    @Test
    void obrisi_profil_success() throws Exception {

        KorisnickiProfil k = new KorisnickiProfil();
        k.setLozinka("Test123!");

        BrisanjeProfilaDto dto = new BrisanjeProfilaDto();
        dto.setLozinka("Test123!");

        when(korisnickiProfilRepository.findById(1L))
                .thenReturn(Optional.of(k));

        doNothing().when(korisnickiProfilRepository).delete(k);

        assertDoesNotThrow(() -> service.obrisiProfil(1L, dto));
    }
}