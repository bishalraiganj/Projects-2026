package com.bishaladhikary.organization_service.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Department {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@ElementCollection(fetch = FetchType.EAGER)
	private Set<Long> employees = new HashSet<>();

//	@ManyToMany
//	@JoinTable(
//			name = "department_roles",
//			joinColumns = @JoinColumn(name = "department_id"),
//			inverseJoinColumns = @JoinColumn(name = "role_id")
//	)

	@OneToMany(mappedBy = "department", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	@JsonManagedReference
	private Set<Role> roles;


	public void addRole(Role role)
	{
		role.setDepartment(this);
		this.roles.add(role);
	}
}
