package com.apps.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.apps.demo.dao.Alienrepo;
import com.apps.demo.model.Alien;
@RestController 
public class AlienController { 
		
		@Autowired
		Alienrepo repo;
		
		@RequestMapping("/")
		public String home() {
			return "home.jsp";
		}
		
		//Normal Form Data
	/*	
		@RequestMapping("/addAItem")
		public String addAItem(Alien alien) {
			repo.save(alien);
			return "home.jsp";
		}
	
	*/
		
		/*
		@RequestMapping("/getAItem")
		
		public ModelAndView getAItem(@RequestParam int aid) {
			
			ModelAndView mv=new ModelAndView("showalien.jsp");
			Alien alien= repo.findById(aid).orElse(new Alien());
			System.out.println(repo.findByTech("java"));
			System.out.println(repo.findByAidGreaterThan(2));
			System.out.println(repo.findByTechSorted("java"));
			
			mv.addObject(alien);
			return mv;
			}
			*/
		
		//Deleting alien
		@DeleteMapping("/alien/{aid}")
		public String deletedAlien(@PathVariable int aid) {
			Alien a=repo.getOne(aid);//Obtain it using get method
			repo.delete(a);
			return "deleted";
		}
		
		//Update alien
		@PutMapping(path="/alien",consumes= {"application/xml","application/json"})
		public Alien updateAItem(@RequestBody Alien alien) {
			repo.save(alien);
			return alien;
		}
		
		//for Post request sending data in json or xml format
		@PostMapping(path="/alien",consumes= {"application/xml","application/json"})
		public Alien addAItem(@RequestBody Alien alien) {
			repo.save(alien);
			return alien;
		}
		
		@GetMapping(path="/aliens",produces= {"application/xml","application/json"})
		
		public List<Alien> getAliens() {
			return repo.findAll();
		}
		/*
		@RequestMapping("/alien/1")
		@ResponseBody
		public String getAlien() {
			return repo.findById(1).toString();
		}
		*/
		
		//Variable aid or wildcard
		@RequestMapping("/alien/{aid}")
		
		public  Optional<Alien> getAlien(@PathVariable int aid) {
			return repo.findById(aid);
		}
}
