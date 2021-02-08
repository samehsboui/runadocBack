package com.app.runadoc.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.test.context.junit4.SpringRunner;

import com.app.runadoc.model.User;
import com.app.runadoc.repository.UserRepository;
import com.app.runadoc.service.UserService;

public class UserServiceImpTest {

	@RunWith(SpringRunner.class)
	public class UserServiceImplTest {

		@Autowired
		private UserService userService;

		@MockBean // création d'un mockBean pour UserRepository
		private UserRepository userRepository;

//		@Test
//		public void testSaveUser() throws Exception {
//			User user = new User("sameh sboui", "samehsboui", "samehsboui.enicar@gmail.com", "password", null, null);
//			User userMock = new User("sameh sboui", "samehsboui", "samehsboui.enicar@gmail.com", "password", null,
//					null);
//			Mockito.when(userRepository.save((user))).thenReturn(userMock);
//			User userSaved = userService.save(user);
//			assertNotNull(userSaved);
//			assertEquals(userMock.getId(), userSaved.getId());
//			assertEquals(userMock.getUsername(), userSaved.getUsername());
//			verify(userRepository).save(any(User.class));
//		}

//		@Test
//		public void testFindUserByUsername() {
//			User user = new User("sameh sboui", "samehsboui", "samehsboui.enicar@gmail.com", "password", null, null);
//			Mockito.when(userRepository.findByUsername(user.getUsername()).get()).thenReturn(user);
//			User userFromDB = userService.findByUsername(user.getUsername()).get();
//			;
//			assertNotNull(userFromDB);
//			assertThat(userFromDB.getUsername(), is(user.getUsername()));
//			verify(userRepository).findByUsername(any(String.class));
//		}

//		@Test
//		public void testUpdateUser() throws Exception {
//			User userToUpdate = new User("sameh sboui", "samehsboui", "samehsboui.enicar@gmail.com", "password", null,
//					null);
//			User userUpdated = new User("samer sboui", "samehsboui", "samehsboui.enicar@gmail.com", "password", null,
//					null);
//			Mockito.when(userRepository.save((userToUpdate))).thenReturn(userUpdated);
//			User userFromDB = userService.save(userToUpdate);
//			assertNotNull(userFromDB);
//			assertEquals(userUpdated.getUsername(), userFromDB.getUsername());
//			verify(userRepository).save(any(User.class));
//		}
	}

}
