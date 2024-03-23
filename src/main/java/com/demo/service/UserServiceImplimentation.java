package com.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.demo.dto.UserDto;
import com.demo.exceptions.UserException;
import com.demo.modal.User;
import com.demo.repository.UserRepository;

public class UserServiceImplimentation implements UserService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public User registerUser(User user) throws UserException {
		Optional<User> ifEmailExist = userRepository.findByEmail(user.getEmail());
		if (ifEmailExist.isPresent()) {
			throw new UserException("Email Is Already Exist");
		}
		Optional<User> ifUsernameExist = userRepository.findByUsername(user.getUsername());
		if (ifUsernameExist.isPresent()) {
			throw new UserException("Username Is Already Taken...");
		}
		if (user.getEmail() == null || user.getPassword() == null || user.getUserimage() == null
				|| user.getName() == null) {
			throw new UserException("All Fields are required");
		}
		User newUser = new User();
		newUser.setEmail(user.getEmail());
		newUser.setPassword(user.getPassword());
		newUser.setUsername(user.getUsername());
		newUser.setName(user.getName());
		return userRepository.save(newUser);
	}

	@Override
	public User findUserById(Integer userId) throws UserException {
		Optional<User> opt = userRepository.findById(userId);
		if (opt.isPresent()) {
			return opt.get();
		}
		throw new UserException("User Not Exist With ID : " + userId);
	}

	@Override
	public User findUserProfile(String token) throws UserException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findUserByUsername(String username) throws UserException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String followUser(Integer reqUserId, Integer followUserId) throws UserException {
		User reqUser = findUserById(reqUserId);
		User followUser = findUserById(followUserId);

		UserDto follower = new UserDto();
		follower.setEmail(reqUser.getEmail());
		follower.setId(reqUser.getId());
		follower.setName(reqUser.getUsername());
		follower.setUserimage(reqUser.getUserimage());
		follower.setUsername(reqUser.getUsername());

		UserDto following = new UserDto();
		following.setEmail(follower.getEmail());
		following.setId(follower.getId());
		following.setUserimage(follower.getUserimage());
		following.setName(follower.getName());
		following.setUsername(follower.getUsername());

		reqUser.getFollowing().add(following);
		followUser.getFollower().add(follower);

		userRepository.save(reqUser);
		userRepository.save(followUser);
		return "You Are Following " + following.getUsername();
	}

	@Override
	public String unFollowUser(Integer reqUserId, Integer followUserId) throws UserException {
		User reqUser = findUserById(reqUserId);
		User followUser = findUserById(followUserId);

		UserDto follower = new UserDto();
		follower.setEmail(reqUser.getEmail());
		follower.setId(reqUser.getId());
		follower.setName(reqUser.getUsername());
		follower.setUserimage(reqUser.getUserimage());
		follower.setUsername(reqUser.getUsername());

		UserDto following = new UserDto();
		following.setEmail(follower.getEmail());
		following.setId(follower.getId());
		following.setUserimage(follower.getUserimage());
		following.setName(follower.getName());
		following.setUsername(follower.getUsername());

		reqUser.getFollowing().remove(following);
		followUser.getFollower().remove(follower);

		userRepository.save(reqUser);
		userRepository.save(followUser);
		return "You have unfollowed " + followUser.getUsername();
	}

	@Override
	public List<User> findUserByIds(List<Integer> userIds) throws UserException {
		List<User> users = userRepository.findAllUsersByUserIds(userIds);
		return users;
	}

	@Override
	public List<User> searchUser(String query) throws UserException {
		List<User> users = userRepository.findByQuery(query);
		if(users.size() == 0) {
			throw new UserException("User Not Found");
		}
		return users;
	}

	@Override
	public User updateUserDetails(User updatedUser, User existingUser) throws UserException {
        if(updatedUser.getEmail() != null) {
        	existingUser.setEmail(updatedUser.getEmail());
        }
//        if(updatedUser.getBio())
		
		return null;
	}

}
