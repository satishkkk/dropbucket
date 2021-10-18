package com.sjsu.dropbucket.dao;

import com.sjsu.dropbucket.model.AppUser;
import com.sjsu.dropbucket.model.File;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;

import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)

public class FileRepositoryTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private FileRepository fileRepo;

    @Autowired
    private AppUserRepository userRepo;

//    @Test
//    public void testCreateFile() {
//        Optional<AppUser> user = userRepo.findById(1L);
//
//        if(user.isPresent()){
//            File file = new File();
////            file.setUserId(user.get());
//            file.setFileDescription("desc");
//            file.setFileName("abc.txt");
//            file.setFileURL("asd.com");
//            file.setIsDeleted(false);
//            file.setModifiedTime(new Date(System.currentTimeMillis()));
//            file.setPublicationTime(new Date(System.currentTimeMillis()));
//
//            File file1 = fileRepo.save(file);
//            File existFile = entityManager.find(File.class, file1.getFileId());
//            assertThat(file.getFileURL()).isEqualTo(existFile.getFileURL());
//        }
//    }
}
