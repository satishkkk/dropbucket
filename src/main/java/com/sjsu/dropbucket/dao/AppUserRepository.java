package com.sjsu.dropbucket.dao;

import com.sjsu.dropbucket.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser,Long> {
    AppUser findByEmail(String email);
    AppUser findByUserId(Long id);
}
