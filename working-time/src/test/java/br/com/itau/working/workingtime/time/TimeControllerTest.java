package br.com.itau.working.workingtime.time;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

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
@WebMvcTest(controllers = TimeController.class)
public class TimeControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private TimeService timeService;
	
	private ObjectMapper mapper = new ObjectMapper();

	private List<Time> timeList = new ArrayList<Time>();
	
	private Time starting;
	private Time finishing;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		
		starting = new Time();
		starting.setTimeId(0);
		starting.setUserId(0);
		starting.setType(TimeEventType.STARTING);
		
		timeList.add(starting);
		
		finishing = new Time();
		finishing.setTimeId(1);
		finishing.setUserId(0);
		finishing.setType(TimeEventType.FINISHING);
		
		timeList.add(finishing);
	}
	
	@Test
	public void testSaveNewWorkTime_StartingADay() throws Exception {
		when(timeService.save(Mockito.any(Time.class))).thenReturn(starting);
		
		String contentJson = mapper.writeValueAsString(starting);
		
		mockMvc.perform(post("/time/save")
				.contentType(MediaType.APPLICATION_JSON)
				.content(contentJson))
//			.andDo(print())
			.andExpect(status().isCreated());
	}
}