package com.demo.modal;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.demo.dto.UserDto;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@Embedded
	@AttributeOverrides({ @AttributeOverride(name = "id", column = @Column(name = "user_id")),
			@AttributeOverride(name = "email", column = @Column(name = "user_email")), })
	private UserDto user;
	private String content;
	@Embedded
	@ElementCollection
	private Set<UserDto> likedByUsers = new HashSet<UserDto>();
	private LocalDateTime createdAt;
}
