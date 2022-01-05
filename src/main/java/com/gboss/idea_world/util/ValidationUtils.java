package com.gboss.idea_world.util;

import com.gboss.idea_world.entity.Idea;
import com.gboss.idea_world.entity.User;
import com.gboss.idea_world.exception.InvalidInputException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Component
public class ValidationUtils {

	public static void validate(User user) throws InvalidInputException {
		List<String> errorFields = new ArrayList<>();
		if (user.getEmailId() == null || !user.getEmailId().matches("^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")) {
			errorFields.add("Email Id");
		}

		if (user.getMobile() == null || !user.getMobile().matches("^\\d{10}$")) {
			errorFields.add("Mobile");
		}

		if (user.getFirstName() == null || !user.getFirstName().matches("^[a-zA-Z\\\\s]{1,10}$")) {
			errorFields.add("First Name");
		}
		if (user.getLastName() == null || !user.getLastName().matches("^[a-zA-Z\\\\s]{1,10}$")) {
			errorFields.add("Last Name");
		}
		if (errorFields.size() > 0) throw new InvalidInputException(errorFields);
	}



	public static boolean isValid(String dateStr) {
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.setLenient(false);
		try {
			sdf.parse(dateStr);
		} catch (ParseException e) {
			return false;
		}
		return true;
	}


	public static void validate(Idea idea) throws InvalidInputException {
		List<String> errorFields = new ArrayList<>();


		if (StringUtils.isEmpty(idea.getIdeaName())) {
			errorFields.add("Idea Name");
		}

		if (StringUtils.isEmpty(idea.getUserId())) {
			errorFields.add("User ID");
		}

		if (StringUtils.isEmpty(idea.getIdeaDesc())) {
			errorFields.add("Idea Descripation");
		}

		if (StringUtils.isEmpty(idea.getIdeaFieldName())) {
			errorFields.add("Idea Field Name");
		}


		if (errorFields.size() > 0) throw new InvalidInputException(errorFields);
	}

}
