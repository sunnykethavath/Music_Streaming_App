package com.example.MusicStreamingService.repo;

import com.example.MusicStreamingService.model.PlayList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPlayListRepo extends JpaRepository<PlayList,Integer> {
}
