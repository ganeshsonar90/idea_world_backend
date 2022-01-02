package com.gboss.idea_world.service;


import com.gboss.idea_world.entity.User;
import com.gboss.idea_world.exception.InvalidInputException;
import com.gboss.idea_world.exception.ResourceUnAvailableException;
import com.gboss.idea_world.provider.PasswordCryptographyProvider;
import com.gboss.idea_world.repository.UserRepository;
import com.gboss.idea_world.util.ValidationUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
	private final UserRepository userRepository;

	@Autowired
	private PasswordCryptographyProvider passwordCryptographyProvider;


	public User register(User user) throws InvalidInputException {
		ValidationUtils.validate(user);

		user.setCreatedDate(LocalDate.now().toString());
		encryptPassword(user);
		userRepository.save(user);
		return user;
	}

	public User getUser(String id) {
		return Optional.ofNullable(userRepository.findById(id))
				.get()
				.orElseThrow(ResourceUnAvailableException::new);
	}

	//create a method named getAllUsers that returns a List of type User
		//return all the users from the database

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	private void encryptPassword(final User newUser) {

		String password = newUser.getPassword();
		final String[] encryptedData = passwordCryptographyProvider.encrypt(password);
		newUser.setSalt(encryptedData[0]);
		newUser.setPassword(encryptedData[1]);
	}
}
