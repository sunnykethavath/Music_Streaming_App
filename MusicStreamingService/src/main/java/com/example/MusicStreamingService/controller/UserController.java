package com.example.MusicStreamingService.controller;

import com.example.MusicStreamingService.dto.AdminSignInInputDto;
import com.example.MusicStreamingService.dto.AuthenticationInputDto;
import com.example.MusicStreamingService.dto.UserSignInInputDto;
import com.example.MusicStreamingService.model.Admin;
import com.example.MusicStreamingService.model.PlayList;
import com.example.MusicStreamingService.model.Song;
import com.example.MusicStreamingService.model.User;
import com.example.MusicStreamingService.service.PlayListService;
import com.example.MusicStreamingService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    PlayListService playListService;

    //sign up
    @PostMapping("/signUp/user")
    public String signUpUser(@RequestBody User user) throws NoSuchAlgorithmException {
        return userService.signUpUser(user);
    }

    //sign in
    @PostMapping("/signIn/user")
    public String signInUser(@RequestBody UserSignInInputDto userSignInInputDto) throws NoSuchAlgorithmException{
        return userService.signInUser(userSignInInputDto);
    }

    //sign out
    @DeleteMapping("/signOut/user")
    public String signOutUser(@RequestBody AuthenticationInputDto authInfo){
        return userService.signOutUser(authInfo);
    }

    //create playlist
    @PostMapping("/playlist")
    public String createPlayList(@RequestParam String userEmail, @RequestParam String tokenValue, @RequestBody PlayList playList){
        return playListService.createPlayList(userEmail, tokenValue, playList);
    }

    //get playlist
    @GetMapping("/playlists")
    public List<PlayList> getPlayLists(@RequestParam String userEmail, @RequestParam String tokenValue){
        return playListService.getPlayLists(userEmail, tokenValue);
    }

    //delete playlist
    @DeleteMapping("/playlist/{id}")
    public String deletePlayList(@RequestParam String userEmail, @RequestParam String tokenValue, @PathVariable Integer id){
        return playListService.deletePlayList(userEmail, tokenValue, id);
    }

    //add song into playlist
    @PutMapping("/playlist/add/{playlistId}")
    public String addSong(@RequestParam String userEmail, @RequestParam String tokenValue, @RequestParam Integer songId, @PathVariable Integer playlistId){
        return playListService.addSong(userEmail, tokenValue, songId, playlistId);
    }

    //remove song from playlist
    @DeleteMapping("/playlist/remove/{playlistId}")
    public String removeSong(@RequestParam String userEmail, @RequestParam String tokenValue, @RequestParam Integer songId, @PathVariable Integer playlistId){
        return playListService.removeSong(userEmail, tokenValue, songId, playlistId);
    }
}
