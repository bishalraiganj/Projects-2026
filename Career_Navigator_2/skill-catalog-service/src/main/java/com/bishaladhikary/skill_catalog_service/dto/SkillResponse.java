package com.bishaladhikary.skill_catalog_service.dto;

public class SkillResponse {

	private Long id;

	private int minLevel;

	private int maxLevel;

	public Long getId() {
		return id;
	}

	public int getMinLevel() {
		return minLevel;
	}

	public int getMaxLevel() {
		return maxLevel;
	}



	public void setId(Long id) {
		this.id = id;
	}
	public void setMinLevel(int minLevel) {
		this.minLevel = minLevel;
	}

	public void setMaxLevel(int maxLevel) {
		this.maxLevel = maxLevel;
	}
}
