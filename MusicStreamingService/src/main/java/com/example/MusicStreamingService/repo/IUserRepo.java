package com.example.MusicStreamingService.repo;

import com.example.MusicStreamingService.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepo extends JpaRepository<User,Integer> {
    User findFirstByUserEmail(String email);
}
