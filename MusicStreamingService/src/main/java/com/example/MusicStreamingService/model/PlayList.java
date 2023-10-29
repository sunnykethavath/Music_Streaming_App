package com.example.MusicStreamingService.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class PlayList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer playListId;

    @NotEmpty
    private String playListName;

    @ManyToOne
    @JoinColumn(name = "fk_user_ID")
    private User user;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "Playlist_Song_table",joinColumns = @JoinColumn(name = "playlist_ID"),inverseJoinColumns = @JoinColumn(name = "song_ID"))
    private List<Song> songs;
}
