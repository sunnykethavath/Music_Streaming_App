package com.example.MusicStreamingService.service;

import com.example.MusicStreamingService.dto.AuthenticationInputDto;
import com.example.MusicStreamingService.model.AuthenticationToken;
import com.example.MusicStreamingService.repo.IAuthenticationTokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationTokenService {
    @Autowired
    IAuthenticationTokenRepo authenticationTokenRepo;

    public void createToken(AuthenticationToken token) {
        authenticationTokenRepo.save(token);
    }

    public void deleteToken(String tokenValue) {

        AuthenticationToken token =  authenticationTokenRepo.findFirstByTokenValue(tokenValue);
        authenticationTokenRepo.delete(token);
    }

    public boolean userAuthenticate (AuthenticationInputDto authInfo){

        String email = authInfo.getEmail();
        String tokenValue = authInfo.getTokenValue();

        AuthenticationToken token = authenticationTokenRepo.findFirstByTokenValue(tokenValue);

        if(token != null){
            return token.getUser().getUserEmail().equals(email);
        }else{
            return false;
        }
    }

    public boolean userAuthenticate (String userEmail, String tokenValue){

        AuthenticationToken token = authenticationTokenRepo.findFirstByTokenValue(tokenValue);

        if(token != null){
            return token.getUser().getUserEmail().equals(userEmail);
        }else{
            return false;
        }
    }

    public boolean adminAuthenticate (String adminEmail, String tokenValue){

        AuthenticationToken token = authenticationTokenRepo.findFirstByTokenValue(tokenValue);

        if(token != null){
            return token.getAdmin().getAdminEmail().equals(adminEmail);
        }else{
            return false;
        }
    }
}
