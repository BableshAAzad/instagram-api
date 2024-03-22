package com.demo.modal;

import java.time.LocalDateTime;

import com.demo.dto.UserDto;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "stories")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Story {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name = "id", column = @Column(name = "user_id")),
		@AttributeOverride(name = "email", column = @Column(name = "user_email")),
	})
	private UserDto user;
	@NotNull
	private String image;
	private String caption;
	private LocalDateTime timestamp;
}
