package com.sjsu.dropbucket.service;

import com.sjsu.dropbucket.config.bucket.BucketName;
import com.sjsu.dropbucket.dao.FileRepository;
import com.sjsu.dropbucket.model.AppUser;
import com.sjsu.dropbucket.model.File;
import org.apache.http.entity.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
public class FileService {

    private final UserService userService;
    private final FileStore fileStore;
    private final FileRepository fileRepo;

    @Autowired
    public FileService(FileRepository fileRepo,FileStore fileStore, UserService userService) {
        this.fileStore = fileStore;
        this.userService = userService;
        this.fileRepo = fileRepo;
    }

    public void uploadFile(Long userId, MultipartFile file,String fileDescription) {
        // 1. Check if image is not empty
        isFileEmpty(file);
        // 2. Get users
        AppUser user = userService.getUserById(userId);
        // 3. Grab some metadata from file if any
        Map<String, String> metadata = extractMetadata(file);

        // 4. Store the image in s3 and update database (userProfileImageLink) with s3 image link
        String path = String.format("%s/%s", BucketName.DROP_BUCKET.getBucketName(), userId);
        String generatedFileName = String.format("%s_%s", System.currentTimeMillis(),file.getOriginalFilename());

        System.out.println("FILEPATH : "+path);
        System.out.println("FILENAME : "+generatedFileName);

        File fileObj = new File();
        fileObj.setFileDescription(fileDescription);
        fileObj.setFileName(generatedFileName);
        fileObj.setFileURL(userId+"/"+generatedFileName);
        fileObj.setIsDeleted(false);
        fileObj.setModifiedTime(new Date(System.currentTimeMillis()));
        fileObj.setPublicationTime(new Date(System.currentTimeMillis()));
        System.out.println("FILEURL"+fileObj.getFileURL());
        user.getFiles().add(fileObj);

        try {
            fileStore.save(path, generatedFileName, Optional.of(metadata), file.getInputStream());
            userService.saveUser(user);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public byte[] downloadFile(Long userId,Long fileId) {
        AppUser user = userService.getUserById(userId);
        File file = user.getFiles().stream().filter(f -> f.getFileId()==fileId).findFirst().get();
        String path = file.getFileURL();

        return  fileStore.download(BucketName.DROP_BUCKET.getBucketName(),path);
    }

    public List<File> getAllFiles() {
        return fileRepo.findAll();
    }

    public void deleteFile(Long userId,Long fileId){
        //fetch user
        AppUser user = userService.getUserById(userId);
        //fetch file
        File file = user.getFiles().stream().filter(f -> f.getFileId()==fileId).findFirst().get();
        String path = file.getFileURL();
        //delete from the s3
        fileStore.delete(BucketName.DROP_BUCKET.getBucketName(),path);
        //update delete flag
        fileRepo.deleteFileByFileId(fileId);
    }
    private Map<String, String> extractMetadata(MultipartFile file) {
        Map<String, String> metadata = new HashMap<>();
        metadata.put("Content-Type", file.getContentType());
        metadata.put("Content-Length", String.valueOf(file.getSize()));
        return metadata;
    }

    private void isFileEmpty(MultipartFile file) {
        if (file.isEmpty()) {
            throw new IllegalStateException("Cannot upload empty file [ " + file.getSize() + "]");
        }
    }

    private void isImage(MultipartFile file) {
        if (!Arrays.asList(
                ContentType.IMAGE_JPEG.getMimeType(),
                ContentType.IMAGE_PNG.getMimeType(),
                ContentType.IMAGE_GIF.getMimeType()).contains(file.getContentType())) {
            throw new IllegalStateException("File must be an image [" + file.getContentType() + "]");
        }
    }



}
