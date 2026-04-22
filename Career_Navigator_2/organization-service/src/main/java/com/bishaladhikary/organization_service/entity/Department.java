package com.bishaladhikary.organization_service.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;

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
	private HashSet<Long> employees = new LinkedHashSet<>();

	@ManyToMany
	@JoinTable(
			name = "department_roles",
			joinColumns = @JoinColumn(name = "department_id"),
			inverseJoinColumns = @JoinColumn(name = "role_id")
	)
	private HashSet<Role> roles;
}
