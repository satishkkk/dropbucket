package com.sjsu.dropbucket.service;

import com.sjsu.dropbucket.dao.AppUserRepository;
import com.sjsu.dropbucket.dao.FileRepository;
import com.sjsu.dropbucket.model.AppUser;
import com.sjsu.dropbucket.model.File;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserService{

    private final AppUserRepository userRepo;

    public void saveUser(AppUser user) {
        log.info("Saving new user to the database {} " , user.getEmail());
        userRepo.save(user);
    }

    public AppUser getUserByEmail(String email) {
        log.info("get user by email {} ",email );
        return userRepo.findByEmail(email);
    }

    public AppUser getUserById(Long id) {
        return userRepo.findByUserId(id);
    }

    public List<File> getAllFilesByUserID(Long Id) {
        AppUser user = userRepo.findByUserId(Id);
        if(user!=null && user.getFiles().size()!=0) return user.getFiles();
        return new ArrayList<>();
    }

    public List<AppUser> getAllUsers() {
        log.info("get all user");
        return userRepo.findAll();
    }


}
