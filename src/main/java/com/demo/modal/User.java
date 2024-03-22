package com.demo.modal;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.demo.dto.UserDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {
     @Id
     @GeneratedValue(strategy = GenerationType.AUTO)
     private Integer id;
     private String username;
     private String email;
     private String mobile;
     private String website;
     private String bio;
     private String gender;
     private String userimage;
     private String name;
     private String password;
     
     private Set<UserDto> follower = new HashSet<UserDto>();
     private Set<UserDto> following = new HashSet<UserDto>();
     
     private List<Story> stories = new ArrayList<Story>();
     
     private List<Post> savedPost = new ArrayList<Post>();
}
