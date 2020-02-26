package br.com.itau.working.workingtime.user;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = UserController.class)
public class UserControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserService userService;
	
	private ObjectMapper mapper = new ObjectMapper();

	private List<User> usersList = new ArrayList<User>();
	
	private User anotherUser;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		
		User user = new User();
		user.setUserId(1);
		user.setName("User One");
		user.setCpf("12345678910");
		user.setEmail("userone@testing.com");
		user.setCreated(new Date());
		
		usersList.add(user);
		
		anotherUser = new User();
		anotherUser.setUserId(2);
		anotherUser.setName("User Two");
		anotherUser.setCpf("10987654321");
		anotherUser.setEmail("usertwo@testing.com");
		anotherUser.setCreated(new Date());
		
		usersList.add(anotherUser);
	}

	@Test
	public void testListAllExistingUsers() throws Exception {
		when(userService.listUsers()).thenReturn(usersList);

		mockMvc.perform(
				get("/users/list"))
//			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$[0].name", equalTo("User One")))
			.andExpect(jsonPath("$[1].name", equalTo("User Two")))
			.andExpect(jsonPath("$", hasSize(usersList.size())));
	}
	
	@Test
	public void testFindExistingUserById() throws Exception {
		when(userService.findOne(Mockito.anyInt())).thenReturn(Optional.of(anotherUser));
		
		mockMvc.perform(
				get("/users/" + anotherUser.getUserId()))
//			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.name", equalTo(anotherUser.getName())))
			.andExpect(jsonPath("$.email", equalTo(anotherUser.getEmail())));
	}
	
	@Test
	public void testFindNonExistingUserById() throws Exception {
		when(userService.findOne(Mockito.anyInt())).thenReturn(Optional.empty());
		
		mockMvc.perform(
				get("/users/" + anotherUser.getUserId()))
//			.andDo(print())
			.andExpect(status().isNotFound());
	}
	
	@Test
	public void testSaveNewUser() throws Exception {
		when(userService.save(Mockito.any(User.class))).thenReturn(anotherUser);
		
		String contentJson = mapper.writeValueAsString(anotherUser);
		
		mockMvc.perform(post("/users/save")
				.contentType(MediaType.APPLICATION_JSON)
				.content(contentJson))
//			.andDo(print())
			.andExpect(status().isCreated());
	}
	
	@Test
	public void testUpdateExistingUser() throws Exception {
		when(userService.save(Mockito.any(User.class))).thenReturn(anotherUser);
		
		String contentJson = mapper.writeValueAsString(anotherUser);
		
		mockMvc.perform(put("/users/save/" + anotherUser.getUserId(), anotherUser.getUserId())
				.contentType(MediaType.APPLICATION_JSON)
				.content(contentJson))
//			.andDo(print())
			.andExpect(status().isCreated());
	}
}