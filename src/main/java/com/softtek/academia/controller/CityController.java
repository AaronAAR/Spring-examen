package com.softtek.academia.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.softtek.academia.entity.City;
import com.softtek.academia.service.CityService;

@Controller
public class CityController {
	
	private CityService cityService;
	
	public CityController() {
		
	}
	
	@Autowired
	public CityController (CityService cityService) {
		this.cityService = cityService;
	}
	
	@RequestMapping(value = "/allCities", method = RequestMethod.POST)
	public ModelAndView displayAllCities() {
		System.out.println("User Page Requested:  All Cities");
		ModelAndView mv = new ModelAndView();
		List<City> cityList = cityService.getAllCities();
		mv.addObject("cityList", cityList);
		mv.setViewName("allCities");
		return mv;
	}
	
	@RequestMapping(value = "/addCity", method = RequestMethod.GET)
	public ModelAndView displayNewCityForm() {
		ModelAndView mv = new ModelAndView("addCities");
		mv.addObject("headerMessage", "Add City Details");
		mv.addObject("city", new City());
		return mv;
	}
	
	@RequestMapping(value = "/addCity", method = RequestMethod.POST)
	public ModelAndView saveNewState (@ModelAttribute City city, BindingResult result) {
		ModelAndView mv = new ModelAndView("redirect:/home");
		
		if (result.hasErrors()) {
			return new ModelAndView("error");
		}
		boolean isAdded = cityService.saveCity(city);
		if (isAdded) {
			mv.addObject("message", "New city successfully added");
		} else {
			return new ModelAndView("error");
		}
		return mv;
	}
	
	@RequestMapping(value = "/editCity/{city_id}", method = RequestMethod.GET)
	public ModelAndView displayEditStateForm(@PathVariable Integer city_id) {
		ModelAndView mv = new ModelAndView("/editCity");
		City city = cityService.getCityById(city_id);
		mv.addObject("headerMessage", "Edit State Details");
		mv.addObject("city", city);
		return mv;
	}
	
	@RequestMapping(value = "/editCity/{city_id}", method = RequestMethod.POST)
	public ModelAndView saveEditedState(@ModelAttribute City city, BindingResult result) {
		ModelAndView mv = new ModelAndView("redirect:/home");
		
		if (result.hasErrors()) {
			System.out.println(result.toString());
			return new ModelAndView("error");
		}
		boolean isSaved = cityService.saveCity(city);
		if(!isSaved) {
			return new ModelAndView("error");
		}
		return mv;
	}
	
	@GetMapping("/deleteCity/{city_id}")
	public ModelAndView deleteStateById(@PathVariable Integer city_id) {
		boolean isDeleted = cityService.deletedCityById(city_id);
		System.out.println("City deletion response: " + isDeleted);
		ModelAndView mv = new ModelAndView("redirect:/home");
		return mv;
		
	}
}
