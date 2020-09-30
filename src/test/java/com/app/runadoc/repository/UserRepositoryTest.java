package com.app.runadoc.repository;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;


import static org.hamcrest.CoreMatchers.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.app.runadoc.model.User;
import com.app.runadoc.repository.UserRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;
	@Autowired
	private UserRepository userRepository;
	User user = new User("sameh sboui", "samehsboui", "samehsboui.enicar@gmail.com", "password", null, null);

	@Before
	public void setup() {
		entityManager.persist(user);
		entityManager.flush();
	}

	@Test
	public void testSaveUser() {
		User user = new User("sameh sboui", "samehsboui", "samehsboui.enicar@gmail.com", "password", null, null);
		User userSaved = userRepository.save(user);
		assertNotNull(userSaved.getId());
		assertThat("samehsboui", is(userSaved.getUsername()));
	}

	@Test
	public void testFindByUsername() {
		User userFromDB = userRepository.findByUsername("samehsboui").get();
		assertThat("samehsboui", is(userFromDB.getUsername()));
	}

	@Test
	public void testUpdateUser() {
		User userToUpdate = userRepository.findByUsername(user.getUsername()).get();
		userToUpdate.setFullName("Sameh Sameh");
		userRepository.save(userToUpdate);
		User userUpdatedFromDB = userRepository.findByUsername(userToUpdate.getUsername()).get();
		assertNotNull(userUpdatedFromDB);
		assertThat("Sameh Sameh", is(userUpdatedFromDB.getFullName()));
	}
}