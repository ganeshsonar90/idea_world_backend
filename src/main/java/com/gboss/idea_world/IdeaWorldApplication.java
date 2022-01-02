package com.gboss.idea_world;

import com.gboss.idea_world.config.ApiConfiguration;
import com.gboss.idea_world.config.WebConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({ApiConfiguration.class, WebConfiguration.class})
public class IdeaWorldApplication {
	public static void main(String[] args) {
		SpringApplication.run(IdeaWorldApplication.class, args);
	}

}


