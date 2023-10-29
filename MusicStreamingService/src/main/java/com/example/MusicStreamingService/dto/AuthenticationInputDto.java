package com.example.MusicStreamingService.dto;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class AuthenticationInputDto {
    @Email
    private String email;
    private String tokenValue;
}
