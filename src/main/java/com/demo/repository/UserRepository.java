package com.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.demo.modal.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	Optional<User> findByEmail(String email);
	
	Optional<User> findByUsername(String username);
	
	@Query("select u from User u where u.id in :users")
	List<User> findAllUsersByUserIds(@Param("users") List<Integer> userIds);
	
	@Query("select distinct u from User u where u.username like %:query% or u.email like %:query%")
	List<User> findByQuery(@Param("query") String query);
}
