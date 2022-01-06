package com.gboss.idea_world.service;

import com.gboss.idea_world.entity.Idea;
import com.gboss.idea_world.exception.InvalidInputException;
import com.gboss.idea_world.repository.IdeaRepository;
import com.gboss.idea_world.repository.UserRepository;
import com.gboss.idea_world.util.ValidationUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IdeaService {


    @Autowired
    private IdeaRepository ideaRepository;


    //mark it autowired
    //create an instance of AppointmentRepository called ideaRepository

    @Autowired
    private UserRepository userRepository;


    public String createIdea(Idea idea) throws InvalidInputException {
        ValidationUtils.validate(idea);
        ideaRepository.save(idea);
        return idea.getIdeaId();
    }


    public List<Idea> getIdeasForUser(String userId) {
        return ideaRepository.findByUserId(userId);
    }

}
