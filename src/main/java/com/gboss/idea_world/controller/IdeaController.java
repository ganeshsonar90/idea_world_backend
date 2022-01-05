package com.gboss.idea_world.controller;

import com.gboss.idea_world.entity.Idea;
import com.gboss.idea_world.exception.InvalidInputException;
import com.gboss.idea_world.service.IdeaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ideas")
public class IdeaController {

	@Autowired
	private IdeaService ideaService;


	@PostMapping(path = "/createIdea")
	public ResponseEntity<String> createIdea(@RequestBody Idea idea) throws InvalidInputException {
		return ResponseEntity.ok(ideaService.createIdea(idea));
	}
	
	



}