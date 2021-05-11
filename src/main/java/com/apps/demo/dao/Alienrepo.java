package com.apps.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.apps.demo.model.Alien;

import java.util.List;

//changed Crud to Jpa
public interface Alienrepo extends JpaRepository<Alien, Integer> {
	List<Alien> findByTech(String tech);
	List<Alien> findByAidGreaterThan(int aid);
	@Query("from Alien where tech=?1 order by aname")
	List<Alien> findByTechSorted(String tech);

}
