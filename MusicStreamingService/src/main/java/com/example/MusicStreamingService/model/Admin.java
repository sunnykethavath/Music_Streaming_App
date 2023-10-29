package com.example.MusicStreamingService.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@NoArgsConstructor
@Data
@AllArgsConstructor
@Entity
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer adminID;
    private String adminName;

    @Pattern(regexp = "^.+@admin\\.com$")
    private String adminEmail;
    @NotBlank(message = "User password should not be blank")
    private String adminPassword;
}
