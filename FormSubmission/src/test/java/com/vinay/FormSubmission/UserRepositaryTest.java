package com.vinay.FormSubmission;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositaryTest {
	
	@Autowired
	private UserRepositary repo;
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	public void testCreateUser() {
	    User user = new User();
	    user.setEmail("sai@gmail.com");
	    user.setPassword("reddy2021");
	    user.setFirstname("vinay");
	    user.setLastname("Kumar");
	    User savedUser = repo.save(user);
	    User existUser = entityManager.find(User.class, savedUser.getId());
	    assertThat(user.getEmail()).isEqualTo(existUser.getEmail());
	}
	
	@Test
	public void testfindByEmail() {
		String email = "reddy@gmail.com";
		User user = repo.findByEmail(email);
		assertThat(user).isNotNull();
	}
}
