package com.example.MusicStreamingService.service;

import com.example.MusicStreamingService.dto.AuthenticationInputDto;
import com.example.MusicStreamingService.dto.UserSignInInputDto;
import com.example.MusicStreamingService.model.AuthenticationToken;
import com.example.MusicStreamingService.model.User;
import com.example.MusicStreamingService.repo.IUserRepo;
import com.example.MusicStreamingService.util.PasswordEncryptor;
import org.hibernate.sql.exec.spi.StandardEntityInstanceResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
@Service
public class UserService {
    @Autowired
    IUserRepo userRepo;

    @Autowired
    AuthenticationTokenService authenticationTokenService;
    public String signUpUser(User user) throws NoSuchAlgorithmException {
        String email = user.getUserEmail();
        User existingUser = userRepo.findFirstByUserEmail(email);

        if(existingUser != null)
            return "email already in use";

        String password = user.getUserPassword();
        String encryptedPassword = PasswordEncryptor.encrypt(password);
        user.setUserPassword(encryptedPassword);
        userRepo.save(user);
        return "User Registered!!!";
    }

    public String signInUser(UserSignInInputDto userSignInInputDto) throws NoSuchAlgorithmException{
        User user = userRepo.findFirstByUserEmail(userSignInInputDto.getEmail());
        if(user == null)
            return "Invalid Credentials";
        String encryptedPassword = PasswordEncryptor.encrypt(userSignInInputDto.getPassword());

        if(encryptedPassword.equals(user.getUserPassword())){
            AuthenticationToken token = new AuthenticationToken(user);
            authenticationTokenService.createToken(token);
            return "Successfully Signed In,"+" token value : "+ token.getTokenValue();
        }
        return "Invalid Credentials";
    }

    public String signOutUser(AuthenticationInputDto authInfo) {

        if(authenticationTokenService.userAuthenticate(authInfo)){
           authenticationTokenService.deleteToken(authInfo.getTokenValue());
           return "sign out successful";
        }
        return "Unauthorised Access";
    }
}
