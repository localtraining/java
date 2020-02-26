package br.com.itau.working.workingtime.time;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TimeService {
	@Autowired
	private TimeRepository timeRepo;
	
	public Time save(Time time) {
		timeRepo.save(time);
		
		return time;
	}
}