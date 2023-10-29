package com.example.MusicStreamingService.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer songID;
    @NotEmpty
    private String songName;
    @NotBlank
    private String songArtist;
    @NotEmpty(message = "song link must not be Empty")
    private String songLink;
    @Enumerated(value = EnumType.STRING)
    private Genre genre;
}
