package com.gboss.idea_world.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Idea {
	@Id
	private String ideaId = UUID.randomUUID().toString();
	private String ideaName;
	private String ideaDesc;
	private String ideaFieldName;
	private String userId;
	@JsonIgnore
	private String createdDate;

}
