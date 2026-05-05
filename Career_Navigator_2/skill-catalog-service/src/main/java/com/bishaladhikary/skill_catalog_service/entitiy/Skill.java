package com.bishaladhikary.skill_catalog_service.entitiy;

import com.bishaladhikary.skill_catalog_service.entitiy.enums.SkillCategory;
import jakarta.persistence.*;


@Entity
@Table(name="skills")
public class Skill {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;

	@Column( unique = true, nullable = false)
	private String name;

	private String description;

	@Enumerated(EnumType.STRING)
	private SkillCategory category;

	@Column( nullable = false)
	private int minLevel;

	@Column(nullable = false)
	private int maxLevel;

	private boolean active;


	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public SkillCategory getCategory() {
		return category;
	}

	public int getMinLevel() {
		return minLevel;
	}

	public int getMaxLevel() {
		return maxLevel;
	}

	public boolean isActive() {
		return active;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setCategory(SkillCategory category) {
		this.category = category;
	}

	public void setMinLevel(int minLevel) {
		this.minLevel = minLevel;
	}

	public void setMaxLevel(int maxLevel) {
		this.maxLevel = maxLevel;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
}
