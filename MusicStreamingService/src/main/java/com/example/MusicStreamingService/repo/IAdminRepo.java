package com.example.MusicStreamingService.repo;

import com.example.MusicStreamingService.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAdminRepo extends JpaRepository<Admin,Integer> {
    Admin findFirstByAdminEmail(String adminEmail);
}
