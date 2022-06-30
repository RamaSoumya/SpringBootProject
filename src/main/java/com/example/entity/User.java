package com.example.entity;

//import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.example.request.CreateUserRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users", uniqueConstraints = {
		@UniqueConstraint(columnNames = {"username"}),
		@UniqueConstraint(columnNames = {"email"})
})
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name = "name")
	private String name;
	@Column(name = "username")
	private String username;
	@Column(name = "email")
	private String email;
	@Column(name = "password", length = 200)
	private String password;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//	@JoinTable(name = "users_roles", 
//		joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
//		//setting foreign key
//		inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
//	)
	private Set<Roles> roles;
	
	public User(CreateUserRequest createUserRequest) {
		this.id = createUserRequest.getId();
		this.email = createUserRequest.getEmail();
		this.name = createUserRequest.getName();
		this.password = createUserRequest.getPassword();
		this.username = createUserRequest.getUsername();
		this.roles = createUserRequest.getRoles();
	}
	
}
