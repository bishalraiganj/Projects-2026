package com.bishaladhikary.skill_catalog_service.controller;


import com.bishaladhikary.skill_catalog_service.dto.CreateSkillRequest;
import com.bishaladhikary.skill_catalog_service.dto.CreateSkillResponse;
import com.bishaladhikary.skill_catalog_service.dto.SkillResponse;
import com.bishaladhikary.skill_catalog_service.entitiy.Skill;
import com.bishaladhikary.skill_catalog_service.repository.SkillCatalogRepository;
import com.bishaladhikary.skill_catalog_service.service.SkillCatalogService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class SkillCatalogController {

	private SkillCatalogRepository repository;

	private SkillCatalogService service;

	public SkillCatalogController(SkillCatalogRepository repository, SkillCatalogService service)
	{
		this.repository = repository;
		this.service = service;
	}

	@PostMapping("skills")
	public ResponseEntity<CreateSkillResponse> createSkill(@RequestBody CreateSkillRequest request)
	{
			CreateSkillResponse response = 	service.createSkill(request);
			return ResponseEntity.ok(response);
	}


	@GetMapping("skills/{id}")
	public ResponseEntity<SkillResponse> getSkill(@PathVariable Long id)
	{
		SkillResponse response = service.getSkill(id);
		return ResponseEntity.ok(response);
	}

	@GetMapping("skills")
	public ResponseEntity<List<Skill>> getAllSkills()
	{
		List<Skill> response = service.getAllSkills();

		return ResponseEntity.ok(response);
	}

	@GetMapping("skills/category/{category}")
	public ResponseEntity<List<Skill>> getSkillsByCategory(@PathVariable String category)
	{
		List<Skill > responses = service.getSkillsByCategory(category);
		return ResponseEntity.ok(responses);
	}

	@GetMapping("/skills/name/{name}")
	public ResponseEntity<SkillResponse> getSkillByName(@PathVariable String name)
	{
		SkillResponse response =  service.getSkillByName(name);
		return ResponseEntity.ok(response);
	}


	@GetMapping("/checkExists/{id}")
	public ResponseEntity<Boolean> checkExists(@PathVariable Long id)
	{
		return ResponseEntity.ok(service.checkExists(id));
	}

}
