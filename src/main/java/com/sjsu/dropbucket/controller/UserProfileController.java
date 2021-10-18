package com.sjsu.dropbucket.controller;

import com.sjsu.dropbucket.model.AppUser;
import com.sjsu.dropbucket.model.File;
import com.sjsu.dropbucket.service.FileService;
import com.sjsu.dropbucket.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("api/v1/")
@CrossOrigin("*")
public class UserProfileController {

    private final UserService userService;
    private final FileService fileService;

    @Autowired
    public UserProfileController(UserService userService, FileService fileService) {
        this.userService = userService;
        this.fileService = fileService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<AppUser>> getUserProfiles() {
        return ResponseEntity.ok().body(userService.getAllUsers());
    }

    @GetMapping("/getFiles")
    public ResponseEntity<List<File>> getFilesById(@RequestHeader("userId") Long userId) {
        return ResponseEntity.ok().body(userService.getAllFilesByUserID(userId));
    }

    @PostMapping(
            path = "/file/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public void uploadFile(@RequestParam("file") MultipartFile file,
                                       @RequestParam("fileDescription") String fileDescription,
                                       @RequestParam("userId") Long userId) {
        fileService.uploadFile(userId, file, fileDescription);
    }

    @GetMapping("/file/download")
    public byte[] downloadFile(@RequestHeader("userId") Long userId,
                                           @RequestHeader("fileId") Long fileId) {
        return fileService.downloadFile(userId, fileId);
    }

    @GetMapping("/file/delete")
    public void deleteFile(@RequestHeader("userId") Long userId,
                                           @RequestHeader("fileId") Long fileId) {
        fileService.deleteFile(userId, fileId);
    }
}
