package com.example.MusicStreamingService.repo;

import com.example.MusicStreamingService.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISongRepo extends JpaRepository<Song,Integer> {
    Song findBySongNameAndSongArtist(String songName, String songArtist);
}
