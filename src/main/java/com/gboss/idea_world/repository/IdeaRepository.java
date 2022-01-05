package com.gboss.idea_world.repository;


import com.gboss.idea_world.entity.Idea;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IdeaRepository extends CrudRepository<Idea, String> {

	public List<Idea> findByUserId(String userId);


}
