package com.example.MusicStreamingService.service;

import com.example.MusicStreamingService.model.Song;
import com.example.MusicStreamingService.repo.ISongRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SongService {

    @Autowired
    ISongRepo songRepo;
    public void addSong(Song song) {
        songRepo.save(song);
    }

    public String updateSong(Song song) {
        Song existingSong = songRepo.findBySongNameAndSongArtist(song.getSongName(), song.getSongArtist());
        if(existingSong != null){
            existingSong.setSongName(song.getSongName());
            existingSong.setSongArtist(song.getSongArtist());
            existingSong.setSongLink(song.getSongLink());
            existingSong.setGenre(song.getGenre());
            songRepo.save(existingSong);
            return "song is updated";
        }
        return song.getSongName() + "does not exist";
    }

    public String deleteSong(Integer id) {
        songRepo.deleteById(id);
        return "song successfully deleted";
    }
}
