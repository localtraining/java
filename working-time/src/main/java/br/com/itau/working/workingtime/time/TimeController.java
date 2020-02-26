package br.com.itau.working.workingtime.time;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/time")
public class TimeController {
	@Autowired
	private TimeService timeService;

	@PostMapping("/save")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Time saveUser(@Valid @RequestBody Time time) {
		return timeService.save(time);
	}
}