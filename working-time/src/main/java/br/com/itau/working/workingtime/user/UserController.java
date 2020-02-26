package br.com.itau.working.workingtime.user;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	private UserService userService;

	@GetMapping("/list")
	@ResponseStatus(code = HttpStatus.OK)
	public List<User> listUsers() {
		return userService.listUsers();
	}

	@GetMapping("/{id}")
	public Resource<User> findUserById(@PathVariable Integer id) {
		User user = userService.findOne(id).orElseThrow(() -> new UserNotFoundException("id-" + id));

		Resource<User> resource = new Resource<User>(user);
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).listUsers());
		resource.add(linkTo.withRel("all-users"));

		return resource;
	}

	@PostMapping("/save")
	@ResponseStatus(code = HttpStatus.CREATED)
	public User saveUser(@Valid @RequestBody User user) {
		return userService.save(user);
	}
	
	@PutMapping("/save/{id}")
	@ResponseStatus(code = HttpStatus.CREATED)
	public User updateUser(@Valid @RequestBody User user, @PathVariable Integer id) {
		user.setUserId(id);

		return userService.save(user);
	}
}