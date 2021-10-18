package com.sjsu.dropbucket.dao;
import static org.assertj.core.api.Assertions.assertThat;

import com.sjsu.dropbucket.model.AppUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AppUserRepository repo;

//    @Test
//    public void testCreateUser() {
//        AppUser user = new AppUser();
//        user.setEmail("lala@gmail.com");
//        user.setPassword("123");
//        user.setFirstName("Satish");
//        user.setLastName("Patel");
//        user.setUserType("U");
//
//        AppUser savedUser = repo.save(user);
//
//        AppUser existUser = entityManager.find(AppUser.class, savedUser.getUserId());
//
//        assertThat(user.getEmail()).isEqualTo(existUser.getEmail());
//
//    }
}
