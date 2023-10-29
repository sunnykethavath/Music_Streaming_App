package com.example.MusicStreamingService.service;

import com.example.MusicStreamingService.dto.AdminSignInInputDto;
import com.example.MusicStreamingService.dto.AuthenticationInputDto;
import com.example.MusicStreamingService.model.Admin;
import com.example.MusicStreamingService.model.AuthenticationToken;
import com.example.MusicStreamingService.model.Song;
import com.example.MusicStreamingService.repo.IAdminRepo;
import com.example.MusicStreamingService.util.PasswordEncryptor;
import com.example.MusicStreamingService.util.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
@Service
public class AdminService {

    @Autowired
    IAdminRepo adminRepo;

    @Autowired
    SongService songService;

    @Autowired
    AuthenticationTokenService authenticationTokenService;

    public String signUpAdmin(Admin admin)throws NoSuchAlgorithmException {
        if(Validate.isValidUser(admin.getAdminEmail())) {
            Admin existingAdmin = adminRepo.findFirstByAdminEmail(admin.getAdminEmail());
            if(existingAdmin != null){
                return "You Are Already Register";
            }else{
                String encryptedPassword = PasswordEncryptor.encrypt(admin.getAdminPassword());
                admin.setAdminPassword(encryptedPassword);
                adminRepo.save(admin);
                return "registered!!!";
            }
        }
        return "Invalid admin Email or Password";
    }

    public String signInAdmin(AdminSignInInputDto adminSignInInputDto) throws NoSuchAlgorithmException {
        Admin existingAdmin = adminRepo.findFirstByAdminEmail(adminSignInInputDto.getEmail());
        if(existingAdmin == null){
            return "register to sign in";
        }
        String password = adminSignInInputDto.getPassword();
        String encryptedPassword = PasswordEncryptor.encrypt(password);
        if(encryptedPassword.equals(existingAdmin.getAdminPassword())){
            AuthenticationToken token = new AuthenticationToken(existingAdmin);
            authenticationTokenService.createToken(token);
            return "Successfully Signed In,"+" token value : "+ token.getTokenValue();
        }
        return "Invalid Credentials!!!";
    }

    public String addSong(String adminEmail, String tokenValue, Song song) {
        if(authenticationTokenService.adminAuthenticate(adminEmail, tokenValue)){
            songService.addSong(song);
            return "song added successfully!!!";
        }else{
            return "you do not have permission to perform this operation..";
        }
    }

    public String signOutAdmin(String adminEmail, String tokenValue) {
        if(authenticationTokenService.adminAuthenticate(adminEmail, tokenValue)){
            authenticationTokenService.deleteToken(tokenValue);
            return "Sign out Successful";
        }
        return "Un Authenticated access!!!";
    }

    public List<Song> getSongs(String adminEmail, String tokenValue) {
        List<Song> songList = new ArrayList<>();
        if(authenticationTokenService.adminAuthenticate(adminEmail, tokenValue)){
            songList = songService.songRepo.findAll();
        }
        return songList;
    }

    public String updateSong(String adminEmail, String tokenValue, Song song) {
        if(authenticationTokenService.adminAuthenticate(adminEmail, tokenValue)){
            return songService.updateSong(song);
        }
        return "unauthorised access";
    }

    public String deleteSong(String adminEmail, String tokenValue, Integer id) {
        if(authenticationTokenService.adminAuthenticate(adminEmail, tokenValue)){
            return songService.deleteSong(id);
        }
        return "unauthorised access";
    }
}
