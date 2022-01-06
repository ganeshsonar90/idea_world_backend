package com.gboss.idea_world.controller;

import com.gboss.idea_world.entity.User;
import com.gboss.idea_world.exception.InvalidInputException;
import com.gboss.idea_world.service.IdeaService;
import com.gboss.idea_world.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/users")
public class UserAdminController {

	@Autowired
	private UserService userService;

	@Autowired
	private IdeaService ideaService;



	@GetMapping(path = "/{id}")
	public ResponseEntity<User> getUser(@RequestHeader("authorization") String accessToken,
	                                    @PathVariable("id") final String userUuid) {
		final User User = userService.getUser(userUuid);
		return ResponseEntity.ok(User);
	}
	
	//create a post method named createUser with return type as ResponseEntity
		//define the method parameter user of type User. Set it final. Use @RequestBody for mapping.
		//declare InvalidInputException using throws keyword
		
		//register the user
	
		//return http response with status set to OK
	

	@PostMapping(path = "/register")
	public ResponseEntity<User> createUser(@RequestBody final User user) throws InvalidInputException {
		final User createdUser = userService.register(user);
		return ResponseEntity.status(HttpStatus.OK).body(createdUser);
	}



	@GetMapping("/{userId}/ideas")
	public ResponseEntity getIdeasForUser(@PathVariable("userId") String userId) {
		return ResponseEntity.ok(ideaService.getIdeasForUser(userId));
	}





}
