package com.example.MusicStreamingService.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    @NotEmpty(message = "User name should not be empty")
    private String userName;
    @NotBlank(message = "User email should not be blank")
    private String userEmail;
    @NotBlank(message = "User password should not be blank")
    private String userPassword;
    @Size(min = 10,max = 12)
    @Pattern(regexp = "^[0-9]+$")
    private String userContactNum;
}
