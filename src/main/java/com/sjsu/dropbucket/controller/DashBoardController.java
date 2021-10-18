package com.sjsu.dropbucket.controller;

import com.sjsu.dropbucket.model.File;
import com.sjsu.dropbucket.service.FileService;
import com.sjsu.dropbucket.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/dashboard")
@CrossOrigin("*")
public class DashBoardController {

    private final UserService userService;
    private final FileService fileService;

    @Autowired
    public DashBoardController(UserService userService, FileService fileService) {
        this.userService = userService;
        this.fileService = fileService;
    }

    @GetMapping("/getAllFiles")
    public ResponseEntity<List<File>> getFilesById() {
        return ResponseEntity.ok().body(fileService.getAllFiles());
    }
}
