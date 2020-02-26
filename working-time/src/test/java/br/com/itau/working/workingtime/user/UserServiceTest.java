package br.com.itau.working.workingtime.user;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = UserService.class)
public class UserServiceTest {
	@MockBean
	private UserRepository userRepo;
	
	private List<User> usersList = new ArrayList<User>();
	
	private User userOne;
	private User anotherUser;
	
	@Before
	public void setup() {
		userOne = new User();
		userOne.setUserId(1);
		userOne.setName("User One");
		userOne.setCpf("12345678910");
		userOne.setEmail("userone@testing.com");
		userOne.setCreated(new Date());
		
		usersList.add(userOne);
		
		anotherUser = new User();
		anotherUser.setUserId(2);
		anotherUser.setName("User Two");
		anotherUser.setCpf("10987654321");
		anotherUser.setEmail("usertwo@testing.com");
		anotherUser.setCreated(new Date());
		
		usersList.add(anotherUser);
	}
	
	@Test
	public void testListAllExistingUsers() {
		when(userRepo.findAll()).thenReturn(usersList);
		
		List<User> users = userRepo.findAll();
		
		assertTrue(users.size() == usersList.size());
		assertEquals(users.get(0).getUserId(), users.get(0).getUserId());
		assertEquals(users.get(0).getCpf(), users.get(0).getCpf());
		assertEquals(users.get(1).getUserId(), users.get(1).getUserId());
		assertEquals(users.get(1).getCpf(), users.get(1).getCpf());

	}
	
	@Test
	public void testFindExistingUserById() {
		when(userRepo.findById(Mockito.anyInt())).thenReturn(Optional.of(userOne));
		
		User user = userRepo.findById(userOne.getUserId()).orElse(new User());
		
		assertEquals(userOne.getUserId(), user.getUserId());
		assertEquals(userOne.getCpf(), user.getCpf());
		assertEquals(userOne.getCreated(), user.getCreated());
	}
	
	@Test
	public void testSaveNewUser() {
		when(userRepo.save(Mockito.any(User.class))).thenReturn(userOne);
		
		User user = userRepo.save(userOne);
		
		assertEquals(userOne.getUserId(), user.getUserId());
		assertEquals(userOne.getCpf(), user.getCpf());
		assertEquals(userOne.getCreated(), user.getCreated());
	}
}
