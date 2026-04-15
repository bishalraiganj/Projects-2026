package com.bishaladhikary.skill_catalog_service.repository;

import com.bishaladhikary.skill_catalog_service.entitiy.Skill;
import com.bishaladhikary.skill_catalog_service.entitiy.enums.SkillCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkillCatalogRepository extends JpaRepository<Skill,Long> {

	public List<Skill> findByCategory(SkillCategory category);
	public Skill findByName(String name);
}
