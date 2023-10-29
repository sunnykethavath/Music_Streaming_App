package com.example.MusicStreamingService.service;

import com.example.MusicStreamingService.dto.AuthenticationInputDto;
import com.example.MusicStreamingService.model.PlayList;
import com.example.MusicStreamingService.model.Song;
import com.example.MusicStreamingService.model.User;
import com.example.MusicStreamingService.repo.IPlayListRepo;
import com.example.MusicStreamingService.repo.ISongRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class PlayListService {

    @Autowired
    IPlayListRepo playListRepo;

    @Autowired
    AuthenticationTokenService authenticationTokenService;

    @Autowired
    ISongRepo songRepo;

    @Autowired
    UserService userService;

    public String createPlayList(String userEmail, String tokenValue, PlayList playList) {
        if(authenticationTokenService.userAuthenticate(userEmail, tokenValue)){
            User user = userService.userRepo.findFirstByUserEmail(userEmail);
            playList.setUser(user);
            playListRepo.save(playList);
            return "playlist created";
        }
        return "Unauthorised access";
    }
    
    public List<PlayList> getPlayLists(String userEmail, String tokenValue) {
        List<PlayList> playlist = new ArrayList<>();
        if(authenticationTokenService.userAuthenticate(userEmail, tokenValue)){
            playlist = playListRepo.findAll();
        }
        return playlist;
    }

    public String deletePlayList(String userEmail, String tokenValue, Integer id) {
        if(authenticationTokenService.userAuthenticate(userEmail, tokenValue)){
            playListRepo.deleteById(id);
            return "playlist deleted";
        }
        return "Unauthorised Access";
    }

    public String addSong(String userEmail, String tokenValue, Integer songId, Integer playlistId) {
        if(authenticationTokenService.userAuthenticate(userEmail, tokenValue)){
            Optional<PlayList> playList = playListRepo.findById(playlistId);
            Optional<Song> song = songRepo.findById(songId);
                List<Song> plSong = playList.get().getSongs();
                for(Song song1 : plSong){
                    if(song1.getSongID().equals(songId)){
                        return "song already exists in playlist";
                    }
                }
            playList.get().getSongs().add(song.get());
            return "song added";
        }
        return "Unauthorised Access";
    }

    public String removeSong(String userEmail, String tokenValue, Integer songId, Integer playlistId) {
        if(authenticationTokenService.userAuthenticate(userEmail, tokenValue)){
            Optional<PlayList> playList = playListRepo.findById(playlistId);
            List<Song> plSongs = playList.get().getSongs();
            for(Song song : plSongs){
                if(song.getSongID().equals(songId)){
                    plSongs.remove(song);
                    return "song is successfully removed from playlist";
                }
            }
            return "song doesn't exist";
        }
        return "Unauthorised Access";
    }
}
