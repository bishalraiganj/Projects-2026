package com.bishaladhikary.skill_catalog_service.service;

import com.bishaladhikary.skill_catalog_service.dto.CreateSkillRequest;
import com.bishaladhikary.skill_catalog_service.dto.CreateSkillResponse;
import com.bishaladhikary.skill_catalog_service.dto.SkillResponse;
import com.bishaladhikary.skill_catalog_service.entitiy.Skill;
import com.bishaladhikary.skill_catalog_service.entitiy.enums.SkillCategory;
import com.bishaladhikary.skill_catalog_service.repository.SkillCatalogRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillCatalogService {

	private SkillCatalogRepository repository;

	public SkillCatalogService(SkillCatalogRepository repository)
	{
		this.repository = repository;
	}

	public CreateSkillResponse createSkill(CreateSkillRequest request)
	{
		Skill skill = mapToSkill(request);

		Skill savedSkill = repository.save(skill);

		return mapToCreateSkillResponse(savedSkill);
	}


	public SkillResponse getSkill(Long id)
	{
		Skill fetchedSkill = repository.findById(id).get();
		SkillResponse response = mapToSkillResponse(fetchedSkill);
		return response;
	}

	public List<Skill> getAllSkills()
	{
		return repository.findAll();
	}

	public List<Skill> getSkillsByCategory(String category)
	{
		SkillCategory cg = SkillCategory.valueOf(category.trim());
		List<Skill> fetchedSkills =  repository.findByCategory(cg);
		return fetchedSkills;
	}

	public SkillResponse getSkillByName(String name)
	{
		Skill fetchedSkill = repository.findByName(name);
		SkillResponse response  = mapToSkillResponse(fetchedSkill);
		return response;
	}

	public Skill mapToSkill(CreateSkillRequest request)
	{
		Skill skill = new Skill();
		skill.setName(request.getName());
		skill.setDescription(request.getDescription());
		skill.setCategory(request.getSkillCategory());
		skill.setMinLevel(request.getMinLevel());
		skill.setMaxLevel(request.getMaxLevel());
		skill.setActive(true);
		return skill;
	}


	public CreateSkillResponse mapToCreateSkillResponse(Skill skill)
	{
		CreateSkillResponse response = new CreateSkillResponse();

		response.setId(skill.getId());
		response.setName(skill.getName());
		response.setDescription(skill.getDescription());
		response.setSkillCategory(skill.getCategory());
		response.setMinLevel(skill.getMinLevel());
		response.setMaxLevel(skill.getMaxLevel());
		response.setActive(skill.isActive());
		return response;
	}

	public SkillResponse mapToSkillResponse(Skill skill)
	{
		SkillResponse response = new SkillResponse();
		response.setId(skill.getId());
		response.setMinLevel(skill.getMinLevel());
		response.setMaxLevel(skill.getMaxLevel());
		return response;
	}


}
