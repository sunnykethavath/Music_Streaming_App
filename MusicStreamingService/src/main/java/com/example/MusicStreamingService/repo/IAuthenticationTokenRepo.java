package com.example.MusicStreamingService.repo;

import com.example.MusicStreamingService.model.AuthenticationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAuthenticationTokenRepo extends JpaRepository<AuthenticationToken,Integer> {
    AuthenticationToken findFirstByTokenValue(String tokenValue);
}
