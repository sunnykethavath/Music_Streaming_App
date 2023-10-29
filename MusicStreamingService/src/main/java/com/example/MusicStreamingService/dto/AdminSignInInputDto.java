package com.example.MusicStreamingService.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class AdminSignInInputDto {

    @Email
    @Pattern(regexp = "^.+@admin\\.com$")
    private String email;

    private String password;
}
