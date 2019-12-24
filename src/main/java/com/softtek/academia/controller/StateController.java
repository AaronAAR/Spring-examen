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

import com.softtek.academia.entity.State;
import com.softtek.academia.service.StateService;

@Controller
public class StateController {
	@Autowired
	private StateService stateService;
	
	public StateController() {
		
	}
	
	@Autowired
	public StateController(StateService stateService) {
		this.stateService = stateService;
	}
	
//	@RequestMapping(value = { "/state", "/home" }, method = RequestMethod.GET)
//	public ModelAndView hello2(HttpServletResponse response) throws IOException {
//		ModelAndView mv = new ModelAndView();
//		mv.setViewName("home");
//		return mv;
//	}
	
	@RequestMapping(value = "/allStates", method = RequestMethod.POST)
	public ModelAndView displayAllStates() {
		System.out.println("User Page Requested : All States");
		ModelAndView mv = new ModelAndView();
		List<State> stateList = stateService.getAllStates();
		mv.addObject("stateList", stateList);
		mv.setViewName("allStates");
		return mv;
	}
	
	@RequestMapping(value = "/addStates", method = RequestMethod.GET)
	public ModelAndView displayNewStateForm() {
		ModelAndView mv = new ModelAndView("addStates");
		mv.addObject("headerMessage", "Add State Details");
		mv.addObject("state", new State());
		return mv;
	}
	
	@RequestMapping(value = "/addStates", method = RequestMethod.POST)
	public ModelAndView saveNewState (@ModelAttribute State state, BindingResult result) {
		ModelAndView mv = new ModelAndView("redirect:/home");
		
		if (result.hasErrors()) {
			return new ModelAndView("error");
		}
		boolean isAdded = stateService.saveState(state);
		if (isAdded) {
			mv.addObject("message", "New state successfully added");
		} else {
			return new ModelAndView("error");
		}
		return mv;
	}
	
	@RequestMapping(value = "/editStates/{state_id}", method = RequestMethod.GET)
	public ModelAndView displayEditStateForm(@PathVariable Integer state_id) {
		ModelAndView mv = new ModelAndView("/editState");
		State state = stateService.getStateById(state_id);
		mv.addObject("headerMessage", "Edit State Details");
		mv.addObject("state", state);
		return mv;
	}
	
	@RequestMapping(value = "/editState/{state_id}", method = RequestMethod.POST)
	public ModelAndView saveEditedState(@ModelAttribute State state, BindingResult result) {
		ModelAndView mv = new ModelAndView("redirect:/home");
		
		if (result.hasErrors()) {
			System.out.println(result.toString());
			return new ModelAndView("error");
		}
		boolean isSaved = stateService.saveState(state);
		if(!isSaved) {
			return new ModelAndView("error");
		}
		return mv;
	}
	
	@GetMapping("/deleteState/{state_id}")
	public ModelAndView deleteStateById(@PathVariable Integer state_id) {
		boolean isDeleted = stateService.deleteStateById(state_id);
		System.out.println("State deletion response: " + isDeleted);
		ModelAndView mv = new ModelAndView("redirect:/home");
		return mv;
		
	}
	
}
