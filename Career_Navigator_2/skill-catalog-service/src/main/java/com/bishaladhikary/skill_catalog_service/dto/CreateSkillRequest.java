package com.bishaladhikary.skill_catalog_service.dto;

import com.bishaladhikary.skill_catalog_service.entitiy.enums.SkillCategory;

public class CreateSkillRequest {

	private String name;

	private String description;

	private SkillCategory skillCategory;

	private int minLevel;

	private int maxLevel;


	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public SkillCategory getSkillCategory() {
		return skillCategory;
	}

	public int getMinLevel() {
		return minLevel;
	}

	public int getMaxLevel() {
		return maxLevel;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setSkillCategory(SkillCategory skillCategory) {
		this.skillCategory = skillCategory;
	}

	public void setMinLevel(int minLevel) {
		this.minLevel = minLevel;
	}

	public void setMaxLevel(int maxLevel) {
		this.maxLevel = maxLevel;
	}
}
