package com.demo.service;

import java.util.List;

import com.demo.exceptions.UserException;
import com.demo.modal.User;

public interface UserService {
	User registerUser(User user) throws UserException;

	User findUserById(Integer userId) throws UserException;

	User findUserProfile(String token) throws UserException;

	User findUserByUsername(String username) throws UserException;

	String followUser(Integer reqUserId, Integer followUserId) throws UserException;

	String unFollowUser(Integer reqUserId, Integer followUserId) throws UserException;

	List<User> findUserByIds(List<Integer> userIds) throws UserException;

	List<User> searchUser(String query) throws UserException;

	User updateUserDetails(User updatedUser, User existingUser) throws UserException;
}
