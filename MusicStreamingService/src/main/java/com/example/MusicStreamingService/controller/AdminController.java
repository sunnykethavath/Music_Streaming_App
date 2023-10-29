package com.example.MusicStreamingService.controller;

import com.example.MusicStreamingService.dto.AdminSignInInputDto;
import com.example.MusicStreamingService.dto.AuthenticationInputDto;
import com.example.MusicStreamingService.model.Admin;
import com.example.MusicStreamingService.model.AuthenticationToken;
import com.example.MusicStreamingService.model.Song;
import com.example.MusicStreamingService.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
public class AdminController {

    @Autowired
    AdminService adminService;

    //sign up
    @PostMapping("/signUp/Admin")
    public String signUpAdmin(@RequestBody Admin admin) throws NoSuchAlgorithmException {
        return adminService.signUpAdmin(admin);
    }

    //sign in
    @PostMapping("/signIn/Admin")
    public String signInAdmin(@RequestBody AdminSignInInputDto adminSignInInputDto) throws NoSuchAlgorithmException{
        return adminService.signInAdmin(adminSignInInputDto);
    }

    //sign out
    @DeleteMapping("/signOut/Admin")
    public String signOutAdmin(@RequestParam String adminEmail, @RequestParam String tokenValue){
        return adminService.signOutAdmin(adminEmail, tokenValue);
    }

    //add song
    @PostMapping("/song")
    public String addSong(@RequestParam String adminEmail, @RequestParam String tokenValue, @RequestBody Song song){
        return adminService.addSong(adminEmail, tokenValue, song);
    }

    //get songs
    @GetMapping("/songs")
    public List<Song> getSongs(@RequestParam String adminEmail, @RequestParam String tokenValue){
        return adminService.getSongs(adminEmail, tokenValue);
    }
    //update song
    @PutMapping("/song")
    public String updateSong(@RequestParam String adminEmail, @RequestParam String tokenValue, @RequestBody Song song){
        return adminService.updateSong(adminEmail, tokenValue, song);
    }
    //delete song
    @DeleteMapping("/song/{id}")
    public String deleteSong(@RequestParam String adminEmail, @RequestParam String tokenValue, @PathVariable Integer id){
        return adminService.deleteSong(adminEmail, tokenValue, id);
    }
}
