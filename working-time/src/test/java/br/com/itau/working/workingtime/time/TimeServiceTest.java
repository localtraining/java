package br.com.itau.working.workingtime.time;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TimeService.class)
public class TimeServiceTest {
	@MockBean
	private TimeRepository timeRepo;
	
	private Time time = new Time();
	
	@Before
	public void setup() {
		time.setTimeId(1);
		time.setUserId(0);
		time.setType(TimeEventType.FINISHING);
	}
	
	@Test
	public void testSaveNewUser() {
		when(timeRepo.save(Mockito.any(Time.class))).thenReturn(time);
		
		Time saved = timeRepo.save(time);
		
		assertEquals(time.getUserId(), saved.getUserId());
		assertEquals(time.getType(), saved.getType());
	}
}
