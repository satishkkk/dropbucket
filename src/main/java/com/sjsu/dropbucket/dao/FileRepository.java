package com.sjsu.dropbucket.dao;

import com.sjsu.dropbucket.model.AppUser;
import com.sjsu.dropbucket.model.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileRepository extends JpaRepository<File,Long> {
    List<File> findByfileId(Long id);

    int deleteFileByFileId(Long id);
}
