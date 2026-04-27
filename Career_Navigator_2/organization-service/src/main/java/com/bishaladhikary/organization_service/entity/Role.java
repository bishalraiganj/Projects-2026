package com.bishaladhikary.organization_service.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "roles")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Role {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;

	private String name;

	@ElementCollection(fetch = FetchType.LAZY)
	private Set<Long> skills;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="department_id")
	@JsonBackReference
	private Department department;
}
