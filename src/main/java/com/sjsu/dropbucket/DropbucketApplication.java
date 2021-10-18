package com.sjsu.dropbucket;

import com.sjsu.dropbucket.dao.AppUserRepository;
import com.sjsu.dropbucket.dao.FileRepository;
import com.sjsu.dropbucket.model.AppUser;
import com.sjsu.dropbucket.model.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication(

)
public class DropbucketApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DropbucketApplication.class, args);
	}

	@Autowired
	private AppUserRepository userRepository;

	@Autowired
	private FileRepository fileRepository;

	@Override
	public void run(String... args) throws Exception {
		System.out.println("APP STARTED . . .");
//		Long id =1L;
//		AppUser user = userRepository.findByUserId(id);
////		AppUser user = new AppUser();
////		user.setEmail("lala@gmail.com");
////		user.setPassword("123");
////		user.setFirstName("Satish");
////		user.setLastName("Patel");
////		user.setUserType("U");
////
//		System.out.println(user.getEmail());
//		File file1 = new File();
//		file1.setFileDescription("desc3");
//		file1.setFileName("abc3.txt");
//		file1.setFileURL("asd3.com");
//		file1.setIsDeleted(false);
//		file1.setModifiedTime(new Date(System.currentTimeMillis()));
//		file1.setPublicationTime(new Date(System.currentTimeMillis()));
//		user.getFiles().add(file1);
//		this.userRepository.save(user);
//
//
//		File file2 = new File();
//		file2.setFileDescription("desc2");
//		file2.setFileName("abc2.txt");
//		file2.setFileURL("asd2.com");
//		file2.setIsDeleted(false);
//		file2.setModifiedTime(new Date(System.currentTimeMillis()));
//		file2.setPublicationTime(new Date(System.currentTimeMillis()));
//
//		user.getFiles().add(file1);
//		user.getFiles().add(file2);
//		this.userRepository.save(user);


	}
}
