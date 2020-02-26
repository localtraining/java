package br.com.itau.working.workingtime.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {
	private static final long serialVersionUID = -7221292624874817878L;

	public UserNotFoundException(String message) {
		super(message);
	}
}
